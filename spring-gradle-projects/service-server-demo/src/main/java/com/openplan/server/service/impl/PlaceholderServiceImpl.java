package com.openplan.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openplan.server.core.dao.mybatis.MybatisMapValidator;
import com.openplan.server.domain.model.Placeholder;
import com.openplan.server.mybatis.mapper.PlaceholderMapper;
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
		return mapper.getById(id);
	}

	@Override
	public boolean deleteById(Placeholder exists) {
		if (exists.getId() == null) {
			LOG.info("Qrcode id null, delete finished.");
			return false;
		}
		exists.setUpdateAt(System.currentTimeMillis());
		return mapper.deleteById(exists) > 0;
	}

	@Override
	public Placeholder insert(Placeholder record) {
		long currentTimeMillis = System.currentTimeMillis();
		Date createAt = new Date(currentTimeMillis);
		record.setCreateAt(createAt);
		record.setUpdateAt(currentTimeMillis);
		int res = mapper.insert(record);
		return (res > 0) ? record : null;
	}

	@Override
	public boolean updateById(Placeholder record) {
		if (record.getId() == null) {
			LOG.info("Qrcode id null, update failed.", record.getId());
			return false;
		}
		record.setUpdateAt(System.currentTimeMillis());
		return mapper.updateById(record) > 0;
	}

	@Override
	public int countAll() {
		return mapper.countAll();
	}

	@Override
	public List<Placeholder> listByCondition(Map<String, Object> params) {
		params = MybatisMapValidator.prepare(params);
		List<Placeholder> rows = new ArrayList<Placeholder>();
		rows = mapper.listByCondition(params);

		return rows;
	}

	@Override
	public int countByCondition(Map<String, Object> params) {
		params = MybatisMapValidator.prepare(params);
		return mapper.countByCondition(params);
	}

}
