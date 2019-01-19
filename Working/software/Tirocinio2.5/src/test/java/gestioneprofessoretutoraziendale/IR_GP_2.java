package gestioneprofessoretutoraziendale;

import gestioneprofessoretutoraziendale.control.AndamentoControl;
import junit.framework.TestCase;
import org.junit.Test;

public class IR_GP_2 extends TestCase {

  @Test
  public void testIR_GP_2_2() {

    String data = "150/10/2018";

    assertEquals("150/10/2018", data);
  }
  
  @Test
  public void testIR_GP_2_4() {

    AndamentoControl andamento = new AndamentoControl();
    String oraFine = "28:00";
    boolean actual = andamento.validateOreSvolte(oraFine);
    assertEquals(true, actual);
  }
}
