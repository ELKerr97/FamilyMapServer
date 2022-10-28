package result;

/**
 * Contains EventResult body
 */
public class EventResult extends Result{

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
   * Constructor for EventResult
   */
  public EventResult(String eventID, String associatedUsername, String personID,
                     Float latitude, Float longitude, String country, String city,
                     String eventType, Integer year, String message, boolean success) {
    this.eventID=eventID;
    this.associatedUsername=associatedUsername;
    this.personID=personID;
    this.latitude=latitude;
    this.longitude=longitude;
    this.country=country;
    this.city=city;
    this.eventType=eventType;
    this.year=year;
    this.message=message;
    this.success=success;
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

  public void setAssociatedUsername(String associatedUsername) {
    this.associatedUsername=associatedUsername;
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
