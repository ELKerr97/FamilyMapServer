package result;

/**
 * Contains RegisterResult body
 */
public class RegisterResult extends Result{

  /**
   * Authoken for registered user
   */
  String authtoken;
  /**
   * Username of registered user
   */
  String username;
  /**
   * PersonID associated with registered user
   */
  String personID;

  /**
   * Constructor for RegisterResult
   * @param authtoken Authoken for registered user
   * @param username Username of registered user
   * @param personID PersonID associated with registered user
   * @param success If register was successful or not
   * @param message Description of error if not successful
   */
  public RegisterResult(String authtoken, String username, String personID,
                        boolean success, String message) {
    this.authtoken=authtoken;
    this.username=username;
    this.personID=personID;
    this.success=success;
    this.message=message;
  }

  public String getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(String authtoken) {
    this.authtoken=authtoken;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }
}
