package com.openplan.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.openplan.server.entity.model.Placer;
import com.openplan.server.mybatis.mapper.PlacerMapper;
import com.openplan.server.query.dao.MybatisQueryParamValidator;
import com.openplan.server.query.service.ConditionQueryResult;
import com.openplan.server.service.PlacerService;
import com.openplan.server.util.StringUtils;

@Service("placerService")
public class PlacerServiceImpl implements PlacerService {
    
    private static final Logger LOG = LoggerFactory.getLogger(QrcoderServiceImpl.class);

    @Autowired
    private PlacerMapper placerMapper;

    @Override
    public boolean deleteById(Placer record) {
        try {
            if (record.getId() == null) {
                LOG.info("placerMapper id null, delete finished.");
                return false;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return placerMapper.deleteById(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public Placer insert(Placer record) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Date createAt = new Date(currentTimeMillis);
            record.setCreateAt(createAt);
            record.setUpdateAt(currentTimeMillis);
            int res = placerMapper.insert(record);
            return (res > 0) ? record : null;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public Placer getById(Integer id) {
        try {
            return placerMapper.getById(id);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public Placer getByMarker(String marker) {
        try {
            return placerMapper.getByMarker(marker);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public boolean updateById(Placer record) {
        try {
            if (record.getId() == null) {
                LOG.info("Placer id null, update failed.", record.getId());
                return false;
            }
            record.setUpdateAt(System.currentTimeMillis());
            return placerMapper.updateById(record) > 0;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public List<Placer> listByCityNo(Integer cityNo) {
        try {
            return placerMapper.listByCityNo(cityNo);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<Placer> listByRegionNo(Integer regionNo) {
        try {
            return placerMapper.listByRegionNo(regionNo);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<Placer> listByLikeGeohash(String geohash, int accuracy) {
        if (StringUtils.isEmpty(geohash)) {
            return null;
        }
        int len = geohash.length();
        String value = geohash;
        if (len > accuracy) {
            value = geohash.substring(0, accuracy);
        }
        try {
            LOG.info("listByLikeGeohash:value:{}", value);
            return placerMapper.listByLikeGeohash(value);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public int countAll() {
        try {
            return placerMapper.countAll();
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return 0;
        }
    }

    @Override
    public ConditionQueryResult<Placer> listByCondition(Map<String, Object> params) {
        params = MybatisQueryParamValidator.prepareForList(params);
        ConditionQueryResult<Placer> result = new ConditionQueryResult<Placer>(params);
        int total = countByCondition(params);
        result.setTotal(total);
        result.setPage(MybatisQueryParamValidator.resetPage(params));
        try {
            if (total > 0) {
                List<Placer> rows = placerMapper.listByCondition(params);
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
            return placerMapper.countByCondition(params);
        } catch (DataAccessException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return 0;
        }
    }

}
