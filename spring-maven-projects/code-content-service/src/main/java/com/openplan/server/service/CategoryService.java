package com.openplan.server.service;

import java.util.List;

import com.openplan.server.entity.extra.CategoryItemExtra;
import com.openplan.server.entity.model.CategoryItem;
import com.openplan.server.entity.model.ConsumerCategory;

public interface CategoryService {
    
    boolean deleteCategoryItem(CategoryItem record);

    CategoryItem insertCategoryItem(CategoryItem record);

    CategoryItem getCategoryItemById(Integer id);

    boolean update(CategoryItem record);
    
    List<CategoryItem> listCategoryItemAll();
    
    List<CategoryItemExtra> listCategoryItemExtraAll();
    
    List<CategoryItem> listCategoryItemByParentId(Integer parentId);
    
    boolean deleteConsumerCategory(ConsumerCategory record);

    ConsumerCategory insertConsumerCategory(ConsumerCategory record);

    ConsumerCategory getConsumerCategoryById(Integer id);

    boolean updateConsumerCategory(ConsumerCategory record);
    
    List<ConsumerCategory> listConsumerCategoryAll();

}
