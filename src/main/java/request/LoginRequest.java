package request;

import handlers.LoginHandler;

/**
 * LoginRequest to be sent to LoginService
 */
public class LoginRequest {

  /**
   * User's username
   */
  private String username;
  /**
   * User's password
   */
  private String password;

  /**
   * Constructor for LoginRequest
   * @param username User's username
   * @param password User's password
   */
  public LoginRequest(String username, String password){
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
  }
}
