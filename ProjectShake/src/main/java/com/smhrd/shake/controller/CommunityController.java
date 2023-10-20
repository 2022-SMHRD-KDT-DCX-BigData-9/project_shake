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

import com.smhrd.shake.entity.CommunityInfo;
import com.smhrd.shake.entity.UserInfo;
import com.smhrd.shake.service.CommunityService;

@Controller
public class CommunityController {

	@Autowired
	CommunityService service;

	@GetMapping("/community")
	public String community(HttpSession session, Model model) {
		UserInfo member = (UserInfo) session.getAttribute("loginMember");
		if (member != null) {
			List<CommunityInfo> list = service.commuinityList();

			model.addAttribute("list", list);
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
		model.addAttribute("board", contents);
		System.out.println(contents.getComm_content());
		return "communityDetail";
	}

}