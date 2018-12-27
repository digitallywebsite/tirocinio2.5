package gestioneprofessoretutoraziendale.model;

/**
 * ProfessoreTutorAziendale è una classe con 28 metodi set e get,
 *  questa classe è utilizzata per modellare un Professore/ Tutor Aziendale. 
 *
 */
public class ProfessoreTutorAziendale {

  /**
  * Costruttore che inizializza le variabili globali come elementi vuoti.
  */
  public ProfessoreTutorAziendale() {
    this.username = "";
    this.nome = "";
    this.cognome = "";
    this.tipo = "";
    this.company = "";
    this.indirizzo = "";
    this.telefono = "";
    this.fax = "";
    this.email = "";
    this.citta = "";
    this.sitoweb = "";
    this.chisono = "";
    this.immagineProfilo = "";
  }

  /**
  * Metodo che restituisce una username.
  * @return username tipo String, variabile contente il valore di una username
  */
  public String getUsername() {
    return username;
  }
  
  /**
  * Metodo che setta una username.s
  * @param username tipo String, variabile contente il valore di una username
  */  
  public void setUsername(String username) {
    this.username = username;
  }

  /**
  * Metodo che restituisce un nome.
  * @return nome tipo String, variabile contente il valore di un nome
  */
  public String getNome() {
    return nome;
  }

  /**
  * Metodo che setta un nome.
  * @param nome tipo String, variabile contente il valore di un nome
  */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
  * Metodo che restituisce un cognome.
  * @return cognome tipo String, variabile contente il valore di un cognome
  */
  public String getCognome() {
    return cognome;
  }

  /**
  * Metodo che setta un cognome.
  * @param cognome tipo String, variabile contente il valore di un cognome
  */
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
  * Metodo che restituisce un tipo.
  * @return tipo "tipo" String, variabile contente il valore di un tipo
  */
  public String getTipo() {
    return tipo;
  }

  /**
  * Metodo che setta un tipo.
  * @param tipo "tipo" String, variabile contente il valore di un tipo
  */
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  /**
  * Metodo che restituisce un company.
  * @return company tipo String, variabile contente il valore di un company
  */
  public String getCompany() {
    return company;
  }

  /**
  * Metodo che setta un company.
  * @param company tipo String, variabile contente il valore di un company
  */
  public void setCompany(String company) {
    this.company = company;
  }

  /**
  * Metodo che restituisce un indirizzo.
  * @return indirizzo tipo String, variabile contente il valore di un indirizzo
  */
  public String getIndirizzo() {
    return indirizzo;
  }
 
  /**
  * Metodo che setta un indirizzo.
  * @param indirizzo company String, variabile contente il valore di un indirizzo
  */
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
  * Metodo che restituisce un telefono.
  * @return telefono tipo String, variabile contente il valore di un telefono
  */
  public String getTelefono() {
    return telefono;
  }
 
  /**
  * Metodo che setta un telefono.
  * @param telefono company String, variabile contente il valore di un telefono
  */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
  * Metodo che restituisce un fax.
  * @return fax tipo String, variabile contente il valore di un fax
  */
  public String getFax() {
    return fax;
  }

  /**
  * Metodo che setta un fax.
  * @param fax company String, variabile contente il valore di un fax
  */
  public void setFax(String fax) {
    this.fax = fax;
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

  /**
  * Metodo che restituisce un citta.
  * @return citta tipo String, variabile contente il valore di un citta
  */
  public String getCitta() {
    return citta;
  }
 
  /**
  * Metodo che setta un citta.
  * @param citta company String, variabile contente il valore di un citta
  */
  public void setCitta(String citta) {
    this.citta = citta;
  }

  /**
  * Metodo che restituisce un sitoweb.
  * @return sitoweb tipo String, variabile contente il valore di un sitoweb
  */
  public String getSitoweb() {
    return sitoweb;
  }

  /**
  * Metodo che setta un sitoweb.
  * @param sitoweb company String, variabile contente il valore di un sitoweb
  */
  public void setSitoweb(String sitoweb) {
    this.sitoweb = sitoweb;
  }

  /**
  * Metodo che restituisce un chisono.
  * @return chisono tipo String, variabile contente il valore di un chisono
  */
  public String getChisono() {
    return chisono;
  }

  /**
  * Metodo che setta un chisono.
  * @param chisono company String, variabile contente il valore di un chisono
  */
  public void setChisono(String chisono) {
    this.chisono = chisono;
  }

  /**
  * Metodo che restituisce un immagine_profilo.
  * @return immagine_profilo tipo String, variabile contente il valore di un immagine_profilo
  */
  public String getImmagine_profilo() {
    return immagineProfilo;
  }

  /**
  * Metodo che setta un immagine_profilo.
  * @param immagineProfilo company String, variabile contente il valore di un immagine_profilo
  */
  public void setImmagine_profilo(String immagineProfilo) {
    this.immagineProfilo = immagineProfilo;
  }

  private String username;
  private String nome;
  private String cognome;
  private String tipo;
  private String company;
  private String indirizzo;
  private String telefono;
  private String fax;
  private String email;
  private String psw;
  private String citta;
  private String sitoweb;
  private String chisono;
  private String immagineProfilo;
}
