package com.kowalczyk.konrad.api_exchange.service;

import reactor.core.publisher.Mono;

public interface WebClientService {

    Mono<Double> getCurrentExchangeRate(String table, String code);
}
