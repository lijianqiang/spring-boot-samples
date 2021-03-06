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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = "com.spring.boot.mybatis.mapper.master", sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class DbMasterConfig {

	@Autowired
	private DbMasterProperties masterProperties;
	
	@Autowired
    private DbCommonProperties commonProperties;

	@Bean(name = "dataSourceMaster")
	@Primary
	public DataSource testDataSource() throws SQLException {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(masterProperties.getUrl());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(masterProperties.getPassword());
		mysqlXaDataSource.setUser(masterProperties.getUsername());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
		xaDataSource.setUniqueResourceName("dataSourceMaster");
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

	@Bean(name = "masterSqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSourceMaster") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/master/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "masterSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
