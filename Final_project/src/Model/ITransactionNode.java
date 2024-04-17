package Model;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * This interface outlines methods for accessing, modifying, and querying transaction data
 * @param <T> The type of the transaction, which must extend Transaction
 */
public interface ITransactionNode<T> {

  /**
   * Retrieves the transaction content at a specified position within the node
   * @param position The index position within the transaction node
   * @return The transaction at the specified position, or null if no such transaction exists
   */
  T getTransactionNodeContent(int position);

  String toString();

  /**
   * Counts the total number of transactions present in the node
   * @return The total number of transactions
   */
  int countTotalTransactions();

  /**
   * Removes a transaction identified by its transaction ID
   * @param transactionID The ID of the transaction to remove
   * @return The node after the transaction has been removed
   */
  ITransactionNode<T> removeTransaction(int transactionID);

  /**
   * Removes transactions based on a given predicate
   * @param predicate A predicate to determine which transactions to remove
   * @return The node after transactions have been removed based on the predicate
   */
  ITransactionNode<T> removeIf(Predicate<T> predicate);

  /**
   * Prints a consolidated string representation of all transactions within the node
   * @return A string listing all transactions
   */
  String printAllTransactions();

  /**
   * Prints transactions filtered by a specific type
   * @param transactionType The type of transactions to filter by
   * @return A string listing transactions of the specified type
   */
  String printTransactionsByTransactionType(TransactionType transactionType);

  /**
   * Updates the description of a specific transaction identified by transaction ID
   * @param transactionID The ID of the transaction to update
   * @param newDescription The new description for the transaction
   * @return The node after the transaction's description has been updated
   */
  ITransactionNode<T> updateTransactionDescription(int transactionID, String newDescription);

  /**
   * Applies a function to each transaction in the node and updates the node accordingly
   * @param mapper A function to apply to each transaction
   * @return The node after the function has been applied to its transactions
   */
  ITransactionNode<T> map(Function<T, T> mapper);

  /**
   * Filters transactions within the node using a predicate
   * @param predicate A predicate to determine which transactions to keep
   * @return The node after it has been filtered
   */
  ITransactionNode<T> filter(Predicate<T> predicate);
  /**
   * Counts transactions that satisfy a specified predicate
   * @param predicate A predicate to test each transaction
   * @return The count of transactions that satisfy the predicate
   */
  int countIf(Predicate<T> predicate);

}
