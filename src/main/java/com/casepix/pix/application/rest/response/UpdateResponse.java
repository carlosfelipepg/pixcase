package com.casepix.pix.application.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Setter
@Getter
public class UpdateResponse {
    private String message;
    private HttpStatus status = HttpStatus.OK;
}
