package dao;

import model.User;

import java.sql.*;

/**
 * Class handles connecting to the database
 */
public class UserDAO {

  /**
   * Connection object for connecting to the server
   */
  private final Connection conn;

  /**
   * Constructor for a UserDAO object
   * @param conn - Connection object for connecting to the server
   */
  public UserDAO(Connection conn){
    this.conn = conn;
  }

  /**
   * Insert a User object into the database
   * @param user - User object to be inserted in database
   */
  public void insert(User user) throws DataAccessException{
    // String similar to a SQL command
    String sql = "INSERT INTO Users (username, password, email, firstName, lastName," +
            "gender, personID) VALUES(?,?,?,?,?,?,?)";

    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      // Assign values to question marks
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getEmail());
      stmt.setString(4, user.getFirstName());
      stmt.setString(5, user.getLastName());
      stmt.setString(6, user.getGender());
      stmt.setString(7, user.getPersonID());

      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new DataAccessException("Error encountered while inserting new User " +
              "into database");
    }
  }

  /**
   * Find a User in the database using the 'username' string
   * @param username - String used to find the User object in database
   * @return - Return User object if found, null otherwise
   */
  public User find(String username) throws DataAccessException{
    User user;
    ResultSet resultSet;
    String sql = "SELECT * FROM Users WHERE username = ?";
    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, username);
      resultSet = stmt.executeQuery();
      // If result was successful
      if(resultSet.next()){
        user = new User(resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("gender"),
                        resultSet.getString("personID"));

        return user;
      } else {
        return null;
      }
    } catch (SQLException ex){
      ex.printStackTrace();
      throw new DataAccessException("Error encountered while finding User " +
              "into database");
    }
  }

  /**
   * Remove User object from database
   */
  public void clear() throws DataAccessException{
    String sql = "DELETE FROM Users";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.executeUpdate();
    } catch (SQLException ex){
      ex.printStackTrace();
      throw new DataAccessException("Error encountered when clearing the Users table");
    }
  }
}
