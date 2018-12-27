package gestioneutente.control;

import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale;
import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendaleModel;
import gestionesegreteria.model.Segreteria;
import gestionesegreteria.model.SegreteriaModel;
import gestionestudente.model.Studente;
import gestionestudente.model.StudenteModel;
import gestioneutente.model.Md5;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * LoginControl è una classe con 5 metodi, che permette di effettuare 
 * il login allo studente, professore, tutor aziendale, segreteria. 
 *
 */
public class LoginControl extends HttpServlet {
  private static final long serialVersionUID = 1L;


  String returnPath = "/index.jsp";

  public LoginControl() {
    super();
  }

  /**
  * Il metodo doGet permette di effettuare il login 
  * allo studente, professore, tutor aziendale, segreteria. 
  */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String action = request.getParameter("action");

    StudenteModel model = null;
    ProfessoreTutorAziendaleModel tutorModel = null;
    SegreteriaModel segreteriaModel = null;

    try {
      if (action != null) {

        if (action.equalsIgnoreCase("login")) {
          String psw = request.getParameter("psw");

          psw = Md5.hashCode(psw, "MD5");

          request.removeAttribute("student");
          request.removeAttribute("teacher");
          request.removeAttribute("tutor");

          String email = request.getParameter("email");
          
          if (validateEmail(email)) {
            if (email.equalsIgnoreCase("segreteria@segreteria.unisa.it")) {
              segreteriaModel = new SegreteriaModel();
              request.getSession().setAttribute("segreteria"
                  + "", segreteriaModel.loginSegreteria(email,psw));
            } else {
              if (isStudent(email)) {
                model = new StudenteModel();
                request.getSession().setAttribute("student", model.loginStudente(email,psw));
              } else {

                if (isTeacher(email)) {
                  tutorModel = new ProfessoreTutorAziendaleModel();
                  request.getSession().setAttribute("teacher"
                      + "", tutorModel.loginProfessoreTutor(email,psw));
                } else {
                  tutorModel = new ProfessoreTutorAziendaleModel();
                  request.getSession().setAttribute("tutor"
                      + "", tutorModel.loginProfessoreTutor(email,psw));
                }
              }
            }
          } else {
            HttpSession sessionLogin = request.getSession();

            segreteriaModel = new SegreteriaModel();
            request.getSession().setAttribute("segreteria"
                + "", segreteriaModel.loginSegreteria(email,psw));

            Segreteria sessioneSegreteria = 
                (Segreteria) request.getSession().getAttribute("segreteria");

            if (sessioneSegreteria != null) {
              if (sessioneSegreteria.getEmail().length() <= 0) {
                sessionLogin.removeAttribute("segreteria");

                model = new StudenteModel();
                request.getSession().setAttribute("student", model.loginStudente(email,psw));

                Studente sessioneStudent = (Studente) request.getSession().getAttribute("student");

                if (sessioneStudent != null) {
                  if (sessioneStudent.getEmail().length() <= 0) {
                    sessionLogin.removeAttribute("student");
                    
                    tutorModel = new ProfessoreTutorAziendaleModel();
                    request.getSession().setAttribute("teacher"
                        + "", tutorModel.loginProfessore(email,psw));

                    ProfessoreTutorAziendale sessioneTeacher =
                        (ProfessoreTutorAziendale) request.getSession().getAttribute("teacher");

                    if (sessioneTeacher != null) {
                      if (sessioneTeacher.getEmail().length() <= 0) {
                        sessionLogin.removeAttribute("teacher");
                        tutorModel = new ProfessoreTutorAziendaleModel();
                        request.getSession().setAttribute("tutor"
                            + "", tutorModel.loginProfessoreTutor(email,psw));

                        ProfessoreTutorAziendale sessioneTutor =
                            (ProfessoreTutorAziendale) request.getSession().getAttribute("tutor");

                        if (sessioneTutor != null) {
                          if (sessioneTutor.getEmail().length() <= 0) {
                            sessionLogin.removeAttribute("tutor");
                            request.setAttribute("login_not_valid", 
                                 "Login Errato. Email/Username o password errati.");
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }

          returnPath = "/Login.jsp";
        }
      }
    } catch (SQLException e) {
      System.out.println("Error:" + e.getMessage());
    }

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(returnPath);
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
  * Il metodo confronta l'email passata con una espressione regolare, 
  * per verificare se la variabile passata è una email valida.
  * @param email tipo Boolean, Variabile che viene cofrontata 
  *     con le espressioni regolari per verificare se è una email valida
  * @return true/false valore boolean che se è false allora 
  *     il parametro passato non è una email valida, true altrimenti.
  */
  public boolean validateEmail(String email) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
    Matcher matcher = pattern.matcher(email);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }

  /**
  * Il metodo confronta l'email passata con una espressione regolare, 
  * per verificare se la variabile passata è una email valida per studente.
  * @param email tipo Boolean, Variabile che viene cofrontata 
  *     con le espressioni regolari per verificare se è una email valida per studente
  * @return true/false valore boolean che se è false allora 
  *     il parametro passato non è una email valida, true altrimenti.
  */
  public boolean isStudent(String email) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9._%-]+@[studenti]+\\.[unisa]+\\.[a-zA-Z]{2,4}");
    Matcher matcher = pattern.matcher(email);

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