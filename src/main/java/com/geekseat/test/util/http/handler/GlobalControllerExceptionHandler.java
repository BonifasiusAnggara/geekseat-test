package com.geekseat.test.util.http.handler;

import com.geekseat.test.util.http.exceptions.BadRequestException;
import com.geekseat.test.util.http.exceptions.NotFoundException;
import com.geekseat.test.util.http.exceptions.RequestValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
class GlobalControllerExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public @ResponseBody HttpErrorInfo handleNotFoundExceptions(
    WebRequest request, NotFoundException ex) {

    return createHttpErrorInfo(NOT_FOUND, request, ex);
  }

  @ResponseStatus(UNPROCESSABLE_ENTITY)
  @ExceptionHandler(RequestValidationException.class)
  public @ResponseBody HttpErrorInfo handleInvalidInputException(WebRequest request, RequestValidationException ex) {

    return createHttpErrorInfo(UNPROCESSABLE_ENTITY, request, ex);
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public @ResponseBody HttpErrorInfo handleBadRequestExceptions(WebRequest request, BadRequestException ex) {

    return createHttpErrorInfo(BAD_REQUEST, request, ex);
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public @ResponseBody
  RequestValidationErrorInfo handleArgumentNotValidExceptions(WebRequest request, MethodArgumentNotValidException ex) {

    return processFieldErrors(BAD_REQUEST, request, ex);
  }

  private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, WebRequest request, Exception ex) {

    final String path = ((ServletWebRequest)request).getRequest().getRequestURI();
    final String message = ex.getMessage();

    LOG.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
    return new HttpErrorInfo(httpStatus, path, message);
  }

  private RequestValidationErrorInfo processFieldErrors(HttpStatus httpStatus, WebRequest request, MethodArgumentNotValidException ex) {

    final String path = ((ServletWebRequest)request).getRequest().getRequestURI();

    BindingResult result = ex.getBindingResult();
    List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

    RequestValidationErrorInfo error = new RequestValidationErrorInfo(httpStatus, path, "Invalid Input");

    for (org.springframework.validation.FieldError fieldError: fieldErrors) {
      error.addFieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
    }

    return error;
  }
}
