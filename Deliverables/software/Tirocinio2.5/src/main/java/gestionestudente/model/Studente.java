package gestionestudente.model;

/**
 * Studente è una classe con 16 metodi set e get, 
 * questa classe è utilizzata per modellare uno Studente. 
 */
public class Studente {

  /**
  * Costruttore che inizializza le variabili globali come elementi vuoti.
  */
  public Studente() {
    this.matricola = "";
    this.nome = "";
    this.cognome = "";
    this.email = "";
    this.username = "";
    this.linkedin = "";
    this.linkcurriculum = "";
  }

  /**
  * Metodo che restituisce una matricola.
  * @return matricola tipo String, variabile contente il valore di una matricola
  */
  public String getMatricola() {
    return matricola;
  }
  
  /**
  * Metodo che setta una matricola.
  * @param matricola tipo String, variabile contente il valore di una matricola
  */
  public void setMatricola(String matricola) {
    this.matricola = matricola;
  }

  /**
   * Metodo che restituisce una email.
   * @return email tipo String, variabile contente il valore di una email
  */
  public String getEmail() {
    return email;
  }
  
  /**
   * Metodo che setta una email.
   * @param email tipo String, variabile contente il valore di una email
  */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Metodo che restituisce una nome.
   * @return nome tipo String, variabile contente il valore di una nome
  */
  public String getNome() {
    return nome;
  }
  
  /**
   * Metodo che setta una nome.
   * @param nome tipo String, variabile contente il valore di una nome
  */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo che restituisce una cognome.
   * @return cognome tipo String, variabile contente il valore di una cognome
  */
  public String getCognome() {
    return cognome;
  }
  
  /**
   * Metodo che setta una cognome.
   * @param cognome tipo String, variabile contente il valore di una cognome
  */
  public void setCognome(String cognome) {
    this.cognome = cognome;
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
   * Metodo che restituisce una psw.
   * @return psw tipo String, variabile contente il valore di una psw
  */
  public String getPsw() {
    return psw;
  }
  
  /**
   * Metodo che setta una psw.
   * @param psw tipo String, variabile contente il valore di una psw
  */
  public void setPsw(String psw) {
    this.psw = psw;
  }

  /**
   * Metodo che restituisce una linkedin.
   * @return linkedin tipo String, variabile contente il valore di una linkedin
  */
  public String getLinkedin() {
    return linkedin;
  }

  /**
   * Metodo che setta una linkedin.
   * @param linkedin tipo String, variabile contente il valore di una linkedin
  */
  public void setLinkedin(String linkedin) {
    this.linkedin = linkedin;
  }

  /**
   * Metodo che restituisce una link_curriculum.
   * @return link_curriculum tipo String, variabile contente il valore di una link_curriculum
  */
  public String getLink_curriculum() {
    return linkcurriculum;
  }

  /**
   * Metodo che setta una link_curriculum.
   * @param linkCurriculum tipo String, variabile contente il valore di una link_curriculum
  */
  public void setLink_curriculum(String linkCurriculum) {
    this.linkcurriculum = linkCurriculum;
  }

  private String matricola;
  private String nome;
  private String cognome;
  private String email;
  private String username;
  private String psw;
  private String linkedin;
  private String linkcurriculum;
}
