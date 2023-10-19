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
	
	@PostMapping("/login/check") // 로그인 서비스
	public String loginCheck(UserInfo user, HttpSession session) {
		UserInfo member = service.loginCheck(user);
		if(member != null) {
			session.setAttribute("loginMember", member);
			return "redirect:/main";
		} else {
			System.out.println("로그인실패");
			return "redirect:/login";
		}
	}
	
	@PostMapping("/join/check") // 회원가입 서비스
	public String joinCheck(UserInfo user) {
		int row = service.joinCheck(user);
		if (row == 1) {
			return "redirect:/login";
		} else {
			System.out.println("로그인 실패"); // 자바 스크립트로 에러메세지 산출 시킬 것.
			return "redirect:/login";
		}
	}
	
	 
}