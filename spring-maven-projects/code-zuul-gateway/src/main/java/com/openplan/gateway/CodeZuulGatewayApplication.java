package com.openplan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.openplan.gateway.filter.AccessZuulFilter;

@EnableZuulProxy
@SpringCloudApplication
public class CodeZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeZuulGatewayApplication.class, args);
	}
	
	@Bean
    public AccessZuulFilter accessFilter() {
        return new AccessZuulFilter();
    }
}
