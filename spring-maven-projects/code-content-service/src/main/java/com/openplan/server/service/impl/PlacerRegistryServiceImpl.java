package com.openplan.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.openplan.server.entity.model.PlacerRegistry;
import com.openplan.server.mybatis.mapper.PlacerRegistryMapper;
import com.openplan.server.query.dao.MybatisQueryParamValidator;
import com.openplan.server.query.service.ConditionQueryResult;
import com.openplan.server.service.PlacerRegistryService;

@Service("placerRegistryService")
public class PlacerRegistryServiceImpl implements PlacerRegistryService {
    
    private static final Logger LOG = LoggerFactory.getLogger(PlacerRegistryServiceImpl.class);
    
    @Autowired
    private PlacerRegistryMapper placerRegistryMapper;

    @Override
    public boolean delete(PlacerRegistry record) {
        try {
            if (record.getId() == null) {
                LOG.info("PlacerRegistry id null, delete finished.");
                return false;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return placerRegistryMapper.delete(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public PlacerRegistry insert(PlacerRegistry record) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Date createAt = new Date(currentTimeMillis);
            record.setCreateAt(createAt);
            record.setUpdateAt(currentTimeMillis);
            int res = placerRegistryMapper.insert(record);
            return (res > 0) ? record : null;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public PlacerRegistry getById(Integer id) {
        try {
            return placerRegistryMapper.getById(id);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public boolean update(PlacerRegistry record) {
        try {
            if (record.getId() == null) {
                LOG.info("PlacerRegistry id null, update failed.", record.getId());
                return false;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return placerRegistryMapper.update(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public int countAll() {
        try {
            return placerRegistryMapper.countAll();
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return 0;
        }
    }

    @Override
    public int countByCondition(Map<String, Object> params) {
        try {
            params = MybatisQueryParamValidator.prepareForList(params);
            return placerRegistryMapper.countByCondition(params);
        } catch (DataAccessException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return 0;
        }
    }

    @Override
    public ConditionQueryResult<PlacerRegistry> listByCondition(Map<String, Object> params) {
        params = MybatisQueryParamValidator.prepareForList(params);
        ConditionQueryResult<PlacerRegistry> result = new ConditionQueryResult<PlacerRegistry>(params);
        int total = countByCondition(params);
        result.setTotal(total);
        result.setPage(MybatisQueryParamValidator.resetPage(params));
        try {
            if (total > 0) {
                List<PlacerRegistry> rows = placerRegistryMapper.listByCondition(params);
                result.setRows(rows);
            }
        } catch (DataAccessException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            result.setTotal(total);
        }
        
        return result;
    }

}
