package result;

/**
 * Contains LoadResult body
 */
public class LoadResult extends Result{

  /**
   * Constructor for LoadResult
   * @param message Message to tell if load was success or failure
   * @param success If load was successful or not
   */
  public LoadResult(String message, boolean success) {
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
