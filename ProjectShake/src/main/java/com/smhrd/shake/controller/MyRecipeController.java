package com.smhrd.shake.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.shake.converter.ImageConverter;
import com.smhrd.shake.converter.ImageToBase64;
import com.smhrd.shake.entity.CommunityInfo;
import com.smhrd.shake.entity.RecipeInfo;
import com.smhrd.shake.entity.RecipeLikes;
import com.smhrd.shake.entity.RecipeTasteInfo;
import com.smhrd.shake.entity.UserInfo;
import com.smhrd.shake.service.MyRecipeService;

@Controller
public class MyRecipeController {
	
	@Autowired
	MyRecipeService service;
	
	@GetMapping("/myRecipe")
	public String myRecipe(HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
		UserInfo member = (UserInfo) session.getAttribute("loginMember");
		if (member != null) {
			int pageSize = 6; // 한 페이지에 표시할 항목 수
			List<RecipeInfo> recipes = service.recipeList();
			int totalRecipes = recipes.size();
			int totalPages = (int) Math.ceil((double) totalRecipes / pageSize);
			
			int startIndex = (page - 1) * pageSize;
	        int endIndex = Math.min(startIndex + pageSize, totalRecipes);
	        
	        List<RecipeInfo> recipesToDisplay = recipes.subList(startIndex, endIndex);

	        model.addAttribute("recipes", recipesToDisplay);
	        model.addAttribute("page", page);
	        model.addAttribute("totalPages", totalPages);        			
		}
		return "myRecipe";
	}
	
	@PostMapping("/myRecipe/write/save")
	public String recipeWrite(RecipeInfo rcp, @RequestPart("image") MultipartFile image) {
		System.out.println(image.getOriginalFilename());
		String newFileName = UUID.randomUUID().toString() + image.getOriginalFilename();
		try {
			image.transferTo(new File(newFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		rcp.setRcp_image(newFileName);
		service.recipeWrite(rcp);
		return "redirect:/myRecipe";
	}
	
	
	@GetMapping("myRecipe/{rcp_idx}")
	public String recipeContent(@PathVariable("rcp_idx") int rcp_idx, Model model) throws IOException {
		RecipeInfo contents = service.recipeContent(rcp_idx);
		File file = new File("c:\\uploadImage\\" + contents.getRcp_image());
		ImageConverter<File, String> converter= new ImageToBase64();
		String fileStringValue = converter.convert(file);
		contents.setRcp_image(fileStringValue);
		model.addAttribute("myRecipe", contents);
		return "myRecipeDetail";
	}
	
	@GetMapping("myRecipe/delete/{rcp_idx}")
	public String recipeDelete(@PathVariable("rcp_idx") int rcp_idx, Model model) throws IOException {
		int row = service.recipeDelete(rcp_idx);
		if (row > 0) {
			return "redirect:/myRecipe"; // 삭제되었습니다 출력.
		} else {
			return "redirect:/myRecipe"; // 삭제되지 않았습니다. 출력
		}
	}
	
	@GetMapping("myRecipe/like")
	public String recipeLike(RecipeLikes like) {
		if (like.getRcp_likes().equals("0")) {
			service.recipeLike(like);
		} else {
			service.recipeDislike(like);
		}
		return "redirect:/myRecipe";
	}
	
	@GetMapping("myRecipe/recipeAssess")
	public String recipeAssess(RecipeTasteInfo rcp) {
		System.out.println(rcp);
		int rcp_idx= rcp.getRcp_idx();
		service.recipeAssess(rcp);
		return "redirect:/myRecipe/"+ rcp_idx;
	}
}
