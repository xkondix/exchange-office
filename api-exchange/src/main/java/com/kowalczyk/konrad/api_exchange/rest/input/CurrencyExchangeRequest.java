package com.kowalczyk.konrad.api_exchange.rest.input;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyExchangeRequest {

    @NotBlank
    private String fromCurrency;

    @NotBlank
    private String toCurrency;

    @NotNull(message = "Balance  is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be greater than zero")
    private Double value;
}
