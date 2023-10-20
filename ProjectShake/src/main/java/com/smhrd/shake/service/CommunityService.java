package com.smhrd.shake.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.shake.entity.CommunityInfo;
import com.smhrd.shake.mapper.CommunityMapper;

@Service
public class CommunityService {
	@Autowired
	CommunityMapper mapper;
	
	public List<CommunityInfo> commuinityList(){
		return mapper.commuinityList();
	}
	
	public void communityWrite(CommunityInfo comm) {
		mapper.communityWrite(comm);
	}
	
	public CommunityInfo communityContent(int comm_idx) {
		return mapper.communityContent(comm_idx);
	}
	
	public int count(int comm_idx) {
		return mapper.count(comm_idx);
	}
	
}
