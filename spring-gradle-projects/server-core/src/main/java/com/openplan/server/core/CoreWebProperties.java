package com.openplan.server.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "server.core.web")
public class CoreWebProperties {

	/**
	 * Server Response Handler Use Global Advisor, default true
	 */
	private boolean responseAdvisorEnable = true;

	/**
	 * Server Error Handler Use Global Advisor, default true
	 */
	private boolean errorAdvisorEnable = true;

	/**
	 * Server Use Global Error Controller, default true
	 */
	private boolean errorControllerEnable = true;

	/**
	 * Server Enable Global Json Converter, default true
	 */
	private boolean mvcCustomEnable = true;

	/**
	 * Server Api Resfult Debug Enable, default false
	 */
	private boolean restfulDebugEnable = false;

	public boolean isResponseAdvisorEnable() {
		return responseAdvisorEnable;
	}

	public void setResponseAdvisorEnable(boolean responseAdvisorEnable) {
		this.responseAdvisorEnable = responseAdvisorEnable;
	}

	public boolean isErrorAdvisorEnable() {
		return errorAdvisorEnable;
	}

	public void setErrorAdvisorEnable(boolean errorAdvisorEnable) {
		this.errorAdvisorEnable = errorAdvisorEnable;
	}

	public boolean isErrorControllerEnable() {
		return errorControllerEnable;
	}

	public void setErrorControllerEnable(boolean errorControllerEnable) {
		this.errorControllerEnable = errorControllerEnable;
	}

	public boolean isMvcCustomEnable() {
		return mvcCustomEnable;
	}

	public void setMvcCustomEnable(boolean mvcCustomEnable) {
		this.mvcCustomEnable = mvcCustomEnable;
	}

	public boolean isRestfulDebugEnable() {
		return restfulDebugEnable;
	}

	public void setRestfulDebugEnable(boolean restfulDebugEnable) {
		this.restfulDebugEnable = restfulDebugEnable;
	}

}
