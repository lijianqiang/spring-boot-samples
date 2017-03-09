package com.spring.boot.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.spring.boot.entity.domain.Foo;
import com.spring.boot.service.FooService;
import com.spring.boot.service.TransService;

@Controller
@RequestMapping("/mysql")
public class MysqlController {

	/**
	 * Member Description
	 */
	private static final Logger LOG = LoggerFactory.getLogger(MysqlController.class);

	@Autowired
	private FooService fooService;
	
	@Autowired
	private TransService transService;
	
	@RequestMapping(method = { RequestMethod.GET }, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String getAction(HttpServletRequest request) {
		LOG.debug("hello world");
		Gson gson = new Gson();
		List<Foo> foos = fooService.getAll();
		return gson.toJson(foos);
	}
	

	@RequestMapping(method = { RequestMethod.GET }, value = "/{id}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getCoachById(HttpServletRequest request, @PathVariable String id) {
		int index = 0;
		try {
			index = Integer.valueOf(id);
		} catch (Exception e) {
			return e.getMessage();
		}

		Foo foo = fooService.getById(index);
		Gson gson = new Gson();
		return gson.toJson(foo);
	}
	
	@RequestMapping("/trans")
	@ResponseBody
	public String actionDoTrans(HttpServletRequest request) {
		try {
			transService.addSomeFoo(new Random().nextInt(10));
		} catch (Exception e) {
			LOG.error("test:error");
		}
		return "finished";
	}
}
