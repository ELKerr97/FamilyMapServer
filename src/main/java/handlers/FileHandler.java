package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Sends files corresponding to test page asked for by HTTP request
 */
public class FileHandler extends Handler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
// .close() will asynchronously send the bytes to the client
    try {
      if (exchange.getRequestMethod().toLowerCase().equals("get")) {
        String path = exchange.getRequestURI().toString(); // Relative path in the web folder
        File file = new File("web" + path);
        if(path.equals("/")){
          // Send response headers
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

          // Read bytes from index.html and send in response body
          OutputStream respBody = exchange.getResponseBody();
          path += "index.html";
          file = new File("web" + path);
          FileInputStream fileInputStream = new FileInputStream(file);
          respBody.write(fileInputStream.readAllBytes());
          exchange.getResponseBody().close();

        } else if (file.exists()){ // check if file exists
          // Send response headers
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

          // Read bytes from index.html and send in response body
          OutputStream respBody = exchange.getResponseBody();
          FileInputStream fileInputStream = new FileInputStream(file);
          respBody.write(fileInputStream.readAllBytes());
          exchange.getResponseBody().close();

        } else if (!file.exists()){
          // Send response headers
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
          // Read bytes from index.html and send in response body
          OutputStream respBody = exchange.getResponseBody();
          File notFoundFile = new File("web/HTML/404.html");
          FileInputStream fileInputStream = new FileInputStream(notFoundFile);
          respBody.write(fileInputStream.readAllBytes());
          exchange.getResponseBody().close();
        }
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
