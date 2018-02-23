package com.openplan.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openplan.server.feign.CodeContentServiceClient;
import com.openplan.server.service.RemoteService;

@RestController
@RequestMapping("/foo")
public class FooController {

    private static Logger LOG = LoggerFactory.getLogger(FooController.class);

    @Autowired
    private CodeContentServiceClient codeContentServiceClient;
    
    @Autowired
    private RemoteService remoteService;

    @RequestMapping
    public String actionIndex(HttpServletRequest request) {
        LOG.info("actionIndex");

        return "/compute";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String actionList(HttpServletRequest request) {
        LOG.info("actionList");
        return "[list result]: " + codeContentServiceClient.getFooList();
    }
    
    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public String actionOne(HttpServletRequest request) {
        int id = strToInt(request.getParameter("id"));
        LOG.info("actionOne:id={}", id);
        return "[id result]: " + codeContentServiceClient.getFooById(id);
    }
    
    @RequestMapping(value = "/list2", method = RequestMethod.GET)
    public String actionList2(HttpServletRequest request) {
        LOG.info("actionList");
        return "[list2 result]: " + remoteService.getFooList();
    }

    private int strToInt(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int res = 0;
        try {
            res = Integer.valueOf(str);
        } catch (NumberFormatException nfe) {
            res = 0;
        }

        return res;
    }

}
