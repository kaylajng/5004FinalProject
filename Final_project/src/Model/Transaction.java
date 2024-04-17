package Model;


/**
 * Represents a financial transaction within the system
 */
public class Transaction {

  private int transactionID;
  private double amount;
  private String description;
  private Date date;
  private TransactionType transactionType;
  private TransactionStatus transactionStatus;
  private static int nextId = 1; // Static variable to generate unique transaction IDs

  /**
   * Constructs a new Transaction with specified details
   * @param transactionID The ID number for the transaction
   * @param amount Amount of money involved in the transaction
   * @param description Description of the transaction
   * @param date Date on which the transaction occurred
   * @param transactionType Type of the transaction
   * @param transactionStatus Current status of the transaction
   */
  public Transaction(int transactionID, double amount, String description, Date date,
      TransactionType transactionType, TransactionStatus transactionStatus) {
    this.transactionID = transactionID;
    this.amount = amount;
    this.description = description;
    this.date = date;
    this.transactionType = transactionType;
    this.transactionStatus = transactionStatus;
  }

  public int getTransactionID() {
    return transactionID;
  }

  public void setTransactionID(int transactionID) {
    this.transactionID = transactionID;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public TransactionStatus getTransactionStatus() {
    return transactionStatus;
  }

  public void setTransactionStatus(TransactionStatus transactionStatus) {
    this.transactionStatus = transactionStatus;
  }

  public static int getNextId() {
    return nextId;
  }

  public static void setNextId(int nextId) {
    Transaction.nextId = nextId;
  }

  @Override
  public String toString() {
    return String.format("TransactionID: %d, Description: %s, Amount: $%.2f, Model.Date: %s, Model.TransactionType: %s, Model.TransactionStatus: %s",
        transactionID, description, amount, date, transactionType, transactionStatus);
  }

}
