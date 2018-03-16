package com.openplan.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.openplan.server.core.dao.mybatis.MybatisMapValidator;
import com.openplan.server.domain.model.Placer;
import com.openplan.server.mybatis.mapper.PlacerMapper;
import com.openplan.server.service.PlacerService;

@Service("placerService")
public class PlacerServiceImpl implements PlacerService {

	private static final Logger LOG = LoggerFactory.getLogger(PlacerServiceImpl.class);

	@Autowired
	private PlacerMapper placerMapper;

	@Override
	public boolean deleteById(Placer record) {
		if (record.getId() == null) {
			LOG.info("placerMapper id null, delete finished.");
			return false;
		}
		record.setUpdateAt(System.currentTimeMillis());
		return placerMapper.deleteById(record) > 0;
	}

	@Override
	public Placer insert(Placer record) {
		long currentTimeMillis = System.currentTimeMillis();
		Date createAt = new Date(currentTimeMillis);
		record.setCreateAt(createAt);
		record.setUpdateAt(currentTimeMillis);
		int res = placerMapper.insert(record);
		return (res > 0) ? record : null;
	}

	@Override
	public Placer getById(Integer id) {
		return placerMapper.getById(id);
	}

	@Override
	public Placer getByMarker(String marker) {
		return placerMapper.getByMarker(marker);
	}

	@Override
	public boolean updateById(Placer record) {
		if (record.getId() == null) {
			LOG.info("Placer id null, update failed.", record.getId());
			return false;
		}
		record.setUpdateAt(System.currentTimeMillis());
		return placerMapper.updateById(record) > 0;
	}

	@Override
	public List<Placer> listByCityNo(Integer cityNo) {
		return placerMapper.listByCityNo(cityNo);
	}

	@Override
	public List<Placer> listByRegionNo(Integer regionNo) {
		return placerMapper.listByRegionNo(regionNo);
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
		LOG.info("listByLikeGeohash:value:{}", value);
		return placerMapper.listByLikeGeohash(value);
	}

	@Override
	public int countAll() {
		return placerMapper.countAll();
	}

	@Override
	public List<Placer> listByCondition(Map<String, Object> params) {
		params = MybatisMapValidator.prepare(params);
		return placerMapper.listByCondition(params);
	}

	@Override
	public int countByCondition(Map<String, Object> params) {
		params = MybatisMapValidator.prepare(params);
		return placerMapper.countByCondition(params);
	}

}
