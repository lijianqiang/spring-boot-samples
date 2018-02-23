package com.openplan.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.openplan.server.entity.model.Corporation;
import com.openplan.server.mybatis.mapper.CorporationMapper;
import com.openplan.server.query.dao.MybatisQueryParamValidator;
import com.openplan.server.query.service.ConditionQueryResult;
import com.openplan.server.service.CorporationService;

@Service("corporationService")
public class CorporationServiceImpl implements CorporationService {

    private static final Logger LOG = LoggerFactory.getLogger(CorporationServiceImpl.class);
    
    @Autowired
    private CorporationMapper corporationMapper;
    
    @Override
    public boolean delete(Corporation record) {
        try {
            if (record.getId() == null) {
                LOG.info("Corporation id null, delete finished.");
                return false;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return corporationMapper.delete(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public Corporation insert(Corporation record) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Date createAt = new Date(currentTimeMillis);
            record.setCreateAt(createAt);
            record.setUpdateAt(currentTimeMillis);
            int res = corporationMapper.insert(record);
            return (res > 0) ? record : null;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public Corporation getById(Integer id) {
        try {
            return corporationMapper.getById(id);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public boolean update(Corporation record) {
        try {
            if (record.getId() == null) {
                LOG.info("Placer id null, update failed.", record.getId());
                return false;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return corporationMapper.update(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public int countAll() {
        try {
            return corporationMapper.countAll();
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return 0;
        }
    }

    @Override
    public int countByCondition(Map<String, Object> params) {
        try {
            params = MybatisQueryParamValidator.prepareForList(params);
            return corporationMapper.countByCondition(params);
        } catch (DataAccessException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return 0;
        }
    }

    @Override
    public ConditionQueryResult<Corporation> listByCondition(Map<String, Object> params) {
        params = MybatisQueryParamValidator.prepareForList(params);
        ConditionQueryResult<Corporation> result = new ConditionQueryResult<Corporation>(params);
        int total = countByCondition(params);
        result.setTotal(total);
        result.setPage(MybatisQueryParamValidator.resetPage(params));
        try {
            if (total > 0) {
                List<Corporation> rows = corporationMapper.listByCondition(params);
                result.setRows(rows);
            }
        } catch (DataAccessException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            result.setTotal(total);
        }
        
        return result;
    }

    @Override
    public List<Corporation> listByPlacerId(Integer placerId) {
        try {
            return corporationMapper.listByPlacerId(placerId);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }


}
