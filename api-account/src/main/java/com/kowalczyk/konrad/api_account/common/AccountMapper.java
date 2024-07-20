package com.kowalczyk.konrad.api_account.common;

import com.kowalczyk.konrad.api_account.entity.AccountEntity;
import com.kowalczyk.konrad.api_account.rest.input.Account;
import com.kowalczyk.konrad.api_account.rest.output.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountDTO accountToAccountDTO(Account account);

    Account accountDTOToAccount(AccountDTO accountDTO);
}
