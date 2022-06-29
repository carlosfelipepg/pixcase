package com.casepix.pix.application.rest.request;

import com.casepix.pix.application.domain.enumaration.TypeAccountEnum;
import com.casepix.pix.application.domain.enumaration.TypeKeyEnum;
import com.casepix.pix.application.domain.enumaration.TypePersonEnum;
import com.casepix.pix.application.rest.validator.StringEnumeration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKeyRequest {
    @Schema(type = "string", description = "Id Key", example = "877f8c2c-e448-4630-ba31-fc7ed5b68aa9")
    @NotNull
    private String id;

    @Schema(type = "string", description = "Valor Chave", example = "+5585989999999")
    @NotBlank
    @Size(max = 77)
    private String valueKey;

    @Schema(implementation = TypeAccountEnum.class, description = "Tipo Conta", example = "CORRENTE")
    @NotNull
    @StringEnumeration(enumClass = TypeAccountEnum.class)
    private String typeAccount;

    @Schema(type = "number", description = "Numero Agencia", example = "0001")
    @NotNull
    @Pattern(regexp = "[0-9]*")
    @Size(max = 4)
    private String numberAgency;

    @Schema(type = "number", description = "Numero Conta", example = "84849122")
    @NotNull
    @Pattern(regexp = "[0-9]*")
    @Size(max = 8)
    private String numberAccount;

    @Schema(type = "string", description = "Nome Correntista", example = "Joao")
    @NotBlank
    @Size(max = 30)
    private String accountHolderName;

    @Schema(type = "string", description = "Sobrenome Correntista", example = "Nascimento")
    @Size(max = 45)
    private String accountHolderLastName;

    @Schema(implementation = TypePersonEnum.class, description = "Tipo Pessoa", example = "F ou J")
    @NotNull
    @StringEnumeration(enumClass = TypePersonEnum.class)
    private String typePerson;
}
