package com.openplan.server.mybatis.mapper;

import java.util.List;

import com.openplan.server.entity.model.ZhProvince;

public interface ZhProvinceMapper {
    int deleteById(Integer id);

    int insert(ZhProvince record);

    ZhProvince getById(Integer id);
    
    ZhProvince getByProvinceNo(Integer provinceNo);

    int updateById(ZhProvince record);
    
    List<ZhProvince> listAll();
}