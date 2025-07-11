package com.vendas.monolito.vendas_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VendasSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VendasSystemApplication.class, args);
	}

}
