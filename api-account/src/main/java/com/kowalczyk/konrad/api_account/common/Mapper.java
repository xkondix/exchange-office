package com.kowalczyk.konrad.api_account.common;

import com.kowalczyk.konrad.api_account.entity.AccountEntity;
import com.kowalczyk.konrad.api_account.rest.input.Account;
import com.kowalczyk.konrad.api_account.rest.output.AccountDTO;
import com.kowalczyk.konrad.common.Role;

public class Mapper {

    public static AccountEntity accountToAccountEntity(Account account) {
        if ( account == null ) {
            return null;
        }

        return new AccountEntity(account.getPesel()
                , Role.USER
                , account.getFirstName()
                , account.getLastName()
                , account.getBalance()
                , account.getCurrency());
    }

    public static AccountDTO accountEntityToAccountDTO (AccountEntity accountEntity) {
        if ( accountEntity == null ) {
            return null;
        }

        return new AccountDTO(accountEntity.getFirstName()
                , accountEntity.getLastName()
                , accountEntity.getBalance()
                , accountEntity.getCurrency());
    }

}
