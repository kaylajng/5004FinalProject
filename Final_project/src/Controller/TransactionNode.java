package Controller;
import Model.EmptyTransactionNode;
import Model.ITransactionNode;
import Model.Transaction;
import Model.TransactionType;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Represents a node in a linked list that holds a transaction and a reference to the next node
 * This class implements the ITransactionNode interface and provides methods for manipulating the transaction data
 * and navigating through the linked list
 * @param <T> The type of the transaction which extends Transaction
 */
public class TransactionNode<T extends Transaction> implements ITransactionNode<T> {

  private T transaction;
  private ITransactionNode<T> next;

  public TransactionNode(T transaction, ITransactionNode<T> next) {
    this.transaction = transaction;
    this.next = next;
  }

  public T getTransactionNodeContent(int position) {
    if (position == 0) {
      return transaction;
    } else {
      return next.getTransactionNodeContent(position - 1);
    }
  }

  @Override
  public String toString() {
    return transaction.toString() + (next instanceof EmptyTransactionNode ? "" : "\n" + next.toString());
  }

  /**
   * Calculates the total number of transactions from this node onwards
   * @return The total number of transactions including this node and all subsequent nodes
   */
  @Override
  public int countTotalTransactions(){
    return 1 + next.countTotalTransactions();
  }

  /**
   * Generates a string that lists all transactions from this node forwards
   * @return A string listing all transactions
   */
  @Override
  public String printAllTransactions() {
    return transaction.toString() + (next != null ? "\n" + next.printAllTransactions() : "");
  }

  /**
   * Filters transactions by type and generates a string representation of all matching transactions
   * @param transactionType The type of transactions to filter by
   * @return A string listing all transactions of the specified type from this node onwards
   */
  @Override
  public String printTransactionsByTransactionType(TransactionType transactionType){
    if(transaction.getTransactionType() == transactionType) {
      return transaction.toString() + (next instanceof EmptyTransactionNode ? "" : "\n" + next.printTransactionsByTransactionType(transactionType));
    } else {
      return next.printTransactionsByTransactionType(transactionType);
    }
  }

  /**
   * Removes a transaction by its ID
   * @param transactionID The ID of the transaction to remove
   * @return The node after removal of the specified transaction
   */
  @Override
  public ITransactionNode<T> removeTransaction(int transactionID) {
    if (transaction.getTransactionID() == transactionID) {
      return next;
    } else {
      next = next.removeTransaction(transactionID);
      return this;
    }
  }

  /**
   * Updates the description of a specific transaction identified by its transaction ID
   * @param transactionID The ID of the transaction to update
   * @param newDescription The new description for the transaction
   * @return This node, after the transaction's description has been updated
   */
  @Override
  public ITransactionNode<T> updateTransactionDescription(int transactionID, String newDescription) {
    if (transaction.getTransactionID() == transactionID) {
      transaction.setDescription(newDescription);
    } else {
      next.updateTransactionDescription(transactionID, newDescription);
    }
    return this;
  }

  /**
   * Removes transactions based on a predicate
   * @param predicate A predicate to determine which transactions to remove
   * @return The node after transactions have been removed based on the predicate
   */
  @Override
  public ITransactionNode<T> removeIf(Predicate<T> predicate){
    if (predicate.test(transaction)){
      return next;
    } else {
      next = next.removeIf(predicate);
      return this;
    }
  }

  /**
   * Applies a function to each transaction in the node and updates the node accordingly
   * @param mapper A function to apply to each transaction
   * @return The node after the function has been applied to its transactions
   */
  @Override
  public ITransactionNode<T> map(Function<T, T> mapper) {
    return new TransactionNode<>(mapper.apply(transaction), next.map(mapper));
  }

  /**
   * Filters transactions within the node using a predicate
   * @param predicate A predicate to determine which transactions to keep
   * @return The node after it has been filtered
   */
  @Override
  public ITransactionNode<T> filter(Predicate<T> predicate) {
    if (predicate.test(transaction)) {
      return new TransactionNode<>(transaction, next.filter(predicate));
    } else {
      return next.filter(predicate);
    }
  }

  /**
   * Counts transactions that satisfy a specified predicate
   * @param predicate A predicate to test each transaction
   * @return The count of transactions that satisfy the predicate
   */
  @Override
  public int countIf(Predicate<T> predicate) {
    int count = predicate.test(transaction) ? 1 : 0;
    if (next != null) {
      count += next.countIf(predicate);
    }
    return count;
  }


  public Transaction getTransaction() {
    return this.transaction;
  }

  public ITransactionNode<T> getNext() {
    return this.next;
  }

  public void setNext(ITransactionNode<T> next) {
    this.next = next;
  }
}

