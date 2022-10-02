package com.kuehnenagel.egcitylist.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = { "com.kuehnenagel.egcitylist.model", "com.kuehnenagel.egcitylist.dao" ,"com.kuehnenagel.egcitylist.service" })
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class DataSourceConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {

		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));

		Properties properties = new Properties();
		properties.put("spring.datasource.sqlScriptEncoding", "UTF-8");
		properties.put("hibernate.connection.useUnicode", true);
		properties.put("hibernate.connection.characterEncoding", "utf8");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("spring.datasource.sql-script-encoding", "UTF-8");
		dataSource.setConnectionProperties(properties);

		return dataSource;
	}
}
