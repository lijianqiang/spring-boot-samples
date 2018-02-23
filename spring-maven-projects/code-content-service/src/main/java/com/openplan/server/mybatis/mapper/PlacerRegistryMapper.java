package com.openplan.server.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.openplan.server.entity.model.PlacerRegistry;

public interface PlacerRegistryMapper {
    int delete(PlacerRegistry record);

    int insert(PlacerRegistry record);

    PlacerRegistry getById(Integer id);

    int update(PlacerRegistry record);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    List<PlacerRegistry> listByCondition(Map<String, Object> params);
}