package com.cos.flower.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id; // 오라클 : 시퀀스, MySql : auto_increment
	
	@Column(nullable = false, length = 100)
	private String title; // 게시판 제목
	
	@Lob // 대용량 데이터
	private String  content; // 게시판 내용 // 섬머노트 라이브러리를 이용 // <html>태그가 섞여서 디자인이 됨.(글자 용량이 엄청 커지게 됨)
	
	@ColumnDefault("0") // int이므로 ' ' 필요 x
	private int count; // 조회수
	
	// 자바에서는 객체를 저장 할 수 있지만 DB에는 객체를 저장할 방법이 없기에 JoinColumn으로 필드값을 만들고 ManyToOne로 연관 관계를 만든다. 
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One 한명의 유저는 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name="userId") // DB에 만들어질때는 User객체의 FK로 userId라는 필드값이 만들어 진다. // User객체의 id를 참조하기에 int 값이다
	private User user; // 게시글을 누가 적었는지 // User객체를 참조한다.
	
	// @JoinColumn은 DB에 칼럼이 만들어지면 게시글 하나에 여러개의 답변이므로 1정규화가 깨지기 때문에 안 넣는다.
	// 하나의 게시글(Board)은 여러개의 답변(Reply)를 가질 수 있다.
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy :  연관관계의 주인이 아니다. 난 FK가 아니기에 DB에 칼럼을 만들지 마라.
	private List<Reply> reply; // 게시글을 가지고 올때 답변도 가지고 올수 있도록 // 답변은 하나만 들고 올수 없기에 List를 넣어서 컬렉션 되게 만들기
	
	@CreationTimestamp
	private Timestamp createData; // 게시판을 올린 시간
}
