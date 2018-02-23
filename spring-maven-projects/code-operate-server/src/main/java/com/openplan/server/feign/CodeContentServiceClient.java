package com.openplan.server.feign;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.openplan.server.http.response.JsonResponse;
import com.openplan.server.module.district.ZhCityDTO;
import com.openplan.server.module.district.ZhCityListDTO;
import com.openplan.server.module.district.ZhProvinceDTO;
import com.openplan.server.module.district.ZhProvinceListDTO;
import com.openplan.server.module.district.ZhRegionDTO;
import com.openplan.server.module.district.ZhRegionListDTO;
import com.openplan.server.module.placer.PlacerDTO;
import com.openplan.server.module.placer.PlacerListDTO;
import com.openplan.server.module.qrcode.QrcoderDTO;
import com.openplan.server.module.qrcode.QrcoderListDTO;

/**
 * 类级别不能加@RequestMapping，否则和hystrix匹配不上
 * fallback需要feign.hystrix.enabled=true
 * 
 * @author lijianqiang
 *
 */
@FeignClient(name = "code-content-service", fallback = CodeContentServiceClientHystrix.class)
public interface CodeContentServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/qrcoders", produces = "application/json; charset=UTF-8")
    JsonResponse<QrcoderListDTO> listQrcoderByCondition(@RequestParam Map<String, Object> map);
    
    @RequestMapping(method = RequestMethod.GET, value = "/qrcoders/{id}", produces = "application/json; charset=UTF-8")
    JsonResponse<QrcoderDTO> getQrcoderById(@PathVariable("id") int id);
    
    @RequestMapping(method = RequestMethod.POST, value = "/qrcoders", produces = "application/json; charset=UTF-8")
    JsonResponse<QrcoderDTO> createQrcoder(@RequestBody QrcoderDTO qrcoderDTO);

    @RequestMapping(method = RequestMethod.PUT, value = "/qrcoders/{id}", produces = "application/json; charset=UTF-8")
    JsonResponse<QrcoderDTO> updateQrcoderById(@RequestBody QrcoderDTO qrcoderDTO);
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/qrcoders/{id}", produces = "application/json; charset=UTF-8")
    JsonResponse<QrcoderDTO> deleteQrcoderById(@PathVariable("id") int id);
    
    
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/provinces")
    JsonResponse<ZhProvinceListDTO> listProvinceAll();
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/provinces/{provinceNo}")
    JsonResponse<ZhProvinceDTO> getProvinceByProvinceNo(@PathVariable("provinceNo") Integer provinceNo);
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/provinces/{provinceNo}/citys")
    JsonResponse<ZhCityListDTO> listCityByProvinceNo(@PathVariable("provinceNo") Integer provinceNo);
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/citys")
    JsonResponse<ZhCityListDTO> listCityAll();
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/citys/{cityNo}")
    JsonResponse<ZhCityDTO> getCityByCityNo(@PathVariable("cityNo") Integer cityNo);
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/citys/{cityName}")
    JsonResponse<ZhCityDTO> getCityByCityName(@PathVariable("cityName") String cityName);
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/provinces/{provinceNo}/regions")
    JsonResponse<ZhRegionListDTO> listRegionByProvinceNo(@PathVariable("provinceNo") Integer provinceNo);
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/citys/{cityNo}/regions")
    JsonResponse<ZhRegionListDTO> listRegionByCityNo(@PathVariable("cityNo") Integer cityNo);
    
    @RequestMapping(method = { RequestMethod.GET }, value = "/regions/{regionNo}")
    JsonResponse<ZhRegionDTO> getRegionByRegionNo(@PathVariable("regionNo") Integer regionNo);
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/placers", produces = "application/json; charset=UTF-8")
    JsonResponse<PlacerListDTO> listPlacerByCondition(@RequestParam Map<String, Object> map);
    
    @RequestMapping(method = RequestMethod.GET, value = "/placers/{id}", produces = "application/json; charset=UTF-8")
    JsonResponse<PlacerDTO> getPlacerById(@PathVariable("id") int id);
    
    @RequestMapping(method = { RequestMethod.POST }, value = "/placers", produces = "application/json; charset=UTF-8")
    JsonResponse<PlacerDTO> createPlacer(@RequestBody PlacerDTO placerDTO);
    
    @RequestMapping(method = { RequestMethod.PUT }, value = "/placers/{id}", produces = "application/json; charset=UTF-8")
    JsonResponse<PlacerDTO> updatePlacerById(@PathVariable("id") int id, @RequestBody PlacerDTO placerDTO);
    
    @RequestMapping(method = { RequestMethod.DELETE }, value = "/placers/{id}", produces = "application/json; charset=UTF-8")
    JsonResponse<PlacerDTO> deletePlacerById(@PathVariable("id") int id);
    
}
