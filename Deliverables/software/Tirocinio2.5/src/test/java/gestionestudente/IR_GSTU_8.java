package gestionestudente;
 
import gestionestudente.control.UploadDocument;
import junit.framework.TestCase;
import org.junit.Test;

public class IR_GSTU_8 extends TestCase {

  @Test
  public void testIR_GP_8_3() {

    UploadDocument precedente = new UploadDocument();
    String company = "NapoliInternet--/*";
    boolean actual = precedente.validateCompany(company);
    
    assertEquals(false, actual);
  }
  
  @Test
  public void testIR_GP_8_7() {

    UploadDocument precedente = new UploadDocument();
    String oresvolte = "150**-/";
    boolean actual = precedente.validateOreSvolte(oresvolte);
    
    assertEquals(false, actual);
  }
}
