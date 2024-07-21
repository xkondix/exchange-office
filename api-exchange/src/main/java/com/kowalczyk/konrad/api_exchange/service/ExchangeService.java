package com.kowalczyk.konrad.api_exchange.service;

import reactor.core.publisher.Mono;

public interface ExchangeService {

    Mono<Double> getCurrentExchangeRate(String table, String code);
}
