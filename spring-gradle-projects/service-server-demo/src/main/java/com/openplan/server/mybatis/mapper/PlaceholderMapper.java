package com.openplan.server.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.openplan.server.domain.model.Placeholder;
import com.openplan.server.mybatis.base.SqlMapper;

public interface PlaceholderMapper extends SqlMapper {
    Placeholder getById(Integer id);

    int deleteById(Placeholder record);

    int insert(Placeholder record);

    int updateById(Placeholder record);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    List<Placeholder> listByCondition(Map<String, Object> params);
}