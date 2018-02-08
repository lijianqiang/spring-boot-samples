package com.openplan.server.mybatis.mapper;

import java.util.List;

import com.openplan.server.domain.model.ZhZipcode;

public interface ZhZipcodeMapper {
    
    int deleteById(Integer id);

    int insert(ZhZipcode record);

    ZhZipcode getById(Integer id);

    int updateById(ZhZipcode record);
    
    List<ZhZipcode> listAll();
    
    List<ZhZipcode> listByCityNo(Integer cityNo);
}