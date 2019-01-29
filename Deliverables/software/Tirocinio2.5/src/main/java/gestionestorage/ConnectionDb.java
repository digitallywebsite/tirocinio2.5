package gestionestorage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * ConnectionDB è una classe che permette di inizzializzare la connessione a un database.
 * 
 */
public class ConnectionDb {

  private static DataSource ds;

  static {
    try {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");

      ds = (DataSource) envCtx.lookup("jdbc/tirocinio2_5");
      
    } catch (NamingException e) {
      System.out.println("Error:" + e.getMessage());
    }
  }

  /**
  * Metodo che restituisce una username.
  * @return ds tipo DataSource, variabile contente il valore di un ds
  */
  public DataSource getDs() {
    return ds;
  }
}
