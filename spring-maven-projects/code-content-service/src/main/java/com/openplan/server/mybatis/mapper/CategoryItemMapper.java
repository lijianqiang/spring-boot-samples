package com.openplan.server.mybatis.mapper;

import java.util.List;

import com.openplan.server.entity.extra.CategoryItemExtra;
import com.openplan.server.entity.model.CategoryItem;

public interface CategoryItemMapper {
    int delete(CategoryItem record);

    int insert(CategoryItem record);

    CategoryItem getById(Integer id);

    int update(CategoryItem record);
    
    List<CategoryItem> listAll();
    
    List<CategoryItemExtra> listExtraAll();
    
    List<CategoryItem> listByParentId(Integer parentNumber);
}