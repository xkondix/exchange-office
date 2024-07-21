package com.kowalczyk.konrad.api_account.rest.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kowalczyk.konrad.common.validation.annotation.PeselValid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @PeselValid
    private String pesel;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotNull(message = "Balance  is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Balance must be greater than zero")
    private Double balance;

    @NotBlank(message = "Currency  is mandatory")
    private String currency;

}
