package com.kowalczyk.konrad.api_exchange.rest.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountBalanceDTO {

    private String firstName;
    private String lastName;
    private String balancePln;
    private String balanceUsd;

}
