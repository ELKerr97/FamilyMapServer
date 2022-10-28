package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import request.LoadRequest;
import request.LoginRequest;
import result.LoadResult;
import result.LoginResult;
import result.RegisterResult;
import service.LoginService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;

/**
 * Handles a load HTTP request from client
 * Clears all data from the database
 * Loads the user, person, and event data from the request body into the database
 */
public class LoadHandler extends Handler implements HttpHandler {

  /**
   * Handles HTTP requests that have "/load" URL
   * @param exchange the exchange containing the request from the
   *      client and used to send the response
   * @throws IOException
   */
  @Override
  public void handle(HttpExchange exchange) throws IOException {


  }
}
