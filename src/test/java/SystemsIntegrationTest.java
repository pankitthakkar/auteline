package java;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;

public class SystemsIntegrationTest {

    CashDispenser cashDispenser = new CashDispenser();
    DepositSlot depositSlot = new DepositSlot();
    BankDatabase bankDatabase = new BankDatabase();
    ATM atm;
    Keypad keypad = mock(Keypad.class);
    Screen screen = mock(Screen.class);

    @BeforeEach
    public final void setUpProject(){
        atm = new ATM(screen, keypad, cashDispenser, depositSlot, bankDatabase);
    }

    @Test
    public void SIT001_getBalance_happyTest() {
        when(keypad.getInput()).thenReturn(12345, 54321, 1, 4);

        atm.run();

        verify(screen, times(1)).displayDollarAmount(1000.0);
    }

    @Test
    public void SIT002_getBalance_sadTest_InvalidPin() {

        when(keypad.getInput()).thenReturn(12345, 12345, 4);
        atm.run();
        verify(screen, times(1)).displayMessageLine("[!] Invalid account number or PIN. Please try again.");
    }

    @Test
    public void SIT003_getBalance_sadTest_InvalidAccountNumber() {

        when(keypad.getInput()).thenReturn(54321, 54321, 4);
        atm.run();
        verify(screen, times(1)).displayMessageLine("[!] Invalid account number or PIN. Please try again.");
    }

    @Test
    public void SIT004_withdrawCash_happyTest() {
        when(keypad.getInput()).thenReturn(12345, 54321, 2, 2, 1);

        atm.run();

        verify(screen, times(1)).displayDollarAmount(960.00);
    }

    @Test
    public void SIT005_withdrawCash_sadTest() {
        when(keypad.getInput()).thenReturn(12345, 54321, 2, 5, 2, 5, 2, 5, 2, 5, 6, 4);

        atm.run();

        verify(screen, times(1)).displayMessageLine("[!] Insufficient funds in your account!\n" +
                "    Please choose a smaller amount.");
    }
}
