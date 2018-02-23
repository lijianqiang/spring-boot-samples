package com.openplan.server.service;

import java.util.Map;

import com.openplan.server.entity.model.PlacerRegistry;
import com.openplan.server.query.service.ConditionQueryResult;

public interface PlacerRegistryService {
    boolean delete(PlacerRegistry record);

    PlacerRegistry insert(PlacerRegistry record);

    PlacerRegistry getById(Integer id);

    boolean update(PlacerRegistry record);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    ConditionQueryResult<PlacerRegistry> listByCondition(Map<String, Object> params);

}
