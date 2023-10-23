package com.smhrd.shake.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.shake.entity.CommunityInfo;

@Mapper
public interface CommunityMapper {
	public List<CommunityInfo> commuinityList();
	public void communityWrite(CommunityInfo comm);
	public CommunityInfo communityContent(int comm_idx);
	public int communityDelete(int comm_idx);

	public int count(int comm_idx);
}
