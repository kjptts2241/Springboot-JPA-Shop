package com.cos.flower.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter, Setter 만들어 주는 기능을 포함하고 있다.
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 전체 생성자
@Builder // 빌더 패턴
//[JPA]ORM -> Java(다른언어 포함) Object -> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySql에 테이블이 생성이 된다.
// @DynamicInsert // Insert 시에 null인 필드를 제외 시켜준다. // 하지만 나중에 어노테이션을 붙일게 많아진다
public class User {
	
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다(auto_increment)
	private int id; // 오라클 : 시퀀스, MySql : auto_increment
	
	@Column(nullable = false, length = 30, unique = true) // null값이 false로 없도록 하고, 길이는 30자로 제한
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // null값이 false로 없도록 하고, 길이는 100자로 제한 [123456 => 해쉬 (비밀번호 암호화)]
	private String password; // 비밀번호
	
	@Column(nullable = false, length = 50) // null값이 false로 없도록 하고, 길이는 50자로 제한
	private String email; // 이메일
	
	// @ColumnDefault("'user'") // Default값을 user로 지정 // 문자이기에 ' '를 넣어준다. // RoleType Enum을 만들기에 주석 처리
	@Enumerated(EnumType.STRING) // DB는 RoleType이라는게 없기에 해당 Enum이 String라는걸 알려준다.
	private RoleType role; // ADMIN, USER 권한 // String는 실수 할 수 있기에 RoleType Enum을 만들어서 값을 강제한다.
	
	@CreationTimestamp // 값을 비워두고 insert해도 시간이 자동 입력
	private Timestamp createDate; // 회원이 가입한 시간
	
}
