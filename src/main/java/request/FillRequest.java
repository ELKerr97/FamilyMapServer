package request;

/**
 * FillRequest object to be sent to FillService
 */
public class FillRequest {

  /**
   * Username of user
   */
  private String username;
  /**
   * Optional number of generations to be filled (defualt is 4) in user family tree
   */
  private int generations;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public int getGenerations() {
    return generations;
  }

  public void setGenerations(int generations) {
    this.generations=generations;
  }

  /**
   * Constructor for default generations
   * @param username Username of user
   */
  public FillRequest(String username) {
    this.username = username;
    this.generations = 4; // Default generations
  }

  /**
   * Constructor for specified number of generations
   * @param username Username of user
   * @param generations Number of generations to be filled in user family tree
   */
  public FillRequest(String username, int generations){
    this.username = username;
    this.generations = generations;
  }

}
