package com.revature.goshopping.exception;

import org.springframework.http.HttpStatus;

/**
 * the only exception that should be thrown in the service layer is this.
 * go ahead and catch exceptions not of this type in the service layer and
 * wrap it with this exception and then throw this. then the controller layer
 * just has to catch one type of exception and the status/reason will be known.
 */
public class ServiceException extends Exception {
  public final HttpStatus status;

  public ServiceException(HttpStatus status) {
    this.status = status;
  }

  public ServiceException(String reason) {
    this(HttpStatus.INTERNAL_SERVER_ERROR, reason);
  }

  public ServiceException(Throwable throwable) {
    this(HttpStatus.INTERNAL_SERVER_ERROR, throwable);
  }

  public ServiceException(HttpStatus status, String reason) {
    super(reason);
    this.status = status;
  }

  public ServiceException(HttpStatus status, Throwable throwable) {
    super(throwable);
    this.status = status;
  }

  @Override
  public String toString() {
    String msg = getMessage();
    return msg == null ? "ServiceException{status=" + status + '}' :
        "ServiceException{status=" + status + ", reason=" + msg + '}';
  }
}