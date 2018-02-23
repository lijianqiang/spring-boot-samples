package com.openplan.server.service;

import java.util.Map;

import com.openplan.server.entity.model.Qrcoder;
import com.openplan.server.query.service.ConditionQueryResult;

public interface QrcoderService {
    Qrcoder getById(Integer id);

    boolean deleteById(Qrcoder record);

    Qrcoder insert(Qrcoder record);

    boolean updateById(Qrcoder record);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    ConditionQueryResult<Qrcoder> listByCondition(Map<String, Object> params);

}
