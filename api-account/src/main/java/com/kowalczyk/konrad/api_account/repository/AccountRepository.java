package com.kowalczyk.konrad.api_account.repository;

import com.kowalczyk.konrad.api_account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
}
