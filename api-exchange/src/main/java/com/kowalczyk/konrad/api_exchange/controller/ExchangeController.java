package com.kowalczyk.konrad.api_exchange.controller;

import com.kowalczyk.konrad.api_exchange.service.CachedExchangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ExchangeController {
    private final CachedExchangeService cachedExchangeRateService;

    public ExchangeController(CachedExchangeService cachedExchangeRateService) {
        this.cachedExchangeRateService = cachedExchangeRateService;
    }

    @GetMapping("/exchange-usa")
    public Mono<Double> getUsaExchangeRate() {
        return cachedExchangeRateService.getExchangeRate();
    }
}
