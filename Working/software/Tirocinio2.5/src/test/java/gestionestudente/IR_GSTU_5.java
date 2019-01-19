package gestionestudente;
 
import gestionestudente.control.InternalExternal;
import junit.framework.TestCase;
import org.junit.Test;

public class IR_GSTU_5 extends TestCase {

  @Test
  public void testIR_GP_5_2() {

    InternalExternal internal = new InternalExternal();
    String email = "ARossi@gmail.com";
    boolean actual = internal.isTeacher(email);
    
    assertEquals(false, actual);
  }
}
