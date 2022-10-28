package dao;

import model.Event;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
  private Database database;
  private User user;
  private UserDAO userDAO;

  @BeforeEach
  public void setUp() throws DataAccessException {

    database = new Database();
    user = new User("Guy123", "freeGuy123", "freeguy@gmail.com",
            "Guy", "Free", "m", "guy1234");
    Connection conn = database.getConnection();
    userDAO = new UserDAO(conn);
    userDAO.clear();
  }

  @AfterEach
  public void tearDown() {
    // Rollback changes
    database.closeConnection(false);
  }

  @Test
  public void insertPass() throws DataAccessException{
    userDAO.insert(user);
    User compareTest = userDAO.find(user.getUsername());
    assertNotNull(compareTest);
    assertEquals(compareTest, user);
  }

  @Test
  public void insertFail() throws DataAccessException {
    userDAO.insert(user);
    assertThrows(DataAccessException.class, () -> userDAO.insert(user));
  }

  @Test
  public void retrievalPass() throws DataAccessException {
    userDAO.insert(user);
    User testUser = userDAO.find(user.getUsername());
    assertNotNull(testUser);
    assertEquals(testUser, user);
  }

  @Test
  public void retrievalFail() throws DataAccessException {
    userDAO.insert(user);
    User testUser = userDAO.find("fakeID123");
    assertNull(testUser);

  }

  @Test
  public void clearPass() throws DataAccessException {
    User otherUser = new User("username123", "pass123", "email123", "first",
            "last", "m", "pweron123456");
    userDAO.insert(user);
    userDAO.insert(otherUser);

    userDAO.clear();

    assertNull(userDAO.find(otherUser.getUsername()));
    assertNull(userDAO.find(user.getUsername()));
  }
}
