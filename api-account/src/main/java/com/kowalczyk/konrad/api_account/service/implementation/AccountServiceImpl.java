package com.kowalczyk.konrad.api_account.service.implementation;

import com.kowalczyk.konrad.api_account.common.AccountEntityMapper;
import com.kowalczyk.konrad.api_account.repository.AccountRepository;
import com.kowalczyk.konrad.api_account.rest.input.Account;
import com.kowalczyk.konrad.api_account.service.AccountService;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountEntityMapper accountToAccountEntity;

    public AccountServiceImpl(AccountRepository accountRepository, AccountEntityMapper accountToAccountEntity) {
        this.accountRepository = accountRepository;
        this.accountToAccountEntity = accountToAccountEntity;
    }

    @Override
    public void createAccount(Account account) {
        accountRepository.save(accountToAccountEntity.accountToAccountEntity(account));
    }
}
