package com.springboot.druid.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.druid.demo.repository.UserRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Object queryUser() {
		return userRepository.findAll();
	}

}
