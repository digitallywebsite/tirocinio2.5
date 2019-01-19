package gestionestudente;
 
import gestionestudente.control.UploadFile;
import junit.framework.TestCase;
import org.junit.Test;

public class IR_GSTU_1 extends TestCase {

  @Test
  public void testIR_GP_1_2() {

    UploadFile nomePdf = new UploadFile();
    String curriculum = "curriculum.docx";
    boolean actual = nomePdf.validateNomeFile(curriculum);
    
    assertEquals(false, actual);
  }
}
