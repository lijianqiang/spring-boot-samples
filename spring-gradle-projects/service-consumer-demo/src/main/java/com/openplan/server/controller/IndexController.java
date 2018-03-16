package com.openplan.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openplan.server.exception.DemoConsumerException;
import com.openplan.server.exception.ErrorCode;
import com.openplan.server.feign.PlaceholderFeignClient;
import com.openplan.server.protocol.result.JsonResponse;
import com.openplan.server.vo.PlaceholderVO;
import com.openplan.server.web.restful.parser.JsonResponsePaser;

@RestController
//@RequestMapping(produces = "application/json; charset=UTF-8")
public class IndexController {

	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private PlaceholderFeignClient placeholderService;

	@RequestMapping("/")
	public String actionIndex(HttpServletRequest request) {

		return "index";
	}

	@RequestMapping(value = "/info")
	@ResponseBody
	public String actionInfo(HttpServletRequest request) {

		return "hello info";
	}

	@RequestMapping("/feign")
	public PlaceholderVO getPlaceholder(HttpServletRequest request) {
		String val = request.getParameter("id");
		Integer id = 1;
		id = Integer.valueOf(val);

		JsonResponse<PlaceholderVO> response = placeholderService.getPlaceholder(id);
		
		PlaceholderVO data = JsonResponsePaser.safeGet(response);
		
		LOG.info("getPlaceholder, error message:{}", response.getMessage());
		return data;
	}
	
	@RequestMapping(value = "/test", produces = "application/json; charset=UTF-8")
	public String testException(HttpServletRequest request) {
		
		String val = request.getParameter("t");
		
		if ("1".equals(val)) {
			throw new DemoConsumerException(ErrorCode.DATA_NOT_FOUND);
		}
		
		if ("2".equals(val)) {
			throw new NullPointerException("xadfasfdasdfasdfasdf");
		}

		return "testException";
	}
}
