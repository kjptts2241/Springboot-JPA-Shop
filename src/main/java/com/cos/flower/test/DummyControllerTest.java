package com.cos.flower.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.flower.model.RoleType;
import com.cos.flower.model.User;
import com.cos.flower.repository.UserRepository;

@RestController // html 파일이 아니라 data를 리턴 해주는 controller = RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	
	
	// User 정보 Delete
	// http://localhost:8000/flower/dummy/user/{id}
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다. id : " + id;
	}
	
	
	
	// 더티 체킹을 이용한 Updata
	// email, password 수정 할 수 있게
	// http://localhost:8000/flower/dummy/user/{id}
	// 더티 체킹 : User오브젝트와 JPA 영속성 컨텍스트 안에 영속화된 User오브젝트를 비교해서 변경 된걸 인식하면 Controller 종료 시(함수 종료 시 자동 commit)에 DB에 Updata문을 실행
	@Transactional // @Transactional 어노테이션을 사용하면, userRepository.save(user); 를 쓰지 않더라도 값을 변경하면 데이터가 update된다.
	@PutMapping("/dummy/user/{id}")
	// @RequestBody : // json 데이터를 요청 => Java Object(MessageConverter의 Jackson 라이브러리가 변환해서 로 변환해서 받아줌.)
	public User updataUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		// id로 DB에 있는 데이터를 user에 담고 id가 없다면 orElseThrow 실행
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword()); // PUT(수정)요청을 보낸 requestUser.getPassword()가 user.setPassword에 저장
		user.setEmail(requestUser.getEmail()); // Put(수정)요청을 보낸requestUser.getEmail()가 user.setEmail에 저장
		
		// save함수는 id를 전달하지 않으면 insert를 해주고
		// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 updata를 해주고
		// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다.
		// userRepository.save(user); // 수정된 user를 DB에 저장
		
		return user;
	}
	
	
	
	// user 전체 select
	// http://localhost:8000/flower/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	
	
	// 페이징
	// 한 페이지당 2건의 데이터를 리턴받아 보기
	// http://localhost:8000/flower/dummy/user
	// http://localhost:8000/flower/dummy/user?page=0 첫번째 page
	// http://localhost:8000/flower/dummy/user?page=1 두번째 page
	@GetMapping("/dummy/user")
	// 페이지에 두건의 데이터와 sort는 id로 DESC로 최신순으로 페이징
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> paginUser = userRepository.findAll(pageable);
		
		List<User> users = paginUser.getContent(); // User의 Content만 가져오기
		return users;
	}
	
	
	
	// user를 id 값으로 select
	// {id} 주소로 파라미터를 전달 받을 수 있음.
	// http://localhost:8000/flower/dummy/user/{id}
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null 이 리턴이 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		// 그러니 Repository에 있는 Optional로 너의 user 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!!
		
//		// 람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
//		return user;
		
		// id로 DB에 있는 데이터를 user에 담고 id가 없다면 orElseThrow 실행
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			// orElseThrow이 실행되어 빈 객체가 user로 저장이 된다. 결과 null 값이 나올 수 없게 된다.
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("유저는 없습니다. id : " + id); 
			}
		});
		// 요청 : 웹 브라우저
		// user 객체 = 자바 오브젝트
		// RestController 인데 어떻게 웹 브라우저가 자바 오브젝트(User)를 받을 수 있느냐
		// 변환 ( 웹 브라우저가 이해할 수 있는 데이터) -> json (예전에는 Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 return 하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
	}
	
	
	// User 정보 Insert
	// http://localhost:8000/flower/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { // key=value (약속된 규칙) 
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER); // user의 role를 USER로 set
		userRepository.save(user); // user정보를 DB에 저장
		return "회원가입이 완료되었습니다.";
	}
}
