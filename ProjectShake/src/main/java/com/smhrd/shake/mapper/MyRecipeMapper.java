package com.smhrd.shake.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.shake.entity.RecipeInfo;

@Mapper
public interface MyRecipeMapper {
	public List<RecipeInfo> recipeList();
	public void recipeWrite(RecipeInfo rcp);
	public RecipeInfo recipeContent(int rcp_idx);
	public int recipeDelete(int rcp_idx);
}
