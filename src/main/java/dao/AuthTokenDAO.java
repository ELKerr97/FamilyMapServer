package dao;

import model.AuthToken;
import model.Event;

import java.sql.*;

/**
 * Class handles connecting to the database
 */
public class AuthTokenDAO {

  /**
   * Connection object to connect to the server
   */
  private final Connection conn;

  /**
   * Constructor for an AuthToken object
   * @param conn - Connection object to connect to the server
   */
  public AuthTokenDAO(Connection conn){
    this.conn = conn;
  }

  /**
   * Inserts an authToken object into the database
   * @param authToken - authToken to be inserted into database
   */
  public void insert(AuthToken authToken) throws DataAccessException{

    String sql = "INSERT INTO AuthTokens (authtoken, username) VALUES(?,?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)){

      stmt.setString(1, authToken.getAuthtoken());
      stmt.setString(2, authToken.getUsername());

      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new DataAccessException("Error encountered when inserting Authtoken into " +
              "database");
    }
  }

  public AuthToken findWithUsername(String username) throws DataAccessException {
    AuthToken resultToken;
    ResultSet rs;
    String sql = "SELECT * FROM AuthTokens WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, username);
      rs = stmt.executeQuery();
      if(rs.next()){
        resultToken = new AuthToken(rs.getString("authtoken"),
                rs.getString("username"));
        return resultToken;
      } else {
        return null;
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new DataAccessException("Error occured when finding authoken in " +
              "database.");
    }
  }

  /**
   * Finds an AuthToken in the database using the 'authtoken' string
   * @param authToken - The string used to find the AuthToken in database
   * @return - The AuthToken object if found, null otherwise
   */
  public AuthToken find(String authToken) throws DataAccessException {
    AuthToken resultToken;
    ResultSet rs;
    String sql = "SELECT * FROM AuthTokens WHERE authtoken = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, authToken);
      rs = stmt.executeQuery();
      if(rs.next()){
        resultToken = new AuthToken(rs.getString("authtoken"),
                rs.getString("username"));
        return resultToken;
      } else {
        return null;
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new DataAccessException("Error occured when finding authoken in " +
              "database.");
    }

  }

  /**
   * Deletes an AuthToken in the database
   */
  public void clear() throws DataAccessException {
    String sql = "DELETE FROM AuthTokens";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.executeUpdate();
    } catch (SQLException ex){
      ex.printStackTrace();
      throw new DataAccessException("Error encountered while clearing the AuthTokens table");
    }
  }
}
