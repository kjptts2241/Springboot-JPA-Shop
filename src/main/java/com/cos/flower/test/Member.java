package com.cos.flower.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 빈 생성자
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder // 객체를 생성할때 builder()를 사용함으로써 순서대로 안해도 되고 지역변수가 부족해도 괜찮다
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
