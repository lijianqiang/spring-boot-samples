package com.openplan.server.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openplan.server.entity.extra.CategoryItemExtra;
import com.openplan.server.entity.model.CategoryItem;
import com.openplan.server.entity.model.ConsumerCategory;
import com.openplan.server.mybatis.mapper.CategoryItemMapper;
import com.openplan.server.mybatis.mapper.ConsumerCategoryMapper;
import com.openplan.server.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);
    
    @Autowired
    private ConsumerCategoryMapper consumerCategoryMapper;
    
    @Autowired
    private CategoryItemMapper categoryItemMapper;

    @Override
    public boolean deleteCategoryItem(CategoryItem record) {
        try {
            if (record.getId() == null) {
                LOG.info("categoryItemMapper id null, delete finished.");
                return true;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return categoryItemMapper.delete(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public CategoryItem insertCategoryItem(CategoryItem record) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Date createAt = new Date(currentTimeMillis);
            record.setCreateAt(createAt);
            record.setUpdateAt(currentTimeMillis);
            int res = categoryItemMapper.insert(record);
            return (res > 0) ? record : null;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public CategoryItem getCategoryItemById(Integer id) {
        try {
            return categoryItemMapper.getById(id);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public boolean update(CategoryItem record) {
        try {
            if (record.getId() == null) {
                LOG.info("CategoryItem id null, update failed.", record.getId());
                return false;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return categoryItemMapper.update(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public List<CategoryItem> listCategoryItemAll() {
        try {
            return categoryItemMapper.listAll();
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }
    
    @Override
    public List<CategoryItemExtra> listCategoryItemExtraAll() {
        try {
            return categoryItemMapper.listExtraAll();
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<CategoryItem> listCategoryItemByParentId(Integer parentId) {
        try {
            return categoryItemMapper.listByParentId(parentId);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public boolean deleteConsumerCategory(ConsumerCategory record) {
        try {
            if (record.getId() == null) {
                LOG.info("placerMapper id null, delete finished.");
                return true;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return consumerCategoryMapper.delete(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public ConsumerCategory insertConsumerCategory(ConsumerCategory record) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Date createAt = new Date(currentTimeMillis);
            record.setCreateAt(createAt);
            record.setUpdateAt(currentTimeMillis);
            int res = consumerCategoryMapper.insert(record);
            return (res > 0) ? record : null;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public ConsumerCategory getConsumerCategoryById(Integer id) {
        try {
            return consumerCategoryMapper.getById(id);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public boolean updateConsumerCategory(ConsumerCategory record) {
        try {
            if (record.getId() == null) {
                LOG.info("ConsumerCategory id null, update failed.", record.getId());
                return false;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return consumerCategoryMapper.update(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public List<ConsumerCategory> listConsumerCategoryAll() {
        try {
            return consumerCategoryMapper.listAll();
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

}
