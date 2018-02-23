package com.openplan.server.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openplan.server.AppConstant;
import com.openplan.server.constant.HttpConstant;
import com.openplan.server.feign.CodeContentServiceClient;
import com.openplan.server.helper.HttpRequestHelper;
import com.openplan.server.http.response.JsonResponse;
import com.openplan.server.http.response.ResponseBuilder;
import com.openplan.server.module.qrcode.QrcoderDTO;
import com.openplan.server.module.qrcode.QrcoderKey;
import com.openplan.server.module.qrcode.QrcoderListDTO;
import com.openplan.server.util.JsonUtil;

@Controller
@RequestMapping(AppConstant.API)
public class QrcoderApiController {

    private static final Logger LOG = LoggerFactory.getLogger(QrcoderApiController.class);
    
    @Autowired
    private CodeContentServiceClient codeContentServiceClient;
    

    @ResponseBody
    @RequestMapping(method = { RequestMethod.GET }, value = "/qrcoders")
    public String actionListJson(HttpServletRequest request) {
        LOG.debug("actionListJson");
        JsonResponse<QrcoderListDTO> jsonResponse = codeContentServiceClient.listQrcoderByCondition(buildQueryParam(request));
        if (jsonResponse.getCode() != JsonResponse.OK) {
            return ResponseBuilder.fail(1, "没有数据");
        }
        return ResponseBuilder.success(jsonResponse.getData());
    }
    
    private Map<String, Object> buildQueryParam(HttpServletRequest request) {
        Map<String, Object> conditions = new HashMap<String, Object>();
        Integer limit = HttpRequestHelper.getAsInteger(request, HttpConstant.LIMIT_KEY);
        if (limit != null) {
            conditions.put(HttpConstant.LIMIT_KEY, limit);
        }
        Integer page = HttpRequestHelper.getAsInteger(request, HttpConstant.PAGE_KEY);
        if (page != null) {
            conditions.put(HttpConstant.PAGE_KEY, page);
        }
        String order = HttpRequestHelper.getAsString(request, HttpConstant.ORDER_KEY, null);
        if (order != null) {
            conditions.put(HttpConstant.ORDER_KEY, order);
        }
        String sort = HttpRequestHelper.getAsString(request, HttpConstant.SORT_KEY, null);
        if (sort != null) {
            conditions.put(HttpConstant.SORT_KEY, sort);
        }
        Boolean enable = HttpRequestHelper.getAsBoolean(request, QrcoderKey.ENABLE);
        if (enable != null) {
            conditions.put(QrcoderKey.ENABLE, enable);
        }
        return conditions;
    }

    @RequestMapping(method = { RequestMethod.DELETE }, value = "/qrcoders/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionDeleteById(HttpServletRequest request, @PathVariable Integer id) {
        JsonResponse<QrcoderDTO> jsonResponse = codeContentServiceClient.deleteQrcoderById(id);
        if (jsonResponse.getCode() != JsonResponse.OK) {
            return ResponseBuilder.fail(4, "删除失败");
        }
        return ResponseBuilder.success(jsonResponse.getData());
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = "/qrcoders", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionCreateQrcoder(HttpServletRequest request, @RequestBody QrcoderDTO qrcoderDTO) {
        JsonResponse<QrcoderDTO> createResult = codeContentServiceClient.createQrcoder(qrcoderDTO);
        if (createResult == null || createResult.getCode() != JsonResponse.OK) {
            LOG.error("actionCreatePlacer failed");
            return ResponseBuilder.fail(1, "save failed");
        }
        LOG.info("actionCreatePlacer success");
        return JsonUtil.toJson(createResult);
    }


}
