package gestioneutente.model;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * Classe che cripta la password.
 * */
public class Md5 {

  /**
   * Cripta la password ricevuta.
   * 
   * @param stringa variabile di tipo String
   * @param algoritmo variabile di tipo String
   * @return str variabile di tipo StringBuilder, che contine la password criptata.
  */
  public static String hashCode(String stringa, String algoritmo) {

    try {
      MessageDigest md = MessageDigest.getInstance(algoritmo);
      if (Charset.isSupported("CP1252")) {
   
        md.update(stringa.getBytes(Charset.forName("CP1252")));
      } else {
        md.update(stringa.getBytes(Charset.forName("ISO-8859-1")));
      }

      byte[]bytes = md.digest();
      StringBuilder str = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        str.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
      }
      return str.toString();
    } catch (Exception e) {
      return "Errore: " + e.getMessage();
    }
  }
}


