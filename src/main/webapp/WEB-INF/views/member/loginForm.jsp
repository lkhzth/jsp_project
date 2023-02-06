<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<div class="text-center jumbotron bg-light">
		<h1>로그인</h1>
	</div>
	
	<form action="${contextPath}/member/login" method="post" class="text-center">
		<div class="form-group">
			아이디 : <input type="text" name="id">
		</div>
		<div class="form-group">
			비밀번호 : <input type="password" name="pwd">
		</div>
		<button class="btn btn-primary">로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>
