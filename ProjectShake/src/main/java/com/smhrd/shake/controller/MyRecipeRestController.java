package com.smhrd.shake.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.shake.entity.RecipeLikes;
import com.smhrd.shake.service.MyRecipeService;

@RestController
public class MyRecipeRestController {
	
	@Autowired
	MyRecipeService service;
	
	 @GetMapping("/rcpCount")
	    public String count(@RequestParam("rcp_idx") int rcp_idx) {
	        System.out.println(rcp_idx);
	        int cnt = service.count(rcp_idx);
	        System.out.println(cnt);
	        return String.valueOf(cnt);
	    }
	
	@GetMapping("/checkLike")
	public Integer checkLike(@ModelAttribute RecipeLikes like) throws Exception {
		System.out.println(like);
		Integer cnt = service.checkLike(like);
		System.out.println(cnt);
		if (cnt == null) {
			cnt = 0;
		} else {
			cnt = 1;
		}
		System.out.println(cnt);
		return cnt;
	}
	

}
