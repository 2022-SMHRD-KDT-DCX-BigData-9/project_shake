package com.smhrd.shake.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.shake.entity.UserInfo;

@Mapper
public interface UserMapper {
	public UserInfo login(UserInfo user);
	public int join(UserInfo user);
}
