package com.openplan.server.web.bean.mvc;

import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcCustom implements WebMvcConfigurer {
	
	// server.core.web.mvc.custom.enable
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		super.configureMessageConverters(converters);
		converters.add(0, new MappingJackson2HttpMessageConverter());
		
	}
}
