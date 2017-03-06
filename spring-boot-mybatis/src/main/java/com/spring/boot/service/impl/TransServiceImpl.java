package com.spring.boot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.entity.domain.Foo;
import com.spring.boot.service.FooService;
import com.spring.boot.service.TransService;
import com.spring.boot.utils.StringUtil;

@Service("transService")
public class TransServiceImpl implements TransService {
	
	private static final Logger LOG = LoggerFactory.getLogger(TransServiceImpl.class);
	
	@Autowired
	FooService fooService;

	@Transactional
	@Override
	public boolean addSomeFoo(int num) {
	    LOG.info("do add and update : num=" + num);
        Foo john = fooService.getById(1);
        john.setAge(num);
        boolean update = fooService.update(john);
        LOG.info("update result:" + (update ? "success" : "failed"));
        
        Foo foo = new Foo();
        foo.setAge(num);
        foo.setName(StringUtil.getRandomString(20));
        fooService.add(foo);
        LOG.info("add result: ok");
        
        if (num%2 == 0) {
            throw new NullPointerException();
        }
		return false;
	}

}
