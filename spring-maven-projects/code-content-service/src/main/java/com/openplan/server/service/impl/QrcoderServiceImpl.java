package com.openplan.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.openplan.server.entity.model.Qrcoder;
import com.openplan.server.mybatis.mapper.QrcoderMapper;
import com.openplan.server.query.dao.MybatisQueryParamValidator;
import com.openplan.server.query.service.ConditionQueryResult;
import com.openplan.server.service.QrcoderService;

/**
 * @author lijianqiang
 *
 */
@Service("qrcoderService")
public class QrcoderServiceImpl implements QrcoderService {

    private static final Logger LOG = LoggerFactory.getLogger(QrcoderServiceImpl.class);

    @Autowired
    private QrcoderMapper mapper;
    
    @Override
    public Qrcoder getById(Integer id) {
        try {
            return mapper.getById(id);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public boolean deleteById(Qrcoder exists) {
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
    public Qrcoder insert(Qrcoder record) {
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
    public boolean updateById(Qrcoder record) {
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
    public ConditionQueryResult<Qrcoder> listByCondition(Map<String, Object> params) {
        params = MybatisQueryParamValidator.prepareForList(params);
        ConditionQueryResult<Qrcoder> result = new ConditionQueryResult<Qrcoder>(params);
        int total = countByCondition(params);
        result.setTotal(total);
        result.setPage(MybatisQueryParamValidator.resetPage(params));
        try {
            if (total > 0) {
                List<Qrcoder> rows = mapper.listByCondition(params);
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
