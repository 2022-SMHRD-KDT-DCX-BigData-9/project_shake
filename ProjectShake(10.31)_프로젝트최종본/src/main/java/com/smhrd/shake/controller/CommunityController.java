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
import com.smhrd.shake.entity.CommunityComment;
import com.smhrd.shake.entity.CommunityInfo;

import com.smhrd.shake.entity.UserInfo;
import com.smhrd.shake.service.CommunityService;

@Controller
public class CommunityController {

	@Autowired
	CommunityService service;

	@GetMapping("/community")
	public String community(HttpSession session, Model model, @RequestParam(defaultValue = "1") int page) {
		UserInfo member = (UserInfo) session.getAttribute("loginMember");
		if (member != null) {
			int pageSize = 10;
			List<CommunityInfo> list = service.commuinityList();
			int totalLists = list.size();
			int totalPages = (int) Math.ceil((double) totalLists / pageSize);
			
			int startIndex = (page - 1) * pageSize;
	        int endIndex = Math.min(startIndex + pageSize, totalLists);
	        
	        List<CommunityInfo> listToDisplay = list.subList(startIndex, endIndex);
	        model.addAttribute("list", listToDisplay);
	        model.addAttribute("page", page);
	        model.addAttribute("totalPages", totalPages);
		}
		return "community";
	}
	
	@GetMapping("/communitySearch")
	public String communitySearch(HttpSession session, Model model, @RequestParam(defaultValue = "1")  int page, String communitySearch) {
		UserInfo member = (UserInfo) session.getAttribute("loginMember");
		if (member != null) {
			int pageSize = 10;
			List<CommunityInfo> list = service.communitySearch(communitySearch);
			int totalLists = list.size();
			int totalPages = (int) Math.ceil((double) totalLists / pageSize);
			
			int startIndex = (page - 1) * pageSize;
	        int endIndex = Math.min(startIndex + pageSize, totalLists);
	        
	        List<CommunityInfo> listToDisplay = list.subList(startIndex, endIndex);
			
	        model.addAttribute("list", listToDisplay);
	        model.addAttribute("page", page);
	        model.addAttribute("totalPages", totalPages);
		}
		return "community";
	}
	

	@PostMapping("/community/write/save")
	public String communityWrite(CommunityInfo comm, @RequestPart("image") MultipartFile image) {
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
		comm.setComm_image(newFileName); // 새로만들어준 이름으로 img 필드 초기화
		service.communityWrite(comm);
		return "redirect:/community";
	}
	
	@GetMapping("community/{comm_idx}")
	public String communityContent(@PathVariable("comm_idx") int comm_idx, Model model) throws IOException {
		CommunityInfo contents = service.communityContent(comm_idx);
		File file = new File("c:\\uploadImage\\" + contents.getComm_image());
		ImageConverter<File, String> converter= new ImageToBase64();
		String fileStringValue = converter.convert(file);
		contents.setComm_image(fileStringValue);
		System.out.println(contents.getComm_image());
		model.addAttribute("board", contents);
		return "communityDetail";
	}

	@GetMapping("community/delete/{comm_idx}")
	public String communityDelete(@PathVariable("comm_idx") int comm_idx, Model model) throws IOException {
		int row = service.communityDelete(comm_idx);
		if (row > 0) {
			return "redirect:/community"; // 삭제되었습니다 출력.
		} else {
			return "redirect:/myRecipe"; // 삭제되지 않았습니다. 출력
		}
	}
	@GetMapping("community/commCmt")
	public String commCmt(CommunityComment cmt) {
		int comm = service.commCmt(cmt);
		int comm_idx= cmt.getComm_idx();
		return "redirect:/community/"+comm_idx;
	}
}