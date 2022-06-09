package com.cos.flower.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.flower.model.User;

// DAO
//  자동으로 bean등록이 된다.
// @Repository 생략이 가능
// JpaRepository에 붙어있는 @NoRepositoryBean 어노테이션이 인스턴스를 생성할 수 있게 해준다.  
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
}
