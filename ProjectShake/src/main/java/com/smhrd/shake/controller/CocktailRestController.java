package com.smhrd.shake.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.shake.entity.CocktailInfo;
import com.smhrd.shake.service.CocktailService;

@RestController
public class CocktailRestController {

	@Autowired
	CocktailService service;

	@GetMapping("/search")
	public List<CocktailInfo> search(String input) {
		List<CocktailInfo> list = service.search(input);
		return list;
	}
}
