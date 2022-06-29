package com.casepix.pix.application.rest.response;

import com.casepix.pix.application.domain.enumaration.TypeAccountEnum;
import com.casepix.pix.application.domain.enumaration.TypeKeyEnum;
import com.casepix.pix.application.domain.enumaration.TypePersonEnum;
import com.casepix.pix.application.rest.validator.StringEnumeration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PixResponse {

    @Schema(implementation = TypeKeyEnum.class, description = "Tipo Chave", example = "CELULAR")
    @StringEnumeration(enumClass = TypeKeyEnum.class)
    private String typeKey;

    @Schema(type = "string", description = "Valor Chave", example = "85989999999")
    private String valueKey;

    @Schema(implementation = TypeAccountEnum.class, description = "Tipo Conta", example = "CORRENTE")
    @StringEnumeration(enumClass = TypeAccountEnum.class)
    private String typeAccount;

    @Schema(type = "number", description = "Numero Agencia", example = "0001")
    private String numberAgency;

    @Schema(type = "number", description = "Numero Conta", example = "84849122")
    private String numberAccount;

    @Schema(type = "string", description = "Nome Correntista", example = "Joao")
    private String accountHolderName;

    @Schema(type = "string", description = "Sobrenome Correntista", example = "Nascimento")
    private String accountHolderLastName;

    @Schema(implementation = TypePersonEnum.class, description = "Tipo Pessoa", example = "F ou J")
    @StringEnumeration(enumClass = TypePersonEnum.class)
    private String typePerson;
}
