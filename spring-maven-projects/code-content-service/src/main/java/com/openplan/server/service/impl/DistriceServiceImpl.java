/**
 * 
 */
package com.openplan.server.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openplan.server.entity.model.ZhCityExtra;
import com.openplan.server.entity.model.ZhProvince;
import com.openplan.server.entity.model.ZhRegionExtra;
import com.openplan.server.mybatis.mapper.ZhCityMapper;
import com.openplan.server.mybatis.mapper.ZhProvinceMapper;
import com.openplan.server.mybatis.mapper.ZhRegionMapper;
import com.openplan.server.service.DistrictService;

/**
 * @author lijianqiang
 *
 */
@Service("districtService")
public class DistriceServiceImpl implements DistrictService {
    
    private static final Logger LOG = LoggerFactory.getLogger(DistriceServiceImpl.class);
    
    @Autowired
    private ZhProvinceMapper zhProvinceMapper;
    
    @Autowired
    private ZhCityMapper zhCityMapper;
    
    @Autowired
    private ZhRegionMapper zhRegionMapper;

    @Override
    public ZhCityExtra getCityById(Integer id) {
            return zhCityMapper.getById(id);
    }

    @Override
    public ZhCityExtra getCityByCityNo(Integer cityNo) {
        try {
            return zhCityMapper.getByCityNo(cityNo);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<ZhCityExtra> listCityAll() {
        try {
            return zhCityMapper.listAll();
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<ZhCityExtra> listCityByProvinceNo(Integer provinceNo) {
        try {
            return zhCityMapper.listByProvinceNo(provinceNo);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public ZhProvince getProvinceById(Integer id) {
        try {
            return zhProvinceMapper.getById(id);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public ZhProvince getProvinceByProvinceNo(Integer provinceNo) {
        try {
            return zhProvinceMapper.getByProvinceNo(provinceNo);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<ZhProvince> listProvinceAll() {
        try {
            return zhProvinceMapper.listAll();
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public ZhRegionExtra getRegionById(Integer id) {
        try {
            return zhRegionMapper.getById(id);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public ZhRegionExtra getRegionByRegionNo(Integer regionNo) {
        try {
            return zhRegionMapper.getByRegionNo(regionNo);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<ZhRegionExtra> listRegionAll() {
        try {
            return zhRegionMapper.listAll();
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<ZhRegionExtra> listRegionByCityNo(Integer cityNo) {
        try {
            return zhRegionMapper.listByCityNo(cityNo);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<ZhRegionExtra> listRegionByProvinceNo(Integer provinceNo) {
        try {
            return zhRegionMapper.listByProvinceNo(provinceNo);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public ZhCityExtra getCityByCityName(String cityName) {
        try {
            return zhCityMapper.getByCityName(cityName);
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public ZhRegionExtra getRegionByCityAndRegionName(String cityName, String regionName) {
        try {
            List<ZhRegionExtra> list = zhRegionMapper.listByRegionName(regionName);
            if (list == null || list.isEmpty()) {
                return null;
            }
            String prefix = cityName.substring(0, 2);
            for (ZhRegionExtra extra : list) {
                if (extra.getCityName().startsWith(prefix)) {
                    return extra;
                }
                
            }
            return null;
        } catch (RuntimeException e) {
            LOG.error("[***DAO***]" + e.getMessage(), e.getCause());
            return null;
        }
    }

}
