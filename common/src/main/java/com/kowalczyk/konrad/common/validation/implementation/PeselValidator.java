package com.kowalczyk.konrad.common.validation.implementation;


import com.kowalczyk.konrad.common.validation.annotation.PeselValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import static com.kowalczyk.konrad.common.ValidationHelper.*;

public class PeselValidator implements ConstraintValidator<PeselValid, String> {
    @Override
    public void initialize(PeselValid peselAgeValid) {

    }

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext constraintValidatorContext) {

        if(Objects.isNull(pesel)) {
            formatNullMessage(constraintValidatorContext, "PESEL");
            return Boolean.FALSE;
        } else if (pesel.length() != 11) {
            formatAdditionalSizeMessage(constraintValidatorContext, "PESEL", 11);
            return Boolean.FALSE;
        }

        int year = Integer.parseInt(pesel.substring(0, 2));
        int month = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));

        if (month > 80) {
            year += 1800;
            month -= 80;
        } else if (month > 60) {
            year += 2200;
            month -= 60;
        } else if (month > 40) {
            year += 2100;
            month -= 40;
        } else if (month > 20) {
            year += 2000;
            month -= 20;
        } else {
            year += 1900;
        }

        try {
            LocalDate birthDate = LocalDate.of(year, month, day);
            return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
        } catch (Exception e) {
            formatMessage(constraintValidatorContext, String.format("Invalid date extracted from PESEL - %s", e.getMessage()));
            return Boolean.FALSE;
        }
    }
}
