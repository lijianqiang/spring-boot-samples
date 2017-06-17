package com.spring.boot.core.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.spring.boot.core.listener.CustomListener;

@Component
public class ApplicationConfig { 

    @Bean
    public FilterRegistrationBean  filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CustomFilter actionFilter = new CustomFilter();
        registrationBean.setFilter(actionFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
    
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
      ServletListenerRegistrationBean<CustomListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<CustomListener>();
      servletListenerRegistrationBean.setListener(new CustomListener());
      return servletListenerRegistrationBean;
    }
    

}
