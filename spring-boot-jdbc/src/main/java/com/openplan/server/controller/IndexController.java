package com.openplan.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);


	@RequestMapping("/")
	public String actionIndex(HttpServletRequest request) {
		LOG.info("index");

		return "index";
	}

}
