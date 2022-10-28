package request;

import model.AuthToken;
import model.Person;

/**
 * PersonRequest object to be sent to service methods
 */
public class PersonRequest {

  /**
   * Unique ID associated with person
   */
  private String personID;
  /**
   * Unique AuthToken to verify user with server
   */
  private String authtoken;

  /**
   * Constructor for PersonRequest with specific ID
   */
  public PersonRequest(String authtoken, String personID){
    this.authtoken = authtoken;
    this.personID = personID;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }
  public String getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(String authtoken) {
    this.authtoken=authtoken;
  }
}
