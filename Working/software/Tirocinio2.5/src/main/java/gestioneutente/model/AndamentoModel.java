package gestioneutente.model;

import gestionestorage.ConnectionDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;



/**
 * AndamentoModel è una classe con 6 metodi, questa classe si occupa di gestire varie 
 * operazioni di inserimento, modifica e cancellazione delle tuple della tabella andamento. 
 *
*/
public class AndamentoModel {

  static ConnectionDb database = new ConnectionDb();
  private static DataSource ds = database.getDs();

  private static final String TABLE_NAME = "andamento";

  /**
   * Il metodo doSave, viene utilizzato per poter salvare le informazione 
   * dell'andamento nel DataBase.
   * @param andamento tipo Andamento, variabile che ci da accesso a tutti i metodi set e get.
   * @throws SQLException eccezione che viene lanciata quando viene rilevato
   *     un errore nell'esecuzione di una query.
  */
  public synchronized void doSave(Andamento andamento) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "insert into " + AndamentoModel.TABLE_NAME
        + " (DataT, Ora_Inizio, Ora_Fine,TirocinioID) values (?, ?, ?, ?)";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, andamento.getDataT());
      preparedStatement.setString(2, andamento.getOra_inizio());
      preparedStatement.setString(3, andamento.getOra_fine());
      preparedStatement.setInt(4, andamento.getTirocinioId());

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
   * Il metodo doMdofify, viene utilizzato per poter modificare i dati di un'andamento.
   * @param andamento tipo Andamento, variabile che ci da accesso a tutti i metodi set e get.
   * @return result tipo int, restituisce il risultato della query di UPDATE.
   * @throws SQLException eccezione che viene lanciata quando viene 
   *     rilevato un errore nell'esecuzione di una query.
  */
  public synchronized boolean doModify(Andamento andamento) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String insertSql = "UPDATE " + AndamentoModel.TABLE_NAME + " SET DataT = ?, Ora_Inizio = ?, "
          + "Ora_Fine = ? where id = ?";


    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, andamento.getDataT());
      preparedStatement.setString(2, andamento.getOra_inizio());
      preparedStatement.setString(3, andamento.getOra_fine());
      preparedStatement.setInt(4, andamento.getId());

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
   * Il metodo doRetrieveByKey restituisce informazioni su un Andamento in base al suo id.
   * @param id tipo String, variabile che contiente 
   *     un possibile riferimento ad una tupla in un DataBase
   * @return bean tipo Andamento, variabile che ci da accesso a tutti i metodi set e get.
   * @throws SQLException eccezione che viene lanciata quando 
   *     viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Andamento doRetrieveByKey(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Andamento bean = new Andamento();

    

    String selectSql = "select * from studente S, tirocinio T, andamento A, "
        + "professore_tutoraziendale PT "
        + "where S.Matricola=T.MatricolaStudente AND T.id=A.TirocinioID "
        + "AND PT.Username=T.TutorUsername AND A.id = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setId(rs.getInt("A.id"));
        bean.setDataT(rs.getString("DataT"));
        bean.setOra_inizio(rs.getString("Ora_Inizio"));
        bean.setOra_fine(rs.getString("Ora_Fine"));
        bean.setTirocinioId(rs.getInt("TirocinioID"));
        bean.setNomeCognomeStudent(rs.getString("S.Nome") + " " + rs.getString("S.Cognome"));
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
   * @param id tipo String, variabile che contiente
   *      un possibile riferimento ad una tupla in un DataBase
   * @return result tipo Integer, variabile che restituisce un valore della query di Delete.
   * @throws SQLException eccezione che viene lanciata quando 
   *     viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized boolean doDelete(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSql = "delete from " + AndamentoModel.TABLE_NAME + " where id = ?";

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
   * Il metodo doRetrieveAll, permette di ricevere tutte le tuple di andamento.
   * @param order tipo String, variabile che verrà utilizzata nella query 
   *     per poter specificare un ordine.
   * @return customers tipo Collection di Andamento, Array che conterrà 
   *     tutte le informazioni sullo Andamento
   * @throws SQLException eccezione che viene lanciata quando 
   *     viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Collection<Andamento> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Collection<Andamento> customers = new LinkedList<Andamento>();

    String selectSql = "select * from " + AndamentoModel.TABLE_NAME;

    if (order != null && !order.equals("")) {
      selectSql += " order by " + order;
    }

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Andamento bean = new Andamento();

        bean.setId(rs.getInt("id"));
        bean.setDataT(rs.getString("DataT"));
        bean.setOra_inizio(rs.getString("Ora_Inizio"));
        bean.setOra_fine(rs.getString("Ora_Fine"));
        bean.setTirocinioId(rs.getInt("TirocinioID"));
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
   * Il metodo RequestTrend, permette di ricevere tutte le tuple di uno 
   *     andamento filtrato per id di un tirocinio.
   * @param order tipo String, variabile che verrà utilizzata nella query 
   *     per poter specificare un ordine.
   * @param id tipo Integer, variabile che verrà utilizzate nella query per
   *      poter specificare un TirocinioID.
   * @return trend tipo Collection di Andamento, Array che conterrà 
   *     tutte le informazioni sullo Andamento
   * @throws SQLException eccezione che viene lanciata quando viene 
   *     rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Collection<Andamento> requestTrend(String order, int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Collection<Andamento> trend = new LinkedList<Andamento>();

    String selectSql = "select * from studente S, tirocinio T, andamento A,"
        + " professore_tutoraziendale PT "
        + "where S.Matricola=T.MatricolaStudente AND T.id=A.TirocinioID AND "
        + "PT.Username=T.TutorUsername AND A.TirocinioID = ?";

    if (order != null && !order.equals("")) {
      selectSql += " order by " + order;
    }

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Andamento bean = new Andamento();

        bean.setId(rs.getInt("A.id"));
        bean.setDataT(rs.getString("DataT"));
        bean.setOra_inizio(rs.getString("Ora_Inizio"));
        bean.setOra_fine(rs.getString("Ora_Fine"));
        bean.setTirocinioId(rs.getInt("TirocinioID"));
        bean.setNomeCognome(rs.getString("PT.Nome") + " " + rs.getString("PT.Cognome"));
        bean.setNomeCognomeStudent(rs.getString("S.Nome") + " " + rs.getString("S.Cognome"));
        trend.add(bean);
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
    return trend;
  }
}

