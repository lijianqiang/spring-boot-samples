package com.openplan.server.service;

import java.util.List;

import com.openplan.server.entity.model.ZhCityExtra;
import com.openplan.server.entity.model.ZhProvince;
import com.openplan.server.entity.model.ZhRegionExtra;

public interface DistrictService {
    ZhCityExtra getCityById(Integer id);
    
    ZhCityExtra getCityByCityNo(Integer cityNo);
    
    ZhCityExtra getCityByCityName(String cityName);

    List<ZhCityExtra> listCityAll();
    
    List<ZhCityExtra> listCityByProvinceNo(Integer provinceNo);
    
    ZhProvince getProvinceById(Integer id);
    
    ZhProvince getProvinceByProvinceNo(Integer provinceNo);

    List<ZhProvince> listProvinceAll();
    
    ZhRegionExtra getRegionById(Integer id);
    
    ZhRegionExtra getRegionByRegionNo(Integer regionNo);
    
    List<ZhRegionExtra> listRegionAll();
    
    List<ZhRegionExtra> listRegionByCityNo(Integer cityNo);
    
    List<ZhRegionExtra> listRegionByProvinceNo(Integer provinceNo);
    
    ZhRegionExtra getRegionByCityAndRegionName(String cityName, String regionName);

}
