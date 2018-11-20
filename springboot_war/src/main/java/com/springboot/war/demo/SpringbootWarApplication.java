package com.springboot.war.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle
//继承自SpringBootServletInitializer原因:spring-webflux不是严格的依赖于servlet容器.当servlet容器(tomcat)启动,让程序能够启动。
//如果不继承,该应用不会启动,可以去掉试一下。
@SpringBootApplication
public class SpringbootWarApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootWarApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWarApplication.class, args);
	}
}
