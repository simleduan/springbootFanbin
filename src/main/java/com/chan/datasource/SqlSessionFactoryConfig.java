package com.chan.datasource;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;



@Configuration
public class SqlSessionFactoryConfig {
	
	@Resource(name="selfDatasource")
	private DataSource selfDatasource;
	
	@Bean(name="selfSqlSessionFactory")
	public SqlSessionFactory ccdbSqlSessionFactoryBean(){
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(selfDatasource);
		
		PathMatchingResourcePatternResolver resolver_config = new PathMatchingResourcePatternResolver();
		try {
			bean.setConfigLocation(resolver_config.getResources("classpath:mybatis-config.xml")[0]);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		PathMatchingResourcePatternResolver resolver_mapper = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver_mapper.getResources("classpath:com/chan/info/mapper/*_mapper.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		try {
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}











