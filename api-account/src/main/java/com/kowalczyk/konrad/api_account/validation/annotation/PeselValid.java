package com.kowalczyk.konrad.api_account.validation.annotation;

import com.kowalczyk.konrad.api_account.validation.implementation.PeselValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PeselValidator.class)
public @interface PeselValid {

    String message() default "The age cannot be less than 18 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
