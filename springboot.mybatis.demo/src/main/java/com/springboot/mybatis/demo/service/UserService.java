package com.springboot.mybatis.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mybatis.demo.entity.User;
import com.springboot.mybatis.demo.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> queryAll() {
		return userMapper.queryAll();
	}
}
