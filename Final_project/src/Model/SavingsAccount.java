package Model;


/**
 * Represents a savings account, inheriting from the Account class
 */
public class SavingsAccount extends Account{

  private double interestRate;

  /**
   * Constructs a new SavingsAccount with a specified account ID, initial balance, and interest rate
   * @param accountID The ID number for this savings account
   * @param initialBalance The initial amount of money deposited into the account
   * @param interestRate The annual interest rate applied to the balance
   */
  public SavingsAccount(int accountID, double initialBalance, double interestRate) {
    super(accountID, initialBalance);
    this.interestRate = interestRate;
  }

  /**
   * Transfers a specified amount from this savings account to another account
   * The amount must be positive and within the current balance to proceed with the transfer
   * @param toAccount The account to which funds will be transferred
   * @param amount The amount of money to transfer
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

  /**
   * Closes this savings account by setting the balance to zero
   */
  @Override
  public void closeAccount(){
    this.balance = 0;
  }

  /**
   * Calculates the compound interest over a specified number of years using the principal amount and the account's interest rate
   * @param principle The initial amount of money on which interest is calculated
   * @param years The number of years over which to calculate compound interest
   * @return The amount of money accrued from the principle after applying compound interest for the specified number of years
   */
  public double calculateCompoundInterest(double principle, int years) {
    if(years == 0){
      return principle;
    } else {
      return calculateCompoundInterest(principle * (1 + interestRate), years - 1);
    }
  }

}
