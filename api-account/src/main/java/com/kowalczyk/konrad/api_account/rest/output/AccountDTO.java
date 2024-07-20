package com.kowalczyk.konrad.api_account.rest.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String firstName;
    private String lastName;
    private double balance;
    private String currency;


}
