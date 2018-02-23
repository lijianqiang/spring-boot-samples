package com.openplan.server.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.openplan.server.entity.model.Placer;

public interface PlacerMapper {
    int deleteById(Placer record);

    int insert(Placer record);

    Placer getById(Integer id);
    
    Placer getByMarker(String marker);
    
    int updateById(Placer record);
    
    List<Placer> listByCityNo(Integer cityNo);
    
    List<Placer> listByRegionNo(Integer regionNo);
    
    List<Placer> listByLikeGeohash(String geohash);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    List<Placer> listByCondition(Map<String, Object> params);
    
}