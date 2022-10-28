package result;

/**
 * Contains FillResult body
 */
public class FillResult extends Result{

  /**
   * Constructor for FillResult
   * @param message Message to tell if fill was success or failure
   * @param success If fill was successful or not
   */
  public FillResult(String message, boolean success) {
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
