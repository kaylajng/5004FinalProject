package Controller;

import Model.EmptyTransactionNode;
import Model.ITransactionNode;
import Model.Transaction;
import Model.TransactionType;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Implements a linked list to manage transactions within an account
 */
public class AccountTransactionLinkedList {

  private ITransactionNode<Transaction> head;

  /**
   * Constructs an empty AccountTransactionLinkedList with a head node initialized to an EmptyTransactionNode
   * This serves as the starting point for the linked list
   */
  public AccountTransactionLinkedList() {
    head = new EmptyTransactionNode<>();
  }

  /**
   * Adds a new transaction to the front of the linked list
   * @param transaction The Transaction object to add
   */
  public void addTransaction(Transaction transaction) {
    head = new TransactionNode<>(transaction, head);
  }

  /**
   * Removes a transaction from the linked list based on its transaction ID
   * @param transactionID The ID of the transaction to remove
   */
  public void removeTransaction(int transactionID) {
    head = head.removeTransaction(transactionID);
  }

  /**
   * Counts the total number of transactions in the linked list
   * @return The total number of transactions
   */
  public int countTotalTransactions(){
    return head.countTotalTransactions();
  }

  /**
   * Generates a string representation of all transactions in the linked list
   * @return A string listing all transactions
   */
  public String printAllTransactions() {
    return head.printAllTransactions();
  }

  /**
   * Filters and prints transactions based on their type
   * @param transactionType The type of transactions to filter by
   * @return A string listing transactions of the specified type
   */
  public String printTransactionsByTransactionType(TransactionType transactionType){
    return head.printTransactionsByTransactionType(transactionType);
  }

  /**
   * Returns the head of the linked list
   * @return The head node of the linked list
   */
  public ITransactionNode<Transaction> getHead(){
    return head;
  }

  /**
   * Updates the description of a specific transaction identified by its transaction ID
   * @param transactionID The ID of the transaction to update
   * @param newDescription The new description for the transaction
   */
  public void updateTransactionDescription(int transactionID, String newDescription){
    head.updateTransactionDescription(transactionID, newDescription);
  }

  /**
   * Applies a specified function to all transactions in the linked list
   * @param mapper A function that takes a Transaction and returns a modified Transaction
   */
  public void mapTransactions(Function<Transaction, Transaction> mapper) {
    this.head = this.head.map(mapper);
  }

  /**
   * Filters transactions in the linked list based on a specified predicate
   * Transactions that do not meet the predicate criteria will be removed
   * @param predicate A condition that returns true for transactions to be retained
   */
  public void filterTransactions(Predicate<Transaction> predicate){
    this.head = this.head.filter(predicate);
  }
}