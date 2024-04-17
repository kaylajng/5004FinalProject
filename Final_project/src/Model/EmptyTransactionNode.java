package Model;

import java.util.function.Function;
import java.util.function.Predicate;



/**
 * Represents an empty node in a transaction node structure, implementing the ITransactionNode interface
 * @param <T> The type of the transaction which extends Transaction
 */
public class EmptyTransactionNode<T extends Transaction> implements ITransactionNode<T> {

  /**
   * Returns null as there is no transaction at any position in an empty node
   * @param position The position of the transaction requested
   * @return Always returns null
   */
  public T getTransactionNodeContent(int position) {
    return null;
  }

  /**
   * Returns an empty string representation for the empty transaction node
   * @return An empty string
   */
  @Override
  public String toString() {
    return "";
  }

  /**
   * Returns the total number of transactions in this node, which is always zero for an empty node
   * @return Zero, as there are no transactions
   */
  @Override
  public int countTotalTransactions(){
    return 0;
  }

  /**
   * Returns an empty string as there are no transactions to print
   * @return An empty string
   */
  @Override
  public String printAllTransactions() {
    return "";
  }

  /**
   * Returns an empty string as there are no transactions of any transaction type
   * @param transactionType The type of transactions to print
   * @return An empty string
   */
  @Override
  public String printTransactionsByTransactionType(TransactionType transactionType){
    return "";
  }

  /**
   * Does not modify the node and returns itself, as there are no transactions to remove
   * @param transactionID The ID of the transaction to be removed
   * @return The current empty node instance
   */
  @Override
  public ITransactionNode<T> removeTransaction(int transactionID) {
      return this;
  }

  /**
   * Does not modify the node and returns itself, as there are no transactions to update
   * @param transactionID The ID of the transaction to update
   * @param newDescription The new description for the transaction
   * @return The current empty node instance
   */
  @Override
  public ITransactionNode<T> updateTransactionDescription(int transactionID, String newDescription) {
    return this;
  }

  /**
   * Does not modify the node and returns itself, as there are no transactions to remove based on a predicate
   * @param predicate A predicate to evaluate transactions
   * @return The current empty node instance
   */
  @Override
  public ITransactionNode<T> removeIf(Predicate<T> predicate){
      return this;
  }

  /**
   * Does not modify the node and returns itself, as there are no transactions to modify with a mapper function
   * @param mapper A function to apply to transactions
   * @return The current empty node instance
   */
  @Override
  public ITransactionNode<T> map(Function<T, T> mapper) {
    return this;
  }

  /**
   * Does not modify the node and returns itself, as there are no transactions to filter with a predicate
   * @param predicate A predicate to evaluate transactions
   * @return The current empty node instance
   */
  @Override
  public ITransactionNode<T> filter(Predicate<T> predicate) {
    return this;
  }

  /**
   * Returns zero as there are no transactions that match the given predicate
   * @param predicate A predicate to evaluate transactions
   * @return Zero, as there are no transactions
   */
  @Override
  public int countIf(Predicate<T> predicate) {
    return 0;
  }


}
