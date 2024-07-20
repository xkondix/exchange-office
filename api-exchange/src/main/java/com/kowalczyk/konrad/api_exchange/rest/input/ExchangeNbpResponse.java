package com.kowalczyk.konrad.api_exchange.rest.input;

import lombok.Data;

import java.util.List;

@Data
public class ExchangeNbpResponse {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;
}
