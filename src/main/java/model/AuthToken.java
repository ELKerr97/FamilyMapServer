package model;

import java.util.Objects;

/**
 * Class stores data for an AuthToken object
 */
public class AuthToken {

  /**
   * A String associated with this AuthToken object
   */
  private String authtoken;

  /**
   * A String that associates a user with this AuthToken object
   */
  private String username;

  /**
   * Constructor for AuthToken object
   * @param authtoken - String for unique AuthToken
   * @param username - String for username associated with a user
   */
  public AuthToken(String authtoken, String username){
    this.authtoken = authtoken;
    this.username = username;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AuthToken authToken=(AuthToken) o;
    return Objects.equals(authtoken, authToken.authtoken) && Objects.equals(username, authToken.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(authtoken, username);
  }
}
