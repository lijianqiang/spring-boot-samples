package com.spring.boot.service;

import java.util.List;

import com.spring.boot.entity.domain.Foo;

public interface FooService {
	public Foo add(Foo foo);
	
	public Foo getById(int id);
	
	public List<Foo> getAll();
}
