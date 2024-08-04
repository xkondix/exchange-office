package com.kowalczyk.konrad.api_exchange.service.implementation;

import com.kowalczyk.konrad.api_exchange.repository.AccountRepository;
import com.kowalczyk.konrad.api_exchange.rest.input.CurrencyExchangeRequest;
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
                                    balanceInUsd + " USD"
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

    @Override
    public Mono<String> changeCurrencyValue(CurrencyExchangeRequest request) {
        if ("PLN".equalsIgnoreCase(request.getFromCurrency())) {
            return webClientService.getCurrentExchangeRate("A", request.getToCurrency())
                    .map(rate -> request.getValue() / rate + " " + request.getToCurrency().toUpperCase());
        } else if ("PLN".equalsIgnoreCase(request.getToCurrency())) {
            return webClientService.getCurrentExchangeRate("A", request.getFromCurrency())
                    .map(rate -> request.getValue() * rate + " " + request.getToCurrency().toUpperCase());
        } else {
            Mono<Double> fromRateMono = webClientService.getCurrentExchangeRate("A", request.getFromCurrency());
            Mono<Double> toRateMono = webClientService.getCurrentExchangeRate("A", request.getToCurrency());

            return Mono.zip(fromRateMono, toRateMono)
                    .map(tuple -> {
                        double fromRate = tuple.getT1();
                        double toRate = tuple.getT2();
                        double valuePln = request.getValue() * fromRate;
                        double valueInTargetCurrency = valuePln / toRate;
                        return valueInTargetCurrency + " " + request.getToCurrency().toUpperCase(); //StringUtils.SPACE not work? java 21
                    });
        }
    }

    private void comment() {
        //worse than zip?
//        return Mono.when(fromRateMono, toRateMono)
//                .then(Mono.defer(() -> {
//                    return fromRateMono.zipWith(toRateMono, (fromRate, toRate) -> {
//                        double valueInPln = request.getValue() * fromRate;
//                        double valueInTargetCurrency = valueInPln / toRate;
//                        return valueInTargetCurrency + " " + request.getToCurrency().toUpperCase();
//                    });
//                }));
//    }
    }

}

