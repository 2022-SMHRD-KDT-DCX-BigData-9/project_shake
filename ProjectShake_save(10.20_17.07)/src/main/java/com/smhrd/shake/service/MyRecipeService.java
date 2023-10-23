package com.smhrd.shake.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.shake.entity.RecipeInfo;
import com.smhrd.shake.mapper.MyRecipeMapper;

@Service
public class MyRecipeService {
	
	@Autowired
	 MyRecipeMapper mapper;
	
	public List<RecipeInfo> recipeList(){
		return mapper.recipeList();
	}
	
	public void recipeWrite(RecipeInfo rcp) {
		mapper.recipeWrite(rcp);
	}
	
	public RecipeInfo recipeContent(int rcp_idx) {
		return mapper.recipeContent(rcp_idx);
	}
	public int recipeDelete(int rcp_idx) {
		return mapper.recipeDelete(rcp_idx);
	}
}
