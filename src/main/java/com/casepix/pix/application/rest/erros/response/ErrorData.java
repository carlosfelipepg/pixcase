//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.casepix.pix.application.rest.erros.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.v3.oas.annotations.media.Schema;

public class ErrorData {
    @Schema(
        description = "Attribute error name"
    )
    @JsonInclude(Include.NON_NULL)
    private String field;
    @Schema(
        description = "Description of the error for that attribute"
    )
    private String message;
    @Schema(
        description = "Received value"
    )
    private Object value;

    public ErrorData withField(final String field) {
        return this.field == field ? this : new ErrorData(field, this.message, this.value);
    }

    public ErrorData withMessage(final String message) {
        return this.message == message ? this : new ErrorData(this.field, message, this.value);
    }

    public ErrorData withValue(final Object value) {
        return this.value == value ? this : new ErrorData(this.field, this.message, value);
    }

    public String getField() {
        return this.field;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getValue() {
        return this.value;
    }

    public void setField(final String field) {
        this.field = field;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    public ErrorData(final String field, final String message, final Object value) {
        this.field = field;
        this.message = message;
        this.value = value;
    }

    public ErrorData() {
    }
}
