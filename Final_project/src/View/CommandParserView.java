package View;

import Controller.AccountTransactionLinkedList;
import Controller.UserAccountMapping;
import Model.Account;
import Model.CheckingAccount;
import Model.Date;
import Model.IndividualUser;
import Model.SavingsAccount;
import Model.Transaction;
import Model.TransactionStatus;
import Model.TransactionType;
import java.util.Scanner;


/**
 * Provides a console-based interface for users to manage accounts and perform transactions
 */
public class CommandParserView {
  private Scanner scanner = new Scanner(System.in);
  private UserAccountMapping userAccountMapping = new UserAccountMapping();
  private AccountTransactionLinkedList transactionLinkedList = new AccountTransactionLinkedList();


  public void parseCommand(String[] args) {
    boolean running = true;
    while (running) {
      System.out.println("Welcome to the Account Management System");
      System.out.println("1. Create User and Account");
      System.out.println("2. Add Account");
      System.out.println("3. Remove Account");
      System.out.println("4. Print Account Balances");

      System.out.println("5. Make a Deposit");
      System.out.println("6. Make a Withdrawal");
      System.out.println("7. Transfer Funds");
      System.out.println("8. Add Transaction");
      System.out.println("9. Remove Transaction");

      System.out.println("10. Update Transaction Description");
      System.out.println("11. Map Pending Transactions to Completed");
      System.out.println("12. Remove All Transactions");

      System.out.println("13. List Account Transactions");
      System.out.println("14. Filter and Display Income Transactions");
      System.out.println("15. Filter and Display Expense Transactions");
      System.out.println("16. Filter and Display Transfer Transactions");
      System.out.println("17. Filter Transactions by Minimum Amount");
      System.out.println("18. Count Total Transactions for an Account");
      System.out.println("19. Print User Details");


      System.out.println("20. Exit");
      System.out.print("Enter your choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); //clear the buffer

      //Display menu items
      //Read the user's choice and process it
      switch (choice) {
        case 1:
          createUserAndAccount();
          break;
        case 2:
          addAccount();
          break;
        case 3:
          removeAccount();
          break;
        case 4:
          printBalances();
          break;
        case 5:
          handleDeposit();
          break;
        case 6:
          handleWithdrawal();
          break;
        case 7:
          handleTransfer();
          break;
        case 8:
          addTransaction();
          break;
        case 9:
          removeTransaction();
          break;
        case 10:
          updateTransactionDescription();
          break;
        case 11:
          mapPendingTransactionsToCompleted();
          break;
        case 12:
          removeAllTransactions();
          break;
        case 13:
          listAccountTransactions();
          break;
        case 14:
          filterAndDisplayTransactionsByType(TransactionType.INCOME);
          break;
        case 15:
          filterAndDisplayTransactionsByType(TransactionType.EXPENSE);
          break;
        case 16:
          filterAndDisplayTransferTransactions();
          break;
        case 17:
          filterTransactionsByAmount();
          break;
        case 18:
          countTotalTransactions();
          break;
        case 19:
          printIndividualUserInfo();
          break;
        case 20:
          System.out.println("Exiting...");
          running = false;
          break;
        default:
          System.out.println("Invalid option, please try again.");
      }
    }
    scanner.close();
  }

  /**
   * Handles the creation of a new user and their first account
   * Prompts the user for necessary details to set up an individual user and an account
   */
  private void createUserAndAccount() {
    System.out.print("Enter User ID: ");
    int userId = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Enter Name: ");
    String name = scanner.nextLine();
    System.out.print("Enter Address: ");
    String address = scanner.nextLine();
    System.out.print("Enter Email: ");
    String email = scanner.nextLine();
    IndividualUser user = new IndividualUser(userId, name, address, email);

    //Save individual user information
    this.userAccountMapping.saveIndividualUserInfo(user);

    System.out.print("Enter Account Type (Checking/Savings): ");
    String type = scanner.nextLine();
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();
    System.out.print("Enter Initial Balance: ");
    double initialBalance = scanner.nextDouble();
    Account account;
    if ("Checking".equalsIgnoreCase(type)) {
      account = new CheckingAccount(accountNumber, initialBalance);
    } else {
      System.out.print("Enter Interest Rate: ");
      double interestRate = scanner.nextDouble();
      account = new SavingsAccount(accountNumber, initialBalance, interestRate);
    }
    //Save current user account information
    userAccountMapping.addAccount(userId, account);
  }

  /**
   * Adds a new account for an existing user by prompting for user ID and account details
   * If the user exists, a new account is added based on the specified type
   */
  private void addAccount() {
    System.out.print("Enter User ID (to add account): ");
    int userId = scanner.nextInt();
    scanner.nextLine();  //Clear buffer

    if (!userAccountMapping.userExists(userId)) {
      System.out.println("User does not exist. Please create the user first.");
      return;
    }

    System.out.print("Enter Account Type (Checking/Savings): ");
    String type = scanner.nextLine();
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();
    System.out.print("Enter Initial Balance: ");
    double initialBalance = scanner.nextDouble();

    Account account;
    if ("Checking".equalsIgnoreCase(type)) {
      account = new CheckingAccount(accountNumber, initialBalance);
    } else {
      System.out.print("Enter Interest Rate: ");
      double interestRate = scanner.nextDouble();
      account = new SavingsAccount(accountNumber, initialBalance, interestRate);
    }

    userAccountMapping.addAccount(userId, account);
    System.out.println("New account added successfully for User ID " + userId);
  }


  private int transactionID = 0;

  /**
   * Generates a transaction ID incrementally
   * @return The next available transaction ID
   */
  private int generateTransactionID() {
    return ++transactionID;
  }

  /**
   * Prompts the user for date input and returns a new Date object based on the input
   * @return A Date object representing the user-specified year, month, and day
   */
  private Date inputDate() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Year: ");
    int year = scanner.nextInt();
    System.out.print("Enter Month (1-12): ");
    int month = scanner.nextInt();
    System.out.print("Enter Day (1-31): ");
    int day = scanner.nextInt();
    return new Date(month, day, year);
  }

  /**
   * Handles deposit by prompting for the account number and the deposit amount
   * If the specified account is found, the deposit is made, and the transaction is recorded
   */
  private void handleDeposit() {
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();
    System.out.print("Enter Deposit Amount: ");
    double amount = scanner.nextDouble();

    Account account = userAccountMapping.getAccountByNumber(accountNumber);
    if (account != null) {
      Date transactionDate = inputDate(); //Get date from user input
      account.deposit(amount);
      System.out.println("Deposit successful. New balance: $" + account.getBalance());

      Transaction depositTransaction = new Transaction(
          generateTransactionID(),
          amount,
          "Deposit into account",
          transactionDate,
          TransactionType.INCOME,
          TransactionStatus.COMPLETED
      );
      account.getTransactionList().addTransaction(depositTransaction);
    } else {
      System.out.println("Account not found.");
    }
  }

  /**
   * Handles withdrawal by prompting for the account number and withdrawal amount
   * If the account is found and has sufficient funds, the withdrawal is executed, and the transaction is recorded
   */
  private void handleWithdrawal() {
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();
    System.out.print("Enter Withdrawal Amount: ");
    double amount = scanner.nextDouble();

    Account account = userAccountMapping.getAccountByNumber(accountNumber);
    if (account != null) {
      try {
        Date transactionDate = inputDate(); //Get date from user input
        account.withdraw(amount);
        System.out.println("Withdrawal successful. New balance: $" + account.getBalance());

        //Create and add the withdrawal transaction
        Transaction withdrawalTransaction = new Transaction(
            generateTransactionID(),
            amount,
            "Withdrawal from account",
            transactionDate,
            TransactionType.EXPENSE,
            TransactionStatus.COMPLETED
        );
        account.getTransactionList().addTransaction(withdrawalTransaction);
      } catch (IllegalArgumentException e) {
        System.out.println("Withdrawal failed: " + e.getMessage());
      }
    } else {
      System.out.println("Account not found.");
    }
  }

  /**
   * Handles the transfer of funds between two accounts
   * The user is prompted for the account numbers and the transfer amount. If both accounts are found, the transfer is made
   */
  private void handleTransfer() {
    System.out.print("Enter From Account Number: ");
    int fromAccountNumber = scanner.nextInt();
    System.out.print("Enter To Account Number: ");
    int toAccountNumber = scanner.nextInt();
    System.out.print("Enter Transfer Amount: ");
    double amount = scanner.nextDouble();

    Account fromAccount = userAccountMapping.getAccountByNumber(fromAccountNumber);
    Account toAccount = userAccountMapping.getAccountByNumber(toAccountNumber);
    if (fromAccount != null && toAccount != null) {
      try {
        Date transactionDate = inputDate(); //Get date from user input
        fromAccount.transfer(toAccount, amount);
        System.out.println(
            "Transfer successful. New balances: From Account: $" + fromAccount.getBalance()
                + ", To Account: $" + toAccount.getBalance());

        //Create and add the transfer transactions
        Transaction debitTransaction = new Transaction(
            generateTransactionID(),
            amount,
            "Transfer to account " + toAccountNumber,
            transactionDate,
            TransactionType.EXPENSE,
            TransactionStatus.COMPLETED
        );
        fromAccount.getTransactionList().addTransaction(debitTransaction);

        Transaction creditTransaction = new Transaction(
            generateTransactionID(),
            amount,
            "Transfer from account " + fromAccountNumber,
            transactionDate,
            TransactionType.INCOME,
            TransactionStatus.COMPLETED
        );
        toAccount.getTransactionList().addTransaction(creditTransaction);

      } catch (IllegalArgumentException e) {
        System.out.println("Transfer failed: " + e.getMessage());
      }
    } else {
      System.out.println("One or both accounts not found.");
    }
  }


  /**
   * Prints all accounts managed by the UserAccountMapping along with their current balances
   */
  private void printBalances() {
    System.out.println("Accounts and Balances:");
    userAccountMapping.printAccounts();
  }

  /**
   * Removes a specified account for a user by prompting for the user and account IDs
   */
  private void removeAccount() {
    System.out.print("Enter User ID: ");
    int userID = scanner.nextInt();
    System.out.print("Enter Account ID: ");
    int accountID = scanner.nextInt();
    userAccountMapping.removeAccount(userID, accountID);
    System.out.println("Account removed successfully.");
  }

  /**
   * Lists all transactions for a specific account
   * Prompts for user and account IDs, then prints transaction details if found
   */
  private void listAccountTransactions() {
    System.out.print("Enter User ID: ");
    int userID = scanner.nextInt();
    System.out.print("Enter Account ID: ");
    int accountID = scanner.nextInt();

    System.out.println(this.userAccountMapping.listAccountTransaction(userID, accountID));


  }


  /**
   * Updates the description of a specific transaction in an account
   * Prompts for account and transaction IDs and the new description
   */
  private void updateTransactionDescription() {
    System.out.print("Enter Account ID: ");
    int accountID = scanner.nextInt();
    System.out.print("Enter Transaction ID: ");
    int transactionID = scanner.nextInt();
    scanner.nextLine(); // flush scanner
    System.out.print("Enter New Description: ");
    String newDescription = scanner.nextLine();

    Account account = this.userAccountMapping.getAccountByNumber(accountID);
    account.getTransactionList().updateTransactionDescription(transactionID, newDescription);
    System.out.println("Transaction description updated successfully.");
  }

  /**
   * Adds a transaction to a specific account
   * Details such as transaction ID, amount, description, date, type, and status are prompted from the user
   */
  private void addTransaction() {
    System.out.print("Enter User ID: ");
    int userID = scanner.nextInt();
    System.out.print("Enter Account ID: ");
    int accountID = scanner.nextInt();
    System.out.print("Enter Transaction ID: ");
    int transactionID = scanner.nextInt();
    System.out.print("Enter Transaction Amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine();  // Clear buffer
    System.out.print("Enter Transaction Description: ");
    String description = scanner.nextLine();
    System.out.print("Enter Transaction Date (MM/DD/YYYY): ");
    String dateInput = scanner.nextLine();
    System.out.print("Enter Transaction Type (Income/Expense): ");
    String type = scanner.nextLine();
    System.out.print("Enter Transaction Status (Pending/Completed): ");
    String status = scanner.nextLine();

    //Split the date input into month, day, and year
    String[] dateParts = dateInput.split("/");
    int month = Integer.parseInt(dateParts[0]);
    int day = Integer.parseInt(dateParts[1]);
    int year = Integer.parseInt(dateParts[2]);

    Date date = new Date(month, day, year);
    TransactionType transactionType = TransactionType.valueOf(type.toUpperCase());
    TransactionStatus transactionStatus = TransactionStatus.valueOf(status.toUpperCase());

    Transaction transaction = new Transaction(transactionID, amount, description, date,
        transactionType, transactionStatus);
    Account account = userAccountMapping.getAccountByNumber(accountID);
    if (account != null) {
      account.getTransactionList().addTransaction(transaction);
      System.out.println("Transaction added successfully.");
    } else {
      System.out.println("Account not found.");
    }
  }

  /**
   * Removes all transactions associated with a specific user's account
   * The user and account numbers are prompted, and all transactions are deleted
   */
  private void removeAllTransactions() {
    System.out.print("Enter User ID: ");
    int userId = scanner.nextInt();
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();
    this.userAccountMapping.removeTransactions(userId, accountNumber);
  }

  /**
   * Filters and displays transactions of a specified type for a given account
   * The account number and transaction type are prompted from the user
   */
  private void filterAndDisplayTransactionsByType(TransactionType type) {
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();

    Account account  = this.userAccountMapping.getAccountByNumber(accountNumber);
    AccountTransactionLinkedList accountTransactionLinkedList = account.getTransactionList();

    accountTransactionLinkedList.filterTransactions(p -> p.getTransactionType().equals(type));
    System.out.println(accountTransactionLinkedList.printAllTransactions());


  }


  /**
   * Filters transactions for a given account that exceed a specified amount
   * The account number and minimum transaction amount are prompted from the user
   */
  private void filterTransactionsByAmount() {
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();
    System.out.print("Enter the minimum amount for filtering transactions ($): ");
    double amountThreshold = scanner.nextDouble();  // User specifies the threshold

    Account account = userAccountMapping.getAccountByNumber(accountNumber);
    AccountTransactionLinkedList accountTransactionLinkedList = account.getTransactionList();
    accountTransactionLinkedList.filterTransactions(p -> p.getAmount() > amountThreshold);
    System.out.println(accountTransactionLinkedList.printAllTransactions());

  }

  /**
   * Changes all pending transactions to completed status for a given account
   * The account number is prompted, and all pending transactions are updated
   */
  private void mapPendingTransactionsToCompleted() {
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();

    Account account = userAccountMapping.getAccountByNumber(accountNumber);
    AccountTransactionLinkedList accountTransactionLinkedList = account.getTransactionList();
    accountTransactionLinkedList.mapTransactions(transaction -> {
      if (transaction.getTransactionStatus() == TransactionStatus.PENDING) {
        transaction.setTransactionStatus((TransactionStatus.COMPLETED));
      }
      return transaction;
    });
    accountTransactionLinkedList.printAllTransactions();

  }

  /**
   * Counts and prints the total number of transactions for a specified account
   */
  private void countTotalTransactions() {
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();
    Account account = userAccountMapping.getAccountByNumber(accountNumber);
    if (account != null) {
      int totalTransactions = account.getTransactionList().countTotalTransactions();
      System.out.println(
          "Total transactions for Account " + accountNumber + ": " + totalTransactions);
    } else {
      System.out.println("Account not found.");
    }
  }

  /**
   * Prints details for an individual user by prompting for the user ID
   * User details such as name, address, and email are displayed if the user is found
   */
  private void printIndividualUserInfo(){
    System.out.print("Enter User ID: ");
    int userId = scanner.nextInt();

    IndividualUser userObj = this.userAccountMapping.getIndividualUserInfo(userId);
    System.out.println("User information with user ID: " + userId);
    System.out.println("User information: " + userObj.getIndividualName() + ";" +
        userObj.getIndividualAddress() + ";" + userObj.getIndividualEmail());
  }


  /**
   * Removes a specific transaction from an account by prompting for the account and transaction IDs
   */
  private void removeTransaction() {
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();
    System.out.print("Enter Transaction ID to remove: ");
    int transactionID = scanner.nextInt();

    Account account = userAccountMapping.getAccountByNumber(accountNumber);
    if (account != null) {
      AccountTransactionLinkedList accountTransactionLinkedList = account.getTransactionList();
      accountTransactionLinkedList.removeTransaction(transactionID);
      System.out.println("Transaction ID " + transactionID + " has been removed.");
    } else {
      System.out.println("Account not found.");
    }
  }


  /**
   * Filters and displays all transfer-type transactions for a given account
   * The account number is prompted, and transfer transactions are displayed
   */
  private void filterAndDisplayTransferTransactions() {
    System.out.print("Enter Account Number: ");
    int accountNumber = scanner.nextInt();

    Account account = userAccountMapping.getAccountByNumber(accountNumber);
    AccountTransactionLinkedList accountTransactionLinkedList = account.getTransactionList();
    accountTransactionLinkedList.filterTransactions(p -> p.getTransactionType().equals(TransactionType.TRANSFER));
    System.out.println(accountTransactionLinkedList.printAllTransactions());

  }


}
