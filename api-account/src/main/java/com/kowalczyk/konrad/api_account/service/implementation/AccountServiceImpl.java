package com.kowalczyk.konrad.api_account.service.implementation;

import com.kowalczyk.konrad.api_account.common.AccountEntityMapper;
import com.kowalczyk.konrad.api_account.common.AccountMapper;
import com.kowalczyk.konrad.api_account.repository.AccountRepository;
import com.kowalczyk.konrad.api_account.rest.input.Account;
import com.kowalczyk.konrad.api_account.rest.output.AccountDTO;
import com.kowalczyk.konrad.api_account.service.AccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import static com.kowalczyk.konrad.api_account.common.AccountEntityMapper.ACCOUNT_ENTITY_MAPPER;
import static com.kowalczyk.konrad.api_account.common.AccountMapper.ACCOUNT_MAPPER;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public AccountServiceImpl(AccountRepository accountRepository, AccountEntityMapper accountToAccountEntity
            , AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void createAccount(Account account) {
        entityManager.persist(ACCOUNT_ENTITY_MAPPER.accountToAccountEntity(account));
    }

    @Override
    @Transactional
    public void updateAccount(Account account) {
        accountRepository.save(ACCOUNT_ENTITY_MAPPER.accountToAccountEntity(account));
    }

    @Override
    public AccountDTO getAccount(String pesel) {
        return accountRepository.findById(pesel)
                .map(ACCOUNT_MAPPER::accountEntityToAccountDTO)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
