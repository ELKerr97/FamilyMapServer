package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import request.LoginRequest;
import result.LoginResult;
import service.LoginService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

/**
 * This class handles a login HTTP request from client to log the user in
 * Returns an authtoken
 */
public class LoginHandler extends Handler implements HttpHandler {

  /**
   * Handles HTTP requests that contain the "/user/login" URL
   * @param exchange the exchange containing the request from the
   *      client and used to send the response
   * @throws IOException
   */
  @Override
  public void handle(HttpExchange exchange) throws IOException {
    try {
      if (exchange.getRequestMethod().equalsIgnoreCase("post")){

        InputStream requestBody = exchange.getRequestBody();

        String requestData = readString(requestBody);

        Gson gson = new Gson();

        LoginRequest loginRequest = gson.fromJson(requestData, LoginRequest.class);
        LoginService loginService = new LoginService();
        LoginResult loginResult = loginService.login(loginRequest);

        if (loginResult.isSuccess()){
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        } else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }

        Writer responsebody = new OutputStreamWriter(exchange.getResponseBody());
        gson.toJson(loginResult, responsebody);
        responsebody.close();

      }

    } catch (IOException ex){
      // Some kind of internal error has occurred inside the server (not the
      // client's fault), so we return an "internal server error" status code
      // to the client.
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);

      // We are not sending a response body, so close the response body
      // output stream, indicating that the response is complete.
      exchange.getResponseBody().close();

      // Display/log the stack trace
      ex.printStackTrace();
    }
  }
}
