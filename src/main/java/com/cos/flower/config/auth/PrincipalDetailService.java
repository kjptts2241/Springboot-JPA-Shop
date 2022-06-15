package com.cos.flower.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.flower.model.User;
import com.cos.flower.repository.UserRepository;

// 회원가입한 정보를 가지고 세션에 넣고 그걸로 로그인 처리를 하기 위해 만든다.
@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired // DI
	private UserRepository userRepository;
	
	// 스프링 서큐리티가 로그인 요청을 가로챌 때 username, password 변수 두개를 가로채는데
	// password 부분 처리는 알아서 한다, username이 DB에 있는지만 확인하면 된다.
	@Override
	// 로그인을 가로채서 username을 가져와서 DB에서 찾는다.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username) // User 오브젝트를 가져와서 Principal 에 담고
				.orElseThrow(()->{ // 없으면 Exception 실행
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
				});
		return new PrincipalDetail(principal); // 스프링 시큐리티의 세션에 principal를 가져온 후 PrincipalDetail에 담아서 저장이 된다.
	}
}
