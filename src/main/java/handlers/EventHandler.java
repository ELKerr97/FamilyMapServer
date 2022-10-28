package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import request.EventRequest;
import result.EventResult;
import service.EventService;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * This class handles HTTP requests to get an Event based on ID or all
 * Event objects associated with the User
 */
public class EventHandler extends Handler implements HttpHandler {

  /**
   * Handles HTTP requests that have "/event" or "/event/[eventID]" URL
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

        // Check if "Authorization" header is present
        if (reqHeaders.containsKey("Authorization")) {

          String authToken=reqHeaders.getFirst("Authorization");

          // Verify that it's the one we're looking for
          EventRequest eventRequest = new EventRequest(authToken);
          EventService eventService = new EventService();
          EventResult eventResult = eventService.getEvent(eventRequest);

          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

          // Convert resonse to JSON
          Gson gson = new Gson();

          OutputStream resBody = exchange.getResponseBody();
          String resultString = gson.toJson(eventResult); // Pass JSON to response body

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
    } catch (IOException | DataAccessException ex) {
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
