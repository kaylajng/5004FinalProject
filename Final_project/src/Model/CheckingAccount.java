package Model;

import Model.Account;


/**
 * Represents a checking account, inheriting from the Account class
 */
public class CheckingAccount extends Account {

  /**
   * Constructs a new CheckingAccount with a specified account ID and initial balance.
   * @param accountID The ID number for this checking account
   * @param initialBalance The initial amount of money deposited into the account
   */
  public CheckingAccount(int accountID, double initialBalance) {
    super(accountID, initialBalance);
  }

  /**
   * Transfers a specified amount from this checking account to another account.
   * The amount to be transferred must be positive and within the available balance
   * @param toAccount The account to which funds will be transferred
   * @param amount The amount of money to transfer.
   * @throws IllegalArgumentException If the amount is negative or exceeds the current balance
   */
  @Override
  public void transfer(Account toAccount, double amount) {
    if(amount > 0 && amount <= balance){
      this.withdraw(amount);
      toAccount.deposit(amount);
    } else {
      throw new IllegalArgumentException("Insufficient funds for transfer");
    }
  }

  @Override
  public void closeAccount(){
    this.balance = 0;
  }
















}
