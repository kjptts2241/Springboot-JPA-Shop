package com.cos.flower.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.flower.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	// 매개변수로 principal 들고오는 법 : @AuthenticationPrincipal PrincipalDetail principal
	// 메인(index) 페이지
	@GetMapping({"", "/"})
	public String index() { // Controller에서 세션 접근할 때 
		// /WEB-INF/views/index.jsp
		return "index";
	}
	
	// Q&A 페이지
	@GetMapping("/auth/boardForm")
	public String FQAForm() {
		return "board";
	}
	
	// Q&A 글쓰기 페이지
	// USER 권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
