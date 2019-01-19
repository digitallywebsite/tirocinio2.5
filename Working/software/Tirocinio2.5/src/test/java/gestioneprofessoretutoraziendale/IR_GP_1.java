package gestioneprofessoretutoraziendale;

import gestioneprofessoretutoraziendale.control.ProfileControl;
import junit.framework.TestCase;
import org.junit.Test;



public class IR_GP_1 extends TestCase {

  @Test
  public void testIR_GP_1_3() {

    ProfileControl profile = new ProfileControl();
    String email = "A.Rossi5gmail.com";
    boolean actual = profile.validateEmail(email);

    assertEquals(false, actual);
  }
  
  @Test
  public void testIR_GP_1_6() {

    String cognome = "Rossijdkfpfpoejekfgpgjdmflepwkenflwwpfcfkmwdnfjnerkjcfnerncff";
    int m = cognome.length();
    
    assertEquals(61, m);
  }

  @Test
  public void testIR_GP_1_17() {

    ProfileControl profile = new ProfileControl();
    String luogo = "Via roma, 56 -- -----/*";
    boolean actual = profile.validateLuogo(luogo);

    assertEquals(false, actual);
  }
}
