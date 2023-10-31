package com.smhrd.shake.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.shake.entity.CommunityComment;
import com.smhrd.shake.service.CommunityService;

@RestController
public class CommunityRestController {
    
    @Autowired
    CommunityService service;

    @GetMapping("/commCount")
    public String count(@RequestParam("comm_idx") int comm_idx) {
        System.out.println(comm_idx);
        int cnt = service.count(comm_idx);
        System.out.println(cnt);
        return String.valueOf(cnt);
    }
    
	@GetMapping("/commCmtList")
	public List<CommunityComment> commCmtList(@RequestParam("comm_idx") int comm_idx){
		List<CommunityComment> list = service.commCmtList(comm_idx);
		return list;
	}
}