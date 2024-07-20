package com.kowalczyk.konrad.api_account.validation.implementation;

import com.kowalczyk.konrad.api_account.validation.annotation.PeselAgeValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class PeselAgeValidator implements ConstraintValidator<PeselAgeValid, String> {
    @Override
    public void initialize(PeselAgeValid peselAgeValid) {

    }

    @Override
    public boolean isValid(String pesel, ConstraintValidatorContext constraintValidatorContext) {

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

        LocalDate birthDate = LocalDate.of(year, month, day);
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }
}
