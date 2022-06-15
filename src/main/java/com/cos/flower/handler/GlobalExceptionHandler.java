package com.cos.flower.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.flower.dto.ResponseDto;

// 오류 페이지 (예외 처리)
@ControllerAdvice // 모든 인셉션이 발생하면 여기로 오게 한다.
@RestController
public class GlobalExceptionHandler {
	
	// Exception 이 발생하면  그 인셉션에 대한 것은 밑의 파라미터에 전달하고 리턴
	@ExceptionHandler(value=Exception.class) // Exception(예외) : 모든 인셉션
	public ResponseDto<String> handleArgumentException(Exception e) {
		// 오류 페이지로 status는 에러로 뜨게 하고, 에러 메세지가 뜨게 한다.
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
}
