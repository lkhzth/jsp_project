<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js" integrity="sha512-aVKKRRi/Q/YV+4mjoKBsE4x3H+BkegoM/em46NNlCqNTmUYADjBbeNefNxYV7giUp0VxICtqdrbqU7iVaeZNXA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500&display=swap" rel="stylesheet">

<style>
*{font-family: 'Quicksand', sans-serif;}
	body{
		 /* background-image: url("${contextPath}/resources/image/02.png"); */ 
	}
	.text-center a img {
    	height: 100px;
    	
	}
	.navbar-dark .navbar-nav .nav-link {
		padding-right: 50px;
    	color: black;
    	font-size: 20px;
		font-weight: 700;
	}
	.navbar-dark .navbar-nav .nav-link:hover {
    	color: red;
    	font-size: 25px;
	}
	
	.navbar-dark .navbar-nav .nav-link.r {
		padding-right: 0;
    	
	}
	
</style>
</head>
<body>
 
 	<div class="text-center"><h1><a href="${contextPath}/main"><img src="${contextPath}/resources/image/logo.png"></a></h1></div>

<div>
	<nav class="navbar navbar-expand-sm navbar-dark justify-content-between">
  <!-- Brand/logo -->
  
  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">Wedding Hall</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">예약문의</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${contextPath}/board">방문후기</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">오시는길</a>
    </li>
  </ul>
  
	 <ul class="navbar-nav text-right">
    <c:if test="${empty auth }">
	    <li class="nav-item"> <!-- 세션값이 없을 때 -->
	      <a class="nav-link r" href="${contextPath }/member/joinForm">회원가입</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link r" href="${contextPath }/member/loginForm">로그인</a>
	    </li>
    </c:if>
    
  <c:if test="${not empty auth }"> <!-- 세션값이 있을 때 -->
    <li class="nav-item ">
    	<span class="nav-link"><b>${auth.id }</b>님 로그인 중</span>
    </li>
    <li class="nav-item">
      <a class="nav-link r" href="${contextPath }/member/myPage">나의정보보기</a>
    </li>
    <li class="nav-item">
      <a class="nav-link r" href="${contextPath }/member/logout">로그아웃</a>
    </li>
  </c:if>
  </ul>
</nav>

</div>



</body>

</html>