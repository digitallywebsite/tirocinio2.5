package gestionestudente.control;

import gestionestudente.model.Studente;
import gestioneutente.model.Tirocinio;
import gestioneutente.model.TirocinioModel;
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
 * UploadDocument è una classe che permetterà di salvare un 
 * file documento nel server e di inserire nel database un tirocinio precedente.
 */
public class UploadDocument extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static TirocinioModel model;

  static {

    model = new TirocinioModel();
  }
 
  static String return_path = "/PersonalArea.jsp";
  
  public UploadDocument() {
     super();
  }

  /**
    * Il metodo doPost, tenterà di inserire un documento 
    * come file in una cartella specifica dell'utente che fa richiesta 
    * ed inserire nel database un tirocinio precedente.
  */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
           throws ServletException, IOException {

    if (ServletFileUpload.isMultipartContent(request)) {
      try {
        Studente sessioneStudent = (Studente) request.getSession().getAttribute("student");
        
        List<FileItem> multiparts = 
            new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
        
        String nameFolder = null;
        String dir = null;
        String action = null;
        String company = null;
        String firstLastName = null;
        String job = null;
        String mansioni = null;
        String filePdf = null;
        String name = null;
        int i = 0;

        for (FileItem item : multiparts) {
          if (!item.isFormField()) {
            name = new File(item.getName()).getName();
  
            if (validateNomeFile(name)) {
              if (sessioneStudent != null && i == 0) {
                sessioneStudent.setMatricola(sessioneStudent.getMatricola()
                      .replaceAll("^\\s+", ""));
                sessioneStudent.setMatricola(sessioneStudent.getMatricola()
                      .replaceAll("\\s+$", ""));
                nameFolder = 
                     folder(sessioneStudent.getMatricola(),sessioneStudent.getMatricola().length());
                dir = creaDir(nameFolder);
                i = 1;
              }

              if (filePdf == null) {
                filePdf = "/Tirocinio2.5/Users/Students/" + nameFolder + "/" + name;
              } else {
                filePdf = filePdf + nameFolder + "/" + name;
              }
            
              item.write(new File(dir + File.separator + name));
            } else {
              request.setAttribute("filenotsupported", "File non caricato formato diverso da pdf.");
            }
          } else {
            if ("action".equals(item.getFieldName())) {
              action = item.getString();
            }
            
            if ("company".equals(item.getFieldName())) {
              company = item.getString();
            }
            
            if ("first_last_name".equals(item.getFieldName())) {
              firstLastName = item.getString();
            }
            
            if ("job".equals(item.getFieldName())) {
              job = item.getString();
            }
            
            if ("mansioni".equals(item.getFieldName())) {
              mansioni = item.getString();
            }
          }
        }
        
        if (action.equalsIgnoreCase("insert_old_training")) {

          boolean control = true;
          
          if (!validateCompany(company)) {
            request.setAttribute("company_not_valid_upload", ""
                  + "Il campo company non è valido, deve avere solo numeri o caratteri o (&).");
            control = false;
          }
          
          if (!validateOreSvolte(job)) {
            request.setAttribute("job_not_valid_upload", ""
                    + "Il campo durata lavoro non è valido, deve avere solo numeri.");
            control = false;
          }
          
          if (!validateNomeFile(name)) {
            control = false;
          }
          
          if (control) {
            Tirocinio bean = new Tirocinio();
            bean.setDocumentLink(filePdf);
            bean.setMatricola_studente(sessioneStudent.getMatricola());
            bean.setCompany(company);
            bean.setNomeCognome(firstLastName);
            bean.setOreSvolte(job);
            bean.setCompitiSvolti(mansioni);
            bean.setStato("In Attesa");
            bean.setSegreteria_username("segreteria");
            model.doSaveOld(bean);

            request.setAttribute("message_success", "Tirocinio Precedente inserito con successo.");
            return_path = "/PersonalArea.jsp";
          } else {
            return_path = "/OldTraining.jsp";
          }
        }
      } catch (Exception ex) {
        request.setAttribute("message_danger", "File upload failed due to : " + ex);
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
    * @param n tipo int, variabile che misura la lunghezza del nome di una cartella
    * @return nameFolder tipo String, il nome di una cartella pulita da (spazi) e * con _
    * 
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
   * 
   */
  private static String creaDir(String nameFolder) {
    //String Dir = "C:/Users/ciro9/eclipse-workspace/Tirocinio2.5/WebContent/Users/Students/" 
    //  + name_folder;
    String dir = "C:/apache-tomcat-8.5.11/webapps/Tirocinio2.5/Users/Students/" + nameFolder;

    new File(dir).mkdir();
    return dir;
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
    Pattern pattern = Pattern.compile("[a-zA-Z0-9.& ]+$");
    Matcher matcher = pattern.matcher(company);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
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
    Pattern pattern = Pattern.compile("[0-9]+$");
    Matcher matcher = pattern.matcher(oresvolte);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Il metodo confronta il nome del file con una espressione 
   * regolare, per verificare se la variabile passata è un formato giusto.
   * @param nomeFile tipo String, Variabile che viene cofrontata 
   *     con le espressioni regolari per verificare se  è un formato giusto
   * @return true/false valore boolean che se è false allora 
   *     il parametro passato non è  è un formato giusto, true altrimenti.
  */
  public boolean validateNomeFile(String nomeFile) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9._%-]+\\.(pdf)");
    Matcher matcher = pattern.matcher(nomeFile);

    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }
}