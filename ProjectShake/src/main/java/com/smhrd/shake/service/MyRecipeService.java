package com.smhrd.shake.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.shake.entity.RecipeInfo;
import com.smhrd.shake.entity.RecipeLikes;
import com.smhrd.shake.entity.RecipeTasteInfo;
import com.smhrd.shake.entity.RecipeTasteInfoAVG;
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
	
	public int count(int rcp_idx) {
		return mapper.count(rcp_idx);
	}
	
	public Integer checkLike(RecipeLikes like) {
		return mapper.checkLike(like);
	}
	
	public int recipeLike(RecipeLikes like) {
		System.out.println(like);
		return mapper.recipeLike(like);
	}
	
	public int recipeDislike(RecipeLikes like) {
		return mapper.recipeDislike(like);
	}
	
	public List<RecipeTasteInfoAVG> recipeChart(int rcp_idx) {
		return mapper.recipeChart(rcp_idx);
	}
	
	public int recipeAssess(RecipeTasteInfo rcp) {
		return mapper.recipeAssess(rcp);
	}
}
