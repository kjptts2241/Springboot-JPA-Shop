package com.cos.flower.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 라고 되있는 곳만 허용하도록 한다.
//  + 그냥 주소가 / 이면 index.jsp 허용한다.
//  + static이하에 있는 /js/**, /css/**, /image/**, /assets/** 들도 허용한다.

@Controller
public class UserController {

	// 회원가입 페이지
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	// 로그인 페이지
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
}
