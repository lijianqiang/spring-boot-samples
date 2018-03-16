package com.openplan.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OpenDisplayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenDisplayServerApplication.class, args);
	}
}
