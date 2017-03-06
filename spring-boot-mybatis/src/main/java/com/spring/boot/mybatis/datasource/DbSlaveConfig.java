package com.spring.boot.mybatis.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = "com.spring.boot.mybatis.mapper.slave", sqlSessionTemplateRef = "slaveSqlSessionTemplate")
public class DbSlaveConfig {

	@Autowired
	private DbSlaveProperties slaveProperties;
	
	@Autowired
    private DbCommonProperties commonProperties;

	@Bean(name = "dataSourceSlave")
	public DataSource testDataSource() throws SQLException {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(slaveProperties.getUrl());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(slaveProperties.getPassword());
		mysqlXaDataSource.setUser(slaveProperties.getUsername());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
		xaDataSource.setUniqueResourceName("dataSourceSlave");
		xaDataSource.setXaDataSource(mysqlXaDataSource);

		xaDataSource.setMinPoolSize(commonProperties.getMinPoolSize());
		xaDataSource.setMaxPoolSize(commonProperties.getMaxPoolSize());
		xaDataSource.setMaxLifetime(commonProperties.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(commonProperties.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(commonProperties.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(commonProperties.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(commonProperties.getMaxIdleTime());
		//xaDataSource.setTestQuery(masterProperties.getTestQuery());
		return xaDataSource;
	}

	@Bean(name = "slaveSqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSourceSlave") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/slave/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "slaveSqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
