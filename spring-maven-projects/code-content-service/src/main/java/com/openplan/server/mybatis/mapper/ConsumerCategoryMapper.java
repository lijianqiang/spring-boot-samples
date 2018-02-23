package com.openplan.server.mybatis.mapper;

import java.util.List;

import com.openplan.server.entity.model.ConsumerCategory;

public interface ConsumerCategoryMapper {
    int delete(ConsumerCategory record);

    int insert(ConsumerCategory record);

    ConsumerCategory getById(Integer id);

    int update(ConsumerCategory record);
    
    List<ConsumerCategory> listAll();
}