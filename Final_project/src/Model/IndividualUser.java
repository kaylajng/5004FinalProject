package Model;


/**
 * Represents an individual user within the banking system, implementing the User interface
 */
public class IndividualUser implements User {

  private int individualUserID;
  private String individualName;
  private String individualAddress;
  private String individualEmail;

  /**
   * Constructs a new IndividualUser with specified details
   * @param individualUserID The ID number for this individual user
   * @param individualName The name of the individual
   * @param individualAddress The address of the individual
   * @param individualEmail The email address of the individual
   */
  public IndividualUser(int individualUserID, String individualName, String individualAddress,
      String individualEmail) {
    this.individualUserID = individualUserID;
    this.individualName = individualName;
    this.individualAddress = individualAddress;
    this.individualEmail = individualEmail;
  }

  public int getIndividualUserID() {
    return individualUserID;
  }

  public void setIndividualUserID(int individualUserID) {
    this.individualUserID = individualUserID;
  }

  public String getIndividualName() {
    return individualName;
  }

  public void setIndividualName(String individualName) {
    this.individualName = individualName;
  }

  public String getIndividualAddress() {
    return individualAddress;
  }

  public void setIndividualAddress(String individualAddress) {
    this.individualAddress = individualAddress;
  }

  public String getIndividualEmail() {
    return individualEmail;
  }

  public void setIndividualEmail(String individualEmail) {
    this.individualEmail = individualEmail;
  }
}
