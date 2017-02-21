package com.chan.datasource;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlSessionTemplateConfig {
	
	@Resource(name="selfSqlSessionFactory")
	private SqlSessionFactory selfSqlSessionFactory;
	
	@Bean(name="selfSqlSessionTemplate")
	public SqlSessionTemplate ccdbSqlSessionTemplate(){
		return new SqlSessionTemplate(selfSqlSessionFactory);
	}
	
}
