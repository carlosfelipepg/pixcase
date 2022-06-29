package com.casepix.pix.application.rest.annotation;

import com.casepix.pix.application.rest.erros.response.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Inherited
@ApiResponse(
        responseCode = "400",
        description = "${swagger-config.responses.error.400}",
        content = {@Content(
                mediaType = "application/json",
                schema = @Schema(
                        implementation = ErrorResponse.class
                )
        )}
)
public @interface ApiResponseBadRequest {
}

