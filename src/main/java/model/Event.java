package model;

import java.util.Objects;
import java.util.SplittableRandom;

/**
 * Class stores data for an Event object
 */
public class Event {

  /**
   * Unique String associated with the event
   */
  private String eventID;
  /**
   * String that associates a user's username to this Event
   */
  private String associatedUsername;
  /**
   * String that associates a person to this Event
   */
  private String personID;
  /**
   * Latitude of the Event
   */
  private Float latitude;
  /**
   * Longitude of the Event
   */
  private Float longitude;
  /**
   * Country in which the Event took place
   */
  private String country;
  /**
   * City in which the Event took place
   */
  private String city;
  /**
   * String associated with what type of Event it is
   */
  private String eventType;
  /**
   * Year in which Event took place
   */
  private Integer year;

  /**
   * Constructor for Event object
   * @param eventID -  Unique String associated with the event
   * @param associateUsername - String that associates a user's username to this Event
   * @param personID - String that associates a person to this Event
   * @param latitude - Latitude of the Event
   * @param longitude -Longitude of the Event
   * @param country - Country in which the Event took place
   * @param city - City in which the Event took place
   * @param eventType - String associated with what type of Event it is
   * @param year - Year in which Event took place
   */
  public Event(String eventID, String associateUsername, String personID, Float latitude,
               Float longitude, String country, String city,
               String eventType, Integer year) {
    this.eventID=eventID;
    this.associatedUsername=associateUsername;
    this.personID=personID;
    this.latitude=latitude;
    this.longitude=longitude;
    this.country=country;
    this.city=city;
    this.eventType=eventType;
    this.year=year;
  }

  public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID=eventID;
  }

  public String getAssociatedUsername() {
    return associatedUsername;
  }

  public void setAssociateUsername(String associateUsername) {
    this.associatedUsername=associateUsername;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude=latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude=longitude;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country=country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city=city;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType=eventType;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year=year;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Event event = (Event) o;
    return Objects.equals(eventID, event.eventID) && Objects.equals(associatedUsername, event.associatedUsername) && Objects.equals(personID, event.personID) && Objects.equals(latitude, event.latitude) && Objects.equals(longitude, event.longitude) && Objects.equals(country, event.country) && Objects.equals(city, event.city) && Objects.equals(eventType, event.eventType) && Objects.equals(year, event.year);
  }
}
