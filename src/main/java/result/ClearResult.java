package result;

/**
 * Contains ClearResult body
 */
public class ClearResult extends Result{

  /**
   * Constructor for ClearResult
   * @param message Message to tell if clear was success or failure
   * @param success If clear was successful or not
   */
  public ClearResult(String message, boolean success) {
    this.message=message;
    this.success=success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }
}
