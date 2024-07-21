package com.kowalczyk.konrad.api_exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableWebFlux
@EnableR2dbcRepositories
@ComponentScan(basePackages = {"com.kowalczyk.konrad.api_exchange", "com.kowalczyk.konrad.common"})
public class ApiExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiExchangeApplication.class, args);
    }

}
