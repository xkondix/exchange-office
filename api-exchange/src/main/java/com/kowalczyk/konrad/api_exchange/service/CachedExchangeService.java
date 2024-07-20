package com.kowalczyk.konrad.api_exchange.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class CachedExchangeService {
    private final ExchangeService exchangeRateService;
    private Mono<Double> cachedExchange;

    public CachedExchangeService(ExchangeService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public Mono<Double> getExchangeRate() {
        return cachedExchange;
    }

    @Scheduled(fixedRate = 30000) //30s
    public void refreshRateCache() {
        cachedExchange = exchangeRateService.getCurrentExchangeRate("A", "USD")
                .cache()
                .subscribeOn(Schedulers.boundedElastic());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getFirstTimeExchangeRateCache() {
        refreshRateCache();
    }
}
