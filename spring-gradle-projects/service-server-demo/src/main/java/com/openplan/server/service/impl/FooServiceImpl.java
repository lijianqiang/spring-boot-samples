package com.openplan.server.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openplan.server.domain.model.Foo;
import com.openplan.server.mybatis.mapper.FooMapper;
import com.openplan.server.service.FooService;

@Service("fooService")
public class FooServiceImpl implements FooService {

	@Autowired
	FooMapper mapper;

	@Override
	public Foo add(Foo foo) {
		foo.setCreated(new Date(System.currentTimeMillis()));
		int res = mapper.insert(foo);
		if (res < 1) {
			return null;
		} else {
			return mapper.selectByPrimaryKey(foo.getId());
		}
	}

	@Override
	public Foo getById(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Foo> getAll() {
		return mapper.selectAll();
	}

	@Override
	public boolean update(Foo foo) {
		int res = mapper.updateByPrimaryKey(foo);
		return (res > 0);
	}

}
