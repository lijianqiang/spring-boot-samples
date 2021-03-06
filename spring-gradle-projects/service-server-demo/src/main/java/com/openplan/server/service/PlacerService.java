package com.openplan.server.service;

import java.util.List;
import java.util.Map;

import com.openplan.server.domain.model.Placer;

public interface PlacerService {
    boolean deleteById(Placer record);

    Placer insert(Placer record);

    Placer getById(Integer id);
    
    Placer getByMarker(String marker);
    
    boolean updateById(Placer record);
    
    List<Placer> listByCityNo(Integer cityNo);
    
    List<Placer> listByRegionNo(Integer regionNo);
    
    List<Placer> listByLikeGeohash(String geohash, int accuracy);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    List<Placer> listByCondition(Map<String, Object> params);
}