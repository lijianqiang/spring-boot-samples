package com.openplan.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.openplan.server.entity.model.Placeholder;
import com.openplan.server.mybatis.mapper.PlaceholderMapper;
import com.openplan.server.query.dao.MybatisQueryParamValidator;
import com.openplan.server.query.service.ConditionQueryResult;
import com.openplan.server.service.PlaceholderService;

/**
 * @author lijianqiang
 *
 */
@Service("placeholderService")
public class PlaceholderServiceImpl implements PlaceholderService {

    private static final Logger LOG = LoggerFactory.getLogger(PlaceholderServiceImpl.class);

    @Autowired
    private PlaceholderMapper mapper;
    
    @Override
    public Placeholder getById(Integer id) {
        try {
            return mapper.getById(id);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public boolean deleteById(Placeholder exists) {
        try {
            if (exists.getId() == null) {
                LOG.info("Qrcode id null, delete finished.");
                return false;
            }
            exists.setUpdateAt(System.currentTimeMillis());
            return mapper.deleteById(exists) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public Placeholder insert(Placeholder record) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Date createAt = new Date(currentTimeMillis);
            record.setCreateAt(createAt);
            record.setUpdateAt(currentTimeMillis);
            int res = mapper.insert(record);
            return (res > 0) ? record : null;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public boolean updateById(Placeholder record) {
        try {
            if (record.getId() == null) {
                LOG.info("Qrcode id null, update failed.", record.getId());
                return false;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return mapper.updateById(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public int countAll() {
        try {
            return mapper.countAll();
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return 0;
        }
    }

    @Override
    public ConditionQueryResult<Placeholder> listByCondition(Map<String, Object> params) {
        params = MybatisQueryParamValidator.prepareForList(params);
        ConditionQueryResult<Placeholder> result = new ConditionQueryResult<Placeholder>(params);
        int total = countByCondition(params);
        result.setTotal(total);
        result.setPage(MybatisQueryParamValidator.resetPage(params));
        try {
            if (total > 0) {
                List<Placeholder> rows = mapper.listByCondition(params);
                result.setRows(rows);
            }
        } catch (DataAccessException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            result.setTotal(total);
        }
        
        return result;
    }
    
    @Override
    public int countByCondition(Map<String, Object> params) {
        try {
            params = MybatisQueryParamValidator.prepareForList(params);
            return mapper.countByCondition(params);
        } catch (DataAccessException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return 0;
        }
    }

}
