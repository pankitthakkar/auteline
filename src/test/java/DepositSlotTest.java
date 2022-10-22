package test.java;
import main.java.DepositSlot;
import org.junit.jupiter.api.Test;
import static org.testng.AssertJUnit.assertEquals;

public class DepositSlotTest {

    @Test
    public void T001_envelopeReceived_true_true(){
        DepositSlot depositSlot = new DepositSlot();
        boolean expected = true;
        boolean actual = depositSlot.isEnvelopeReceived();
        assertEquals(expected, actual);
    }
}