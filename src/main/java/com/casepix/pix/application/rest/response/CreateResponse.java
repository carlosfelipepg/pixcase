package com.casepix.pix.application.rest.response;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Setter
@Getter
public class CreateResponse {
    private String message;
    private UUID id;
    private HttpStatus status = HttpStatus.OK;
}
