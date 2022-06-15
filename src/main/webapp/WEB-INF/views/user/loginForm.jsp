<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="mb-3">
			<label for="username" class="form-label">Username</label>
			<input type="username" name="username" class="form-control" id="username" aria-describedby="usernameHelp">
		</div>

		<div class="mb-3">
			<label for=password class="form-label">Password</label>
			<input type="password" name="password" class="form-control" id="password">
		</div>

		<!--
		<div class="mb-3 form-check">
			<input type="checkbox" name="remember" class="form-check-input" id="exampleCheck1"> <label class="form-check-label" for="exampleCheck1">Check me out</label>
		</div>
		 -->
		 
		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>

</div>

<%@ include file="../layout/footer.jsp"%>


