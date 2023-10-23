package com.smhrd.shake.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.shake.service.CommunityService;

@RestController
public class CommunityRestController {
	@Autowired
	CommunityService service;
	
	@RequestMapping("/count")
	public String count(int comm_idx) {
		int cnt = service.count(comm_idx);
		System.out.println(cnt);
		return cnt + "";
	}
}
