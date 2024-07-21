package com.kowalczyk.konrad.api_account.service;

import com.kowalczyk.konrad.api_account.rest.input.Account;
import com.kowalczyk.konrad.api_account.rest.output.AccountDTO;

public interface AccountService {

    public void createAccount(Account account);
    public void updateAccount(Account account);
    public AccountDTO getAccount(String pesel);

}
