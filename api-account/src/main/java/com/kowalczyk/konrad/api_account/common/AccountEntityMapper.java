package com.kowalczyk.konrad.api_account.common;

import com.kowalczyk.konrad.api_account.entity.AccountEntity;
import com.kowalczyk.konrad.api_account.rest.input.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)

public interface AccountEntityMapper {

    Account accountEntityToAccount(AccountEntity accountEntity);

    AccountEntity accountToAccountEntity(Account account);
}
