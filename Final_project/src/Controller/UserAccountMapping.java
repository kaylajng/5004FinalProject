package Controller;

import Model.Account;
import Model.BusinessUser;
import Model.IndividualUser;
import Model.SavingsAccount;
import Model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Manages the mapping between users and their respective accounts within the system
 */
public class UserAccountMapping {

  //Maps user IDs to lists of accounts associated with each user
  public Map<Integer, List<Account>> userAccountsMap;

  //Maps user IDs to user information
  public Map<Integer, User> userInfoMap;


  public UserAccountMapping() {
    this.userInfoMap = new HashMap<>();
    this.userAccountsMap = new HashMap<>();

  }

  /**
   * Adds an account to a user's list of accounts. If the user does not already have an account list, one is created
   * @param userID The ID of the user to whom the account is to be added
   * @param account The account to add to the user's list
   */
  public void addAccount(Integer userID, Account account) {
    //get the linked list of accounts for the user, if it exists; otherwise, create a new linked list
    List<Account> accounts = this.userAccountsMap.computeIfAbsent(userID, k -> new ArrayList<>());
    accounts.add(account);
  }

  /**
   * Removes an account from a user's list based on account index. If the list is empty after removal, it is also removed from the map
   * @param userID The ID of the user from whom the account is to be removed
   * @param account The  account to remove from the list
   */
  public void removeAccount(Integer userID, int account) {
    //check if the user has accounts
    List<Account> accounts = this.userAccountsMap.get(userID);
    if (accounts != null) {
      accounts.remove(account);
      //if the linked list is empty after the removal, we can remove the mapping
      if(accounts.isEmpty()) {
        this.userAccountsMap.remove(userID);
      }
    }
  }

  /**
   * Prints all accounts and their details for all users
   */
  public void printAccounts(){
    for (Entry<Integer, List<Account>> entry : this.userAccountsMap.entrySet()){
      System.out.println("User ID: " + entry.getKey());
      for (Account acc : entry.getValue()){
        System.out.println("  Account ID: " + acc.getAccountID() + ", Balance: $" + acc.getBalance());
      }
    }

  }


  /**
   * Retrieves and lists all transactions for a specific account belonging to a given user
   * @param userID The ID of the user
   * @param accountID The ID of the account whose transactions are to be listed
   * @return A string listing all transactions, or an error message if no user or account is found
   */
  public String listAccountTransaction(Integer userID, Integer accountID) {
    if (this.userAccountsMap.containsKey(userID)) {
      List<Account> accountList = this.userAccountsMap.get(userID);
      Account account = accountList.stream().filter(a -> a.getAccountID() == accountID).findFirst().get();

      if (account == null) {
        System.out.println("No account ID " + accountID + "belongs to user " + userID);
        return "";
      }

      AccountTransactionLinkedList accountTransactionLinkedList = account.getTransactionList();
      return accountTransactionLinkedList.printAllTransactions();
    } else {
      System.out.println("Can not find user ID");
      return "";
    }
  }

  /**
   * Retrieves an account by its account number across all users
   * @param accountNumber The account number to search for
   * @return The Account object if found, null otherwise
   */
  public Account getAccountByNumber(int accountNumber){
    for(List<Account> accounts : userAccountsMap.values()){
      for(Account account: accounts){
        if(account.getAccountID() == accountNumber){
          return account;
        }
      }
    }
    return null;
  }

  /**
   * Removes all transactions associated with an account of a particular user
   * @param userID The ID of the user whose account's transactions are to be removed
   * @param accountNumber The account number from which all transactions are to be removed
   */
  public void removeTransactions(int userID, int accountNumber){
    List<Account> accountList = userAccountsMap.get(userID);
    Account account = accountList.stream().filter(a -> a.getAccountID() == accountNumber).findFirst().get();
    account.setTransactionList(null);
  }

  /**
   * Checks if a user exists in the system based on user ID
   * @param userID The user ID to check
   * @return true if the user exists, false otherwise
   */
  public boolean userExists(int userID) {
    return userAccountsMap.containsKey(userID);
  }

  /**
   * Saves individual user information into the mapping
   * @param individualUser The individual user to save
   */
  public void saveIndividualUserInfo(IndividualUser individualUser) {
    this.userInfoMap.put(individualUser.getIndividualUserID(),individualUser);
  }

  /**
   * Retrieves individual user information based on user ID
   * @param userId The ID of the individual user to retrieve
   * @return The IndividualUser object, or null if not found
   */
  public IndividualUser getIndividualUserInfo(int userId) {
    IndividualUser user = (IndividualUser) this.userInfoMap.computeIfAbsent(userId, k -> null);
    return user;
  }

  /**
   * Saves business user information into the mapping
   * @param businessUser The business user to save
   */
  public void saveBusinessUserInfo(BusinessUser businessUser) {
    this.userInfoMap.put(businessUser.getBusinessUserID(),businessUser);
  }

  /**
   * Retrieves business user information based on user ID
   * @param userId The ID of the business user to retrieve
   * @return The BusinessUser object, or null if not found
   */
  public BusinessUser getBusinessUserInfo(int userId) {
    BusinessUser user = (BusinessUser) this.userInfoMap.computeIfAbsent(userId, k -> null);
    return user;
  }



}
