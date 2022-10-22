package test;
import main.java.BankDatabase;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;

class BankDatabaseTest {
    BankDatabase bankdatabase = new BankDatabase();
    @Test
    public void T001_validatePin_12345_54321_true(){
        int accountNumber = 12345;
        int userPin = 54321;
        boolean expected = true;

        boolean actual = bankdatabase.authenticateUser(accountNumber, userPin);
        assertEquals(expected, actual);
    }

    @Test
    public void T002_validatePin_12345_12345_false(){
        int accountNumber = 12345;
        int userPin = 12345;
        boolean expected = false;

        boolean actual = bankdatabase.authenticateUser(accountNumber, userPin);
        assertEquals(expected, actual);
    }

    @Test
    public void T003_validateAccountNumber_12345_54321_true(){
        int accountNumber = 12345;
        int userPin = 54321;
        boolean expected = true;

        boolean actual = bankdatabase.authenticateUser(accountNumber, userPin);
        assertEquals(expected, actual);
    }

    @Test
    public void T004_validateAccountNumber_54321_54321_false(){
        int accountNumber = 54321;
        int userPin = 12345;
        boolean expected = false;

        boolean actual = bankdatabase.authenticateUser(accountNumber, userPin);
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

    @Test
    public void T007_validateCredit_12345_120_1320(){
        int accountNumber = 12345;
        double bankBalance = bankdatabase.getTotalBalance(12345);
        double creditedAmount = 120.00;
        double expected = bankBalance + creditedAmount;

        bankdatabase.credit(accountNumber, creditedAmount);
        double actual = bankdatabase.getTotalBalance(12345);

        assertEquals(expected, actual);
    }

    @Test
    public void T008_validateDebit_12345_120_1180(){
        int accountNumber = 12345;
        double bankBalance = bankdatabase.getTotalBalance(12345);
        double debitedAmount = 120.00;
        double expected = bankBalance - debitedAmount;

        bankdatabase.debit(accountNumber, debitedAmount);
        double actual = bankdatabase.getTotalBalance(12345);

        assertEquals(expected, actual);
    }

}