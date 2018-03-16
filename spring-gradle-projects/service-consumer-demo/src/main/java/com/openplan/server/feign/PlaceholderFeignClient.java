package com.openplan.server.feign;


import org.springframework.cloud.openfeign.FeignClient;

import com.openplan.server.api.PlaceholderHttpApi;
import com.openplan.server.feign.callback.PlaceholderFeignClientHystrix;

@FeignClient(name = "service-server-demo", fallback = PlaceholderFeignClientHystrix.class)
public interface PlaceholderFeignClient extends PlaceholderHttpApi {

}
