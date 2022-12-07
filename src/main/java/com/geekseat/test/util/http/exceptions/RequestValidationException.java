package com.geekseat.test.util.http.exceptions;

public class RequestValidationException extends RuntimeException {
  public RequestValidationException() {}

  public RequestValidationException(String message) {
    super(message);
  }

  public RequestValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public RequestValidationException(Throwable cause) {
    super(cause);
  }
}
