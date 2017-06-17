package com.spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    
    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String actionIndex() {
        LOG.info("actionIndex");
        return "hello world";
    }

}