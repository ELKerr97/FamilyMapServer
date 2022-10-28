package model;

import java.util.Objects;

/**
 * Class stores data for a Person object
 */
public class Person {

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
   * Constructor for a Person object
   * @param personID String of the unique ID for this Person object
   * @param associatedUsername String of username associated with this Person
   * @param firstName String of first name of this Person
   * @param lastName String of last name of this Person
   * @param gender String of gender ("m" or "f")
   * @param fatherID String of personID associated with the father of this person
   * @param motherID String of personID associated with the mother of this person
   * @param spouseID String of personID associated with the spouse of this person
   */
  public Person(String personID, String associatedUsername, String firstName,
                String lastName, String gender, String fatherID, String motherID,
                String spouseID) {

    this.personID=personID;
    this.associatedUsername=associatedUsername;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.fatherID=fatherID;
    this.motherID=motherID;
    this.spouseID=spouseID;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person=(Person) o;
    return Objects.equals(personID, person.personID) && Objects.equals(associatedUsername, person.associatedUsername) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(gender, person.gender) && Objects.equals(fatherID, person.fatherID) && Objects.equals(motherID, person.motherID) && Objects.equals(spouseID, person.spouseID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID);
  }
}
