package com.kowalczyk.konrad.api_exchange.service.implementation;

import com.kowalczyk.konrad.api_exchange.entity.AccountEntity;
import com.kowalczyk.konrad.api_exchange.repository.AccountRepository;
import com.kowalczyk.konrad.api_exchange.rest.input.CurrencyExchangeRequest;
import com.kowalczyk.konrad.api_exchange.rest.output.AccountBalanceDTO;
import com.kowalczyk.konrad.api_exchange.service.WebClientService;
import com.kowalczyk.konrad.common.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceImplTest {


    private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final WebClientService webClientService = mock(WebClientService.class);
    @Spy
    private final ExchangeServiceImpl exchangeService = new ExchangeServiceImpl(webClientService, accountRepository);

    @Test
    public void testGetAccountWithCurrencyPLN() {
        String pesel = "98101598643";
        AccountEntity accountEntity = new AccountEntity("98101598643", Role.USER, "Jan"
                , "Kowalski", 400.0, "PLN");

        when(accountRepository.findByPesel(pesel)).thenReturn(Mono.just(accountEntity));
        when(exchangeService.getExchangeRate()).thenReturn(Mono.just(4.0));

        Mono<AccountBalanceDTO> result = exchangeService.getAccount(pesel);

        StepVerifier.create(result)
                .expectNextMatches(dto ->
                        dto.getFirstName().equals("Jan") &&
                                dto.getLastName().equals("Kowalski") &&
                                dto.getBalancePln().equals("400.0 PLN") &&
                                dto.getBalanceUsd().equals("100.0 USD")
                )
                .verifyComplete();
    }

    @Test
    public void testGetAccountWithCurrencyUSD() {
        String pesel = "98101598643";
        AccountEntity accountEntity = new AccountEntity("98101598643", Role.USER, "Jan"
                , "Kowalski", 100.0, "USD");

        when(accountRepository.findByPesel(pesel)).thenReturn(Mono.just(accountEntity));
        when(exchangeService.getExchangeRate()).thenReturn(Mono.just(4.0));

        Mono<AccountBalanceDTO> result = exchangeService.getAccount(pesel);

        StepVerifier.create(result)
                .expectNextMatches(dto ->
                        dto.getFirstName().equals("Jan") &&
                                dto.getLastName().equals("Kowalski") &&
                                dto.getBalancePln().equals("400.0 PLN") &&
                                dto.getBalanceUsd().equals("100.0 USD")
                )
                .verifyComplete();
    }

    @Test
    public void testChangeCurrencyValueFromPlnToUsd() {
        CurrencyExchangeRequest request = new CurrencyExchangeRequest("PLN", "USD", 100.0);

        when(webClientService.getCurrentExchangeRate("A", "USD")).thenReturn(Mono.just(4.0));

        Mono<String> result = exchangeService.changeCurrencyValue(request);

        StepVerifier.create(result)
                .expectNext("25.0 USD")
                .verifyComplete();
    }

    @Test
    public void testChangeCurrencyValueFromUsdToPln() {
        CurrencyExchangeRequest request = new CurrencyExchangeRequest("USD", "PLN", 100.0);

        when(webClientService.getCurrentExchangeRate("A", "USD")).thenReturn(Mono.just(4.0));

        Mono<String> result = exchangeService.changeCurrencyValue(request);

        StepVerifier.create(result)
                .expectNext("400.0 PLN")
                .verifyComplete();
    }

    @Test
    public void testChangeCurrencyValueFromUsdToEur() {
        CurrencyExchangeRequest request = new CurrencyExchangeRequest("USD", "EUR", 100.0);

        when(webClientService.getCurrentExchangeRate("A", "USD")).thenReturn(Mono.just(4.0));
        when(webClientService.getCurrentExchangeRate("A", "EUR")).thenReturn(Mono.just(5.0));

        Mono<String> result = exchangeService.changeCurrencyValue(request);

        StepVerifier.create(result)
                .expectNext("80.0 EUR")
                .verifyComplete();
    }

}