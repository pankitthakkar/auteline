package test;

import main.java.Screen;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ScreenTest {
    Screen screenTest = new Screen();


    @Test
    public void T001_displayMessage_message(){
        screenTest.displayMessage("Assignment 2 - Phase 2");
    }

    @Test
    public void T002_displayMessageLine_message(){
        screenTest.displayMessageLine("Assignment 2 - Phase 2");
    }

    @Test
    public void T003_displayDollarAmount_DollarAmount(){
        screenTest.displayDollarAmount(150.00);
    }

    @Test
    public void T004_getMessage_message(){
        String expected = "Assignment 2 - Phase 2";

        String actual = screenTest.getMessage(expected);

        assertEquals(expected, actual);
    }

    @Test
    public void T005_displayDollarAmount_$(){
        double amount = 125.00;
        String expected = "$125.00";
        String actual = screenTest.getDollarAmount(amount);
        assertEquals(expected, actual);
    }
}
