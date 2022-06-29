package com.casepix.pix.application.domain.helper;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidatorHelper<T> {

    public static <T> void validate(T domain) {
        var validates = Validation.buildDefaultValidatorFactory().getValidator().validate(domain);

        if (!validates.isEmpty()) {
            throw new ConstraintViolationException(validates);
        }
    }
}
