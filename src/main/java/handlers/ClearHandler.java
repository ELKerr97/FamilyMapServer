package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.ClearRequest;
import result.ClearResult;
import service.ClearService;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

/**
 * This class handles an HTTP request to delete ALL data from the database, including
 * user, authtoken, person and event data
 */
public class ClearHandler extends Handler implements HttpHandler {

  /**
   * Handle the HTTP requests that contain "/clear" URL path
   * @param exchange the exchange containing the request from the
   *      client and used to send the response
   * @throws IOException
   */
  @Override
  public void handle(HttpExchange exchange) throws IOException {

    try{
      if(exchange.getRequestMethod().equalsIgnoreCase("post")){

        Gson gson = new Gson();
        ClearService clearService = new ClearService();
        ClearResult clearResult = clearService.clear();

        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
        gson.toJson(clearResult, resBody);
        resBody.close();
      }
    } catch (IOException ex){
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
