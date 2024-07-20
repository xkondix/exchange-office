package com.kowalczyk.konrad.api_account.controller;

import com.kowalczyk.konrad.api_account.rest.input.Account;
import com.kowalczyk.konrad.api_account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody @Valid Account account) {
        accountService.createAccount(account);
        return ResponseEntity.ok("Account created successfully");
    }
}
