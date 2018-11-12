package com.springboot.druid.demo.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = { "com.springboot.druid.demo.repository" })
@EntityScan(basePackages = { "com.springboot.druid.demo.bean" })
public class MyDataSource {

	@Bean
	@Primary
	PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Primary
	@Bean(name = "springPoiDataSource")
	@ConfigurationProperties("spring.datasource") // 该注解可以自动注入对象的属性(对应配置文件news.datasource下的属性)
	public DruidDataSource newsPoiDataSource() {
		return new DruidDataSource();
	}
}
