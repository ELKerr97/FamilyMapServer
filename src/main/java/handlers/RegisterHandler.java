package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.RegisterRequest;
import result.RegisterResult;
import service.RegisterService;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;

/**
 * Handles a register HTTP request to create a new user account, generaet 4 generations of
 * ancestor data, log the user in and return an AuthToken
 */
public class RegisterHandler extends Handler implements HttpHandler {

  /**
   * Handles HTTP requests that contain the "/user/register" URL
   *
   * @param exchange the exchange containing the request from the
   *                 client and used to send the response
   * @throws IOException
   */
  @Override
  public void handle(HttpExchange exchange) throws IOException {

    try {
      if (exchange.getRequestMethod().equalsIgnoreCase("post")) {

        // Get the request body input stream
        InputStream reqBody = exchange.getRequestBody();

        // Read JSON string from the input stream
        String reqData = readString(reqBody);

        Gson gson = new Gson();

        // Take request body and deserialize it into register request object
        RegisterRequest registerRequest = gson.fromJson(reqData, RegisterRequest.class);
        RegisterService registerService = new RegisterService();
        RegisterResult registerResult = registerService.register(registerRequest);

        if (registerResult.isSuccess()){
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        } else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }

        Writer resBody = new OutputStreamWriter(exchange.getResponseBody());
        gson.toJson(registerResult, resBody);
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
