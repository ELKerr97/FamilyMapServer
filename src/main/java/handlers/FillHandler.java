package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.FillRequest;
import request.RegisterRequest;
import result.FillResult;
import result.RegisterResult;
import service.FillService;
import service.RegisterService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;

/**
 * This class handles a fill HTTP request from the client
 * Populates the server's database with generated data for the specified username
 * If there is any data in the database already associated with the given username, it is deleted
 */
public class FillHandler extends Handler implements HttpHandler {

  /**
   * Handles HTTP requests that have the "/fill/{username}{generations}" URL
   * @param exchange the exchange containing the request from the
   *      client and used to send the response
   * @throws IOException
   */
  @Override
  public void handle(HttpExchange exchange) throws IOException {

    try {
      if (exchange.getRequestMethod().equalsIgnoreCase("post")) {


        // Take request body and deserialize it into register request object
        String url = exchange.getRequestURI().toString();
        String[] reqData = url.split("/");
        FillRequest fillRequest;
        if(reqData.length == 3){
          // 4 generations
          fillRequest = new FillRequest(reqData[2], 4);
        } else {
          // Custom generations
          fillRequest = new FillRequest(reqData[2], Integer.parseInt(reqData[3]));
        }
        FillService fillService = new FillService();
        FillResult fillResult = fillService.fill(fillRequest);

        if(fillResult.isSuccess()){
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        } else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }

        Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
        Gson gson = new Gson();
        gson.toJson(fillResult, resBody);
        resBody.close();

      }
    } catch (IOException e) {
      // Some kind of internal error has occurred inside the server (not the
      // client's fault), so we return an "internal server error" status code
      // to the client.
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);

      // We are not sending a response body, so close the response body
      // output stream, indicating that the response is complete.
      exchange.getResponseBody().close();

      // Display/log the stack trace
      e.printStackTrace();
    }
  }
}
