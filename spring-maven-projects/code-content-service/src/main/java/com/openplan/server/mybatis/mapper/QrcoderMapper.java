package com.openplan.server.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.openplan.server.entity.model.Qrcoder;
import com.openplan.server.mybatis.base.SqlMapper;

public interface QrcoderMapper extends SqlMapper {
    Qrcoder getById(Integer id);

    int deleteById(Qrcoder record);

    int insert(Qrcoder record);

    int updateById(Qrcoder record);
    
    int countAll();
    
    int countByCondition(Map<String, Object> params);
    
    List<Qrcoder> listByCondition(Map<String, Object> params);
}