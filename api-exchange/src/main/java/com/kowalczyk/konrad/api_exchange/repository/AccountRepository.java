package com.kowalczyk.konrad.api_exchange.repository;


import com.kowalczyk.konrad.api_exchange.entity.AccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, String> {

    Mono<AccountEntity> findByPesel(String pesel);

}
