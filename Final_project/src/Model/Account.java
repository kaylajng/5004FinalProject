package Model;

import Controller.AccountTransactionLinkedList;


/**
 * Abstract class representing a bank account.It contains methods to manage account transactions
 * such as deposits, withdrawals, and transfers
 */
public abstract class Account {
  protected int accountID;
  protected double balance;
  protected AccountTransactionLinkedList transactionList;

  /**
   * Constructs a new Account instance with a specified account ID and initial balance
   * @param accountID The accountID for an account
   * @param initialBalance The initial amount of money in the account
   */
  public Account(int accountID, double initialBalance) {
    this.accountID = accountID;
    this.balance = initialBalance;
    this.transactionList = new AccountTransactionLinkedList();

  }

  public int getAccountID() {
    return accountID;
  }

  public void setAccountID(int accountID) {
    this.accountID = accountID;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public AccountTransactionLinkedList getTransactionList() {
    return transactionList;
  }

  public void setTransactionList(AccountTransactionLinkedList transactionList) {
    this.transactionList = transactionList;
  }

  /**
   * Deposits a specified amount into the account. The amount must be positive
   * @param amount The amount to be deposited into the account
   * @throws IllegalArgumentException If the amount is not positive
   */
  public void deposit(double amount) {
    if (amount > 0) {
      this.balance += amount;
    } else {
      throw new IllegalArgumentException("Deposit amount must be positive");
    }
  }

  /**
   * Withdraws a specified amount from the account. The amount must be positive and less than or equal to the current balance
   * @param amount The amount to be withdrawn.
   * @throws IllegalArgumentException If the amount is not positive or if insufficient funds are available
   */
  public void withdraw(double amount){
    if (amount > 0) {
      if (amount <= balance) {
        this.balance -= amount;
      } else {
        throw new IllegalArgumentException("Insufficient funds available");
      }
    } else {
      throw new IllegalArgumentException("Withdrawal amount must be positive");
    }
  }

  /**
   * Abstract method to transfer a specified amount to another account
   * @param toAccount The account to which the money will be transferred
   * @param amount The amount to be transferred
   */
  public abstract void transfer(Account toAccount, double amount);

  public abstract void closeAccount();


















}
