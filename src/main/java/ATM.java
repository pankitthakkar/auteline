package main.java;

/**
 * SMTI06, 54411850, M Haidar Hanif Task Five: Automated Teller Machine Auteline | Simple ATM
 * simulator with basic features
 */

// ATM.java
// Represents an automated teller machine

public class ATM {

    private boolean userAuthenticated; // whether user is authenticated
    private int currentAccountNumber; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser
    private DepositSlot depositSlot; // ATM's deposit slot
    private BankDatabase bankDatabase; // account information database

    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;
    private static final int PANKIT = 5;
    private static final int FAZEEL = 6;
    private static final int DHYEY = 7;
    private static final int ALI = 8;


    // no-argument ATM constructor initializes instance variables
    public ATM() {
        userAuthenticated = false; // user is not authenticated to start
        currentAccountNumber = 0; // no current account number to start
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad
        cashDispenser = new CashDispenser(); // create cash dispenser
        depositSlot = new DepositSlot(); // create deposit slot
        bankDatabase = new BankDatabase(); // create acct info database
    }

    // start ATM
    public void run() {
        // welcome and authenticate user; perform transactions
        // loop while user is not yet authenticated
        while (!userAuthenticated) {
            screen.displayMessageLine("\n[i] Welcome to Auteline Bank ATM!");
            authenticateUser();
        }
        performTransactions(); // user is now authenticated
        userAuthenticated = false; // reset before next ATM session
        currentAccountNumber = 0; // reset before next ATM session
        screen.displayMessageLine("\n[i] Thank you for banking with Auteline Bank!");

    }

    // attempts to authenticate user against database
    private void authenticateUser() {
        screen.displayMessage("\n[?] Please enter your account number: ");
        int accountNumber = keypad.getInput(); // input account number
        screen.displayMessage("\n[?] Enter your PIN: "); // prompt for PIN
        int pin = keypad.getInput(); // input PIN

        // set userAuthenticated to boolean value returned by database
        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
        // check whether authentication succeeded
        if (userAuthenticated) {
            currentAccountNumber = accountNumber;
        } else {
            screen.displayMessageLine("[!] Invalid account number or PIN. Please try again.");
        }
    }

    // display the main menu and perform transactions
    private void performTransactions() {

        // local variable to store transaction currently being processed
        Transaction currentTransaction;
        boolean userExited = false; // user has not chosen to exit

        // loop while user has not chosen option to exit system
        while (!userExited) {
            // show main menu and get user selection
            int mainMenuSelection = displayMainMenu();
            // decide how to proceed based on user's menu selection
            switch (mainMenuSelection) {
                // user chose to perform one of three transaction types
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT: // initialize as new object of chosen type
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute(); // execute transaction
                    break;
                case PANKIT:
                    screen.displayMessageLine("\nPav Bhaji");
                    break;
		case FAZEEL:
                    screen.displayMessageLine("\nSushi");
		            break;
                case DHYEY:
			        screen.displayMessageLine("\nPizza");
			        break;
                case ALI:
			        screen.displayMessageLine("\nBurgers");
			        break;
                case EXIT: // user chose to terminate session
                    screen.displayMessageLine("\n[~] Exiting the system...");
                    userExited = true; // this ATM session should end
                    break;
                default: // user did not enter an integer from 1-4
                    screen.displayMessageLine("\n[!] You did not enter a valid selection! Please try again.");
                    break;
            }
        }
    }

    // display the main menu and return an input selection
    private int displayMainMenu() {
        screen.displayMessageLine("\n[Main menu]");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit");
        screen.displayMessageLine("5 - Pankit's favourite lunch");
        screen.displayMessageLine("6 - Fazeel's favorite lunch\n");
        screen.displayMessageLine("7 - Dhyey's favorite lunch\n");
        screen.displayMessageLine("8 - Ali's favorite lunch\n");
        screen.displayMessage("[?] Enter a choice: ");
        return keypad.getInput(); // return user's selection
    }

    // return object of specified Transaction subclass
    private Transaction createTransaction(int type) {
        Transaction temp = null; // temporary Transaction variable
        // determine which type of Transaction to create
        switch (type) {
            case BALANCE_INQUIRY: // create new BalanceInquiry transaction
                temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
                break;
            case WITHDRAWAL: // create new Withdrawal transaction
                temp = new Withdrawal(currentAccountNumber, screen,
                        bankDatabase, keypad, cashDispenser);
                break;
            case DEPOSIT: // create new Deposit transaction
                temp = new Deposit(currentAccountNumber, screen,
                        bankDatabase, keypad, depositSlot);
                break;
        }
        return temp; // return the newly created object
    }

}