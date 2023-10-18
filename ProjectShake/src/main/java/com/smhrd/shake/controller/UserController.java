package com.smhrd.shake.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.shake.entity.UserInfo;
import com.smhrd.shake.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/login/check")
	public String loginCheck(UserInfo user, HttpSession session) {
		UserInfo member = service.logincheck(user);
		if(member != null) {
			session.setAttribute("loginMember", member);
		} else {
			System.out.println("로그인실패");
		}
		return "redirect:/mainPage";
	}
}