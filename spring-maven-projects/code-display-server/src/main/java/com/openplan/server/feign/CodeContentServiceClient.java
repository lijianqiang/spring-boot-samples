package com.openplan.server.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 类级别不能加@RequestMapping，否则和hystrix匹配不上
 * fallback需要feign.hystrix.enabled=true
 * 
 * @author lijianqiang
 *
 */
@FeignClient(name = "code-content-service", fallback = CodeContentServiceClientHystrix.class)
public interface CodeContentServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/mysql")
    String getFooList();
    
    @RequestMapping(method = RequestMethod.GET, value = "/mysql/{id}")
    String getFooById(@PathVariable("id") int id);

}
