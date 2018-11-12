package com.springboot.druid.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.druid.demo.bean.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
