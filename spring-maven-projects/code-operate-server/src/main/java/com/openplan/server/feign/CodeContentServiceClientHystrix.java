package com.openplan.server.feign;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

@Component
public class CodeContentServiceClientHystrix implements CodeContentServiceClient {
    
    private static final Logger LOG = LoggerFactory.getLogger(CodeContentServiceClientHystrix.class);

    @Override
    public JsonResponse<QrcoderListDTO> listQrcoderByCondition(Map<String, Object> map) {
        LOG.error("******getByPage*****");
        JsonResponse<QrcoderListDTO> callBack = new JsonResponse<QrcoderListDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<QrcoderDTO> getQrcoderById(int id) {
        LOG.error("******服务忙*****");
        JsonResponse<QrcoderDTO> callBack = new JsonResponse<QrcoderDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<QrcoderDTO> createQrcoder(QrcoderDTO qrcodeDTO) {
        LOG.error("******服务忙*****");
        JsonResponse<QrcoderDTO> callBack = new JsonResponse<QrcoderDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<QrcoderDTO> updateQrcoderById(QrcoderDTO qrcoderDTO) {
        LOG.error("******服务忙*****");
        JsonResponse<QrcoderDTO> callBack = new JsonResponse<QrcoderDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<QrcoderDTO> deleteQrcoderById(int id) {
        LOG.error("******服务忙*****");
        JsonResponse<QrcoderDTO> callBack = new JsonResponse<QrcoderDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<ZhProvinceListDTO> listProvinceAll() {
        LOG.error("******服务忙*****");
        JsonResponse<ZhProvinceListDTO> callBack = new JsonResponse<ZhProvinceListDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<ZhProvinceDTO> getProvinceByProvinceNo(Integer provinceNo) {
        LOG.error("******服务忙*****");
        JsonResponse<ZhProvinceDTO> callBack = new JsonResponse<ZhProvinceDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<ZhCityListDTO> listCityByProvinceNo(Integer provinceNo) {
        LOG.error("******服务忙*****");
        JsonResponse<ZhCityListDTO> callBack = new JsonResponse<ZhCityListDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<ZhCityListDTO> listCityAll() {
        LOG.error("******服务忙*****");
        JsonResponse<ZhCityListDTO> callBack = new JsonResponse<ZhCityListDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<ZhCityDTO> getCityByCityNo(Integer cityNo) {
        LOG.error("******服务忙*****");
        JsonResponse<ZhCityDTO> callBack = new JsonResponse<ZhCityDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }
    
    @Override
    public JsonResponse<ZhCityDTO> getCityByCityName(String cityName) {
        LOG.error("******服务忙*****");
        JsonResponse<ZhCityDTO> callBack = new JsonResponse<ZhCityDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<ZhRegionListDTO> listRegionByProvinceNo(Integer provinceNo) {
        LOG.error("******服务忙*****");
        JsonResponse<ZhRegionListDTO> callBack = new JsonResponse<ZhRegionListDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<ZhRegionListDTO> listRegionByCityNo(Integer cityNo) {
        LOG.error("******服务忙*****");
        JsonResponse<ZhRegionListDTO> callBack = new JsonResponse<ZhRegionListDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<ZhRegionDTO> getRegionByRegionNo(Integer regionNo) {
        LOG.error("******服务忙*****");
        JsonResponse<ZhRegionDTO> callBack = new JsonResponse<ZhRegionDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<PlacerListDTO> listPlacerByCondition(Map<String, Object> map) {
        LOG.error("******服务忙*****");
        JsonResponse<PlacerListDTO> callBack = new JsonResponse<PlacerListDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<PlacerDTO> createPlacer(PlacerDTO placerDTO) {
        LOG.error("******服务忙*****");
        JsonResponse<PlacerDTO> callBack = new JsonResponse<PlacerDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<PlacerDTO> updatePlacerById(int id, PlacerDTO placerDTO) {
        LOG.error("******服务忙*****");
        JsonResponse<PlacerDTO> callBack = new JsonResponse<PlacerDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<PlacerDTO> deletePlacerById(int id) {
        LOG.error("******服务忙*****");
        JsonResponse<PlacerDTO> callBack = new JsonResponse<PlacerDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    @Override
    public JsonResponse<PlacerDTO> getPlacerById(int id) {
        LOG.error("******服务忙*****");
        JsonResponse<PlacerDTO> callBack = new JsonResponse<PlacerDTO>();
        callBack.setCode(1000);
        callBack.setMessage("服务忙");
        return callBack;
    }

    

}
