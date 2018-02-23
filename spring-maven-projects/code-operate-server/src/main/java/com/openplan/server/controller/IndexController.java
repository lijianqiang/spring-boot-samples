package com.openplan.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/")
    public String actionIndex(HttpServletRequest request) {

        return "operate/main";
    }
    
    @RequestMapping(value = "/info")
    @ResponseBody
    public String actionInfo(HttpServletRequest request) {

        return "hello info";
    }

}
