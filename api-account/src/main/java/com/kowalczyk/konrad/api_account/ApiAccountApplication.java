package com.kowalczyk.konrad.api_account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.kowalczyk.konrad.api_account", "com.kowalczyk.konrad.common"})
public class ApiAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAccountApplication.class, args);
	}

}
