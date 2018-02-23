package com.openplan.server.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.openplan.server.entity.model.Corporation;

public interface CorporationMapper {
    int delete(Corporation record);

    int insert(Corporation record);

    Corporation getById(Integer id);

    int update(Corporation record);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    List<Corporation> listByCondition(Map<String, Object> params);
    
    List<Corporation> listByPlacerId(Integer placerId);
}