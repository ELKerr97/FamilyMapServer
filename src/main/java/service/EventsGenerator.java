package service;

import dao.*;
import model.Event;
import model.Person;
import model.User;

import java.util.Random;
import java.util.UUID;

/**
 * Generate basic events for a person
 */
public class EventsGenerator extends Service {

  private final JSONDataCache jsonDataCache = JSONDataCache.getInstance();
  private final int generation;
  private final String personID;
  private final String username;
  private final String husbandID;
  private final String wifeID;
  private final int USER_BIRTH_YEAR = 1990;
  private final int YEAR_BUFFER = 15; // Age to have kids and get married

  public EventsGenerator(Person person, int generation){
    this.database = new Database();
    this.generation = generation;
    this.personID = person.getPersonID();
    this.username = person.getAssociatedUsername();
    this.wifeID = null;
    this.husbandID = null;
  }

  public EventsGenerator(Person wife, Person husband, int generation){
    this.database = new Database();
    this.generation = generation;
    this.wifeID = wife.getPersonID();
    this.husbandID = husband.getPersonID();
    this.username = wife.getAssociatedUsername();
    this.personID = null;
  }

  public void generateEvents() throws DataAccessException {
    try{
      eventDAO = new EventDAO(database.openConnection());
      if(generation == 0){
        // User's person only needs a birth event
        Event birth = birthEvent(USER_BIRTH_YEAR);
        eventDAO.insert(birth);

      } else {
        // Parents and ancestors
        // BIRTH
        int birthYear = USER_BIRTH_YEAR - generation * YEAR_BUFFER;
        Event wifeBirth = birthEvent(birthYear);
        Event husbandBirth = birthEvent(birthYear);

        // MARRIAGE
        int marriageYear = birthYear + YEAR_BUFFER;
        Event marriage = marriageEvent(marriageYear);


        // DEATH
        int deathYear = marriageYear + 20;
        Event wifeDeath = deathEvent(deathYear);
        Event husbandDeath = deathEvent(deathYear);

        // Insert events into database
        husbandBirth.setPersonID(husbandID);
        eventDAO.insert(husbandBirth);
        wifeBirth.setPersonID(wifeID);
        eventDAO.insert(wifeBirth);
        marriage.setPersonID(husbandID);
        eventDAO.insert(marriage);
        marriage.setPersonID(wifeID);
        eventDAO.insert(marriage);
        husbandDeath.setPersonID(husbandID);
        eventDAO.insert(husbandDeath);
        wifeDeath.setPersonID(wifeID);
        eventDAO.insert(wifeDeath);

      }
    } catch (DataAccessException ex){
      ex.printStackTrace();
    } finally {
      database.closeConnection(true);
    }

  }

  private Event birthEvent(int birthYear) {
    Random random = new Random();
    Event birthEvent = jsonDataCache.getEvents()
            .get(random.nextInt(jsonDataCache.getEvents().size()));
    UUID eventID =UUID.randomUUID();
    birthEvent.setEventID(eventID.toString());
    birthEvent.setAssociateUsername(username);
    birthEvent.setEventType("birth");
    birthEvent.setYear(birthYear);
    birthEvent.setPersonID(personID);

    return birthEvent;
  }

  private Event deathEvent(int deathYear) {
    // Randomly select event location
    Random random = new Random();
    Event deathEvent = jsonDataCache.getEvents()
            .get(random.nextInt(jsonDataCache.getEvents().size()));

    UUID eventID =UUID.randomUUID();
    deathEvent.setEventID(eventID.toString());
    deathEvent.setAssociateUsername(username);
    deathEvent.setEventType("death");
    deathEvent.setYear(deathYear);
    deathEvent.setPersonID(personID);

    return deathEvent;
  }


  private Event marriageEvent(int marriageYear){
    // Randomly select event location
    Random random = new Random();
    Event marriageEvent = jsonDataCache.getEvents()
            .get(random.nextInt(jsonDataCache.getEvents().size()));

    UUID eventID =UUID.randomUUID();
    marriageEvent.setEventID(eventID.toString());
    marriageEvent.setAssociateUsername(username);
    marriageEvent.setEventType("marriage");
    marriageEvent.setYear(marriageYear);

    return marriageEvent;
  }


}
