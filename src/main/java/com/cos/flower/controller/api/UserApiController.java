package com.cos.flower.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.flower.dto.ResponseDto;
import com.cos.flower.model.RoleType;
import com.cos.flower.model.User;
import com.cos.flower.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired // UserService를 DI 한다.
	private UserService userService;
	
	 // 회원가입 Controller
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		
		// 실제로 DB에 insert를 하고 아래에서 return이 되면 된다.
		userService.회원가입(user); // UserService에 있는 save 메서드를 실행
		
		// ResponseDto 에 있는 status 값을 HttpStatus.OK(http에서 통신이 정상적으로 성공했다는 의미)로 보여준다.
		// ResponseDto 에 있는 data 값을 result(회원가입에 성공하면 1을 return, 실패하면 -1을 return)로 보여준다.
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴 (Jackson 라이브러리)
	}
	
}
