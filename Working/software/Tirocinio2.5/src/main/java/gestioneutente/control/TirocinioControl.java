package gestioneutente.control;

import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale;
import gestionesegreteria.model.Segreteria;
import gestionestudente.model.Studente;
import gestioneutente.model.Tirocinio;
import gestioneutente.model.TirocinioModel;

import java.io.IOException;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TirocinioControl è una classe con 2 metodi e permeti di 
 * cancellare/convalidare/confermare/rifiutare/accettare una domanda di tirocinio. 
 * 
 */
public class TirocinioControl extends HttpServlet {
  private static final long serialVersionUID = 1L;


  String returnPath = "/index.jsp";

  public TirocinioControl() {
    super();
  }

  /**
  * Il metodo doGet cerca di cancellare/convalidare/confermare/rifiutare/accettare
  * una domanda di tirocinio. 
  */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String action = request.getParameter("action");

    TirocinioModel tirocinioModel = null;

    try {
      if (action != null) {

        if (action.equalsIgnoreCase("delete_training_student")) {
          Studente sessioneStudent = (Studente) request.getSession().getAttribute("student");

          if (sessioneStudent != null) {
            if (sessioneStudent.getEmail().length() > 0) {
              String id = request.getParameter("id");
              int idInt = Integer.parseInt(id);

              if (id != null) {
                tirocinioModel = new TirocinioModel();

                Tirocinio tirocinio = (Tirocinio) tirocinioModel.doRetrieveByKey(idInt);

                if (tirocinio.getStato().equalsIgnoreCase("In Attesa")) {
                  tirocinioModel.doDelete(idInt);
                  request.setAttribute("message_success_training", ""
                      + "Richiesta di Tirocinio Cancellata.");
                } else {
                  request.setAttribute("message_fault_training", "Per cancellare "
                      + "la richiesta di Tirocinio lo stato deve essere In Attesa.");
                }

                returnPath = "/MyTraining.jsp";
              }
            }
          }
        } else if (action.equalsIgnoreCase("confirm_student_training_student")) {
          Studente sessioneStudent = (Studente) request.getSession().getAttribute("student");

          if (sessioneStudent != null) {
            if (sessioneStudent.getEmail().length() > 0) {
              String id = request.getParameter("id");
              int idInt = Integer.parseInt(id);

              if (id != null) {
                tirocinioModel = new TirocinioModel();

                Tirocinio tirocinio = (Tirocinio) tirocinioModel.doRetrieveByKey(idInt);

                if (tirocinio.getStato().equalsIgnoreCase("Accettata")) {
                  tirocinioModel.doModify("Confermata",idInt);
                  request.setAttribute("message_success_training", "Richiesta"
                      + " di Tirocinio è stata Confermata.");
                } else {
                  request.setAttribute("message_fault_training", "Per confermare "
                      + "la richiesta di Tirocinio lo stato deve essere in Accettata.");
                }

                returnPath = "/MyTraining.jsp";
              }
            }
          }
        } else if (action.equalsIgnoreCase("reject_student_training_student")) {
          Studente sessioneStudent = (Studente) request.getSession().getAttribute("student");

          if (sessioneStudent != null) {
            if (sessioneStudent.getEmail().length() > 0) {
              String id = request.getParameter("id");
              int idInt = Integer.parseInt(id);

              if (id != null) {
                tirocinioModel = new TirocinioModel();

                Tirocinio tirocinio = (Tirocinio) tirocinioModel.doRetrieveByKey(idInt);

                if (tirocinio.getStato().equalsIgnoreCase("Accettata")) {
                  tirocinioModel.doModify("Rifiutata dallo Studente",idInt);
                  request.setAttribute("message_success_training", "Richiesta "
                      + "di Tirocinio è stata Rifiutata.");
                } else {
                  request.setAttribute("message_fault_training", "Per rifiutare "
                      + "la richiesta di Tirocinio lo stato deve essere in Accettata.");
                }

                returnPath = "/MyTraining.jsp";
              }
            }
          }
        } else if (action.equalsIgnoreCase("accept_training_student")) {
          ProfessoreTutorAziendale sessioneTeacher = (ProfessoreTutorAziendale) 
              request.getSession().getAttribute("teacher");
          ProfessoreTutorAziendale sessioneTutor = (ProfessoreTutorAziendale) 
              request.getSession().getAttribute("tutor");

          boolean control = false;

          if (sessioneTeacher != null) {
            if (sessioneTeacher.getEmail().length() > 0) {
              control = true;
            }
          }

          if (sessioneTutor != null) {
            if (sessioneTutor.getEmail().length() > 0) {
              control = true;
            }
          }

          if (control) {
            String id = request.getParameter("id");
            int idInt = Integer.parseInt(id);

            if (id != null) {
              tirocinioModel = new TirocinioModel();

              Tirocinio tirocinio = (Tirocinio) tirocinioModel.doRetrieveByKey(idInt);

              if (tirocinio.getStato().equalsIgnoreCase("In Attesa")) {
                tirocinioModel.doModify("Accettata",idInt);
                request.setAttribute("message_success_training", "Richiesta "
                    + "di Tirocinio Accettata.");
              } else {
                request.setAttribute("message_fault_training", "Per accettare"
                    + " la richiesta di Tirocinio lo stato deve essere In Attesa.");
              }

              returnPath = "/RequestTraining.jsp";
            }
          }
        } else if (action.equalsIgnoreCase("reject_training_student")) {
          ProfessoreTutorAziendale sessioneTeacher = 
              (ProfessoreTutorAziendale) request.getSession().getAttribute("teacher");
          ProfessoreTutorAziendale sessioneTutor = 
              (ProfessoreTutorAziendale) request.getSession().getAttribute("tutor");

          boolean control = false;

          if (sessioneTeacher != null) {
            if (sessioneTeacher.getEmail().length() > 0) {
              control = true;
            }
          }

          if (sessioneTutor != null) {
            if (sessioneTutor.getEmail().length() > 0) {
              control = true;
            }
          }

          if (control) {
            String id = request.getParameter("id");
            int idInt = Integer.parseInt(id);

            if (id != null) {
              tirocinioModel = new TirocinioModel();

              Tirocinio tirocinio = (Tirocinio) tirocinioModel.doRetrieveByKey(idInt);
             
              if (tirocinio.getStato().equalsIgnoreCase("In Attesa")) {
                tirocinioModel.doModify("Rifiutata",idInt);
                request.setAttribute("message_success_training", "Richiesta"
                    + " di Tirocinio Rifiutata.");
              } else {
                request.setAttribute("message_fault_training", "Per "
                    + "rifiutare la richiesta di Tirocinio lo stato deve essere In Attesa.");
              }

              returnPath = "/RequestTraining.jsp";
            }
          }
        } else if (action.equalsIgnoreCase("convalidate_training_student")) {
          Segreteria sessioneSegreteria =
                (Segreteria) request.getSession().getAttribute("segreteria");

          boolean control = false;

          if (sessioneSegreteria != null) {
            if (sessioneSegreteria.getEmail().length() > 0) {
              control = true;
            }
          }

          if (control) {
            String id = request.getParameter("id");
            int idInt = Integer.parseInt(id);

            if (id != null) {
              tirocinioModel = new TirocinioModel();

              Tirocinio tirocinio = (Tirocinio) tirocinioModel.doRetrieveByKey(idInt);

              if (tirocinio.getStato().equalsIgnoreCase("Completato")) {
                tirocinioModel.doModify("Convalidato",idInt);
                request.setAttribute("message_success_training", "Tirocinio Convalidato.");
              } else {
                request.setAttribute("message_fault_training", "Per convalidare "
                    + "il Tirocinio lo stato deve essere in Completato.");
              }

              returnPath = "/segreteria/CompletedTraining.jsp";
            }
          }
        } else if (action.equalsIgnoreCase("convalidate_old_training_student")) {
          Segreteria sessioneSegreteria = 
                (Segreteria) request.getSession().getAttribute("segreteria");

          boolean control = false;

          if (sessioneSegreteria != null) {
            if (sessioneSegreteria.getEmail().length() > 0) {
              control = true;
            }
          }

          if (control) {
            String id = request.getParameter("id");
            int idInt = Integer.parseInt(id);

            if (id != null) {
              tirocinioModel = new TirocinioModel();

              Tirocinio tirocinio = (Tirocinio) tirocinioModel.doRetrieveOldByKey(idInt);

              if (tirocinio.getStato().equalsIgnoreCase("In Attesa")) {
                tirocinioModel.doModifyOld("Convalidato", idInt);
                request.setAttribute("message_success_training", "Tirocinio Convalidato.");
              } else {
                request.setAttribute("message_fault_training",
                    "Per convalidare " + "il Tirocinio lo stato deve essere in \"In Attesa\".");
              }

              returnPath = "/segreteria/OldTraining.jsp";
            }
          }
        } else if (action.equalsIgnoreCase("reject_old_training_student")) {
          Segreteria sessioneSegreteria = 
                    (Segreteria) request.getSession().getAttribute("segreteria");

          boolean control = false;

          if (sessioneSegreteria != null) {
            if (sessioneSegreteria.getEmail().length() > 0) {
              control = true;
            }
          }

          if (control) {
            String id = request.getParameter("id");
            int idInt = Integer.parseInt(id);

            if (id != null) {
              tirocinioModel = new TirocinioModel();

              Tirocinio tirocinio = (Tirocinio) tirocinioModel.doRetrieveOldByKey(idInt);

              if (tirocinio.getStato().equalsIgnoreCase("In Attesa")) {
                tirocinioModel.doModifyOld("Rifiutato", idInt);
                request.setAttribute("message_success_training", "Tirocinio Rifiutato.");
              } else {
                request.setAttribute("message_fault_training",
                     "Per rifiutare " + "il Tirocinio lo stato deve essere in \"In Attesa\".");
              }

              returnPath = "/segreteria/OldTraining.jsp";
            }
          }
        } else if (action.equalsIgnoreCase("convalidate_old_training_student")) {
          Segreteria sessioneSegreteria = 
                    (Segreteria) request.getSession().getAttribute("segreteria");

          boolean control = false;

          if (sessioneSegreteria != null) {
            if (sessioneSegreteria.getEmail().length() > 0) {
              control = true;
            }
          }

          if (control) {
            String id = request.getParameter("id");
            int idInt = Integer.parseInt(id);

            if (id != null) {
              tirocinioModel = new TirocinioModel();

              Tirocinio tirocinio = (Tirocinio) tirocinioModel.doRetrieveOldByKey(idInt);

              if (tirocinio.getStato().equalsIgnoreCase("In Attesa")) {
                tirocinioModel.doModifyOld("Convalidato", idInt);
                request.setAttribute("message_success_training", "Tirocinio Convalidato.");
              } else {
                request.setAttribute("message_fault_training",
                     "Per convalidare " + "il Tirocinio lo stato deve essere in \"In Attesa\".");
              }

              returnPath = "/segreteria/OldTraining.jsp";
            }
          }
        }
      }
    } catch (SQLException e) {
      System.out.println("Error:" + e.getMessage());
    }

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(returnPath);
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
}