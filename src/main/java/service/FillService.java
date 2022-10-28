package service;

import com.google.gson.stream.JsonReader;
import dao.*;
import model.Person;
import request.FillRequest;
import result.FillResult;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * FillService takes a FillRequest and returns a FillResult after using a DAO to
 * perform database functions
 */
public class FillService extends Service{

  private JSONDataCache jsonDataCache = JSONDataCache.getInstance();
  private ArrayList<Person> people = new ArrayList<>();
  private int addedAncestors = 0;

  /**
   * Constructor for FillService
   */
  public FillService() {
    this.database = new Database();
  }

  /**
   * Fills the database with generations of people associated with user
   * @param fillRequest FillRequest containing request body
   * @return A FillResult
   */
  public FillResult fill(FillRequest fillRequest){
    FillResult fillResult;
    String message;
    try {
      int generations = fillRequest.getGenerations();
      String username = fillRequest.getUsername();

      fillAncestors(generations, username);
      message = "Successfully added " + addedAncestors + " persons " +
              "and " + addedAncestors*3 + " events into the database.";

      return new FillResult(message, true);

    } catch (DataAccessException ex){

      ex.printStackTrace();
      database.closeConnection(false);
      return new FillResult("Error: Fill failed", false);

    }
  }

  private void fillAncestors (int generations, String username) throws DataAccessException{
    EventsGenerator eventsGenerator;
    int genCounter = generations;

    // For each generation there will be x2 couples
      UUID wifeID = UUID.randomUUID();
      UUID husbandID = UUID.randomUUID();
      Random random = new Random();
      String wifeName = jsonDataCache.getFemaleNames().get(random.nextInt(jsonDataCache.getFemaleNames().size()));
      String husbandName = jsonDataCache.getMaleNames().get(random.nextInt(jsonDataCache.getMaleNames().size()));
      String husbandLastName = jsonDataCache.getLastNames().get(random.nextInt(jsonDataCache.getLastNames().size()));
      String wifeLastName = jsonDataCache.getLastNames().get(random.nextInt(jsonDataCache.getLastNames().size()));

      Person wife = new Person(wifeID.toString(), username,
              wifeName, wifeLastName, "f", null, null, husbandID.toString());
      Person husband = new Person(husbandID.toString(), username, husbandName, husbandLastName,
              "m", null, null, wifeID.toString());

      // Generate events for husband and wife
      eventsGenerator = new EventsGenerator(wife, husband, 1);
      eventsGenerator.generateEvents();

      fillAncestors_Helper(wife, username, generations, 2);
      fillAncestors_Helper(husband, username, generations, 2);

  }

  private void fillAncestors_Helper(Person child, String username, int maxGeneration,
                                    int currentGeneration) throws DataAccessException {

    if (currentGeneration == maxGeneration) {
      personDAO = new PersonDAO(database.openConnection());
      personDAO.insert(child);
      addedAncestors += 1;
      database.closeConnection(true);

    } else {
      // Make parents
      UUID momID=UUID.randomUUID();
      UUID dadID=UUID.randomUUID();

      // Get random namdes and last names for mom and dad
      Random random=new Random();
      String momName=jsonDataCache.getFemaleNames().get(random.nextInt(jsonDataCache.getFemaleNames().size()));
      String momLastName=jsonDataCache.getLastNames().get(random.nextInt(jsonDataCache.getLastNames().size()));
      String dadName=jsonDataCache.getMaleNames().get(random.nextInt(jsonDataCache.getMaleNames().size()));
      String dadLastName=child.getLastName(); // child name is same as dad name

      // Make mom and dat person objects
      Person mom=new Person(momID.toString(), username, momName, momLastName, "f",
              null, null, dadID.toString());
      Person dad=new Person(dadID.toString(), username, dadName, dadLastName, "m",
              null, null, momID.toString());

      // Generate events for mom and dad
      EventsGenerator eventsGenerator=new EventsGenerator(mom, dad, currentGeneration);
      eventsGenerator.generateEvents();

      // Add mom and dad to people so we can get their ancestors


      personDAO=new PersonDAO(database.openConnection());
      child.setFatherID(dad.getPersonID());
      child.setMotherID(mom.getPersonID());
      personDAO.insert(child);
      addedAncestors += 1;
      database.closeConnection(true);

      fillAncestors_Helper(mom, username, maxGeneration, currentGeneration + 1);
      fillAncestors_Helper(dad, username, maxGeneration, currentGeneration + 1);
    }
  }
}
