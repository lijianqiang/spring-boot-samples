package com.openplan.server.service;

import java.util.List;

import com.openplan.server.model.Foo;

public interface FooService {
    public Foo add(Foo foo);

    public Foo getById(int id);

    public List<Foo> getAll();

    public boolean update(Foo foo);
}
