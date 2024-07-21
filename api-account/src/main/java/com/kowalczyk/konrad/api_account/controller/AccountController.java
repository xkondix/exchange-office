package com.kowalczyk.konrad.api_account.controller;

import com.kowalczyk.konrad.api_account.rest.input.Account;
import com.kowalczyk.konrad.api_account.rest.output.AccountDTO;
import com.kowalczyk.konrad.api_account.service.AccountService;
import com.kowalczyk.konrad.common.validation.annotation.PeselValid;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

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
        return ok("Account created successfully");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateAccount(@RequestBody @Valid Account account) {
        accountService.updateAccount(account);
        return ok("Account created successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<AccountDTO> getAccount(@RequestParam @Valid @PeselValid String pesel) {
        AccountDTO account = accountService.getAccount(pesel);
        return ResponseEntity.ok(account);
    }
}
