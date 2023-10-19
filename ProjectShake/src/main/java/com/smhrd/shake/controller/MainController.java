package com.smhrd.shake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 페이지 이동 컨트롤러
@Controller
public class MainController {
	@GetMapping("/")
	public String introPage() { // 인트로 페이지로 이동
		return "introPage";
	}
	@GetMapping("/login")
	public String loginPage() { // 로그인 페이지로 이동
		return "loginPage";
	}

	@GetMapping("/main")
	public String maingPage() { // 메인 페이지로 이동
		return "mainPage";
	}
	
	@GetMapping("/community")
	public String community() {
		return "community";
	}

	@GetMapping("/cockInfo")
	public String cockInfo() {
		return "cockInfo";
	}

	@GetMapping("/myRecipe")
	public String myRecipe() {
		return "myRecipe";
	}
}
