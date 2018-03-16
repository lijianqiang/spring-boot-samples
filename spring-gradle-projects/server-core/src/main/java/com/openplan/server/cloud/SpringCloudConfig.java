package com.openplan.server.cloud;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openplan.server.cloud.bean.feign.FeignCoreInterceptor;
import com.openplan.server.cloud.bean.hystirx.FeignHystrixConcurrencyStrategy;
import com.openplan.server.core.CoreCloudProperties;

@Configuration
@EnableConfigurationProperties(CoreCloudProperties.class)
public class SpringCloudConfig {
	
	@Bean
	@ConditionalOnProperty(prefix = "server.core.cloud", name = "feign-strategy-enable", matchIfMissing = true)
	public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
		return new FeignHystrixConcurrencyStrategy();
	}
	
	@Bean
	@ConditionalOnProperty(prefix = "server.core.cloud", name = "feign-interceptor-enable", matchIfMissing = true)
	@ConditionalOnMissingBean
	public FeignCoreInterceptor feignCoreInterceptor() {
		return new FeignCoreInterceptor();
	}

}
