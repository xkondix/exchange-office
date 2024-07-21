package com.kowalczyk.konrad.api_exchange.service.implementation;

import com.kowalczyk.konrad.api_exchange.repository.AccountRepository;
import com.kowalczyk.konrad.api_exchange.rest.output.AccountBalanceDTO;
import com.kowalczyk.konrad.api_exchange.service.ExchangeService;
import com.kowalczyk.konrad.api_exchange.service.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {
    private final WebClientService webClientService;
    private final AccountRepository accountRepository;
    private Mono<Double> cachedExchange;

    public ExchangeServiceImpl(WebClientService webClientService, AccountRepository accountRepository) {
        this.webClientService = webClientService;
        this.accountRepository = accountRepository;
    }

    public Mono<Double> getExchangeRate() {
        return cachedExchange;
    }

    @Override
    public Mono<AccountBalanceDTO> getAccount(String pesel) {
        return accountRepository.findByPesel(pesel)
                .flatMap(account -> getExchangeRate()
                        .map(exchangeRate -> {
                            double balanceInUsd = isCurrentCurrency("USD", account.getCurrency())
                                    ? account.getBalance()
                                    : account.getBalance() / exchangeRate;
                            double balanceInPln = isCurrentCurrency("PLN", account.getCurrency())
                                    ? account.getBalance()
                                    : account.getBalance() * exchangeRate;

                            return new AccountBalanceDTO(
                                    account.getFirstName(),
                                    account.getLastName(),
                                    balanceInPln + " PLN",
                                    balanceInUsd + " DOL"
                            );
                        })
                );
    }

    private boolean isCurrentCurrency(String currency, String databaseCurrency) {
        return currency.equalsIgnoreCase(databaseCurrency);
    }

    @Scheduled(fixedRate = 30000) //30s
    private void refreshRateCache() {
        cachedExchange = webClientService.getCurrentExchangeRate("A", "USD")
                .cache()
                .subscribeOn(Schedulers.boundedElastic());
    }

    @EventListener(ApplicationReadyEvent.class)
    private void getFirstTimeExchangeRateCache() {
        refreshRateCache();
    }
}
