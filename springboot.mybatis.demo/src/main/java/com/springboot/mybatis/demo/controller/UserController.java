package com.springboot.mybatis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mybatis.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "users", method = RequestMethod.GET)
	public Object queryUsers() {
		return userService.queryAll();
	}
}
