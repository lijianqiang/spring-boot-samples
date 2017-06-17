package com.spring.boot.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现ServletContextListener，覆盖其两个方法
 * 使用注解的话，要在启动类加@ServletComponentScan
 * @author Administrator
 * 
 */
//@WebListener
public class CustomListener implements ServletContextListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(CustomListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent servletContext) {
        LOG.info("servletContext销毁......");

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        LOG.info("servletContext初始化......");
    }

}
