package com.spring.boot.mybatis.mapper;

import java.util.List;

import com.spring.boot.entity.domain.Foo;
import com.spring.boot.mybatis.base.SqlMapper;

public interface FooMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Foo record);

    int insertSelective(Foo record);

    Foo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Foo record);

    int updateByPrimaryKey(Foo record);

    List<Foo> selectAll();

}
