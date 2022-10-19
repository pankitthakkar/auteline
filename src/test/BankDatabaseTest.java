package test;
import main.java.BankDatabase;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

class BankDatabaseTest {
    BankDatabase bankdatabase = new BankDatabase();
    @Test
    public void T001_validatePin_12345_54321_true(){
        int account = 12345;
        int pin = 54321;
        boolean expected = true;

        boolean actual = bankdatabase.authenticateUser(account, pin);
        assertEquals(expected, actual);
    }

    @Test
    public void T002_validatePin_12345_12345_false(){
        int account = 12345;
        int pin = 12345;
        boolean expected = false;

        boolean actual = bankdatabase.authenticateUser(account, pin);
        assertEquals(expected, actual);
    }

    @Test
    public void T003_validateAccountNumber_12345_54321_true(){
        int account = 12345;
        int pin = 54321;
        boolean expected = true;

        boolean actual = bankdatabase.authenticateUser(account, pin);
        assertEquals(expected, actual);
    }

    @Test
    public void T004_validateAccountNumber_54321_54321_false(){
        int account = 54321;
        int pin = 12345;
        boolean expected = false;

        boolean actual = bankdatabase.authenticateUser(account, pin);
        assertEquals(expected, actual);
    }

    @Test
    public void T005_validateAvailableBalance_12345_1000_1000(){
        double expected = 1000.00;
        double actual = bankdatabase.getAvailableBalance(12345);
        assertEquals(expected, actual);
    }

    @Test
    public void T006_validateTotalBalance_12345_1200_1200(){
        double expected = 1200.00;
        double actual = bankdatabase.getTotalBalance(12345);
        assertEquals(expected, actual);
    }

    /** TO-DO: Write tests for Credit and Debit method **/

}