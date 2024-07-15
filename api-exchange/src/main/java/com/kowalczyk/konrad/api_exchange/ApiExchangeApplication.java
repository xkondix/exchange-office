package com.kowalczyk.konrad.api_exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiExchangeApplication.class, args);
	}

}
