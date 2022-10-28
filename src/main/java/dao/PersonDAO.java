package dao;

import model.Person;
import result.Result;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class handles connecting to the database and performing SQL commands
 */
public class PersonDAO {

  /**
   * Connection object for connecting to the server
   */
  private final Connection conn;

  /**
   * Constructor for a PersonDAO object
   * @param conn - Connection object for connecting to the server
   */
  public PersonDAO(Connection conn){
    this.conn = conn;
  }

  /**
   * Inserts a Person in the database
   * @param person - The Person object to be inserted in Database
   */
  public void insert(Person person) throws DataAccessException{
    // String similar to a SQL command
    String sql = "INSERT INTO People (personID, associatedUsername, firstname, lastName," +
            "gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";

    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      // Assign values to question marks
      stmt.setString(1, person.getPersonID());
      stmt.setString(2, person.getAssociatedUsername());
      stmt.setString(3, person.getFirstName());
      stmt.setString(4, person.getLastName());
      stmt.setString(5, person.getGender());
      stmt.setString(6, person.getFatherID());
      stmt.setString(7, person.getMotherID());
      stmt.setString(8, person.getSpouseID());

      stmt.executeUpdate();
    } catch (SQLException ex){
      ex.printStackTrace();
      throw new DataAccessException("Error encountered while inserting new Person " +
              "into database");
    }
  }

  /**
   * Find a Person object in the database using a 'personID' string
   * @param personID - String to be used to find Person object in database
   * @return - Return the Person object if found, null otherwise
   */
  public Person find(String personID) throws  DataAccessException{
    Person person;
    ResultSet resultSet;
    String sql = "SELECT * FROM People WHERE personID = ?;";
    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, personID);
      resultSet = stmt.executeQuery();
      // If the result was successful
      if(resultSet.next()){
        person = new Person(resultSet.getString("personID"),
                resultSet.getString("associatedUsername"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("gender"),
                resultSet.getString("fatherID"),
                resultSet.getString("motherID"),
                resultSet.getString("spouseID"));
        return person;
      } else {
        return null;
      }
    } catch(SQLException ex){
      ex.printStackTrace();
      throw new DataAccessException("Error encountered while finding a person in " +
              "the database.");
    }
  }

  public Person[] findAll() throws DataAccessException {
    Person[] people;
    ResultSet resultSet;
    String sql = "SELECT * FROM People;";
    try(PreparedStatement stmt = conn.prepareStatement(sql)) {

      resultSet = stmt.executeQuery();

      Person person;
      System.out.println(resultSet.getString("personID"));

      return new Person[5];

    } catch (SQLException ex){
      ex.printStackTrace();
      throw new DataAccessException("Error encountered while finding all people "+
              "in database.");
    }
  }


  /**
   * Remove all Person objects from the database
   */
  public void clear() throws DataAccessException{
    String sql = "DELETE FROM People";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.executeUpdate();
    } catch (SQLException ex){
      ex.printStackTrace();
      throw new DataAccessException("Error encountered when clearing the people table");
    }
  }
}
