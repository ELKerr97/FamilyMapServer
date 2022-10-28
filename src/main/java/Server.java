import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.HttpServer;
import dao.JSONDataCache;
import handlers.*;
import model.Event;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * Main Server class to listen for HTTP requests from client
 * Determines the request and forwards request to Handler as JSON
 */
public class Server {

  /**
   * The maximum number of waiting incoming connections to queue.
   */
  public static final int MAX_WAITING_CONNECTIONS = 12;
  /**
   * HttpServer object to handle HTTP requests
   */
  private HttpServer server;

  /**
   * Initializes and runs the server
   * @param portNumber The port number on which the server should accept incoming client
   *                   connections
   */
  private void run(String portNumber){

    System.out.println("Initializing HTTP Server");

    try {
      // Create a new HttpServer object.
      // Rather than calling "new" directly, we instead create
      // the object by calling the HttpServer.create static factory method.
      // Just like "new", this method returns a reference to the new object.
      server = HttpServer.create(
              new InetSocketAddress(Integer.parseInt(portNumber)),
              // this is how you tell Java what port number it
              // should use for incoming client connections
              MAX_WAITING_CONNECTIONS); // <- ignore this
    }
    catch (IOException e) {
      e.printStackTrace();
      return;
    }

    server.setExecutor(null);

    System.out.println("Creating contexts");

    server.createContext("/user/register", new RegisterHandler());
    server.createContext("/user/login", new LoginHandler());
    server.createContext("/clear", new ClearHandler());
    server.createContext("/fill/", new FillHandler());
    server.createContext("/load", new LoadHandler());
    server.createContext("/person", new PersonHandler());
    server.createContext("/event/", new EventHandler());
    server.createContext("/", new FileHandler());

    System.out.println("Starting Server");

    server.start();

    System.out.println("Server Started");
  }

  /**
   * Sets the JSONDataCache from JSON files
   */
  private void setJSONCache() {
    try {
      System.out.println("Setting up JSON Data Cache");
      JSONDataCache jsonDataCache = JSONDataCache.getInstance();

      Object maleNamesReader = new JsonParser().parse(new FileReader("json/mnames.json"));
      Object femaleNamesReader = new JsonParser().parse(new FileReader("json/fnames.json"));
      Object lastNamesReader = new JsonParser().parse(new FileReader("json/snames.json"));
      Object locationsReader = new JsonParser().parse(new FileReader("json/locations.json"));

      JsonObject maleNameObject = (JsonObject) maleNamesReader;
      JsonObject femaleNameObject = (JsonObject) femaleNamesReader;
      JsonObject lastNameObject = (JsonObject) lastNamesReader;
      JsonObject locationsObject = (JsonObject) locationsReader;

      Gson gson = new Gson();

      JsonArray maleNames = maleNameObject.get("data").getAsJsonArray();
      JsonArray femaleNames = femaleNameObject.get("data").getAsJsonArray();
      JsonArray lastNames = lastNameObject.get("data").getAsJsonArray();
      JsonArray locations = locationsObject.get("data").getAsJsonArray();

      ArrayList<Event> events = new ArrayList<>();
      for(int i = 0; i < locations.size(); i ++){
        events.add(gson.fromJson(locations.get(i), Event.class));
      }

      ArrayList<String> mNames = new ArrayList<>();
      for(int i = 0; i < maleNames.size(); i ++){
        mNames.add(maleNames.get(i).toString());
      }

      ArrayList<String> fNames = new ArrayList<>();
      for(int i = 0; i < femaleNames.size(); i ++){
        fNames.add(femaleNames.get(i).toString());
      }

      ArrayList<String> lNames = new ArrayList<>();
      for(int i = 0; i < lastNames.size(); i ++){
        lNames.add(lastNames.get(i).toString());
      }

      jsonDataCache.setEvents(events);
      jsonDataCache.setMaleNames(mNames);
      jsonDataCache.setFemaleNames(fNames);
      jsonDataCache.setLastNames(lNames);

    } catch (FileNotFoundException ex){
      ex.printStackTrace();
    }
  }

  /**
   * Main entry point for server
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    String portNumber = args[0];
    Server server = new Server();
    server.setJSONCache();
    server.run(portNumber);
    //new Server().run(portNumber);

  }
}
