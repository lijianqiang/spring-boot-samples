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
import com.openplan.server.module.placer.PlacerDTO;
import com.openplan.server.module.placer.PlacerListDTO;
import com.openplan.server.module.placer.PlacerKey;
import com.openplan.server.util.JsonUtil;

/**
 * @author lijianqiang
 *
 */
@Controller
@RequestMapping(AppConstant.API)
public class PlacerApiController {
    
    private static final Logger LOG = LoggerFactory.getLogger(PlacerApiController.class);
    
    @Autowired
    private CodeContentServiceClient codeContentServiceClient;
    
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/placers")
    @ResponseBody
    public String actionListPlacer(HttpServletRequest request) {
        Map<String, Object> params = buildQueryParam(request);
        JsonResponse<PlacerListDTO> jsonResponse = codeContentServiceClient.listPlacerByCondition(params);
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
        Boolean enable = HttpRequestHelper.getAsBoolean(request, PlacerKey.ENABLE);
        if (enable != null) {
            conditions.put(PlacerKey.ENABLE, enable);
        }
        Integer provinceNo = HttpRequestHelper.getAsInteger(request, PlacerKey.PROVINCE_NO);
        if (provinceNo != null) {
            conditions.put(PlacerKey.PROVINCE_NO, provinceNo);
        }
        Integer cityNo = HttpRequestHelper.getAsInteger(request, PlacerKey.CITY_NO);
        if (cityNo != null) {
            conditions.put(PlacerKey.CITY_NO, cityNo);
        }
        Integer regionNo = HttpRequestHelper.getAsInteger(request, PlacerKey.REGION_NO);
        if (regionNo != null) {
            conditions.put(PlacerKey.REGION_NO, regionNo);
        }
        String name = HttpRequestHelper.getAsString(request, PlacerKey.NAME, null);
        if (name != null) {
            conditions.put(PlacerKey.NAME, name);
        }
        return conditions;
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = "/placers", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String actionCreatePlacer(HttpServletRequest request, @RequestBody String placerJson) {
        PlacerDTO placerDTO = JsonUtil.fromJson(placerJson, PlacerDTO.class);
        JsonResponse<PlacerDTO> createResult = codeContentServiceClient.createPlacer(placerDTO);
        if (createResult == null || createResult.getCode() != JsonResponse.OK) {
            LOG.error("actionCreatePlacer failed");
            return ResponseBuilder.fail(1, "save failed");
        }
        LOG.info("actionCreatePlacer success");
        return JsonUtil.toJson(createResult);
    }
    
    @RequestMapping(method = { RequestMethod.PUT }, value = "/placers/{id}")
    @ResponseBody
    public String actionUpdatePlacerById(HttpServletRequest request, @PathVariable Integer id, @RequestBody PlacerDTO placerDTO) {
        if (placerDTO == null) {
            LOG.error("actionUpdatePlacerById:{} failed, not exist", id);
            return ResponseBuilder.fail(2, "data null");
        }
        JsonResponse<PlacerDTO> updateResult = codeContentServiceClient.updatePlacerById(id, placerDTO);
        if (updateResult == null || updateResult.getCode() != JsonResponse.OK) {
            LOG.error("actionUpdatePlacerById:{} failed", id);
            return ResponseBuilder.fail(1, "update failed");
        }
        LOG.info("actionUpdatePlacerById:{} success", id);
        return JsonUtil.toJson(updateResult);
    }
    
    @RequestMapping(method = { RequestMethod.DELETE }, value = "/placers/{id}")
    @ResponseBody
    public String actionDeletePlacerById(HttpServletRequest request, @PathVariable Integer id) {
        JsonResponse<PlacerDTO> deleteResult = codeContentServiceClient.deletePlacerById(id);
        if (deleteResult == null || deleteResult.getCode() != JsonResponse.OK) {
            return ResponseBuilder.fail(4, "删除失败");
        }
        return JsonUtil.toJson(deleteResult);
    }

}
