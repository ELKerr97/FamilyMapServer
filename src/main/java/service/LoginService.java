package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.UserDAO;
import model.AuthToken;
import model.Person;
import model.User;
import request.LoginRequest;
import result.LoginResult;

import java.util.Objects;

/**
 * LoginService will take a LoginRequest and return a LoginResponse after using a DAO to
 * perform database functions
 */
public class LoginService extends Service{

  /**
   * Constructor for LoginService
   */
  public LoginService() {
    database = new Database();
  }

  /**
   * Log user in
   * @param loginRequest LoginRequest containing request body
   * @return LoginResult containint response body
   */
  public LoginResult login(LoginRequest loginRequest){
    LoginResult loginResult;
    String message;
    try {
      String username = loginRequest.getUsername();
      userDAO = new UserDAO(database.openConnection());
      User user = userDAO.find(username);
      if (user != null){

        String password = user.getPassword();

        // Check password
        if (password.equals(loginRequest.getPassword())) {

          message = "Login success";
          AuthTokenDAO authTokenDAO = new AuthTokenDAO(database.getConnection());
          String authToken = authTokenDAO.findWithUsername(username).getAuthtoken();
          loginResult = new LoginResult(authToken, user.getUsername(), user.getPersonID(),
                  true, message);

        } else {

          message = "Error: User exists, but password is incorrect.";
          loginResult = new LoginResult(null, null, null, false,
                  message);
        }

      } else {

        message = "Error: Username does not exist.";
        loginResult = new LoginResult(null, null, null, false,
                message);

      }

      return loginResult;

    } catch (DataAccessException ex){
      ex.printStackTrace();
    } finally {
      database.closeConnection(true);
    }
    return null;
  }
}
