package com.openplan.server.service;

import java.util.Map;

import com.openplan.server.entity.model.Placeholder;
import com.openplan.server.query.service.ConditionQueryResult;

public interface PlaceholderService {
    Placeholder getById(Integer id);

    boolean deleteById(Placeholder record);

    Placeholder insert(Placeholder record);

    boolean updateById(Placeholder record);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    ConditionQueryResult<Placeholder> listByCondition(Map<String, Object> params);

}
