package com.revature.goshopping.utility;

import com.revature.goshopping.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ControllerUtility {
  /**
   * a utility method to avoid repeating error handling logic and to
   * avoid dealing with creating the response entity in your controller methods.
   */
  public static <T> ResponseEntity<T> handle(Callback<T> cb) {
    try {
      return ResponseEntity
          .status(HttpStatus.OK)
          .contentType(MediaType.APPLICATION_JSON)
          .body(cb.execute());
    } catch (ServiceException e) {
      return onError(e);
    } catch (Throwable e) {
      return onError(e);
    }
  }

  private static <T> ResponseEntity<T> onError(Throwable e) {
    e.printStackTrace();
    return onError(new ServiceException(e));
  }

  private static <T> ResponseEntity<T> onError(ServiceException err) {
    if (err.status == HttpStatus.INTERNAL_SERVER_ERROR) {
      err.printStackTrace();
    } else {
      System.err.println(err.getMessage());
    }
    return new ResponseEntity<>(null, err.status);
  }

  @FunctionalInterface
  public interface Callback<T> {
    /**
     * @return the body you want in the response entity. can be null
     */
    T execute() throws Throwable;
  }
}
