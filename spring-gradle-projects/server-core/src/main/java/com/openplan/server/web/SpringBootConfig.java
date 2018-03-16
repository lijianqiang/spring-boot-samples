package com.openplan.server.web;

import java.util.List;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.openplan.extra.controller.CustomErrorController;
import com.openplan.server.core.CoreWebProperties;
import com.openplan.server.web.bean.mvc.WebMvcCustom;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties({ ResourceProperties.class, CoreWebProperties.class })
public class SpringBootConfig {

	@Autowired(required = false)
	private List<ErrorViewResolver> errorViewResolvers;

	private final ServerProperties serverProperties;

	public SpringBootConfig(ServerProperties serverProperties) {
		this.serverProperties = serverProperties;
	}

	@Bean
	@ConditionalOnProperty(prefix = "server.core.web", name = "error-controller-enable", matchIfMissing = true)
	@ConditionalOnMissingBean(value = ErrorController.class, search = SearchStrategy.CURRENT)
	public CustomErrorController basicErrorController(ErrorAttributes errorAttributes) {
		return new CustomErrorController(errorAttributes, this.serverProperties.getError(), this.errorViewResolvers);
	}
	
	/** 
	@Bean
	@ConditionalOnProperty(prefix = "server.core.web", name = "error-controller-enable", matchIfMissing = true)
	public JsonResponseAdvisor jsonResponseAdvisor() {
		return new JsonResponseAdvisor();
	}
	*/
	
	
	@Bean
	@ConditionalOnProperty(prefix = "server.core.web", name = "mvc-custom-enable", matchIfMissing = true)
	public WebMvcCustom webMvcCustom() {
		return new WebMvcCustom();
	}

}
