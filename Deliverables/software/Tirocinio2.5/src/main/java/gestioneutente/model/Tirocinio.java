package gestioneutente.model;

/**
 * Tirocinio è una classe con 6 metodi set e get, 
 * questa classe è utilizzata per modellare una Tirocinio. 
 *
 */
public class Tirocinio {

  /**
   * Costruttore che inizializza le variabili globali come elementi vuoti.
   */
  public Tirocinio() {
    this.id = -1;
    this.stato = "";
    this.tipo = "";
    this.matricolastudente = "";
    this.segreteriausername = "";
    this.tutorusername = "";
  }

  /**
   * Metodo che restituisce un id.
   * @return id tipo Integer, variabile contente il valore di un id
   */
  public int getId() {
    return id;
  }

  /**
   * Metodo che setta un id.
   * @param id tipo Integer, variabile contente il valore di un id
  */
  public void setId(int id) {
    this.id = id;
  }

  /**
  * Metodo che restituisce un stato.
  * @return stato tipo String, variabile contente il valore di un stato
 */
  public String getStato() {
    return stato;
  }

  /**
   * Metodo che setta un stato.
   * @param stato tipo String, variabile contente il valore di un stato
  */
  public void setStato(String stato) {
    this.stato = stato;
  }

  /**
   * Metodo che restituisce un tipo.
   * @return tipo tipo String, variabile contente il valore di un tipo
  */
  public String getTipo() {
    return tipo;
  }
  
  /**
   * Metodo che setta un tipo.
   * @param tipo tipo String, variabile contente il valore di un tipo
  */ 
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  /**
   * Metodo che restituisce un matricola_studente.
   * @return matricola_studente tipo String, variabile contente il valore di un matricola_studente
  */
  public String getMatricola_studente() {
    return matricolastudente;
  }

  /**
   * Metodo che setta un matricola_studente.
   * @param matricolastudente tipo String, variabile contente il valore di un matricola_studente
  */
  public void setMatricola_studente(String matricolastudente) {
    this.matricolastudente = matricolastudente;
  }

  /**
   * Metodo che restituisce un segreteria_username.
   * @return segreteria_username tipo String, variabile contente il valore di un segreteria_username
  */
  public String getSegreteria_username() {
    return segreteriausername;
  }

  /**
   * Metodo che setta un segreteria_username.
   * @param segreteriausername tipo String, variabile contente il valore di un segreteria_username
  */
  public void setSegreteria_username(String segreteriausername) {
    this.segreteriausername = segreteriausername;
  }

  /**
   * Metodo che restituisce un tutor_username.
   * @return tutor_username tipo String, variabile contente il valore di un tutor_username
  */
  public String getTutor_username() {
    return tutorusername;
  }

  /**
    * Metodo che setta un tutor_username.
    * @param tutorusername tipo String, variabile contente il valore di un tutor_username
   */
  public void setTutor_username(String tutorusername) {
    this.tutorusername = tutorusername;
  }

  /**
   * Metodo che restituisce un NomeCognome.
   * @return NomeCognome tipo String, variabile contente il valore di un NomeCognome
  */
  public String getNomeCognome() {
    return nomeCognome;
  }
  
  /**
   * Metodo che setta un NomeCognome.
   * @param nomeCognome tipo String, variabile contente il valore di un nomeCognome
  */
  public void setNomeCognome(String nomeCognome) {
    this.nomeCognome = nomeCognome;
  }

  /**
   * Metodo che restituisce un TipoTutorProfessore.
   * @return TipoTutorProfessore tipo String, variabile contente il valore di un TipoTutorProfessore
  */
  public String getTipoTutorProfessore() {
    return tipotutorProfessore;
  }
  
  /**
   * Metodo che setta un TipoTutorProfessore.
   * @param tipoTutorProfessore tipo String, variabile contente il valore di un tipoTutorProfessore
  */
  public void setTipoTutorProfessore(String tipoTutorProfessore) {
    this.tipotutorProfessore = tipoTutorProfessore;
  }

  /**
   * Metodo che restituisce un student_usename.
   * @return studentusename tipo String, variabile contente il valore di un student_usename
  */
  public String getStudent_usename() {
    return studentusename;
  }
  
  /**
   * Metodo che setta un student_usename.
   * @param studentusename tipo String, variabile contente il valore di un student_usename
  */
  public void setStudent_usename(String studentusename) {
    this.studentusename = studentusename;
  }

  /**
   * Metodo che restituisce un student_email.
   * @return student_email tipo String, variabile contente il valore di un student_email
  */
  public String getStudent_email() {
    return studentemail;
  }
  
  /**
   * Metodo che setta un student_email.
   * @param studentemail tipo String, variabile contente il valore di un student_email
  */
  public void setStudent_email(String studentemail) {
    this.studentemail = studentemail;
  }

  /**
   * Metodo che restituisce un NomeCognomeStudent.
   * @return NomeCognomeStudent tipo String, variabile contente il valore di un NomeCognomeStudent
  */
  public String getNomeCognomeStudent() {
    return nomecognomeStudent;
  }
  
  /**
   * Metodo che setta un NomeCognomeStudent.
   * @param nomeCognomeStudent tipo String, variabile contente il valore di un nomeCognomeStudent
  */
  public void setNomeCognomeStudent(String nomeCognomeStudent) {
    this.nomecognomeStudent = nomeCognomeStudent;
  }

  /**
   * Metodo che restituisce un company.
   * @return company tipo String, variabile contente il valore di una company
  */
  public String getCompany() {
    return company;
  }

  /**
   * Metodo che setta un company.
   * @param company tipo String, variabile contente il valore di una company
  */
  public void setCompany(String company) {
    this.company = company;
  }

  /**
   * Metodo che restituisce un oreSvolte.
   * @return oreSvolte tipo String, variabile contente il valore di un oreSvolte
  */
  public String getOreSvolte() {
    return oreSvolte;
  }
  
  /**
   * Metodo che setta un oreSvolte.
   * @param oreSvolte tipo String, variabile contente il valore di un oreSvolte
  */
  public void setOreSvolte(String oreSvolte) {
    this.oreSvolte = oreSvolte;
  }

  /**
   * Metodo che restituisce un compitiSvolti.
   * @return compitiSvolti tipo String, variabile contente il valore di un compitiSvolti
  */
  public String getCompitiSvolti() {
    return compitiSvolti;
  }

  /**
   * Metodo che setta un compitiSvolti.
   * @param compitiSvolti tipo String, variabile contente il valore di un compitiSvolti
  */
  public void setCompitiSvolti(String compitiSvolti) {
    this.compitiSvolti = compitiSvolti;
  }

  /**
   * Metodo che restituisce un compitiSvolti.
   * @return compitiSvolti tipo String, variabile contente il valore di un compitiSvolti
  */
  public String getDocumentLink() {
    return documentLink;
  }

  /**
   * Metodo che setta un documentLink.
   * @param documentLink tipo String, variabile contente il valore di un documentLink
  */
  public void setDocumentLink(String documentLink) {
    this.documentLink = documentLink;
  }

  private int id;
  private String stato;
  private String tipo;
  private String matricolastudente;
  private String segreteriausername;
  private String tutorusername;
  private String nomeCognome;
  private String tipotutorProfessore;
  private String nomecognomeStudent;
  private String studentusename;
  private String studentemail;
  private String company;
  private String oreSvolte;
  private String compitiSvolti;
  private String documentLink;

}
