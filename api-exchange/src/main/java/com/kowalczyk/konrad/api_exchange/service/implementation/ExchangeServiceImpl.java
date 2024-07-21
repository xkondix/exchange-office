package com.kowalczyk.konrad.api_exchange.service.implementation;

import com.kowalczyk.konrad.api_exchange.rest.input.ExchangeNbpResponse;
import com.kowalczyk.konrad.api_exchange.service.CachedExchangeService;
import com.kowalczyk.konrad.api_exchange.service.ExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {

    private final WebClient exchangeClient;

    public ExchangeServiceImpl(@Qualifier("ExchangeClient") WebClient exchangeClient) {
        this.exchangeClient = exchangeClient;
    }

    public Mono<Double> getCurrentExchangeRate(String table, String code) {
        return exchangeClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/exchangerates/rates/{table}/{code}")
                        .build(table, code))
                .retrieve()
                .bodyToMono(ExchangeNbpResponse.class)
                .map(exchangeRateResponse -> exchangeRateResponse.getRates().getFirst().getMid())
                .onErrorMap(WebClientResponseException.class, ex -> {
                    log.error("Error occurred while fetching exchange rate: {}", ex.getMessage());
                    return ex;
                });
    }
}
