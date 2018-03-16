package com.openplan.server.dao;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.openplan.server.model.Foo;

@Repository
public class FooDao {

	private static final Logger LOG = LoggerFactory.getLogger(FooDao.class);

	private final JdbcTemplate jdbcTemplate;

	private static final String TABLE = "tbl_foo";

	private static final String KEYS = "name, age, created";

	@Autowired
	public FooDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	public Foo insert(Foo model) {
		LOG.debug("create");
		final String sql = "insert into " + TABLE + " (" + KEYS + ") values(?,?,?)";

		KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, model.getName());
				ps.setLong(2, model.getAge());
				ps.setDate(3, model.getCreated());

				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		model.setId(newUserId);
		return model;
	}

	public Foo selectByPrimaryKey(Integer id) {
		return null;
	}

	public int updateByPrimaryKeySelective(Foo model) {
		return 0;
	}

	public int updateByPrimaryKey(Foo model) {
		return 0;
	}

	public List<Foo> selectAll() {
		return null;
	}

}
