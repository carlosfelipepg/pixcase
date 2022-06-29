package com.casepix.pix.application.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class KeyResponse {

    private String typeKey;

    private String valueKey;

    private String typeAccount;

    private String numberAgency;

    private String numberAccount;

    private String accountHolderName;

    private String accountHolderLastName;

    private String typePerson;

    private HttpStatus status = HttpStatus.OK;
}
