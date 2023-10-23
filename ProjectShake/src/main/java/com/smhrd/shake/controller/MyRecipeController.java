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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.shake.entity.RecipeInfo;
import com.smhrd.shake.entity.RecipeLikes;
import com.smhrd.shake.entity.UserInfo;
import com.smhrd.shake.service.MyRecipeService;

@Controller
public class MyRecipeController {
	
	@Autowired
	MyRecipeService service;
	
	@GetMapping("/myRecipe")
	public String myRecipe(HttpSession session, Model model) {
		UserInfo member = (UserInfo) session.getAttribute("loginMember");
		if (member != null) {
			List<RecipeInfo> list = service.recipeList();
			model.addAttribute("list", list);
		}
		return "myRecipe";
	}

	@PostMapping("/myRecipe/write/save")
	public String recipeWrite(RecipeInfo rcp, @RequestPart("image") MultipartFile image) {
		System.out.println(image.getOriginalFilename());
		// 파일 이름이 겹치지 않도록 -> UUID사용(시스템적으로 절대 겹치지 않는 문자열을 생성)
		// UUID+OriginalFilename
		String newFileName = UUID.randomUUID().toString() + image.getOriginalFilename();

		// 지정한 경로에 이미지 파일 저장(경로는 application.properties에)
		// 런타임 오류 : 코드가 실행되었을 때 발생하는 오류 -> 예외처리 (try~catch)
		try {
			image.transferTo(new File(newFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		rcp.setRcp_image(newFileName); // 새로만들어준 이름으로 img 필드 초기화
		service.recipeWrite(rcp);
		return "redirect:/myRecipe";
	}
	
	@GetMapping("myRecipe/{rcp_idx}")
	public String recipeContent(@PathVariable("rcp_idx") int rcp_idx, Model model) throws IOException {
		RecipeInfo contents = service.recipeContent(rcp_idx);
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
}
