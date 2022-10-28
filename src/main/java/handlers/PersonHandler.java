package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import request.EventRequest;
import request.PersonRequest;
import result.EventResult;
import result.PersonResult;
import service.EventService;
import service.PersonService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * This class handles HTTP requests to load a single Person based on ID or all Person objects associated with user
 *
 */
public class PersonHandler extends Handler implements HttpHandler {

  /**
   * Handles HTTP requests that have "/person/[personID]" URL
   * @param exchange the exchange containing the request from the
   *      client and used to send the response
   * @throws IOException
   */
  @Override
  public void handle(HttpExchange exchange) throws IOException {

    boolean success = false;

    try {
      if(exchange.getRequestMethod().equalsIgnoreCase("get")) {

        // Get HTTP request headers
        Headers reqHeaders=exchange.getRequestHeaders();
        System.out.println(exchange.getRequestHeaders().toString());
        // Check if "Authorization" header is present
        if (reqHeaders.containsKey("Authorization")) {

          String authToken = reqHeaders.getFirst("Authorization");
          String url = exchange.getRequestURI().toString();
          String[] reqData = url.split("/");
          PersonRequest personRequest;
          if (reqData.length == 3){
            String id = reqData[reqData.length - 1];
            personRequest = new PersonRequest(authToken, id);
          } else {
            personRequest = new PersonRequest(authToken, "");
          }
          // Verify that it's the one we're looking for
          PersonService personService = new PersonService();
          PersonResult personResult = personService.getPerson(personRequest);

          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

          // Convert resonse to JSON
          Gson gson = new Gson();

          OutputStream resBody = exchange.getResponseBody();
          String resultString = gson.toJson(personResult); // Pass JSON to response body

          writeString(resultString, resBody);

          resBody.close();

          success = true;
        }

        if (!success) {
          // The HTTP request was invalid somehow, so we return a "bad request"
          // status code to the client.
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
          // Since the client request was invalid, they will not receive the
          // list of games, so we close the response body output stream,
          // indicating that the response is complete.
          exchange.getResponseBody().close();
        }
      }
    } catch (IOException ex) {
      // Some kind of internal error has occurred inside the server (not the
      // client's fault), so we return an "internal server error" status code
      // to the client.
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      // Since the server is unable to complete the request, the client will
      // not receive the list of games, so we close the response body output stream,
      // indicating that the response is complete.
      exchange.getResponseBody().close();

      // Display/log the stack trace
      // Lets you see what went wrong
      ex.printStackTrace();
    }
  }
}
