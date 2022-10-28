package service;

import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.Person;
import request.PersonRequest;
import result.PersonResult;

import java.util.Objects;

/**
 * PersonService will take a PersonRequest and return a PersonResult after using a DAO to
 * perform database functions
 */
public class PersonService extends Service{

  /**
   * Constructor for PersonService
   */
  public PersonService() {
    this.database = new Database();
  }

  /**
   * Get a Person or all Persons in dabase
   * @param personRequest PersonRequest containing request body
   * @return PersonResult containing response body
   */
  public PersonResult getPerson(PersonRequest personRequest){
    String message;
    PersonResult personResult;
    try {
      personDAO = new PersonDAO(database.openConnection());
      if (personRequest.getPersonID().equals("")){
        // Get all people
        // TODO: Implement findAll(), need help iterating over rows of data
        if (personDAO.findAll() != null){
          personResult = new PersonResult(personDAO.findAll(), null, true);
        } else {
          message = "Error: No one found in database";
          personResult = new PersonResult(null, message, false);
        }

      } else {

        String personID = personRequest.getPersonID();

        Person person = personDAO.find(personID);
        if (person == null) {

          message = "Error: Person does not exist.";
          personResult = new PersonResult(null, null, null,
                  null, null, null, null, null,
                  message, false);
        } else {

          message = "Success! Person was found with ID " + person.getPersonID();
          personResult = new PersonResult(person.getPersonID(),person.getAssociatedUsername(), person.getFirstName(),
                  person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(),
                  person.getSpouseID(), message, true);
        }
      }
    } catch (DataAccessException ex){
      ex.printStackTrace();
      personResult = new PersonResult(null, "Error: Data Access Exception", false);
    } finally {
      database.closeConnection(true);
    }
    return personResult;
  }

}
