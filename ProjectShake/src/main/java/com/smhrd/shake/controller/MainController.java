package com.smhrd.shake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {	
	@GetMapping("/")
	public String introPage() {
		return "introPage";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "loginPage";
	}
	
	@GetMapping("/main")
	public String maingPage() {
		return "mainPage";
	}
}
