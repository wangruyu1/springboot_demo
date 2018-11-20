package com.springboot.war.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	@RequestMapping(value = { "", "/", "index", "hello" })
	public String index() {
		return "hello";
	}
}
