package com.springboot.devtools.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDevtoolsApplication {

	public static void main(String[] args) {
		// 禁用重启 
//		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(SpringbootDevtoolsApplication.class, args);
	}
}
