package gestionesegreteria.model;

import gestionestorage.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

/**
 * SegreteriaModel è una classe con 5 metodi, questa classe si occupa di 
 * gestire varie operazioni di inserimento, modifica e cancellazione 
 * delle tuple della tabella segreteria. 
 *
 */
public class SegreteriaModel {

  static ConnectionDb database = new ConnectionDb();
  private static DataSource ds = database.getDs();

  private static final String TABLE_NAME = "segreteria";

  /**
  * Il metodo doSave, viene utilizzato per poter salvare 
  *     le informazione del segreteria nel DataBase.
  * @param segreteria tipo Segreteria, variabile che ci da accesso a tutti i metodi set e get.
  * @throws SQLException eccezione che viene lanciata quando 
  *     viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized void doSave(Segreteria segreteria) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSql = "insert into " + SegreteriaModel.TABLE_NAME
        + " (Username, Email, psw) values (?, ?, ?)";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, segreteria.getUsername());
      preparedStatement.setString(2, segreteria.getEmail());
      preparedStatement.setString(3, segreteria.getPsw());

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
  * Il metodo doRetrieveByKey restituisce informazioni 
  *     su un Segreteria in base al suo id.
  * @param id tipo String, variabile che contiente 
  *     un possibile riferimento ad una tupla in un DataBase
  * @return bean tipo Segreteria, variabile che ci da accesso a tutti i metodi set e get.
  * @throws SQLException eccezione che viene lanciata 
  *     quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Segreteria doRetrieveByKey(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Segreteria bean = new Segreteria();

    String selectSql = "select * from " + SegreteriaModel.TABLE_NAME + " where Username = ?";

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setUsername(rs.getString("Username"));
        bean.setEmail(rs.getString("Email"));
        bean.setPsw(rs.getString("psw"));
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
  *     un possibile riferimento ad una tupla in un DataBase
  * @return result tipo Integer, variabile che restituisce un valore della query di Delete.
  * @throws SQLException eccezione che viene lanciata 
  *     quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized boolean doDelete(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    String deleteSql = "delete from " + SegreteriaModel.TABLE_NAME + ""
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
  * Il metodo doRetrieveAll, permette di ricevere tutte le persone di Segreteria registrate.
  * @param order tipo String, variabile che verrà 
  *     utilizzata nella query per poter specificare un ordine.
  * @return customers tipo Collection di Segreteria, Array che 
  *     conterrà tutte le informazioni degli utenti della Segreteria
  * @throws SQLException eccezione che viene lanciata quando 
  *     viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Collection<Segreteria> doRetrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Collection<Segreteria> customers = new LinkedList<Segreteria>();

    String selectSql = "select * from " + SegreteriaModel.TABLE_NAME;

    if (order != null && !order.equals("")) {
      selectSql += " order by " + order;
    }

    try {
      connection = ds.getConnection();
      preparedStatement = connection.prepareStatement(selectSql);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Segreteria bean = new Segreteria();

        bean.setUsername(rs.getString("Username"));
        bean.setEmail(rs.getString("Email"));
        bean.setPsw(rs.getString("psw"));
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
  * Il metodo loginSegreteria, interroga la tabella segreteria con combinazione email_username, psw.
  * @param emailUser tipo String, variabile che 
  *     viene utilizzata per l'interrogazione al DataBase.
  * @param psw tipo String, variabile che viene utilizzata per l'interrogazione al DataBase.
  * @return bean tipo Segreteria, variabile che ci da accesso a tutti i metodi set e get.
  * @throws SQLException eccezione che viene lanciata 
  *     quando viene rilevato un errore nell'esecuzione di una query.
  */
  public synchronized Segreteria loginSegreteria(String emailUser,String psw) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Segreteria bean = new Segreteria();

    String selectSql = "SELECT * FROM " + SegreteriaModel.TABLE_NAME + ""
        + " WHERE (Email = ? OR Username = ?) AND psw = ?";

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
          bean.setEmail(rs.getString("Email"));
          //bean.setPsw(rs.getString("psw"));
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

