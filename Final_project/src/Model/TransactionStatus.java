package Model;

/**
 * Enum representing the statuses of a transaction within the system
 */
public enum TransactionStatus {
  PENDING, //Transaction is not yet finalized, and is awaiting completion
  COMPLETED, //Transaction has been successfully completed
  FAILED, //Transaction has failed due to an error or rejection
}
