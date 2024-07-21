package com.kowalczyk.konrad.api_account.rest.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String firstName;
    private String lastName;
    private double balance;
    private String currency;


}
