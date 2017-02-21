package com.chan.datasource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	@Bean(name = "selfDatasource")
	@Primary
	@ConfigurationProperties(prefix = "self.datasource")
	public DataSource CCDBDataSource() {
		return DataSourceBuilder.create().build();
	}
}
