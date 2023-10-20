package com.smhrd.shake.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.shake.entity.CocktailInfo;
import com.smhrd.shake.mapper.CocktailMapper;

@Service
public class CocktailService {
	
	@Autowired
	CocktailMapper mapper;
	
	public List<CocktailInfo> cocktailList(){
		return mapper.cocktailList();
	}	
}
