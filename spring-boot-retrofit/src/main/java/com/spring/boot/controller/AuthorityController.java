package com.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.net.request.AppsRequest;
import com.spring.boot.net.response.ApiResponse;
import com.spring.boot.net.response.body.AppItem;
import com.spring.boot.net.response.body.AppsBody;
import com.spring.boot.utils.JsonUtil;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthorityController.class);
	
	@RequestMapping(value="/apps", method = { RequestMethod.POST }, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String actionApps(HttpServletRequest request, @RequestBody AppsRequest appsRequest) {
		LOG.info("hello actionApps email:{}", appsRequest.email);
		AppsBody body = new AppsBody();
		body.setIs_manager(true);
		List<String> names = new ArrayList<String>(3);
		names.add("call-chain-demo-ljq-dev");
		names.add("call-chain-demo-ljq");
		names.add("call-chain-demo-9100");
		AppItem item1 = new AppItem();
		item1.setApp_name("调用链demo");
		item1.setModel_name(names);
		
		AppItem item2 = new AppItem();
		item2.setApp_name("空的demo");
		
		List<AppItem> apps = new ArrayList<AppItem>(3);
		apps.add(item1);
		apps.add(item2);
		body.setApps(apps);
		ApiResponse<AppsBody> apiResponse = new ApiResponse<AppsBody>();
		apiResponse.setResponse(body);
		return JsonUtil.toJson(apiResponse);
	}

}
