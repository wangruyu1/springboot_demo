package com.springboot.mybatis.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@RequestMapping(value = { "", "/", "/index", "/hello" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Object hello(@RequestParam(name = "name", required = false, defaultValue = "游客") String name) {
		return "hello " + name;
	}
}
