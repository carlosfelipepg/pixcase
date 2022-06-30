package com.casepix.pix.application.domain.dto;

import com.casepix.pix.application.domain.enumaration.TypeAccountEnum;
import com.casepix.pix.application.domain.enumaration.TypeKeyEnum;
import com.casepix.pix.application.domain.enumaration.TypePersonEnum;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class KeyDto {
    private String id;

    private TypeKeyEnum typeKey;

    private String valueKey;

    private TypeAccountEnum typeAccount;

    private String numberAgency;

    private String numberAccount;

    private String accountHolderName;

    private String accountHolderLastName;

    private TypePersonEnum typePerson;

    private String createdAt;
}
