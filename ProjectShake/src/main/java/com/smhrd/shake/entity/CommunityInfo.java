package com.smhrd.shake.entity;

import java.util.Date;

import lombok.Data;

// id는 어노테이션으로 따로 잡을 것. 따른 것은 맘대로.
@Data
public class CommunityInfo {
	private int comm_idx;
	private String user_id;
	private String comm_title;
	private String comm_content;
	private Date create_at;
	private int comm_views;
	private int comm_likes;
}
