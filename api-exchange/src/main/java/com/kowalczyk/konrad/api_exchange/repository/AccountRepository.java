package com.kowalczyk.konrad.api_exchange.repository;


import com.kowalczyk.konrad.api_exchange.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {


}
