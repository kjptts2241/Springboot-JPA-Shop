package com.cos.flower.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Reply {
	
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id; // 오라클 : 시퀀스, MySql : auto_increment
	
	@Column(nullable = false, length = 200)
	private String  content; // 답변 내용
	
	@ManyToOne // 하나의 게시글(Board)에 여러개의 답변(Reply)을 쓸 수 있다.
	@JoinColumn(name="boardId")
	private Board board; // 어떤 게시판의 답변인지
	
	@ManyToOne // 하나의 유저(User)는 여러개의 답변(Reply)을 쓸 수 있다.
	@JoinColumn(name="userId")
	private User user; // 어떤 유저가 답변을 적었는지 
	
	@CreationTimestamp
	private Timestamp createDate; // 답변을 적은 시간
}
