package com.openplan.server.service.impl;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openplan.server.entity.model.Foo;
import com.openplan.server.mybatis.mapper.FooMapper;
import com.openplan.server.service.FooService;

@Service("fooService")
public class FooServiceImpl implements FooService {

    private static final Logger LOG = LoggerFactory.getLogger(FooServiceImpl.class);

    @Autowired
    FooMapper mapper;

    @Override
    public Foo add(Foo foo) {
        try {
            foo.setCreated(new Date(System.currentTimeMillis()));
            int res = mapper.insert(foo);
            if (res < 1) {
                return null;
            } else {
                return mapper.selectByPrimaryKey(foo.getId());
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public Foo getById(int id) {
        try {
            return mapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public List<Foo> getAll() {
        try {
            return mapper.selectAll();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public boolean update(Foo foo) {
        int res = mapper.updateByPrimaryKey(foo);
        return (res > 0);
    }

}
