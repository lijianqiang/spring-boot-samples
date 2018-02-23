package com.openplan.server.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import feign.Feign;
import feign.Logger;

//@Configuration
public class FeignCustomConfiguration {
    
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder().logLevel(Logger.Level.FULL);
    }
    
}
