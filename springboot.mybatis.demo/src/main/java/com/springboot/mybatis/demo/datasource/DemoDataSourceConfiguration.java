package com.springboot.mybatis.demo.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源配置,将数据源和mybatis绑定
 * 
 * @version 1.0
 */
@Configuration
@MapperScan(basePackages = DemoDataSourceConfiguration.MAPPER, sqlSessionFactoryRef = "demoPoiSqlSessionFactory")
public class DemoDataSourceConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataSourceConfiguration.class);
	static final String MAPPER = "com.springboot.mybatis.demo.mapper"; // mybatis生成的mapper文件所在的位置
	private static final String XML_MAPPER = "classpath:mapper/*.xml";// mybatis生成的xml文件所在的位置

	/**
	 * 数据源,alibaba的数据源比较强大。
	 * 
	 * @return
	 */
	@Primary
	@Bean(name = "demoPoiDataSource")
	@ConfigurationProperties("demo.datasource") // 该注解可以自动注入对象的属性(对应配置文件demo.datasource下的属性)
	public DruidDataSource demoPoiDataSource() {
		return new DruidDataSource();
	}

	/**
	 * 事务管理
	 * 
	 * @return
	 */
	@Bean(name = "demoPoiTransactionManager")
	@Primary
	public DataSourceTransactionManager demoPoiTransactionManager() {
		return new DataSourceTransactionManager(demoPoiDataSource());
	}

	/**
	 * 工厂类
	 * 
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "demoPoiSqlSessionFactory")
	@Primary
	public SqlSessionFactory demoPoiSqlSessionFactory(@Qualifier("demoPoiDataSource") DataSource dataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		// 指定扫描的xml路径
		sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(XML_MAPPER));
		return sessionFactoryBean.getObject();
	}

	@Bean(name = "demoPoiSqlSessionTemplate")
	@Primary
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public SqlSessionTemplate demoPoiSqlSessionTemplate(
			@Qualifier("demoPoiSqlSessionFactory") SqlSessionFactory demoPoiSqlSessionFactory) {
		SqlSessionTemplate demoPoiSqlSession = new SqlSessionTemplate(demoPoiSqlSessionFactory);
		LOGGER.info("注入demoPoi sqlsession({})成功.", demoPoiSqlSession);
		return demoPoiSqlSession;
	}
}
