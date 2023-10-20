package com.smhrd.shake.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.shake.entity.CocktailInfo;

@Mapper
public interface CocktailMapper {
	public List<CocktailInfo> cocktailList();
}
