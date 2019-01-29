package gestioneutente.model;

/**
 * Andamento è una classe con 14 metodi set e get, 
 * questa classe è utilizzata per modellare un'Andamento. 
 *
*/
public class Andamento {

  /**
    * Costruttore che inizializza le variabili globali come elementi vuoti.
   */
  public Andamento() {
    this.id = -1;
    this.dataT = "";
    this.orainizio = "";
    this.orafine = "";
    this.tirocinioId = -1;
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
    * Metodo che restituisce un dataT.
    * @return dataT tipo String, variabile contente il valore di un dataT
   */
  public String getDataT() {
    return dataT;
  }
  
  /**
   * Metodo che setta un dataT.
   * @param dataT tipo String, variabile contente il valore di un dataT
  */
  public void setDataT(String dataT) {
    this.dataT = dataT;
  }
 
  /**
    * Metodo che restituisce un ora_inizio.
    * @return ora_inizio tipo String, variabile contente il valore di un ora_inizio
   */
  public String getOra_inizio() {
    return orainizio;
  }

  /**
   * Metodo che setta un orainizio.
   * @param orainizio tipo String, 
   *     variabile contente il valore di un ora_inizio
   */
  public void setOra_inizio(String orainizio) {
    this.orainizio = orainizio;
  }

  /**
    * Metodo che restituisce un orafine.
    * @return orafine tipo String, 
    *     variabile contente il valore di un ora_fine
   */
  public String getOra_fine() {
    return orafine;
  }

  /**
    * Metodo che setta un orafine.
    * @param orafine tipo String, 
    *     variabile contente il valore di un ora_fine
   */
  public void setOra_fine(String orafine) {
    this.orafine = orafine;
  }

  /**
    * Metodo che restituisce un tirocinioID.
    * @return tirocinioID tipo Integer, variabile contente il valore di un tirocinioID
  */
  public int getTirocinioId() {
    return tirocinioId;
  }

  /**
   * Metodo che setta un tirocinioID.
   * @param tirocinioId tipo Integer, 
   *     variabile contente il valore di un tirocinioID
  */
  public void setTirocinioId(int tirocinioId) {
    this.tirocinioId = tirocinioId;
  }

  /**
   * Metodo che restituisce un NomeCognome.
   * @return NomeCognome tipo Integer, variabile contente il valore di un NomeCognome
  */
  public String getNomeCognome() {
    return nomeCognome;
  }

  /**
   * Metodo che setta un NomeCognome.
   * @param nomeCognome tipo String, variabile contente il valore di un Nome Cognome
  */
  public void setNomeCognome(String nomeCognome) {
    this.nomeCognome = nomeCognome;
  }

  /**
   * Metodo che restituisce un NomeCognomeStudent.
   * @return NomeCognomeStudent tipo Integer, variabile contente il valore di un NomeCognomeStudent
  */
  public String getNomeCognomeStudent() {
    return nomeCognomeStudent;
  }
  
  /**
   * Metodo che setta un NomeCognomeStudent.
   * @param nomeCognomeStudent tipo Integer, variabile contente il valore di un NomeCognomeStudent
  */
  public void setNomeCognomeStudent(String nomeCognomeStudent) {
    this.nomeCognomeStudent = nomeCognomeStudent;
  }

  private int id;
  private String dataT;
  private String orainizio;
  private String orafine;
  private int  tirocinioId;
  private String nomeCognome;
  private String nomeCognomeStudent;

}
