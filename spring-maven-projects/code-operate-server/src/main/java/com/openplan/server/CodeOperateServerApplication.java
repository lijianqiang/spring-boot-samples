package com.openplan.server;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableHystrix
@EnableFeignClients
@EnableZuulProxy
@SpringCloudApplication
public class CodeOperateServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeOperateServerApplication.class, args);
	}
}
