package com.openplan.server.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.openplan.server.domain.model.ZhRegion;
import com.openplan.server.domain.model.ZhRegionExtra;

public interface ZhRegionMapper {
    int deleteById(Integer id);

    int insert(ZhRegion record);

    ZhRegionExtra getById(Integer id);
    
    ZhRegionExtra getByRegionNo(Integer regionNo);
    
    ZhRegionExtra getByCityAndRegionName(@Param("cityName") String cityName, @Param("regionName") String regionName);
    
    int updateById(ZhRegion record);
    
    List<ZhRegionExtra> listAll();
    
    List<ZhRegionExtra> listByRegionName(String regionName);
    
    List<ZhRegionExtra> listByCityNo(Integer cityNo);
    
    List<ZhRegionExtra> listByProvinceNo(Integer provinceNo);
    
}