package com.smhrd.shake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.shake.entity.UserInfo;
import com.smhrd.shake.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	public UserInfo loginCheck(UserInfo user) {
		return mapper.login(user);
	}
	
	public int joinCheck(UserInfo user) {
		return mapper.join(user);
	}
}
