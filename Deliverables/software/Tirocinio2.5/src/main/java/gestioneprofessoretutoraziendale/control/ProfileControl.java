package gestioneprofessoretutoraziendale.control;

import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale;
import gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendaleModel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * La Classe ProfileControl è una classe contenente 4 metodi,
 *  essa svolge come compito principale di inserire/modificare 
 *  un profilo personale di Professore/Tutor Aziendale.
 */
public class ProfileControl extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  static ProfessoreTutorAziendaleModel Tutormodel;

  static {

    Tutormodel = new ProfessoreTutorAziendaleModel();

  }
  
  static String return_path = "/CreateProfile.jsp";

  public ProfileControl() {
    super();
  }
  
  /**
  * Il metodo doPost, tenterà di inserire/modificare un profilo 
  * personale di Professore/Tutor Aziendale. 
  */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String action = null;
    if (ServletFileUpload.isMultipartContent(request)) {
      try {
        List<FileItem> multiparts = new 
            ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

        String nameFolder = null;
        String dir = null;
        String imageProfile = null;
        String company = null;
        String indirizzo = null;
        String telefono = null;
        String fax = null;
        String email = null;
        String luogo = null;
        String sitoweb = null;
        String chisono = null;

        int i = 0;

        ProfessoreTutorAziendale sessioneTeacher = (ProfessoreTutorAziendale)
            request.getSession().getAttribute("teacher");
        ProfessoreTutorAziendale sessioneTutor = (ProfessoreTutorAziendale) 
            request.getSession().getAttribute("tutor");

        for (FileItem item : multiparts) {
          if (!item.isFormField()) {
            String name = new File(item.getName()).getName();

            if (sessioneTeacher != null && i == 0) {
              if (sessioneTeacher.getEmail().length() > 0) {
                sessioneTeacher.setUsername(sessioneTeacher.getUsername()
                    .replaceAll("^\\s+", "")); //toglie lo spazio all'inizio
                sessioneTeacher.setUsername(sessioneTeacher.getUsername()
                    .replaceAll("\\s+$", "")); //toglie lo spazio alla fine
                nameFolder = folder(sessioneTeacher.getUsername(),
                    sessioneTeacher.getUsername().length());
                dir = creaDir(nameFolder);
                i = 1;
              }
            }
            if (sessioneTutor != null && i == 0) {
              if (sessioneTutor.getEmail().length() > 0) {
                sessioneTutor.setUsername(sessioneTutor.getUsername()
                    .replaceAll("^\\s+", "")); //toglie lo spazio all'inizio
                sessioneTutor.setUsername(sessioneTutor.getUsername()
                    .replaceAll("\\s+$", "")); //toglie lo spazio alla fine
                nameFolder = folder(sessioneTutor.getUsername(),
                    sessioneTutor.getUsername().length());
                dir = creaDir(nameFolder);
                i = 1;
              }
            }

            if (imageProfile == null) {
              imageProfile = "/Tirocinio2.5/Users/TeacherTutor/" + nameFolder + "/" + name;
            } else {
              imageProfile = imageProfile + nameFolder + "/" + name;
            }

            item.write(new File(dir + File.separator + name));
          } else {
            if ("action".equals(item.getFieldName())) {
              action = item.getString();
            }

            if ("company".equals(item.getFieldName())) {
              company = item.getString();
            }

            if ("address".equals(item.getFieldName())) {
              indirizzo = item.getString();
            }

            if ("phone".equals(item.getFieldName())) {
              telefono = item.getString();
            }

            if ("fax".equals(item.getFieldName())) {
              fax = item.getString();
            }

            if ("email".equals(item.getFieldName())) {
              email = item.getString();
            }

            if ("city".equals(item.getFieldName())) {
              luogo = item.getString();
            }

            if ("website".equals(item.getFieldName())) {
              sitoweb = item.getString();
            }

            if ("whoiam".equals(item.getFieldName())) {
              chisono = item.getString();
            }
          }
        }

        if (action.equalsIgnoreCase("createProfile") || action.equalsIgnoreCase("EditProfile")) {
          boolean control = true;
          
          if (!validateEmail(email)) {
            request.setAttribute("email_not_valid_profile", ""
                + "Email inserita non valida.");
            control = false;
          }
          
          if (!validateTelFax(fax)) {
            request.setAttribute("fax_not_valid_profile", ""
                + "Numero del fax inserito non valido.");
            control = false;
          }
          
          if (!validateTelFax(telefono)) {
            request.setAttribute("telefono_not_valid_profile", ""
                + "Numero di telefono inserito non valido.");
            control = false;
          }
          
          if (!validateLuogo(luogo)) {
            request.setAttribute("luogo_not_valid_profile", ""
                + "Luogo inserito non valido. = " + luogo);
            control = false;
          }
          
          if (!validateCompany(company)) {
            request.setAttribute("company_not_valid_upload", ""
                      + "Il campo company non è valido, deve avere solo numeri o caratteri o (&).");
            control = false;
          }
          
          if (control) {
            ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();
            if (sessioneTeacher != null) {
              bean.setTipo("Professore");
              bean.setUsername(sessioneTeacher.getUsername());
            }
            
            if (sessioneTutor != null) {
              bean.setTipo("Tutor Aziendale");
              bean.setUsername(sessioneTutor.getUsername());
            }
            bean.setCompany(company);
            bean.setIndirizzo(indirizzo);
            bean.setTelefono(telefono);
            bean.setFax(fax);
            bean.setEmail(email);
            bean.setCitta(luogo);
            bean.setSitoweb(sitoweb);
            bean.setChisono(chisono);
            bean.setImmagine_profilo(imageProfile);
            Tutormodel.doModifyProfile(bean);
            if (action.equalsIgnoreCase("EditProfile")) {
              request.setAttribute("message_success_profile", ""
                  + "Profilo Modificato con successo.");
              return_path = "/EditProfile.jsp";
            } else {
              request.setAttribute("message_success_profile", "Profilo Creato con successo.");
            }

            if (sessioneTeacher != null) {
              request.getSession().setAttribute("teacher", Tutormodel
                  .doRetrieveByKey(sessioneTeacher.getUsername()));
            }
            if (sessioneTutor != null) {
              request.getSession().setAttribute("tutor", Tutormodel
                  .doRetrieveByKey(sessioneTutor.getUsername()));
            }
          } 
                
          if (action.equalsIgnoreCase("EditProfile")) {
            return_path = "/EditProfile.jsp";
          }
        }
      } catch (Exception ex) {
        request.setAttribute("message_danger_profile", "File upload failed due to : " + ex);

        if (action.equalsIgnoreCase("EditProfile")) {
          return_path = "/EditProfile.jsp";
        }
      }
    } else {
      request.setAttribute("message_danger", 
          "Sorry this servlet only handles file upload request.");
    }

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(return_path);

    dispatcher.forward(request, response);
  }
  
  /**
   * Il metodo è utile per pulire un nome dagli spazi e dagli * sostituiendoli con il _.
   * @param name tipo String, variabile che in input ha un possibile nome di una cartella
   * @param n tipo int, variabile che contiene la lunghezza del nome di una cartella
   * @return name_folder tipo String, variabile che restituisce il nome di una cartella 
   *     pulita da (spazi) e * con _
   */
  public String folder(String name,int n) {
    String nameFolder = "";

    for (int i = 0; i < n; i++) {
      String comparison = name.substring(i, i + 1);

      if ((comparison.equalsIgnoreCase(" ")) || (comparison.equalsIgnoreCase("*"))) {
        nameFolder = nameFolder + "_";
      } else {
        nameFolder = nameFolder + name.substring(i, i + 1);
      }
    }

    return nameFolder;
  }
  /**
   * Il metodo aggiunge alla variabile dir il nome della cartella che gli viene passato.
   * @param name_folder tipo String, variabile che riceve il nome della cartella  
   * @return Dir tipo String, variabile che contiene il path dove verrà memorizzata una cartella
   */
  
  private static String creaDir(String nameFolder) {

    //String Dir = "C:/Users/Luca/Desktop/progetto IS/git/Tirocinio2_5/
    //src/main/webapp/Users/TeacherTutor;
    String dir = "C:/apache-tomcat-8.5.11/webapps/Tirocinio2.5/Users/TeacherTutor/" + nameFolder;

    new File(dir).mkdir();
    return dir;
  }
  
  /**
  * Il metodo confronta l'email passata con una espressione regolare,
  *  per verificare se la variabile passata è una email valida.
  * @param email tipo Boolean, Variabile che viene cofrontata 
  *     con le espressioni regolari per verificare se è una email valida
  * @return true/false valore boolean che se è false allora il 
  *     parametro passato non è una email valida, true altrimenti.
 */
  public boolean validateEmail(String email) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}+$");
    Matcher matcher = pattern.matcher(email);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Il metodo confronta il luogo passato con una espressione regolare,
   *  per verificare se la variabile passata è un luogo valido.
   * @param luogo tipo Boolean, Variabile che viene cofrontata 
   *     con le espressioni regolari per verificare se è un luogo valido
   * @return true/false valore boolean che se è false allora il 
   *     parametro passato non è un luogo valido, true altrimenti.
  */
  public boolean validateLuogo(String luogo) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9',. ()]+$");
    Matcher matcher = pattern.matcher(luogo);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
  * Il metodo confronta il numero di telefono e il fax passati con una espressione regolare,
  *  per verificare se la variabile passata è valida.
  * @param numero tipo String, Variabile che viene cofrontata 
  *     con le espressioni regolari per verificare se è un numero valido
  * @return true/false valore boolean che se è false allora il 
  *     parametro passato non è un numero valido, true altrimenti.
 */
  public boolean validateTelFax(String numero) {
    Pattern pattern = Pattern.compile("[0-9+]+$");
    Matcher matcher = pattern.matcher(numero);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Il metodo confronta l'azienda passata con una espressione 
   * regolare, per verificare se la variabile passata è un formato giusto.
   * @param company tipo String, Variabile che viene cofrontata 
   *     con le espressioni regolari per verificare se è un formato giusto
   * @return true/false valore boolean che se è false allora 
   *     il parametro passato non è un formato giusto., true altrimenti.
  */
  public boolean validateCompany(String company) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9.& ']+$");
    Matcher matcher = pattern.matcher(company);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
}