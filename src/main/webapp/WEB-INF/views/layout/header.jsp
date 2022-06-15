<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- spring-security-taglibs -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!-- isAuthenticated()로 인증을 되었는지 확인하고 property="principal"로 접근 허용하고 var에 principal를 저장한다.-->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Shop</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="/assets/favicon.ico" />
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/css/styles.css" rel="stylesheet" />
<!-- Jquery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>


<body>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="/">로고</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">

				<!-- principal 에 null이거나 user 정보가 없으면 when을 있으면 otherwise를 실행 -->
				<c:choose>
					<c:when test="${empty principal}">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
							<li class="nav-item"><a class="nav-link" href="/">HOME</a></li>
							<li class="nav-item"><a class="nav-link" href="/auth/loginForm">LOGIN</a></li>
							<li class="nav-item"><a class="nav-link" href="/auth/joinForm">SIGN UP</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
							<li class="nav-item"><a class="nav-link" href="/">HOME</a></li>
							<li class="nav-item"><a class="nav-link" href="/userForm">MY PAGE</a></li>
							<li class="nav-item"><a class="nav-link" href="/boardForm">Q&A</a></li>
							<li class="nav-item"><a class="nav-link" href="/logout">LOG OUT</a></li>
						</ul>
					</c:otherwise>
				</c:choose>

				<form class="d-flex">
					<button class="btn btn-outline-dark" type="submit">
						<i class="bi-cart-fill me-1"></i> Cart <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
					</button>
				</form>
			</div>
		</div>
	</nav>


	<!-- Header-->
	<header class="bg-dark py-5">
		<div class="container px-4 px-lg-5 my-5">
			<div class="text-center text-white">
				<h1 class="display-4 fw-bolder">Shop in style</h1>
				<p class="lead fw-normal text-white-50 mb-0">With this shop hompeage template</p>
			</div>
		</div>
	</header>
	<br />