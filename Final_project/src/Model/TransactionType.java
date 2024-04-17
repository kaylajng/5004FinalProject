package Model;


/**
 * Enum representing the types of transactions that can occur within the system
 */
public enum TransactionType {
  EXPENSE, //Transaction where money is spent from the account
  INCOME, //Transaction where money is received into the account
  TRANSFER, //Transaction where money is moved between two accounts

}
