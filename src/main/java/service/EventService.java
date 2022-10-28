package service;

import dao.DataAccessException;
import dao.Database;
import dao.EventDAO;
import model.Event;
import request.EventRequest;
import result.EventResult;

/**
 * EventService will take an EventRequest and return an EventResult after using a DAO to
 * perform database functions
 */
public class EventService extends Service{

  /**
   * Constructor for EventService
   */
  public EventService(){
    database = new Database();
  }

  /**
   * Get a single Event or all Events
   * @param eventRequest EventRequest that contains request body
   * @return EventResult containting response body
   */
  public EventResult getEvent(EventRequest eventRequest) throws DataAccessException {
    EventResult result;
    String message;
    try {

      eventDAO = new EventDAO(database.openConnection());
      Event event = eventDAO.find(eventRequest.getEventID());

      // Check if an event was returned
      if (event != null){
        message = "Get event succeeded";
        result = new EventResult(event.getEventID(), event.getAssociatedUsername(), event.getPersonID(), event.getLatitude(),
                event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(), event.getYear(),
                message, true);

      } else {
        message = "Get event failed";
        result = new EventResult(null, null, null, null, null, null, null,
                null, null, message, false);
      }
      return result;

    } catch (DataAccessException ex){
      ex.printStackTrace();
    } finally {
      database.closeConnection(true);
    }

    return null;

  }

}
