package com.casepix.pix.application.rest.exception;

import com.casepix.pix.application.rest.erros.response.ErrorData;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends ApplicationException {
  public BadRequestException() {
    this(
        "Problem with the data in the request",
        "The application cannot or will not process the request due to something perceived as a client error",
        null);
  }

  public BadRequestException(String title) {
    this(title, null, null);
  }

  public BadRequestException(String title, String details) {
    this(title, details, null);
  }

  public BadRequestException(String title, List<ErrorData> details) {
    this(HttpStatus.BAD_REQUEST,title, details, null);
  }

  public BadRequestException(Throwable cause) {
    this(null, null, cause);
  }

  public BadRequestException(String title, String details, Throwable cause) {
    super(HttpStatus.BAD_REQUEST, title, details, cause);
  }

  public BadRequestException(HttpStatus httpStatus, String title, List<ErrorData> errorList, Throwable cause) {
    super(httpStatus, title, errorList, cause);
  }
}
