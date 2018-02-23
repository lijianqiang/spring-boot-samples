package com.openplan.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CodeContentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeContentServiceApplication.class, args);
	}
}
