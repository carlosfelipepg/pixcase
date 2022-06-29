package com.casepix.pix.application.rest.erros.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import java.util.List;

public class ErrorResponse {
    @Schema(
        description = "Error description"
    )
    private String message;
    @Schema(
        description = "Simple message to identify the problem"
    )
    @JsonInclude(Include.NON_NULL)
    private String error;
    @Schema(
        description = "Http Code"
    )
    private String code;
    private List<ErrorData> details;
    @Schema(
        type = "Datetime",
        description = "Timestamp error"
    )
    private Instant timestamp;

    public ErrorResponse withMessage(final String message) {
        return this.message == message ? this : new ErrorResponse(message, this.error, this.code, this.details, this.timestamp);
    }

    public ErrorResponse withError(final String error) {
        return this.error == error ? this : new ErrorResponse(this.message, error, this.code, this.details, this.timestamp);
    }

    public ErrorResponse withCode(final String code) {
        return this.code == code ? this : new ErrorResponse(this.message, this.error, code, this.details, this.timestamp);
    }

    public ErrorResponse withDetails(final List<ErrorData> details) {
        return this.details == details ? this : new ErrorResponse(this.message, this.error, this.code, details, this.timestamp);
    }

    public ErrorResponse withTimestamp(final Instant timestamp) {
        return this.timestamp == timestamp ? this : new ErrorResponse(this.message, this.error, this.code, this.details, timestamp);
    }

    public String getMessage() {
        return this.message;
    }

    public String getError() {
        return this.error;
    }

    public String getCode() {
        return this.code;
    }

    public List<ErrorData> getDetails() {
        return this.details;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setDetails(final List<ErrorData> details) {
        this.details = details;
    }

    public void setTimestamp(final Instant timestamp) {
        this.timestamp = timestamp;
    }

    public ErrorResponse(final String message, final String error, final String code, final List<ErrorData> details, final Instant timestamp) {
        this.message = message;
        this.error = error;
        this.code = code;
        this.details = details;
        this.timestamp = timestamp;
    }

    public ErrorResponse() {
    }
}
