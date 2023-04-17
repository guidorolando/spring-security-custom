package com.example.authtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthtestApplication.class, args);
	}

}
