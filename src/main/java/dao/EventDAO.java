package dao;

import model.Event;

import java.sql.*;
import java.util.logging.Logger;

/**
 * EventDAO class interacts with and changes data in database related
 * to Events with SQL commands
 */
public class EventDAO {
  /**
   * Connection object to connect to the database
   */
  private final Connection conn;

  /**
   * Constructor for EventDAO
   * @param conn
   */
  public EventDAO(Connection conn) {
    this.conn = conn;
  }

  /**
   * Insert an Event in the database
   * @param event Event to be inserted in database
   * @throws DataAccessException
   */
  public void insert(Event event) throws DataAccessException {
    //We can structure our string to be similar to a sql command, but if we insert question
    //marks we can change them later with help from the statement
    String sql = "INSERT INTO Events (eventID, associatedUsername, personID, latitude, longitude, " +
            "country, city, eventType, year) VALUES(?,?,?,?,?,?,?,?,?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      //Using the statements built-in set(type) functions we can pick the question mark we want
      //to fill in and give it a proper value. The first argument corresponds to the first
      //question mark found in our sql String
      stmt.setString(1, event.getEventID());
      stmt.setString(2, event.getAssociatedUsername());
      stmt.setString(3, event.getPersonID());
      stmt.setFloat(4, event.getLatitude());
      stmt.setFloat(5, event.getLongitude());
      stmt.setString(6, event.getCountry());
      stmt.setString(7, event.getCity());
      stmt.setString(8, event.getEventType());
      stmt.setInt(9, event.getYear());

      stmt.executeUpdate();
    } catch (SQLException e) { // Keeps our technology choice hidden
      e.printStackTrace();
      throw new DataAccessException("Error encountered while inserting an event into the database");
    }
  }

  /**
   * Find Events in database
   * @param eventID Unique ID associated with event
   * @return An Event (if it exists)
   * @throws DataAccessException
   */
  public Event find(String eventID) throws DataAccessException {
    Event event;
    ResultSet rs;
    String sql = "SELECT * FROM Events WHERE EventID = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, eventID);
      rs = stmt.executeQuery();
      if (rs.next()) {
        event = new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                rs.getInt("year"));
        return event;
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding an event in the database");
    }

  }

  /**
   * Clear all Events in database
   * @throws DataAccessException
   */
  public void clear() throws DataAccessException {
    String sql = "DELETE FROM Events";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while clearing the event table");
    }
  }
}
