package com.smhrd.shake.entity;

import java.util.Date;

import lombok.Data;


@Data
public class UserInfo {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_nick;
	private Date user_birthdate;
	private Date user_joindate;	
}
