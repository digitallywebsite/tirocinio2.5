package gestionestudente.control;

import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale;
import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendaleModel;
import gestionestudente.model.Studente;
import gestioneutente.model.Tirocinio;
import gestioneutente.model.TirocinioModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * InternalExternal è una classe che permette di far effettuare una 
 * domanda di tirocino interno esterno a uno studente. 
 */
public class InternalExternal extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static String return_path = "/PersonalArea.jsp";

  public InternalExternal() {
    super();
  }
  
  /**
  * Il metodo doGet cerca di inserire una 
  * domanda di tirocinio interno o esterno, inviata dallo studente.
  */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
       throws ServletException, IOException {

    String action = request.getParameter("action");

    TirocinioModel tirocinioModel = null;
    ProfessoreTutorAziendaleModel tutorModel = null;
    
    try {
      if (action != null) {
           
        if (action.equalsIgnoreCase("insert_training_internal")) {
          String email = request.getParameter("email");
          tutorModel = new ProfessoreTutorAziendaleModel();

          ProfessoreTutorAziendale sessioneTeacher = 
                (ProfessoreTutorAziendale) tutorModel.doRetrieveByInternal(email);

          if (sessioneTeacher != null) {
            if (sessioneTeacher.getEmail().length() > 0) {

              Studente sessioneStudent =
                    (Studente) request.getSession().getAttribute("student");
              tirocinioModel = new TirocinioModel();
              Tirocinio bean = new Tirocinio();
              bean.setStato("In Attesa");
              bean.setMatricola_studente(sessioneStudent.getMatricola());
              bean.setTipo("Interno");
              bean.setSegreteria_username("segreteria");
              bean.setTutor_username(sessioneTeacher.getUsername());
              tirocinioModel.doSave(bean);

              request.setAttribute("session_training", "Il tirocinio Interno con il professore \"" 
                     + sessioneTeacher.getEmail() + "\" è stato inserito nei tuoi tirocini, "
                         + "per visualizzarlo clicca su \"I Miei Tirocini\"");
            } else {
              request.setAttribute("session_training_fault", "Il professore "
                     + "con email \"" + email + "\" non è stato trovato!");
            } 
          } else {
            request.setAttribute("session_training_fault", "Il professore "
                + "con email \"" + email + "\" non è stato trovato!");
          }
        } else if (action.equalsIgnoreCase("insert_training_external")) {
          String email = request.getParameter("email");
          
          tutorModel = new ProfessoreTutorAziendaleModel();
          
          ProfessoreTutorAziendale sessioneTutor = 
                 (ProfessoreTutorAziendale) tutorModel.doRetrieveByExternal(email);

          if (sessioneTutor != null) {
            if (sessioneTutor.getEmail().length() > 0) {
              Studente sessioneStudent = (Studente) request.getSession().getAttribute("student");
              tirocinioModel = new TirocinioModel();

              Tirocinio bean = new Tirocinio();
              bean.setStato("In Attesa");
              bean.setMatricola_studente(sessioneStudent.getMatricola());
              bean.setTutor_username(sessioneStudent.getUsername());
              bean.setTipo("Esterno");
              bean.setSegreteria_username("segreteria");
              bean.setTutor_username(sessioneTutor.getUsername());
              tirocinioModel.doSave(bean);

              request.setAttribute("session_training", "Il tirocinio Esterno con "
                  + "il Turor Aziendale \"" + sessioneStudent.getEmail() + "\" è stato "
                  + "inserito nei tuoi tirocini, per visualizzarlo clicca su \"I Miei Tirocini\"");
            } else {
              request.setAttribute("session_training_fault", "Il Turor "
                  + "Aziendale con email \"" + email + "\" non è stato trovato!");
            }
          } else {
            request.setAttribute("session_training_fault", 
                "Il Turor Aziendale con email \"" + email + "\" non è stato trovato!");
          }
        }
      }
    } catch (SQLException e) {
      System.out.println("Error:" + e.getMessage());
    }
    
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(return_path);
    dispatcher.forward(request, response);
  }

  /**
  * Il metodo riceve una richiesta dal client e richiama 
  * il medoto doGet(request, response) per svolgere la procedura aspettata.
  */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    doGet(request, response);
  }
  
  /**
   * Il metodo confronta la username o la matricola passata con una espressione 
   * regolare, per verificare se la variabile passata è una username o matricola valida.
   * @param userMatr tipo String, Variabile che viene cofrontata 
   *     con le espressioni regolari per verificare se è una username o matricola valida
   * @return true/false valore boolean che se è false allora 
   *     il parametro passato non è una username o matricola valida, true altrimenti.
  */
  public boolean validateUsername_Matricola(String userMatr) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9]+$");
    Matcher matcher = pattern.matcher(userMatr);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Il metodo confronta l'email passata con una espressione regolare, 
   * per verificare se la variabile passata è una email valida per professore.
   * @param email tipo Boolean, Variabile che viene cofrontata con 
   *     le espressioni regolari per verificare se è una email valida per professore
   * @return true/false valore boolean che se è false allora 
   *     il parametro passato non è una email valida, true altrimenti.
   */
  public boolean isTeacher(String email) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9._%-]+@[unisa]+\\.[a-zA-Z]{2,4}");
    Matcher matcher = pattern.matcher(email);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
}