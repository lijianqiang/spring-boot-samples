package com.openplan.server.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "server.core.cloud")
public class CoreCloudProperties {

	/**
	 * Feign Interceptor Enable, default true
	 */
	private boolean feignInterceptorEnable = true;

	/**
	 * Feign Hystrix Strategy Enable, default true
	 */
	private boolean feignStrategyEnable = true;

	public boolean isFeignInterceptorEnable() {
		return feignInterceptorEnable;
	}

	public void setFeignInterceptorEnable(boolean feignInterceptorEnable) {
		this.feignInterceptorEnable = feignInterceptorEnable;
	}

	public boolean isFeignStrategyEnable() {
		return feignStrategyEnable;
	}

	public void setFeignStrategyEnable(boolean feignStrategyEnable) {
		this.feignStrategyEnable = feignStrategyEnable;
	}

}
