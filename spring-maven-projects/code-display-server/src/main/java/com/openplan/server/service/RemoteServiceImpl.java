package com.openplan.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openplan.server.feign.CodeContentServiceClient;

@Service("remoteService")
public class RemoteServiceImpl implements RemoteService {

    @Autowired
    private CodeContentServiceClient codeContentServiceClient;


    @Override
    public String getFooList() {
        return codeContentServiceClient.getFooList();
    }

    @Override
    public String getFooById(int id) {
        return codeContentServiceClient.getFooById(id);
    }
}
