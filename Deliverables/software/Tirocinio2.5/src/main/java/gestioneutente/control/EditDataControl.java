package gestioneutente.control;

import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale;
import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendaleModel;
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
 * EditDataControl è una classe con 6 metodi, in generale permette 
 * di modificare i dati personali di studente, professore, tutor aziendale. 
 *
 */
public class EditDataControl extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static StudenteModel model;

  static {

    model = new StudenteModel();

  }

  static ProfessoreTutorAziendaleModel Tutormodel;

  static {

    Tutormodel = new ProfessoreTutorAziendaleModel();

  }
  
  String returnPath = "/EditData.jsp";

  public EditDataControl() {
    super();
  }

  /**
  * Il metodo doGet cerca di modificare i dati personali di studente, professore, tutor aziendale. 
  */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String action = request.getParameter("action");

    try {
      if (action != null) {
        if (action.equalsIgnoreCase("editData")) {

          returnPath = "/EditData.jsp";

          String firstName = request.getParameter("first_name");
          int n = firstName.length();
          firstName = upper(firstName,n);

          String lastName = request.getParameter("last_name");
          n = lastName.length();
          lastName = upper(lastName,n);

          String matricola = request.getParameter("matricola");
          
          String username = request.getParameter("username");

          String psw = request.getParameter("psw");

          psw = Md5.hashCode(psw, "MD5");

          HttpSession session = request.getSession();

          Studente sessioneStudent = (Studente) request.getSession().getAttribute("student");
          ProfessoreTutorAziendale sessioneTeacher = 
              (ProfessoreTutorAziendale) request.getSession().getAttribute("teacher");
          ProfessoreTutorAziendale sessioneTutor = 
              (ProfessoreTutorAziendale) request.getSession().getAttribute("tutor");

          int controlStudent = 0;
          int controlTeacher = 0;
          int controlTutor = 0;
          
          String email = request.getParameter("email");
          
          boolean control = true;
          
          if (!validateEmail(email)) {
            session.setAttribute("email_not_valid", "Email "
                  + "inserita \"" + email + "\" NON è valida!");
            control = false;
          }
            
          if (!validateUsername(username)) {
            session.setAttribute("username_not_valid", "Username "
                  + "inserita \"" + username + "\" NON è valida!");
            control = false;
          }
          
          if (!validateNomeCognome(firstName)) {
            session.setAttribute("firstname_not_valid", "Nome "
                    + "inserito \"" + firstName + "\" NON è valido!");
            control = false;
          }
          
          if (!validateNomeCognome(lastName)) {
            session.setAttribute("lastname_not_valid", "Cognome "
                    + "inserito \"" + lastName + "\" NON è valido!");
            control = false;
          }
          
          if (control) {
            if (isStudent(email)) {
              if (sessioneStudent != null) {
                if (sessioneStudent.getMatricola().length() > 0) {
                  controlStudent = 1;
                }
              } else {
                if (sessioneTeacher != null) {
                  if (sessioneTeacher.getUsername().length() > 0) {
                    session.setAttribute("email_not_ok", "Se vuoi modificare l'email, "
                        + "quest'ultima deve essere di tipo Professore.");
                  }
                } else {
                  session.setAttribute("email_not_ok", "Se vuoi modificare l'email, "
                        + "quest'ultima deve essere di tipo Tutor Aziendale.");
                }
              }
            } else if (isTeacher(email)) {
              if (sessioneTeacher != null) {
                if (sessioneTeacher.getUsername().length() > 0) {
                  controlTeacher = 1;
                }
              } else {
                if (sessioneStudent != null) {
                  if (sessioneStudent.getMatricola().length() > 0) {
                    session.setAttribute("email_not_ok", "Se vuoi modificare l'email, "
                        + "quest'ultima deve essere di tipo Studente.");
                  }
                } else {
                  session.setAttribute("email_not_ok", "Se vuoi modificare l'email, "
                      + "quest'ultima deve essere di tipo Tutor Aziendale.");
                }
              }
            } else {
              if (sessioneTutor != null) {
                if (sessioneTutor.getUsername().length() > 0) {
                  controlTutor = 1;
                }
              } else {
                if (sessioneStudent != null) {
                  if (sessioneStudent.getMatricola().length() > 0) {
                    session.setAttribute("email_not_ok", "Se vuoi modificare l'email, "
                        + "quest'ultima deve essere di tipo Studente.");
                  }
                } else {
                  session.setAttribute("email_not_ok", "Se vuoi modificare l'email, "
                      + "quest'ultima deve essere di tipo Professore.");
                }
              }
            }

            if (controlStudent == 1) {
              if (matricola == "") {
                session.setAttribute("vuota", "Sei uno studente, la matricola è necessaria!");
              } else {
                if (sessioneStudent.getMatricola().length() > 0) {
                  if (sessioneStudent.getMatricola().equalsIgnoreCase(matricola)) {
                    Studente bean = new Studente();
                    bean.setMatricola(matricola);
                    bean.setNome(firstName);
                    bean.setCognome(lastName);
                    bean.setEmail(email);
                    bean.setUsername(username);
                    bean.setPsw(psw);
                    model.doModify(bean);

                    request.getSession().setAttribute("student"
                        + "", model.doRetrieveByKey(sessioneStudent.getMatricola()));

                    session.setAttribute("editdata_completed", email);
                    session.setAttribute("editdata_completed_as_student_tutor_teacher"
                        + "", "uno Studente");
                  } else {
                    session.setAttribute("not_equals", "La matricola inserita non "
                        + "risulta essere uguale a \"" + sessioneStudent.getMatricola() + "\"");
                  }
                }
              }
            } else {
              if (controlTeacher == 1) {
                if (username == "") {
                  session.setAttribute("vuota", "Sei un professore, la username è necessaria!");
                } else if (sessioneTeacher.getUsername().length() > 0) {
                  if (sessioneTeacher.getUsername().equalsIgnoreCase(username)) {
                    ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();
                    bean.setTipo("Professore");
                    bean.setNome(firstName);
                    bean.setCognome(lastName);
                    bean.setEmail(email);
                    bean.setUsername(username);
                    bean.setPsw(psw);
                    Tutormodel.doModify(bean);

                    request.getSession().setAttribute("teacher"
                          + "", Tutormodel.doRetrieveByKey(sessioneTeacher.getUsername()));

                    session.setAttribute("editdata_completed", email);
                    session.setAttribute("editdata_completed_as_student_tutor_teacher"
                        + "", "un Professore");
                  } else {
                    session.setAttribute("not_equals", "La username inserita "
                        + "non risulta essere uguale "
                          + "a \"" + sessioneTeacher.getUsername() + "\"");
                  }
                }
              } else if (controlTutor == 1) {
                if (username == "") {
                  session.setAttribute("vuota", "Sei un Tutor Aziendale, "
                      + "la username è necessaria!");
                } else if (sessioneTutor.getUsername().length() > 0) {
                  if (sessioneTutor.getUsername().equalsIgnoreCase(username)) {
                    ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();
                    bean.setTipo("TutorAziendale");
                    bean.setNome(firstName);
                    bean.setCognome(lastName);
                    bean.setEmail(email);
                    bean.setUsername(username);
                    bean.setPsw(psw);
                    Tutormodel.doModify(bean);

                    request.getSession().setAttribute("tutor"
                          + "", Tutormodel.doRetrieveByKey(sessioneTutor.getUsername()));

                    session.setAttribute("editdata_completed", email);
                    session.setAttribute("editdata_completed_as_student_tutor_teacher"
                          + "", "un Tutor Aziendale");
                  } else {
                    session.setAttribute("not_equals", "La username inserita "
                        + "non risulta essere uguale a \"" + sessioneTutor.getUsername() + "\"");
                  }
                }
              }
            }
          }
        }
      }
    } catch (SQLException e) {
      System.out.println("Error:" + e.getMessage());

      String email = request.getParameter("email");
      HttpSession session = request.getSession();

      session.setAttribute("editdata_fault", email);
    }

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(returnPath);
    dispatcher.forward(request, response);
  }

  /**
  * Il metodo riceve una richiesta dal client e richiama il medoto 
  * doGet(request, response) per svolgere la procedura aspettata.
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
   * Il metodo confronta la username passata con una espressione 
   * regolare, per verificare se la variabile passata è una username valida.
   * @param username tipo String, Variabile che viene cofrontata 
   *     con le espressioni regolari per verificare se è una username valida
   * @return true/false valore boolean che se è false allora 
   *     il parametro passato non è una username valida, true altrimenti.
  */
  public boolean validateUsername(String username) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9]+$");
    Matcher matcher = pattern.matcher(username);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Il metodo confronta il nome/cognome passato con una espressione 
   * regolare, per verificare se la variabile passata è un nome/congnome valido.
   * @param nome tipo String, Variabile che viene cofrontata 
   *     con le espressioni regolari per verificare se è una nome/congnome valido
   * @return true/false valore boolean che se è false allora 
   *     il parametro passato non è nome/congnome valido, true altrimenti.
  */
  public boolean validateNomeCognome(String nome) {
    Pattern pattern = Pattern.compile("[a-zA-Z ']+$");
    Matcher matcher = pattern.matcher(nome);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
  * Il metodo confronta l'email passata con una espressione regolare, 
  * per verificare se la variabile passata è una email valida per studente.
  * @param email tipo Boolean, Variabile che viene cofrontata con 
  *     le espressioni regolari per verificare se è una email valida per studente
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
  * @param email tipo Boolean, Variabile che viene cofrontata 
  *     con le espressioni regolari per verificare se è una email valida per professore
  * @return true/false valore boolean che se è false 
  *     allora il parametro passato non è una email valida, true altrimenti.
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

  /**
  * Il metodo prende in input il first_name e n e rende la prima lettera del nome Maiuscola.
  * @param firstName tipo String, variabile che in input ha un possibile nome di una cartella
  * @param n tipo int, variabile che contiene la lunghezza del first_name
  * @return upper tipo String, variabile che 
  *     restituisce il first_name con la prima lettera in maiuscola
  */
  public String upper(String firstName,int n) {
    int upperFirstLetter = 1;
    String upper = null;
    for (int i = 0; i < n; i++) {
      String comparison = firstName.substring(i, i + 1);

      if ((comparison.replace(" ", "").length()) == 0) {
        if (upper != null) {
          upper = upper + " ";
        }

        upperFirstLetter = 1;
      } else {
        if (upperFirstLetter == 1) {
          if (upper == null) {
            upper = firstName.substring(i, i + 1).toUpperCase();
          } else {
            upper = upper + firstName.substring(i, i + 1).toUpperCase();
          }
        } else {
          upper = upper + firstName.substring(i, i + 1);
        }

        upperFirstLetter = 0;
      }
    }

    return upper;
  }
}