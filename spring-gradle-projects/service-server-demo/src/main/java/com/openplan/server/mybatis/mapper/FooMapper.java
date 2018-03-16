package com.openplan.server.mybatis.mapper;

import java.util.List;

import com.openplan.server.domain.model.Foo;
import com.openplan.server.mybatis.base.SqlMapper;

public interface FooMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Foo record);

    int insertSelective(Foo record);

    Foo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Foo record);

    int updateByPrimaryKey(Foo record);

    List<Foo> selectAll();

}
