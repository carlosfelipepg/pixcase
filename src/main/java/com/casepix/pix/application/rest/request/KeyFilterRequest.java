package com.casepix.pix.application.rest.request;


import com.casepix.pix.application.domain.enumaration.TypeKeyEnum;
import com.casepix.pix.application.rest.validator.StringEnumeration;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
@Getter
@Setter
public class KeyFilterRequest {

    @Parameter(description = "Unique id key",
            schema = @Schema(type = "String", format = "UUID", example = "877f8c2c-e448-4630-ba31-fc7ed5b68aa9"))
    private String id;

    @Parameter(description = "Type key", schema = @Schema( implementation = TypeKeyEnum.class))
    @StringEnumeration(
            enumClass =
                    TypeKeyEnum.class)
    private String typeKey;

    @Parameter(description = "Number Agency", schema = @Schema( type = "string", example = "0001"))
    private String numberAgency;

    @Parameter(description = "Number Account", schema = @Schema( type = "string", example = "12345678"))
    private String numberAccount;

    @Parameter(description = "Name user", schema = @Schema( type = "string", example = "Jean"))
    private String accountHolderName;

    @Parameter(description = "Filtering by date of record creation",
            schema = @Schema(type = "string", format = "date", example = "2022-07-30"))
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createdAt;
}
