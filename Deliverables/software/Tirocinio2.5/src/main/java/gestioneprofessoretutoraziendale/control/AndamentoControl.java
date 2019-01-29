package gestioneprofessoretutoraziendale.control;

import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale;
import gestioneutente.model.Andamento;
import gestioneutente.model.AndamentoModel;
 
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
 * AndamentoControl è una classe che si occupa di
 * modificare/inserire la data e l'ora di un lavoro di uno studente.
 *
 */
public class AndamentoControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  static ProfessoreTutorAziendale sessione_teacher;
  static ProfessoreTutorAziendale sessione_tutor;
  
  static String return_path = "/index.jsp";

  public AndamentoControl() {
    super();
  }

  /**
  * Il metodo doGet cerca di modificare/inserire la data e l'ora di un lavoro di uno studente.
  */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String action = request.getParameter("action");

    AndamentoModel andamentoModel = null;
    
    try {
      if (action != null) {

        if (action.equalsIgnoreCase("insert_time_work")) {
          sessione_teacher = (ProfessoreTutorAziendale)
              request.getSession().getAttribute("teacher");
          sessione_tutor = (ProfessoreTutorAziendale)
                 request.getSession().getAttribute("tutor");

          boolean control = false;

          if (sessione_teacher != null) {
            if (sessione_teacher.getEmail().length() > 0) {
              control = true;
            }
          }

          if (sessione_tutor != null) {
            if (sessione_tutor.getEmail().length() > 0) {
              control = true;
            }
          }
          
          if (control) {
            String id = request.getParameter("id");
            int idInt = Integer.parseInt(id);

            String data = request.getParameter("data");
            String oraInizio = request.getParameter("ora_inizio");
            String oraFine = request.getParameter("ora_fine");

            if (id != null) {
            
              boolean controllo = true;

              if (!validateOreSvolte(oraInizio)) {
                request.setAttribute("oraInizio_not_valid", ""
                          + "Il campo ora inizio non è valido, deve essere di tipo \"15:00\".");
                controllo = false;
              }
                
              if (!validateOreSvolte(oraFine)) {
                request.setAttribute("oraFine_not_valid", ""
                            + "Il campo ora fine non è valido, deve essere di tipo \"15:00\".");
                controllo = false;
              }
              
              if (!validateData(data)) {
                request.setAttribute("Data_not_valid", ""
                              + "Il campo data non è valido, deve essere di tipo \"aaaa-mm-gg\".");
                controllo = false;
              }
              
              if (controllo) {
                andamentoModel = new AndamentoModel();

                Andamento bean = new Andamento();
                bean.setDataT(data);
                bean.setOra_inizio(oraInizio);
                bean.setOra_fine(oraFine);
                bean.setTirocinioId(idInt);
                andamentoModel.doSave(bean);

                request.setAttribute("message_success_training", "Ore di lavoro Aggiunte.");
              }
              return_path = "/TrendTraining.jsp";
            }
          }
        } else if (action.equalsIgnoreCase("modify_time_work")) {
          sessione_teacher = (ProfessoreTutorAziendale)
              request.getSession().getAttribute("teacher");
          sessione_tutor = (ProfessoreTutorAziendale) 
              request.getSession().getAttribute("tutor");

          boolean control = false;

          if (sessione_teacher != null) {
            if (sessione_teacher.getEmail().length() > 0) {
              control = true;
            }
          }

          if (sessione_tutor != null) {
            if (sessione_tutor.getEmail().length() > 0) {
              control = true;
            }
          }

          if (control) {
            String id = request.getParameter("id");
            int idInt = Integer.parseInt(id);
            String data = request.getParameter("data");
            String oraInizio = request.getParameter("ora_inizio");
            String oraFine = request.getParameter("ora_fine");
            
            if (id != null) {
              
              boolean controllo = true;

              if (!validateOreSvolte(oraInizio)) {
                request.setAttribute("oraInizio_not_valid", ""
                        + "Il campo ora inizio non è valido, deve essere di tipo \"15:00\".");
                controllo = false;
              }
              
              if (!validateOreSvolte(oraFine)) {
                request.setAttribute("oraFine_not_valid", ""
                          + "Il campo ora fine non è valido, deve essere di tipo \"15:00\".");
                controllo = false;
              }
              
              if (!validateData(data)) {
                request.setAttribute("Data_not_valid", ""
                            + "Il campo data non è valido, deve essere di tipo \"aaaa-mm-gg\".");
                controllo = false;
              }
              
              if (controllo) {
                andamentoModel = new AndamentoModel();
                Andamento bean = new Andamento();
                bean.setId(idInt);
                bean.setDataT(data);
                bean.setOra_inizio(oraInizio);
                bean.setOra_fine(oraFine);
                bean.setTirocinioId(idInt);
                andamentoModel.doModify(bean);

                request.setAttribute("message_success_training", "Ore di lavoro Modificate.");
              }
              return_path = "/ModifyTimeTrend.jsp";
            }
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
  * Il metodo riceve una richiesta dal client e 
  * richiama il medoto doGet(request, response) per svolgere la procedura aspettata.
  */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
  
  /**
   * Il metodo confronta le ore svolte con una espressione 
   * regolare, per verificare se la variabile passata è un formato giusto.
   * @param oresvolte tipo String, Variabile che viene cofrontata 
   *     con le espressioni regolari per verificare se  è un formato giusto
   * @return true/false valore boolean che se è false allora 
   *     il parametro passato non è  è un formato giusto, true altrimenti.
  */
  public boolean validateOreSvolte(String oresvolte) {
    Pattern pattern = Pattern.compile("([0-1]\\d|2[0-3]):([0-5]\\d)+$");
    Matcher matcher = pattern.matcher(oresvolte);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Il metodo confronta la data inserita con una espressione 
   * regolare, per verificare se la variabile passata è un formato giusto.
   * @param data tipo String, Variabile che viene cofrontata 
   *     con le espressioni regolari per verificare se  è un formato giusto
   * @return true/false valore boolean che se è false allora 
   *     il parametro passato non è  è un formato giusto, true altrimenti.
  */
  public boolean validateData(String data) {
    Pattern pattern = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
    Matcher matcher = pattern.matcher(data);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
}