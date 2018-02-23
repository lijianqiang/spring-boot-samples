package com.openplan.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openplan.server.entity.model.Foo;
import com.openplan.server.service.FooService;
import com.openplan.server.service.TransService;
import com.openplan.server.util.RandomUtils;

@Transactional
@Service("transService")
public class TransServiceImpl implements TransService {

    private static final Logger LOG = LoggerFactory.getLogger(TransServiceImpl.class);

    @Autowired
    FooService fooService;

    @Override
    public boolean addSomeFoo(int num) {
        LOG.info("do add and update : num=" + num);
        Foo john = fooService.getById(1);
        john.setAge(num);
        boolean update = fooService.update(john);
        LOG.info("update result:" + (update ? "success" : "failed"));

        Foo foo = new Foo();
        foo.setAge(num);
        foo.setName(RandomUtils.getRandomString(20));
        fooService.add(foo);
        LOG.info("add result: ok");

        if (num % 2 == 0) {
            throw new NullPointerException();
        }

        return false;
    }

}
