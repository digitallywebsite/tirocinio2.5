package gestionesegreteria.model;

/**
 * Segreteria è una classe con 6 metodi set e get, 
 * questa classe è utilizzata per modellare una Segreteria. 
 */
public class Segreteria {
  /**
  * Costruttore che inizializza le variabili globali come elementi vuoti.
  */
  public Segreteria() {
    this.username = "";
    this.email = "";
  }

  /**
  * Metodo che restituisce una username.
  * @return username tipo String, variabile contente il valore di una username
  */
  public String getUsername() {
    return username;
  }
  
  /**
  * Metodo che setta una username.
  * @param username tipo String, variabile contente il valore di una username
  */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
  * Metodo che restituisce un email.
  * @return email tipo String, variabile contente il valore di un email
  */
  public String getEmail() {
    return email;
  }
  
  /**
  * Metodo che setta un email.
  * @param email company String, variabile contente il valore di un email
  */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
  * Metodo che restituisce un psw.
  * @return psw tipo String, variabile contente il valore di un psw
  */
  public String getPsw() {
    return psw;
  }

  /**
  * Metodo che setta un psw.
  * @param psw company String, variabile contente il valore di un psw
  */
  public void setPsw(String psw) {
    this.psw = psw;
  }

  private String username;
  private String email;
  private String psw;
}
