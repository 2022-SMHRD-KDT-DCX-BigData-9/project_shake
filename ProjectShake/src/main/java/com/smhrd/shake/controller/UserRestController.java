package com.smhrd.shake.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	@PostMapping("/user_info_naver") // kakao 로그인 및 회원 정보 받아오기
	public String handleUserInfoNaver(@RequestBody Map<String, Object> userInfo) {
		System.out.println(userInfo); // 콘솔 확인용
		return "success"; // 성공 메시지 반환
	}

	@PostMapping("/user_info_kakao") // naver 로그인 및 회원 정보 받아오기
	public String handleUserInfo(@RequestBody Map<String, Object> userInfo) {
		System.out.println(userInfo); // 콘솔 확인용
		return "success"; // 성공 메시지 반환
	}

}
