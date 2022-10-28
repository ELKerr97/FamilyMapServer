package service;

import dao.*;
import request.ClearRequest;
import result.ClearResult;

import java.sql.Connection;

/**
 * ClearService receives a ClearRequest and returns a ClearResult after using a DAO to
 * perform database functions
 */
public class ClearService {

  private AuthTokenDAO authTokenDAO;
  private Database database;
  private EventDAO eventDAO;
  private PersonDAO personDAO;
  private UserDAO userDAO;
  /**
   * Constructor for ClearService
   */
  public ClearService() {
    this.database = new Database();
  }

  /**
   * Clear all tables from database, returns ClearResult
   */
  public ClearResult clear() {

    try {
      authTokenDAO = new AuthTokenDAO(database.openConnection());
      eventDAO = new EventDAO(database.getConnection());
      personDAO = new PersonDAO(database.getConnection());
      userDAO = new UserDAO(database.getConnection());

      authTokenDAO.clear();
      eventDAO.clear();
      personDAO.clear();
      userDAO.clear();

      return new ClearResult("Clear success", true);

    } catch (DataAccessException ex) {
      ex.printStackTrace();
      return new ClearResult("Error: Clear failed, internal error", false);
    } finally {
      database.closeConnection(true);
    }

  }
}
