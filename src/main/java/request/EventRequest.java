package request;

/**
 * EventRequest to be sent to EventService
 */
public class EventRequest {

  /**
   * ID associated with specific event
   */
  private String eventID;
  /**
   * Unique AuthToken to verify user with server
   */
  private String authtoken;

  /**
   * Constructor for EventRequest with no specific ID
   */
  public EventRequest(String authtoken) {
    this.authtoken = authtoken;
  }

  /**
   * Constructor for EventRequest with specifid ID
   * @param eventID ID associated with specific event
   */
  public EventRequest(String eventID, String authtoken){
    this.eventID = eventID;
    this.authtoken = authtoken;
  }

  public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID=eventID;
  }
}
