package com.openplan.server;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.openplan.server.filter.AccessZuulFilter;

@EnableDiscoveryClient
@EnableZuulProxy
@SpringCloudApplication
public class GatewayZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayZuulServerApplication.class, args);
	}
	
	@Bean
    public AccessZuulFilter accessFilter() {
        return new AccessZuulFilter();
    }
}
