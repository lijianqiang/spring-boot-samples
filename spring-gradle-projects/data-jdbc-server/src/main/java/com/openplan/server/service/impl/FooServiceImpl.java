package com.openplan.server.service.impl;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openplan.server.dao.FooDao;
import com.openplan.server.model.Foo;
import com.openplan.server.service.FooService;

@Service("fooService")
public class FooServiceImpl implements FooService {

	private static final Logger LOG = LoggerFactory.getLogger(FooServiceImpl.class);

	@Autowired
	private FooDao fooDao;

	@Override
	public Foo add(Foo foo) {
		foo.setCreated(new Date(System.currentTimeMillis()));
		return fooDao.insert(foo);
	}

	@Override
	public Foo getById(int id) {
		try {
			return fooDao.selectByPrimaryKey(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e.getCause());
			return null;
		}
	}

	@Override
	public List<Foo> getAll() {
		try {
			return fooDao.selectAll();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e.getCause());
			return null;
		}
	}

	@Override
	public boolean update(Foo foo) {
		int res = fooDao.updateByPrimaryKey(foo);
		return (res > 0);
	}

}
