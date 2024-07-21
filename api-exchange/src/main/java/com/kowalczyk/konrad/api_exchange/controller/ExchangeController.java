package com.kowalczyk.konrad.api_exchange.controller;

import com.kowalczyk.konrad.api_exchange.service.CachedExchangeService;
import com.kowalczyk.konrad.api_exchange.service.implementation.CachedExchangeServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/api")
public class ExchangeController {
    private final CachedExchangeService cachedExchangeService;

    public ExchangeController(CachedExchangeService cachedExchangeService) {
        this.cachedExchangeService = cachedExchangeService;
    }

    @GetMapping("/exchange-usa")
    public Mono<Double> getUsaExchangeRate() {
        return cachedExchangeService.getExchangeRate();
    }
}
