package com.cos.flower.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.flower.model.User;

// DAO
//  자동으로 bean등록이 된다.
// @Repository 생략이 가능
// JpaRepository에 붙어있는 @NoRepositoryBean 어노테이션이 인스턴스를 생성할 수 있게 해준다.  
public interface UserRepository extends JpaRepository<User, Integer>{
	// username 을 SELECT 하는 함수를 생성
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}



//로그인 Repository 생성
// JPA Naming 쿼리 전략
// SELECT * FROM user WHERE username = ?(첫번째 파라미터) AND password = ?(두번째 파라미터); 이런 쿼리가 동작한다.
//User findByUsernameAndPassword(String username, String password);
	
// 위의 방식과 밑의 방식은 같다.
//@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//User login(String username, String password);
