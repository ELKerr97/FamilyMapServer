package result;

/**
 * Contains LoginResult body
 */
public class LoginResult extends Result{

  /**
   * User's authtoken
   */
  String authtoken;
  /**
   * User's username
   */
  String username;
  /**
   * User's personID
   */
  String personID;

  /**
   * Constructor for LoginResult
   * @param authtoken User's authtoken
   * @param username User's username
   * @param personID User's personID
   * @param success If login was successful or not
   */
  public LoginResult(String authtoken, String username, String personID, boolean success, String message) {
    this.authtoken=authtoken;
    this.username=username;
    this.personID=personID;
    this.success=success;
    this.message = message;
  }
}
