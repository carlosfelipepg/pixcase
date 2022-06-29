package com.casepix.pix.application.domain.filter;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeyFilter {
    private String typeKey;

    private String valueKey;

    private String typeAccount;

    private String numberAgency;

    private String numberAccount;

    private String accountHolderName;

    private String accountHolderLastName;

    private String typePerson;
}
