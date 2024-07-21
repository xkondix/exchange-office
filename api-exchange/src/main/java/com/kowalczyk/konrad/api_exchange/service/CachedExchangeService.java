package com.kowalczyk.konrad.api_exchange.service;

import reactor.core.publisher.Mono;

public interface CachedExchangeService {
    public Mono<Double> getExchangeRate();
}
