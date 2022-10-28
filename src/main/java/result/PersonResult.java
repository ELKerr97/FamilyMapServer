package result;

import model.Person;

/**
 * Contains PersonResult body
 */
public class PersonResult extends Result{

  /**
   * String of the unique ID for this Person object
   */
  private String personID;
  /**
   * String of username associated with this Person
   */
  private String associatedUsername;
  /**
   * String of first name of this Person
   */
  private String firstName;
  /**
   * String of last name of this Person
   */
  private String lastName;
  /**
   * String of gender ("m" or "f")
   */
  private String gender;
  /**
   * String of personID associated with the father of this person
   */
  private String fatherID;
  /**
   * String of personID associated with the mother of this person
   */
  private String motherID;
  /**
   * String of personID associated with the spouse of this person
   */
  private String spouseID;
  /**
   * Array of people
   */
  private Person[] people;


  public PersonResult(String personID, String associatedUsername, String firstName,
                      String lastName, String gender, String fatherID, String motherID,
                      String spouseID, String message, boolean success) {
    this.personID=personID;
    this.associatedUsername=associatedUsername;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.fatherID=fatherID;
    this.motherID=motherID;
    this.spouseID=spouseID;
    this.message=message;
    this.success=success;
  }

  public PersonResult(Person[] people, String message, boolean success){
    this.people = people;
    this.message = message;
    this.success = true;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public String getAssociatedUsername() {
    return associatedUsername;
  }

  public void setAssociatedUsername(String associatedUsername) {
    this.associatedUsername=associatedUsername;
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

  public String getFatherID() {
    return fatherID;
  }

  public void setFatherID(String fatherID) {
    this.fatherID=fatherID;
  }

  public String getMotherID() {
    return motherID;
  }

  public void setMotherID(String motherID) {
    this.motherID=motherID;
  }

  public String getSpouseID() {
    return spouseID;
  }

  public void setSpouseID(String spouseID) {
    this.spouseID=spouseID;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }
}
