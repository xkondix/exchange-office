package com.kowalczyk.konrad.api_exchange.service;

import com.kowalczyk.konrad.api_exchange.rest.input.CurrencyExchangeRequest;
import com.kowalczyk.konrad.api_exchange.rest.output.AccountBalanceDTO;
import reactor.core.publisher.Mono;

public interface ExchangeService {
    public Mono<Double> getExchangeRate();

    public Mono<AccountBalanceDTO> getAccount(String pesel);

    public Mono<String> changeCurrencyValue(CurrencyExchangeRequest currencyExchangeRequest);
}
