package com.kowalczyk.konrad.api_exchange.rest.input;

import lombok.Data;

@Data
public class Rate {
    private String no;
    private String effectiveDate;
    private double mid;
}
