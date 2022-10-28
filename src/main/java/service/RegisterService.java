package service;

import dao.*;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;
import request.RegisterRequest;
import result.RegisterResult;

import java.util.UUID;

/**
 * RegisterService will take a RegisterRequest and return a RegisterResult after using a DAO to
 * perform database functions
 */
public class RegisterService extends Service {

  /**
   * Constructor for RegisterService
   */
  public RegisterService() {
    this.database = new Database();
  }

  /**
   * Register user
   * @param registerRequest RegisterRequest containing request body
   * @return RegisterResult containing result body
   */
  public RegisterResult register(RegisterRequest registerRequest){
    RegisterResult registerResult;
    String message;
    try {

      userDAO = new UserDAO(database.openConnection());
      personDAO = new PersonDAO(database.getConnection());
      authTokenDAO = new AuthTokenDAO(database.getConnection());

      UUID uuid = UUID.randomUUID();
      String newPersonID = uuid.toString();
      User newUser = new User(
              registerRequest.getUsername(),
              registerRequest.getPassword(),
              registerRequest.getEmail(),
              registerRequest.getFirstName(),
              registerRequest.getLastName(),
              registerRequest.getGender(),
              newPersonID
              );

      if(newUser.getUsername() == null || newUser.getPassword() == null || newUser.getEmail() == null
      || newUser.getFirstName() == null || newUser.getLastName() == null || newUser.getGender() == null){

        message = "Error: Register Request failed";
        registerResult = new RegisterResult(null, null, null,
                false, message);
      } else {
        // Make authtoken for user?
        UUID randomToken = UUID.randomUUID();
        AuthToken newToken = new AuthToken(randomToken.toString(), newUser.getUsername());
        authTokenDAO.insert(newToken);
        message = "Register Request succeeded";
        registerResult = new RegisterResult(newToken.getAuthtoken(), newUser.getUsername(),
                newUser.getPersonID(), true, message);
        // Add user to database
        userDAO.insert(newUser);

        Person newPerson = new Person(newUser.getPersonID(),
                newUser.getUsername(),
                newUser.getFirstName(),newUser.getLastName(),
                newUser.getGender(),
                "", "", "");
        personDAO.insert(newPerson);
        database.closeConnection(true);
        EventsGenerator eventsGenerator = new EventsGenerator(newPerson, 0);
        eventsGenerator.generateEvents();
      }

      return registerResult;


    } catch (DataAccessException ex) {

      ex.printStackTrace();
      message = "ERROR: Register Request failed. Username, Password or Email already exist";
      registerResult = new RegisterResult(null, null, null,
              false, message);
      database.closeConnection(false);
      return registerResult;
    }
  }
}
