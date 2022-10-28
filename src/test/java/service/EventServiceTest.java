package service;

import dao.EventDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import request.EventRequest;

public class EventServiceTest {

  private EventService eventService;
  private EventRequest eventRequest;

  @BeforeEach
  public void setUp() {
    eventService = new EventService();
    eventRequest = new EventRequest("id123", "auth1234");
  }

  @Test
  public void eventWithIDFail() {

  }

}
