package com.cos.flower.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// 오류 페이지
@ControllerAdvice // 모든 인셉션이 발생하면 여기로 오게 한다.
@RestController
public class GlobalExceptionHandler {
	
	// Exception이 발생하면  그 인셉션에 대한 것은 밑의 파라미터에 전달하고 리턴
	@ExceptionHandler(value=Exception.class) // Exception : 모든 인셉션
	public String handleArgumentException(Exception e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
