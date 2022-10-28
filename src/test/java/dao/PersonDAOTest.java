package dao;

import model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOTest {
  private Database db;
  private Person person;
  private PersonDAO personDAO;

  @BeforeEach
  public void setUp() throws DataAccessException {
    db = new Database();
    person = new Person("Guy1234", "GuyNobody", "Guy",
            "Free", "m", "Dad1234", "Mom123",
            "Spouse123");
    Connection conn = db.getConnection();
    personDAO = new PersonDAO(conn);
    personDAO.clear();
  }

  @AfterEach
  public void tearDown() {
    db.closeConnection(false);
  }

  @Test
  public void insertPass() throws DataAccessException {
    personDAO.insert(person);
    Person compareTest = personDAO.find(person.getPersonID());
    assertNotNull(compareTest);
    assertEquals(person, compareTest);
  }

  @Test
  public void insertFail() throws DataAccessException {
    personDAO.insert(person);
    assertThrows(DataAccessException.class, () -> personDAO.insert(person));
  }

  @Test
  public void retrievalPass() throws DataAccessException {
    personDAO.insert(person);
    Person testperson = personDAO.find(person.getPersonID());
    assertNotNull(testperson);
    assertEquals(testperson, person);
  }

  @Test
  public void retrievalFail() throws DataAccessException {
    personDAO.insert(person);
    Person testperson = personDAO.find("fakeID123");
    assertNull(testperson);

  }

  @Test
  public void clearPass() throws DataAccessException {
    Person otherperson = new Person("personname123", "uer1234", "first",
            "last", "m", "father12345", "mother4678", "spuse123");
    personDAO.insert(person);
    personDAO.insert(otherperson);

    personDAO.clear();

    assertNull(personDAO.find(otherperson.getPersonID()));
    assertNull(personDAO.find(person.getPersonID()));
  }
}

