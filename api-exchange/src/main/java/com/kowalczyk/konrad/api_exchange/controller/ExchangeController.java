package com.kowalczyk.konrad.api_exchange.controller;

import com.kowalczyk.konrad.api_exchange.rest.input.CurrencyExchangeRequest;
import com.kowalczyk.konrad.api_exchange.rest.output.AccountBalanceDTO;
import com.kowalczyk.konrad.api_exchange.service.ExchangeService;
import com.kowalczyk.konrad.common.validation.annotation.PeselValid;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/api")
public class ExchangeController {
    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/exchange-usa")
    public Mono<ResponseEntity<Double>> getUsaExchangeRate() {
        return exchangeService.getExchangeRate()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }

    @GetMapping("/getAccountBalance")
    public Mono<ResponseEntity<AccountBalanceDTO>> getAccountBalance(@RequestParam @Valid @PeselValid String pesel) {
        return exchangeService.getAccount(pesel)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/getCurrencyValue")
    public Mono<ResponseEntity<String>> getAccountBalance(@RequestBody @Valid CurrencyExchangeRequest request) {
        return exchangeService.changeCurrencyValue(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }
}
