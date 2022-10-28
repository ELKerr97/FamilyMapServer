package model;

import java.util.Objects;

/**
 * Class stores data for a User object
 */
public class User {

  /**
   * String of username for User
   */
  private String username;
  /**
   * String for password for User
   */
  private String password;
  /**
   * String for User's email
   */
  private String email;
  /**
   * String for User's first name
   */
  private String firstName;
  /**
   * String for User's last name
   */
  private String lastName;
  /**
   * String for User's gender
   */
  private String gender;
  /**
   * String for User's personID which associates with a unique Person object
   */
  private String personID;

  /**
   * Constructor for User object
   * @param username String of username for User
   * @param password String for password for User
   * @param email String for User's email
   * @param firstName String for User's first name
   * @param lastName String for User's last name
   * @param gender String for User's gender
   * @param personID String for User's personID which associates with a unique Person object
   */
  public User(String username, String password, String email, String firstName,
              String lastName, String gender, String personID) {
    this.username=username;
    this.password=password;
    this.email=email;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.personID=personID;
  }

  // MARK: Getters and Setters
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email=email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName=firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName=lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender=gender;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user=(User) o;
    return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(gender, user.gender) && Objects.equals(personID, user.personID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, email, firstName, lastName, gender, personID);
  }
}
