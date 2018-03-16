package com.openplan.server.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openplan.server.model.Foo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FooServiceTest {
	
	@Autowired
    private FooService fooService;

	@Test
	public void testAdd() {
		Foo foo = new Foo();
		foo.setName("aaa");
		foo.setAge(20L);
		
		Foo res = fooService.add(foo);
		assertTrue("xxxxxxxxxxxx", res != null);
	}

}
