package com.openplan.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public String actionIndex(HttpServletRequest request) {

        return "hello index";
    }

    @RequestMapping(value = "/info")
    public String actionInfo(HttpServletRequest request) {

        return "hello info";
    }

}
