package com.kowalczyk.konrad.api_account.rest.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kowalczyk.konrad.api_account.validation.annotation.PeselAgeValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Size(min = 11, max = 11)
    @PeselAgeValid
    private String pesel;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 0, message = "Initial balance must be non-negative")
    private double balance;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String currency;

}
