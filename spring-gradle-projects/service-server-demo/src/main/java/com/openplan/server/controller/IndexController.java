package com.openplan.server.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openplan.server.domain.model.Foo;
import com.openplan.server.service.FooService;
import com.openplan.server.service.TransService;



@RestController
public class IndexController {
	
	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private FooService fooService;
	
	@Autowired
	private TransService transService;

    @RequestMapping("/")
    public String actionIndex(HttpServletRequest request) {

        return "index";
    }

    @RequestMapping(value = "/info")
    @ResponseBody
    public String actionInfo(HttpServletRequest request) {

        return "hello info";
    }
    
    @RequestMapping("/foos")
    public List<Foo> actionGetFooList(HttpServletRequest request) {

    	List<Foo> list = fooService.getAll();
    	LOG.info("actionIndex, list size:{}", list.size());
        return list;
    }

    @RequestMapping("/trans")
    public Map<String, String> actionTestTrans(HttpServletRequest request) {
    	Map<String, String> resutl = new HashMap<String, String>();
    	String msg = "failed";
		try {
			transService.addSomeFoo(GregorianCalendar.getInstance().get(Calendar.SECOND));
			msg = "success";
		} catch (Exception e) {
		}
    	resutl.put("msg", msg);
        return resutl;
    }
    
    @RequestMapping("/exception")
    public Map<String, String> actionTestError(HttpServletRequest request) {
    	Map<String, String> resutl = new HashMap<String, String>();
    	String msg = "finish";
    	if (GregorianCalendar.getInstance().get(Calendar.SECOND) % 2 == 0) {
            throw new NullPointerException();
        }
    	resutl.put("msg", msg);
        return resutl;
    }
}
