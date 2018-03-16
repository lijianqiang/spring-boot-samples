package com.openplan.server.mybatis.mapper;

import java.util.List;

import com.openplan.server.domain.model.ZhCity;
import com.openplan.server.domain.model.ZhCityExtra;

public interface ZhCityMapper {
    int deleteById(Integer id);

    int insert(ZhCity record);

    ZhCityExtra getById(Integer id);
    
    ZhCityExtra getByCityNo(Integer cityNo);
    
    ZhCityExtra getByCityName(String cityName);

    int updateById(ZhCity record);
    
    List<ZhCityExtra> listAll();
    
    List<ZhCityExtra> listByProvinceNo(Integer provinceNo);
}