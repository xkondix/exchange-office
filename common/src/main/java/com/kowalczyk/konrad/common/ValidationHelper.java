package com.kowalczyk.konrad.common;

import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class ValidationHelper {

    public static final String NULL_MSG = "Field %s cannot be null";
    public static final String STRING_SIZE_MSG = "field “%s” must have a length of %s";


    public static ConstraintValidatorContext formatMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return context;
    }

    public static ConstraintValidatorContext formatNullMessage(ConstraintValidatorContext context, String field) {
        String formattedMsg = String.format(NULL_MSG, field);
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(formattedMsg)
                .addConstraintViolation();
        return context;
    }

    public static ConstraintValidatorContext formatAdditionalSizeMessage(ConstraintValidatorContext context
            , String field, int len) {
        String formattedMsg = String.format(STRING_SIZE_MSG, field, len);
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(formattedMsg)
                .addConstraintViolation();
        return context;
    }

}