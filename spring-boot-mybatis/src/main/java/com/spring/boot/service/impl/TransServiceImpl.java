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
		Foo foo = new Foo();
		for (int i=1;i<num;i++) {
			foo.setAge(i);
			foo.setName(StringUtil.getRandomString(i * 10));
			Foo res = fooService.add(foo);
			if (res != null) {
				LOG.info("foo add success : {}", foo.getAge());
			}
		}
		return false;
	}

}
