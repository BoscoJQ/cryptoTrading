package com.example.cryptoTrading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class CryptoTradingApplication {

	public static void main(String[] args) {
		System.out.println("Hello World");
		SpringApplication.run(CryptoTradingApplication.class, args);
	}

}
