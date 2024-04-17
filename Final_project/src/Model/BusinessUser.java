package Model;


/**
 * Represents a business user within the banking system, implementing the User interface
 */
public class BusinessUser implements User {
  private int businessUserID;
  private String businessName;
  private String businessAddress;
  private String businessEmail;

  /**
   * Constructs a new BusinessUser with specified details
   * @param businessUserID The ID number for business user
   * @param businessName The name of the business
   * @param businessAddress The address of the business
   * @param businessEmail The email address for the business
   */
  public BusinessUser(int businessUserID, String businessName, String businessAddress,
      String businessEmail) {
    this.businessUserID = businessUserID;
    this.businessName = businessName;
    this.businessAddress = businessAddress;
    this.businessEmail = businessEmail;
  }

  public int getBusinessUserID() {
    return businessUserID;
  }

  public void setBusinessUserID(int businessUserID) {
    this.businessUserID = businessUserID;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getBusinessAddress() {
    return businessAddress;
  }

  public void setBusinessAddress(String businessAddress) {
    this.businessAddress = businessAddress;
  }

  public String getBusinessEmail() {
    return businessEmail;
  }

  public void setBusinessEmail(String businessEmail) {
    this.businessEmail = businessEmail;
  }
}
