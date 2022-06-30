package com.casepix.pix.application.domain.model;

import com.casepix.pix.application.domain.enumaration.TypeAccountEnum;
import com.casepix.pix.application.domain.enumaration.TypeKeyEnum;
import com.casepix.pix.application.domain.enumaration.TypePersonEnum;
import lombok.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Key {
    @Id
    private String _id;

    @Indexed(unique = true)
    private String id;

    @NotNull
    private TypeKeyEnum typeKey;

    @NotBlank
    @Size(max = 77)
    @Indexed(unique = true)
    private String valueKey;

    @NotNull
    private TypeAccountEnum typeAccount;

    @NotBlank
    @Pattern(regexp = "[0-9]*")
    private String numberAgency;

    @NotBlank
    @Pattern(regexp = "[0-9]*")
    private String numberAccount;

    @NotBlank
    @Size(max = 30)
    private String accountHolderName;

    @NotBlank
    @Size(max = 45)
    private String accountHolderLastName;

    @NotNull
    private TypePersonEnum typePerson;

    private String createdAt;
}