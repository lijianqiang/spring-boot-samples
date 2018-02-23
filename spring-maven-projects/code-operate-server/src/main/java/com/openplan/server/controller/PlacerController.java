package com.openplan.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openplan.server.feign.CodeContentServiceClient;
import com.openplan.server.helper.HttpRequestHelper;
import com.openplan.server.http.response.JsonResponse;
import com.openplan.server.module.placer.PlacerDTO;

@Controller
@RequestMapping("/placer")
public class PlacerController {
    
    private static final Logger LOG = LoggerFactory.getLogger(QrcoderController.class);
    
    @Autowired
    private CodeContentServiceClient codeContentServiceClient;
    
    @RequestMapping("/index.html")
    public String actionPlacerIndex(HttpServletRequest request) {
        LOG.info("actionPlacerIndex");
        return "operate/placer/index";
    }

    @RequestMapping("/create.html")
    public String actionPlacerCreate(HttpServletRequest request) {

        return "operate/placer/create";
    }
    
    @RequestMapping("/update.html")
    public String actionPlacerUpdate(HttpServletRequest request) {

        Integer id = HttpRequestHelper.getAsInteger(request, "id");
        JsonResponse<PlacerDTO> placerResponse = codeContentServiceClient.getPlacerById(id);
        request.setAttribute("placer", placerResponse.getData());
        
        return "operate/placer/update";
    }
    
    @RequestMapping("/detail.html")
    public String actionPlacerDetail(HttpServletRequest request) {

        return "operate/placer/detail";
    }
    
    @RequestMapping("/qrcoder.html")
    public String actionPlacerQrcoderSetting(HttpServletRequest request) {

        Integer id = HttpRequestHelper.getAsInteger(request, "id");
        JsonResponse<PlacerDTO> placerResponse = codeContentServiceClient.getPlacerById(id);
        request.setAttribute("placer", placerResponse.getData());
        
        return "operate/placer/qrcoder";
    }
    

}
