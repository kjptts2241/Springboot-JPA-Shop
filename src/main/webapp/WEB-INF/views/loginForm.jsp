<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>

<div class="container">

	<form>
		<div class="mb-3">
			<label for="username" class="form-label">Username</label> <input type="username" class="form-control" id="username" aria-describedby="usernameHelp">
		</div>
		
		<div class="mb-3">
			<label for=password class="form-label">Password</label> <input type="password" class="form-control" id="password">
		</div>
		
		<div class="mb-3 form-check">
			<input type="checkbox" class="form-check-input" id="exampleCheck1"> <label class="form-check-label" for="exampleCheck1">Check me out</label>
		</div>
		
		<button type="submit" class="btn btn-primary">로그인</button>
	</form>

</div>

<%@ include file="layout/footer.jsp"%>


