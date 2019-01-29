package gestionestudente.model;

import gestionestorage.ConnectionDb;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

/**
 * StudenteModel è una classe con 9 metodi, questa classe si occupa di 
 * gestire varie operazioni di inserimento, modifica e cancellazione 
 * delle tuple della tabella studente. 
 *
 */
public class StudenteModel {

  static ConnectionDb database = new ConnectionDb();
  private static DataSource ds = database.getDs();
  
  private static final String TABLE_NAME = "studente";

  /**
   * Il metodo doSave, viene utilizzato per poter salvare le informazione del studente nel DataBase.
   * @param studente tipo Studente, variabile che ci da accesso a tutti i metodi set e get.
   * @throws SQLException eccezione che viene lanciata 
   *     quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized void doSave(Studente studente) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "insert into " + StudenteModel.TABLE_NAME
        + " (Matricola, Nome, Cognome, Email, Username, psw, linkedin, link_curriculum) "
        + "values (?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, studente.getMatricola());
      preparedStatement.setString(2, studente.getNome());
      preparedStatement.setString(3, studente.getCognome());
      preparedStatement.setString(4, studente.getEmail());
      preparedStatement.setString(5, studente.getUsername());
      preparedStatement.setString(6, studente.getPsw());
      preparedStatement.setString(7, studente.getLinkedin());
      preparedStatement.setString(8, studente.getLink_curriculum());

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
   * Il metodo doMdofify, viene utilizzato per poter modificare i dati di un studente.
   * @param studente tipo Studente, variabile che ci da accesso a tutti i metodi set e get.
   * @return result tipo int, restituisce il risultato della query di UPDATE.
   * @throws SQLException eccezione che viene lanciata quando 
   *     viene rilevato un errore nell'esecuzione di una query.
   */
  public synchronized boolean doModify(Studente studente) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String insertSql = "UPDATE " + StudenteModel.TABLE_NAME 
        + " SET Nome = ?, Cognome = ?, Email = ?, Username = ? ,psw = ? where Matricola = ?";


    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, studente.getNome());
      preparedStatement.setString(2, studente.getCognome());
      preparedStatement.setString(3, studente.getEmail());
      preparedStatement.setString(4, studente.getUsername());
      preparedStatement.setString(5, studente.getPsw());
      preparedStatement.setString(6, studente.getMatricola());

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
    * Il metodo doModifyLinkedin, viene utilizzato per poter modificare il link di linkedin.
    * @param studente tipo Studente, variabile che ci da accesso a tutti i metodi set e get.
    * @return result tipo int, restituisce il risultato della query di UPDATE.
    * @throws SQLException eccezione che 
    *     viene lanciata quando viene rilevato un errore nell'esecuzione di una query.
   */
  public synchronized boolean doModifyLinkedin(Studente studente) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String insertSql = "UPDATE " + StudenteModel.TABLE_NAME 
        + " SET linkedin = ? where Matricola = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, studente.getLinkedin());
      preparedStatement.setString(2, studente.getMatricola());
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
   * Il metodo doModifyLink, viene utilizzato per poter modificare il link del curriculum.
   * @param studente tipo Studente, variabile che ci da accesso a tutti i metodi set e get.
   * @return result tipo int, restituisce il risultato della query di UPDATE.
   * @throws SQLException eccezione che 
   *     viene lanciata quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized boolean doModifyLink(Studente studente) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;
    
    String insertSql = "UPDATE " + StudenteModel.TABLE_NAME 
          + " SET link_curriculum = ? where Matricola = ?";
    

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, studente.getLink_curriculum());
      preparedStatement.setString(2, studente.getMatricola());

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
   * Il metodo doModifyCurriculum, viene utilizzato per 
   * poter modificare il link di dov'è situato il file.
   * @param studente tipo Studente, variabile che ci da accesso a tutti i metodi set e get.
   * @return result tipo int, restituisce il risultato della query di UPDATE.
   * @throws SQLException eccezione che 
   *     viene lanciata quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized boolean doModifyCurriculum(Studente studente) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    
    int result = 0;

    String insertSql = "UPDATE " + StudenteModel.TABLE_NAME 
          + " SET link_curriculum = ? where Matricola = ?";


    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, studente.getLink_curriculum());
      preparedStatement.setString(2, studente.getMatricola());
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
   * Il metodo doRetrieveByKey restituisce informazioni su un Studente in base al suo id.
   * @param id tipo String, variabile che contiente 
   *     un possibile riferimento ad una tupla in un DataBase
   * @return bean tipo Studente, variabile che ci da accesso a tutti i metodi set e get.
   * @throws SQLException eccezione che 
   *     viene lanciata quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Studente doRetrieveByKey(String id) throws SQLException {
  
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Studente bean = new Studente();

    String selectSql = "select * from " 
           + StudenteModel.TABLE_NAME + " where Matricola = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setMatricola(rs.getString("Matricola"));
        bean.setNome(rs.getString("Nome"));
        bean.setCognome(rs.getString("Cognome"));
        bean.setEmail(rs.getString("Email"));
        bean.setUsername(rs.getString("Username"));
        bean.setPsw(rs.getString("psw"));
        bean.setLinkedin(rs.getString("linkedin"));
        bean.setLink_curriculum(rs.getString("link_curriculum"));
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
    * @param id tipo String, variabile che 
    *     contiente un possibile riferimento ad una tupla in un DataBase
    * @return result tipo Integer, variabile che restituisce un valore della query di Delete.
    * @throws SQLException eccezione che 
    *     viene lanciata quando viene rilevato un errore nell'esecuzione di una query.
   */
  public synchronized boolean doDelete(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSql = "delete from " 
        + StudenteModel.TABLE_NAME + " where Matricola = ?";
    
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
   * Il metodo doRetrieveAll, permette di ricevere tutti gli studenti registrati.
   * @param order tipo String, variabile che 
   *     verrà utilizzata nella query per poter specificare un ordine.
   * @return customers tipo Collection di Studente, 
   *     Array che conterrà tutte le informazioni sullo Studente
   * @throws SQLException eccezione che 
   *     viene lanciata quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Collection<Studente> doRetrieveAll(String order) throws SQLException {
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Collection<Studente> customers = new LinkedList<Studente>();

    String selectSql = "select * from " + StudenteModel.TABLE_NAME;

    if (order != null && !order.equals("")) {
      selectSql += " order by " + order;
    }

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Studente bean = new Studente();

        bean.setMatricola(rs.getString("Matricola"));
        bean.setEmail(rs.getString("Email"));
        bean.setNome(rs.getString("Nome"));
        bean.setCognome(rs.getString("Cognome"));
        bean.setUsername(rs.getString("Username"));
        bean.setLinkedin(rs.getString("linkedin"));
        bean.setLink_curriculum(rs.getString("link_curriculum"));
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
    * Il metodo loginStudente, interroga la tabella segreteria con combinazione email_username, psw.
    * @param emailusername tipo String, 
    *     variabile che viene utilizzata per l'interrogazione al DataBase.
    * @param psw tipo String, variabile che viene utilizzata per l'interrogazione al DataBase.
    * @return bean tipo Studente, variabile che ci da accesso a tutti i metodi set e get.
    * @throws SQLException eccezione che 
    *     viene lanciata quando viene rilevato un errore nell'esecuzione di una query.
   */
  public synchronized Studente loginStudente(String emailusername,String psw) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Studente bean = new Studente();

    String selectSql = "SELECT * FROM " + StudenteModel.TABLE_NAME 
          + " WHERE (Email = ? OR username = ?) AND psw = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, emailusername);
      preparedStatement.setString(2, emailusername);
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
          bean.setMatricola(rs.getString("Matricola"));
          bean.setEmail(rs.getString("email"));
          bean.setNome(rs.getString("Nome"));
          bean.setCognome(rs.getString("Cognome"));
          bean.setUsername(rs.getString("Username"));
          bean.setPsw(rs.getString("psw"));
          bean.setLinkedin(rs.getString("linkedin"));
          bean.setLink_curriculum(rs.getString("link_curriculum"));
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

