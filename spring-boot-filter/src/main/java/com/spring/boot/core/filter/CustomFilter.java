package com.spring.boot.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebFilter(filterName = "customFilter", urlPatterns = "/**")
public class CustomFilter implements Filter {
    
    private static final Logger LOG = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void destroy() {
        LOG.info("CustomFilter过滤器销毁");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest http = (HttpServletRequest) request;
        LOG.info("CustomFilter指定过滤器操作......url:" + http.getServletPath());
        // 执行操作后必须doFilter
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        LOG.info("CustomFilter初始化......");
    }

}
