package com.casepix.pix.application.rest.exception;

import com.casepix.pix.application.rest.erros.response.ErrorData;
import com.casepix.pix.application.rest.erros.response.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public abstract class ApplicationException extends RuntimeException {

  private final ErrorResponse errorResponse;
  private final HttpStatus httpStatus;

  protected ApplicationException(HttpStatus status, String title, String details, Throwable cause) {
    this(
        new ErrorResponse(title, details, String.valueOf(status.value()), null, Instant.now()),
        status,
        cause);
  }

  protected ApplicationException(HttpStatus status, String title, List<ErrorData> details, Throwable cause) {
    this(
            new ErrorResponse(title, null, String.valueOf(status.value()), details, Instant.now()),
            status,
            cause);
  }

  private ApplicationException(
      ErrorResponse errorResponse, HttpStatus httpStatus, Throwable cause) {
    super(errorResponse.toString(), cause);
    this.errorResponse = errorResponse;
    this.httpStatus = httpStatus;
  }

  public ErrorResponse getErrorResponse() {
    return this.errorResponse;
  }

  public HttpStatus getHttpStatus() {
    return this.httpStatus;
  }
}
