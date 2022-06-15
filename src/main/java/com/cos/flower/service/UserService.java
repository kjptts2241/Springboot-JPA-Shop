package com.cos.flower.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.flower.model.RoleType;
import com.cos.flower.model.User;
import com.cos.flower.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다. 즉 IoC를 해준다.
public class UserService {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	@Autowired // SecurityConfiguration 에 있는 암호화(해쉬화) 메서드를 DI 한다.
	private BCryptPasswordEncoder encoder;
	
	// 회원가입 service
	@Transactional // 회원가입 전체의 서비스가 하나의 트랜잭션으로 묶이게 된다.
	// 전체가 성공하면 commit이 되고 실패가 뜨면 rollback이 된다.
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // password를 받아서
		String encPassword = encoder.encode(rawPassword); // password를 암호화(해쉬화) 한다.
		user.setPassword(encPassword); // 암호화(해쉬화)한 password를 set한다.
		user.setRole(RoleType.USER); // user 권한을 set 한다.
		userRepository.save(user); // user정보를 DB에 Insert
		// try catch를 쓰지 않아도 save에 오류가 생기면 GlobalExceptionHandler 을 실행한다.
		// 왜냐하면 GlobalExceptionHandler 는 Exception에 대한 모든 것을 처리하기 때문이다.
	}
	
}
