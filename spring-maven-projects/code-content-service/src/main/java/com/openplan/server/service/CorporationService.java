package com.openplan.server.service;

import java.util.List;
import java.util.Map;

import com.openplan.server.entity.model.Corporation;
import com.openplan.server.query.service.ConditionQueryResult;

public interface CorporationService {
    boolean delete(Corporation record);

    Corporation insert(Corporation record);

    Corporation getById(Integer id);

    boolean update(Corporation record);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    ConditionQueryResult<Corporation> listByCondition(Map<String, Object> params);
    
    List<Corporation> listByPlacerId(Integer placerId);
}
