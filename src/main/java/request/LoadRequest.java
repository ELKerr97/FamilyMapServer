package request;

import model.Event;
import model.Person;
import model.User;

/**
 * LoadRequest to be sent to LoadService
 */
public class LoadRequest {

  /**
   * Users to be loaded
   */
  private User[] users;
  /**
   * Persons to be loaded
   */
  private Person[] persons;
  /**
   * Events to be loaded
   */
  private Event[] events;

  /**
   * Constructor for LoadRequest
   */
  public LoadRequest(User[] users, Person[] persons, Event[] events) {
    this.users=users;
    this.persons=persons;
    this.events=events;
  }

  public User[] getUsers() {
    return users;
  }

  public void setUsers(User[] users) {
    this.users=users;
  }

  public Person[] getPersons() {
    return persons;
  }

  public void setPersons(Person[] persons) {
    this.persons=persons;
  }

  public Event[] getEvents() {
    return events;
  }

  public void setEvents(Event[] events) {
    this.events=events;
  }
}
