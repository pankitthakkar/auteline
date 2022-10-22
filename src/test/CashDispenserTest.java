package test;
import main.java.CashDispenser;
import org.junit.jupiter.api.Test;
import static org.testng.AssertJUnit.assertEquals;

public class CashDispenserTest {

    CashDispenser cashDispenser = new CashDispenser();

    @Test
    public void T001_dispenseCash_150_true(){
        int amount = 150;
        cashDispenser.dispenseCash(amount);
        boolean expected = true;
        boolean actual = true;

        assertEquals(expected, actual);
    }

    @Test
    public void T002_sufficientCashValidation_150_true(){
        int amount = 150;
        boolean expected = true;
        boolean actual = cashDispenser.isSufficientCashAvailable(amount);

        assertEquals(expected, actual);
    }

    @Test
    public void T002_sufficientCashValidation_1500000000_false(){
        int amount = 1500000000;
        boolean expected = false;
        boolean actual = cashDispenser.isSufficientCashAvailable(amount);

        assertEquals(expected, actual);
    }

}
