let index = {
	init: function() {
		// id가 btn-save인 회원가입 버튼을 누르면 save 함수를 실행 
		$("#btn-save").on("click", () => { // function(){} 가 아닌 ()=>{} 을 사용한 이유는 this를 바인딩하기 위해서
			this.save();
		});
	},

	save: function() {
		// alert('user의 save함수 호출됨')
		let data = { // username, password, email 값을 받아서 data에 담는다.
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};

		// console.log(data)
		
		// 브라우저에서의 요청에 대한 응답을 Ajax로 사용하는 이유
		// 1. 요청에 대한 응답을 html 이 아닌 Data (Json)를 받기 위해서이다 (앱이 서버에서 Data로 리턴 해주기에 웹도 같이 Data 리턴 서버로 만든다.)
		// 2. 비동기 통신을 하기 위해서이다 ( ex) 동기는 라면을 다 끓어야 친구를 도와줄수 있다면 비동기는 라면을 끓이는 도중에도 친구를 도와줬다가 라면을 끓일수 있다.)
		// 비동기 통신이 필요한 이유는 통신이 일어나면 앱에서 멈춤 현상이 나타나는데 이걸 비동기가 막아준다.
		
		// ajax호출시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body데이터 (javascript Object를 JSON 문자열로 변경)
			contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
			// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환 해준다. (dataType: "json"을 안해줘도 상관없다.)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript Object로 변경
		}).done(function(resp){
			// 응답의 결과가 정상이면
			alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href="/" // 메인 페이지로 이동
		}).fail(function(error){
			// 응답의 결과가 실패이면
			alert(JSON.stringify(error));
		});
	}
}

index.init(); // 호출
