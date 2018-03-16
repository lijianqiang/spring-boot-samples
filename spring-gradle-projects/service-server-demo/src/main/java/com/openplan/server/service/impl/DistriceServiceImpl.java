/**
 * 
 */
package com.openplan.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openplan.server.domain.model.ZhCityExtra;
import com.openplan.server.domain.model.ZhProvince;
import com.openplan.server.domain.model.ZhRegionExtra;
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
		return zhCityMapper.getByCityNo(cityNo);
	}

	@Override
	public List<ZhCityExtra> listCityAll() {
		return zhCityMapper.listAll();
	}

	@Override
	public List<ZhCityExtra> listCityByProvinceNo(Integer provinceNo) {
		return zhCityMapper.listByProvinceNo(provinceNo);
	}

	@Override
	public ZhProvince getProvinceById(Integer id) {
		return zhProvinceMapper.getById(id);
	}

	@Override
	public ZhProvince getProvinceByProvinceNo(Integer provinceNo) {
		return zhProvinceMapper.getByProvinceNo(provinceNo);
	}

	@Override
	public List<ZhProvince> listProvinceAll() {
		return zhProvinceMapper.listAll();
	}

	@Override
	public ZhRegionExtra getRegionById(Integer id) {
		return zhRegionMapper.getById(id);
	}

	@Override
	public ZhRegionExtra getRegionByRegionNo(Integer regionNo) {
		return zhRegionMapper.getByRegionNo(regionNo);
	}

	@Override
	public List<ZhRegionExtra> listRegionAll() {
		return zhRegionMapper.listAll();
	}

	@Override
	public List<ZhRegionExtra> listRegionByCityNo(Integer cityNo) {
		return zhRegionMapper.listByCityNo(cityNo);
	}

	@Override
	public List<ZhRegionExtra> listRegionByProvinceNo(Integer provinceNo) {
		return zhRegionMapper.listByProvinceNo(provinceNo);
	}

	@Override
	public ZhCityExtra getCityByCityName(String cityName) {
		return zhCityMapper.getByCityName(cityName);
	}

	@Override
	public ZhRegionExtra getRegionByCityAndRegionName(String cityName, String regionName) {
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
	}

}
