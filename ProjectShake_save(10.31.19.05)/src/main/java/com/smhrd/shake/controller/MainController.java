package com.smhrd.shake.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.smhrd.shake.entity.CockMix;
import com.smhrd.shake.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	MainService service;
	
	@GetMapping("/")
	public String introPage() { // 인트로 페이지로 이동
		return "introPage";
	}
	@GetMapping("/login")
	public String loginPage() { // 로그인 페이지로 이동
		return "loginPage";
	}

	@GetMapping("/main")
	public String mainPage(Model model) { // 메인 페이지로 이동
		List<CockMix> list = service.mainPage();
		model.addAttribute("cockMix", list);
		return "mainPage";
	}
	
	@GetMapping("/community/write")
	public String comuunityWrite() {
		return "communityWrite";
	}
	
	@GetMapping("/myRecipe/write")
	public String myRecipeWrite() {
		return "myRecipeWrite";
	}
	
	@GetMapping("/userUpdate")
	public String userUpdate() {
		return "userUpdate";
	}
	@GetMapping("/socialJoin")
	public String socialJoin() {
		return "socialJoin";
	}
	
}
