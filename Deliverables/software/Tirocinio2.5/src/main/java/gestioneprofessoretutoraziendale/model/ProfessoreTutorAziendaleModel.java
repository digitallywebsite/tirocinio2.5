package gestioneprofessoretutoraziendale.model;

import gestionestorage.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

/** 
 * ProfessoreTutorAziendaleModel è una classe con 11 metodi, questa classe si occupa 
 * di gestire varie operazioni di inserimento, modifica e cancellazione delle tuple 
 * della tabella professore_tutoraziendale. 
 *
 */
public class ProfessoreTutorAziendaleModel {

  static ConnectionDb database = new ConnectionDb();
  private static DataSource ds = database.getDs();

  private static final String TABLE_NAME = "professore_tutoraziendale";

  /**
  * Il metodo doSave, viene utilizzato per poter salvare le informazione 
  * del ProfessoreTutorAziendale nel DataBase.
  * @param professoreTutor tipo ProfessoreTutorAziendale, variabile 
  *     che ci da accesso a tutti i metodi set e get.
  * @throws SQLException eccezione che viene lanciata 
  *     quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized void doSave(ProfessoreTutorAziendale professoreTutor) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "insert into " + ProfessoreTutorAziendaleModel.TABLE_NAME
        + " (Username, Nome, Cognome,Tipo, Company, Indirizzo, Telefono, Fax, Email, psw, "
        + "Citta, SitoWeb, ChiSono, Immagine_Profilo) values "
        + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, professoreTutor.getUsername());
      preparedStatement.setString(2, professoreTutor.getNome());
      preparedStatement.setString(3, professoreTutor.getCognome());
      preparedStatement.setString(4, professoreTutor.getTipo());
      preparedStatement.setString(5, professoreTutor.getCompany());
      preparedStatement.setString(6, professoreTutor.getIndirizzo());
      preparedStatement.setString(7, professoreTutor.getTelefono());
      preparedStatement.setString(8, professoreTutor.getFax());
      preparedStatement.setString(9, professoreTutor.getEmail());
      preparedStatement.setString(10, professoreTutor.getPsw());
      preparedStatement.setString(11, professoreTutor.getCitta());
      preparedStatement.setString(12, professoreTutor.getSitoweb());
      preparedStatement.setString(13, professoreTutor.getChisono());
      preparedStatement.setString(14, professoreTutor.getImmagine_profilo());

      preparedStatement.executeUpdate();
      connection.commit();

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
  }

  /**
  * Il metodo doMdofify, viene utilizzato per poter modificare 
  *     i dati di un ProfessoreTutorAziendale.
  * @param professoreTutor tipo ProfessoreTutorAziendale, variabile 
  *     che ci da accesso a tutti i metodi set e get.
  * @return result tipo int, restituisce il risultato della query di UPDATE.
  * @throws SQLException eccezione che viene lanciata quando viene 
  *     rilevato un errore nell'esecuzione di una query.
  */
  public synchronized boolean doModify(ProfessoreTutorAziendale professoreTutor)
      throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String insertSql = "UPDATE " + ProfessoreTutorAziendaleModel.TABLE_NAME
        + " SET Nome = ?, Cognome = ?, Email = ? , psw = ? where Username = ?";


    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, professoreTutor.getNome());
      preparedStatement.setString(2, professoreTutor.getCognome());
      preparedStatement.setString(3, professoreTutor.getEmail());
      preparedStatement.setString(4, professoreTutor.getPsw());
      preparedStatement.setString(5, professoreTutor.getUsername());

      result = preparedStatement.executeUpdate();
      connection.commit();
    } catch (SQLException e) {

      System.out.println(e.getMessage());

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return (result != 0);
  }

  /**
  * Il metodo doModifyProfile, viene utilizzato per poter modificare 
  *     il Profilo di un ProfessoreTutorAziendale.
  * @param professoreTutor tipo ProfessoreTutorAziendale, variabile 
  *     che ci da accesso a tutti i metodi set e get.
  * @return result tipo int, restituisce il risultato della query di UPDATE.
  * @throws SQLException eccezione che viene lanciata quando viene 
  *     rilevato un errore nell'esecuzione di una query.
  */
  public synchronized boolean doModifyProfile(ProfessoreTutorAziendale professoreTutor) 
      throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String insertSql = "UPDATE " + ProfessoreTutorAziendaleModel.TABLE_NAME 
        + " SET Tipo = ?, Company = ?, Indirizzo = ?, Telefono = ?, "
        + "Fax = ?, Email = ? , Citta = ?, SitoWeb = ?, ChiSono = ?, "
        + "Immagine_profilo = ? where Username = ?";


    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, professoreTutor.getTipo());
      preparedStatement.setString(2, professoreTutor.getCompany());
      preparedStatement.setString(3, professoreTutor.getIndirizzo());
      preparedStatement.setString(4, professoreTutor.getTelefono());
      preparedStatement.setString(5, professoreTutor.getFax());
      preparedStatement.setString(6, professoreTutor.getEmail());
      preparedStatement.setString(7, professoreTutor.getCitta());
      preparedStatement.setString(8, professoreTutor.getSitoweb());
      preparedStatement.setString(9, professoreTutor.getChisono());
      preparedStatement.setString(10, professoreTutor.getImmagine_profilo());
      preparedStatement.setString(11, professoreTutor.getUsername());

      result = preparedStatement.executeUpdate();

      connection.commit();
    } catch (SQLException e) {

      System.out.println(e.getMessage());

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return (result != 0);
  }

  /**
  * Il metodo doRetrieveByKey restituisce informazioni 
  *     su un ProfessoreTutorAziendale in base al suo id.
  * @param id tipo String, variabile che contiente un possibile 
  *     riferimento ad una tupla in un DataBase
  * @return bean tipo ProfessoreTutorAziendale, 
  *     variabile che ci da accesso a tutti i metodi set e get.
  * @throws SQLException eccezione che viene lanciata 
  *     quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized ProfessoreTutorAziendale doRetrieveByKey(String id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();

    String selectSql = "select * from "
        + "" + ProfessoreTutorAziendaleModel.TABLE_NAME + " where Username = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setUsername(rs.getString("Username"));
        bean.setNome(rs.getString("Nome"));
        bean.setCognome(rs.getString("Cognome"));
        bean.setTipo(rs.getString("Tipo"));
        bean.setCompany(rs.getString("Company"));
        bean.setIndirizzo(rs.getString("Indirizzo"));
        bean.setTelefono(rs.getString("Telefono"));
        bean.setFax(rs.getString("Fax"));
        bean.setEmail(rs.getString("Email"));
        bean.setPsw(rs.getString("psw"));
        bean.setCitta(rs.getString("Citta"));
        bean.setSitoweb(rs.getString("SitoWeb"));
        bean.setChisono(rs.getString("ChiSono"));
        bean.setImmagine_profilo(rs.getString("Immagine_Profilo"));
      }

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return bean;
  }

  /**
  * Il metodo doRetrieveByInternal restituisce informazioni 
  *     su un Professore in base al suo id.
  * @param id tipo String, variabile che contiente 
  *     un possibile riferimento ad una tupla in un DataBase
  * @return bean tipo ProfessoreTutorAziendale, variabile 
  *     che ci da accesso a tutti i metodi set e get.
  * @throws SQLException eccezione che viene lanciata 
  *     quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized ProfessoreTutorAziendale doRetrieveByInternal(String id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();
    
    String selectSql = "select * from " + ProfessoreTutorAziendaleModel.TABLE_NAME + " "
        + "where Email = ? AND Tipo = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, id);
      preparedStatement.setString(2, "Professore");

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setUsername(rs.getString("Username"));
        bean.setNome(rs.getString("Nome"));
        bean.setCognome(rs.getString("Cognome"));
        bean.setTipo(rs.getString("Tipo"));
        bean.setCompany(rs.getString("Company"));
        bean.setIndirizzo(rs.getString("Indirizzo"));
        bean.setTelefono(rs.getString("Telefono"));
        bean.setFax(rs.getString("Fax"));
        bean.setEmail(rs.getString("Email"));
        bean.setPsw(rs.getString("psw"));
        bean.setCitta(rs.getString("Citta"));
        bean.setSitoweb(rs.getString("SitoWeb"));
        bean.setChisono(rs.getString("ChiSono"));
        bean.setImmagine_profilo(rs.getString("Immagine_Profilo"));
      }

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return bean;
  }

  /**
  * Il metodo doRetrieveByInternal restituisce 
  *     informazioni su un Tutor Aziendale in base al suo id.
  * @param id tipo String, variabile che contiente un 
  *     possibile riferimento ad una tupla in un DataBase
  * @return bean tipo ProfessoreTutorAziendale, 
  *     variabile che ci da accesso a tutti i metodi set e get.
 * @throws SQLException eccezione che viene lanciata 
 *     quando viene rilevato un errore nell'esecuzione di una query.
 */
  public synchronized ProfessoreTutorAziendale doRetrieveByExternal(String id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();

    String selectSql = "select * from " + ProfessoreTutorAziendaleModel.TABLE_NAME + ""
        + " where Email = ? AND Tipo = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, id);
      preparedStatement.setString(2, "Tutor Aziendale");

      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        bean.setUsername(rs.getString("Username"));
        bean.setNome(rs.getString("Nome"));
        bean.setCognome(rs.getString("Cognome"));
        bean.setTipo(rs.getString("Tipo"));
        bean.setCompany(rs.getString("Company"));
        bean.setIndirizzo(rs.getString("Indirizzo"));
        bean.setTelefono(rs.getString("Telefono"));
        bean.setFax(rs.getString("Fax"));
        bean.setEmail(rs.getString("Email"));
        bean.setPsw(rs.getString("psw"));
        bean.setCitta(rs.getString("Citta"));
        bean.setSitoweb(rs.getString("SitoWeb"));
        bean.setChisono(rs.getString("ChiSono"));
        bean.setImmagine_profilo(rs.getString("Immagine_Profilo"));
      }

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return bean;
  }

  /**
  * Il Metodo doDelete, cancella una tupla nel database in base al suo id.
  * @param id tipo String, variabile che contiente un 
  *     possibile riferimento ad una tupla in un DataBase
  * @return result tipo Integer, variabile che restituisce un valore della query di Delete.
  * @throws SQLException eccezione che viene lanciata 
  *     quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized boolean doDelete(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSql = "delete from " + ProfessoreTutorAziendaleModel.TABLE_NAME + ""
        + " where Username = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setInt(1, id);

      result = preparedStatement.executeUpdate();
      connection.commit();

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return (result != 0);
  }

  /**
  * Il metodo doRetrieveAll, permette di ricevere tutti i ProfessoreTutorAziendale registrati.
  * @param order tipo String, variabile che verrà utilizzata 
  *     nella query per poter specificare un ordine.
  * @return customers tipo Collection di ProfessoreTutorAziendale, 
  *     Array che conterrà tutte le informazioni dei ProfessoreTutorAziendale
  * @throws SQLException eccezione che viene lanciata quando 
  *     viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Collection<ProfessoreTutorAziendale> doRetrieveAll(String order) 
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Collection<ProfessoreTutorAziendale> customers = new LinkedList<ProfessoreTutorAziendale>();

    String selectSql = "select * from " + ProfessoreTutorAziendaleModel.TABLE_NAME;

    if (order != null && !order.equals("")) {
      selectSql += " order by " + order;
    }

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();

        bean.setUsername(rs.getString("Username"));
        bean.setNome(rs.getString("Nome"));
        bean.setCognome(rs.getString("Cognome"));
        bean.setTipo(rs.getString("Tipo"));
        bean.setCompany(rs.getString("Company"));
        bean.setIndirizzo(rs.getString("Indirizzo"));
        bean.setTelefono(rs.getString("Telefono"));
        bean.setFax(rs.getString("Fax"));
        bean.setEmail(rs.getString("Email"));
        bean.setPsw(rs.getString("psw"));
        bean.setCitta(rs.getString("Citta"));
        bean.setSitoweb(rs.getString("SitoWeb"));
        bean.setChisono(rs.getString("ChiSono"));
        bean.setImmagine_profilo(rs.getString("Immagine_Profilo"));
        customers.add(bean);
      }

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return customers;
  }

  /**
  * Il metodo doRetrieveAllTeachers, permette 
  *     di ricevere tutti i Professori registrati.
  * @param order tipo String, variabile che verrà 
  *     utilizzata nella query per poter specificare un ordine.
  * @return customers tipo Collection di ProfessoreTutorAziendale, 
  *     Array che conterrà tutte le informazioni dei ProfessoreTutorAziendale
  * @throws SQLException eccezione che viene lanciata quando viene 
  *     rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Collection<ProfessoreTutorAziendale> doRetrieveAllTeachers(String order) 
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Collection<ProfessoreTutorAziendale> customers = new LinkedList<ProfessoreTutorAziendale>();

    String selectSql = "select * from " + ProfessoreTutorAziendaleModel.TABLE_NAME + ""
        + " where Tipo = ?";

    if (order != null && !order.equals("")) {
      selectSql += " order by " + order;
    }

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, "Professore");

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();

        bean.setUsername(rs.getString("Username"));
        bean.setNome(rs.getString("Nome"));
        bean.setCognome(rs.getString("Cognome"));
        bean.setTipo(rs.getString("Tipo"));
        bean.setCompany(rs.getString("Company"));
        bean.setIndirizzo(rs.getString("Indirizzo"));
        bean.setTelefono(rs.getString("Telefono"));
        bean.setFax(rs.getString("Fax"));
        bean.setEmail(rs.getString("Email"));
        bean.setPsw(rs.getString("psw"));
        bean.setCitta(rs.getString("Citta"));
        bean.setSitoweb(rs.getString("SitoWeb"));
        bean.setChisono(rs.getString("ChiSono"));
        bean.setImmagine_profilo(rs.getString("Immagine_Profilo"));
        customers.add(bean);
      }

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return customers;
  }

  /**
  * Il metodo doRetrieveAllTutor, permette di ricevere tutti i Tutor Aziendali registrati.
  * @param order tipo String, variabile che verrà utilizzata 
  *     nella query per poter specificare un ordine.
  * @return customers tipo Collection di ProfessoreTutorAziendale, 
  *     Array che conterrà tutte le informazioni dei ProfessoreTutorAziendale
  * @throws SQLException eccezione che viene lanciata 
  *     quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Collection<ProfessoreTutorAziendale> doRetrieveAllTutor(String order) 
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Collection<ProfessoreTutorAziendale> customers = new LinkedList<ProfessoreTutorAziendale>();

    String selectSql = "select * from " + ProfessoreTutorAziendaleModel.TABLE_NAME + ""
        + " where Tipo = ?";

    if (order != null && !order.equals("")) {
      selectSql += " order by " + order;
    }

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, "Tutor Aziendale");

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();

        bean.setUsername(rs.getString("Username"));
        bean.setNome(rs.getString("Nome"));
        bean.setCognome(rs.getString("Cognome"));
        bean.setTipo(rs.getString("Tipo"));
        bean.setCompany(rs.getString("Company"));
        bean.setIndirizzo(rs.getString("Indirizzo"));
        bean.setTelefono(rs.getString("Telefono"));
        bean.setFax(rs.getString("Fax"));
        bean.setEmail(rs.getString("Email"));
        bean.setPsw(rs.getString("psw"));
        bean.setCitta(rs.getString("Citta"));
        bean.setSitoweb(rs.getString("SitoWeb"));
        bean.setChisono(rs.getString("ChiSono"));
        bean.setImmagine_profilo(rs.getString("Immagine_Profilo"));
        customers.add(bean);
      }

    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return customers;
  }

  /**
  * Il metodo loginProfessoreTutor, interroga la tabella 
  *     professore_tutoraziendale con combinazione email_username, psw.
  * @param emailUser tipo String, variabile che 
  *     viene utilizzata per l'interrogazione al DataBase.
  * @param psw tipo String, variabile che viene 
  *     utilizzata per l'interrogazione al DataBase.
  * @return bean tipo ProfessoreTutorAziendale, variabile 
  *     che ci da accesso a tutti i metodi set e get.
  * @throws SQLException eccezione che viene lanciata quando 
  *     viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized ProfessoreTutorAziendale loginProfessoreTutor(String emailUser, String psw) 
      throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();

    String selectSql = "SELECT * FROM " + ProfessoreTutorAziendaleModel.TABLE_NAME + " "
        + "WHERE (Email = ? OR Username = ?) AND psw = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, emailUser);
      preparedStatement.setString(2, emailUser);
      preparedStatement.setString(3, psw);

      ResultSet rs = preparedStatement.executeQuery();

      int numeroRighe = 0;

      if (rs.last()) {
        // Riprendo il numero di righe
        numeroRighe = rs.getRow();

        // Torno alla posizione iniziale, prima della prima righa, 
        //operazione non permessa con il ResultSet.TYPE_FORWARD_ONLY
        rs.beforeFirst();
      }

      if (numeroRighe == 1) {
        while (rs.next()) {
          bean.setUsername(rs.getString("Username"));
          bean.setNome(rs.getString("Nome"));
          bean.setCognome(rs.getString("Cognome"));
          bean.setTipo(rs.getString("Tipo"));
          bean.setCompany(rs.getString("Company"));
          bean.setIndirizzo(rs.getString("Indirizzo"));
          bean.setTelefono(rs.getString("Telefono"));
          bean.setFax(rs.getString("Fax"));
          bean.setEmail(rs.getString("Email"));
          //bean.setPsw(rs.getString("psw"));
          bean.setCitta(rs.getString("Citta"));
          bean.setSitoweb(rs.getString("SitoWeb"));
          bean.setChisono(rs.getString("ChiSono"));
          bean.setImmagine_profilo(rs.getString("Immagine_Profilo"));
        }
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return bean;
  }
  
  /**
   * Il metodo loginProfessore, interroga la tabella 
   *     professore_tutoraziendale con combinazione email_username, psw.
   * @param emailUser tipo String, variabile che 
   *     viene utilizzata per l'interrogazione al DataBase.
   * @param psw tipo String, variabile che viene 
   *     utilizzata per l'interrogazione al DataBase.
   * @return bean tipo ProfessoreTutorAziendale, variabile 
   *     che ci da accesso a tutti i metodi set e get.
   * @throws SQLException eccezione che viene lanciata quando 
   *     viene rilevato un errore nell'esecuzione di una query.
   */
  public synchronized ProfessoreTutorAziendale loginProfessore(String emailUser, String psw) 
       throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    ProfessoreTutorAziendale bean = new ProfessoreTutorAziendale();

    String selectSql = "SELECT * FROM " + ProfessoreTutorAziendaleModel.TABLE_NAME + " "
         + "WHERE (Email = ? OR Username = ?) AND psw = ? AND tipo = ?";
    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, emailUser);
      preparedStatement.setString(2, emailUser);
      preparedStatement.setString(3, psw);
      preparedStatement.setString(4, "Professore");
      ResultSet rs = preparedStatement.executeQuery();
      int numeroRighe = 0;
      if (rs.last()) {
        // Riprendo il numero di righe
        numeroRighe = rs.getRow();

        // Torno alla posizione iniziale, prima della prima righa, 
        //operazione non permessa con il ResultSet.TYPE_FORWARD_ONLY
        rs.beforeFirst();
      }

      if (numeroRighe == 1) {
        while (rs.next()) {
          bean.setUsername(rs.getString("Username"));
          bean.setNome(rs.getString("Nome"));
          bean.setCognome(rs.getString("Cognome"));
          bean.setTipo(rs.getString("Tipo"));
          bean.setCompany(rs.getString("Company"));
          bean.setIndirizzo(rs.getString("Indirizzo"));
          bean.setTelefono(rs.getString("Telefono"));
          bean.setFax(rs.getString("Fax"));
          bean.setEmail(rs.getString("Email"));
          //bean.setPsw(rs.getString("psw"));
          bean.setCitta(rs.getString("Citta"));
          bean.setSitoweb(rs.getString("SitoWeb"));
          bean.setChisono(rs.getString("ChiSono"));
          bean.setImmagine_profilo(rs.getString("Immagine_Profilo"));
        }
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        if (connection != null) {
          connection.close();
        }
      }
    }
    return bean;
  }
}

