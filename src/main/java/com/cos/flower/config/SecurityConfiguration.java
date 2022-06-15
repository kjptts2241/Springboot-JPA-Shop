package com.cos.flower.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.flower.config.auth.PrincipalDetailService;

//Security 설정
//Bean 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // Bean 등록 (IoC 관리)
@EnableWebSecurity // Security 필터가 등록이 된다. (즉 Security 설정을 해당 파일에서 한다.)
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면권한 및 인증을 미리 체크하겠다.
public class SecurityConfiguration {

	@Autowired // 스프링 시큐리티 세션에 저장된 Detail 타입의 User 정보를 DI
	private PrincipalDetailService principalDetailService;
	
	// password 값을 받아서 해쉬화 하는 Security 메서드
	@Bean // IoC
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder(); // 암호화(해쉬화) 해서 return 해준다.
	}
	
	// 스프링 시큐리티 세션에 저장된 Detail 타입의 User 정보를 가지고 DB에 해쉬화 된 password와 비교하면서 로그인 요청을 한다.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Bean // IoC
	public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			// csrf 토큰 비활성화 (요청시 csrf 토큰을 안가지고  요청을 하면 막는다. 테스트 중이기에 disable()를 적어 비활성화 시킨다.)
			.csrf().disable()
			.authorizeRequests() // request가 들어오면
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/assets/**") // " " 안에 적혀있는 경로로는
				.permitAll() // 누구나 들어올 수 있다.
				.anyRequest() // 이게 아닌 다른 모든 요청은
				.authenticated() // 인증이 되야한다.
			.and() // 그리고
				.formLogin() // formLogin 기능을 작동하고
				.loginPage("/auth/loginForm") // 인증이 되지 않은 모든 요청은 loginForm 으로 바로 이동하게
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인을 해주고
				.defaultSuccessUrl("/"); // login을 정상적으로 요청이 끝나면 "/" 경로로 이동
		return http.build(); // 빌드
	}
}
