package com.kowalczyk.konrad.api_account.validation.annotation;

import com.kowalczyk.konrad.api_account.validation.implementation.PeselAgeValidator;
import jakarta.validation.Payload;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PeselAgeValidator.class)
public @interface PeselAgeValid {

    String message() default "The age cannot be less than 18 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
