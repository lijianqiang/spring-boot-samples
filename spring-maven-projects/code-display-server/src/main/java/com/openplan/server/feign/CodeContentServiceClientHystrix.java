package com.openplan.server.feign;

import org.springframework.stereotype.Component;

@Component
public class CodeContentServiceClientHystrix implements CodeContentServiceClient {

    @Override
    public String getFooList() {
        return "error:empty list";
    }

    @Override
    public String getFooById(int id) {
        return "error:none";
    }

}
