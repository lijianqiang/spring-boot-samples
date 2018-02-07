package com.openplan.server.service;

import java.util.List;
import java.util.Map;

import com.openplan.server.domain.model.Placeholder;

public interface PlaceholderService {
    Placeholder getById(Integer id);

    boolean deleteById(Placeholder record);

    Placeholder insert(Placeholder record);

    boolean updateById(Placeholder record);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    List<Placeholder> listByCondition(Map<String, Object> params);

}
