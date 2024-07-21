package com.kowalczyk.konrad.api_account.common;

import com.kowalczyk.konrad.api_account.entity.AccountEntity;
import com.kowalczyk.konrad.api_account.rest.output.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    public final AccountMapper ACCOUNT_MAPPER = Mappers.getMapper(AccountMapper.class);

    AccountDTO accountEntityToAccountDTO(AccountEntity accountEntity);

    AccountEntity accountDTOToAccountEntity(AccountDTO accountDTO);
}
