package dao;

import model.AuthToken;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {
  private Database database;
  private AuthToken authToken;
  private AuthTokenDAO authTokenDAO;

  @BeforeEach
  public void setUp() throws DataAccessException {
    database = new Database();
    authToken = new AuthToken("asdflkj12340987", "Guy123");
    Connection conn = database.getConnection();
    authTokenDAO = new AuthTokenDAO(conn);
    authTokenDAO.clear();
  }

  @AfterEach
  public void tearDown() {
    database.closeConnection(false);
  }

  @Test
  public void insertPass() throws DataAccessException {
    authTokenDAO.insert(authToken);
    AuthToken compareTest = authTokenDAO.find(authToken.getAuthtoken());
    assertNotNull(compareTest);
    assertEquals(compareTest, authToken);
  }

  @Test
  public void insertFail() throws DataAccessException {
    authTokenDAO.insert(authToken);
    assertThrows(DataAccessException.class, () -> authTokenDAO.insert(authToken));
  }

  @Test
  public void retrievalPass() throws DataAccessException {
    authTokenDAO.insert(authToken);
    AuthToken testToken = authTokenDAO.find(authToken.getAuthtoken());
    assertNotNull(testToken);
    assertEquals(testToken, authToken);
  }

  @Test
  public void retrievalFail() throws DataAccessException {
    authTokenDAO.insert(authToken);
    AuthToken testToken = authTokenDAO.find("fakeID123");
    assertNull(authTokenDAO.find("fakeauthToken123"));
    assertNull(testToken);

  }

  @Test
  public void clearPass() throws DataAccessException {
    AuthToken otherToken = new AuthToken("asdflkj098274", "user1234587");
    authTokenDAO.insert(authToken);
    authTokenDAO.insert(otherToken);

    authTokenDAO.clear();

    assertNull(authTokenDAO.find(otherToken.getAuthtoken()));
    assertNull(authTokenDAO.find(authToken.getAuthtoken()));
  }

}
