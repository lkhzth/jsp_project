<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
	
	<div class="text-center jumbotron bg-light">
		<h1>회원가입</h1>
	</div>
	
	<form action="${contextPath}/member/join" method="post" class="text-center">
		<div class="form-group">
			아이디 : <input type="text" name="id">
		</div>
		<div class="form-group">
			비밀번호 : <input type="password" name="pwd">
		</div>
		<div class="form-group">
			이름 : <input type="text" name="name">
		</div>
		<div class="form-group">
			이메일 : <input type="text" name="email">
		</div>
		<div class="form-group">
			휴대폰 : <input type="tel" name="phone">
		</div>
		<input type="checkbox" value="인터넷">인터넷
		<input type="checkbox" value="지인소개">지인소개
		<input type="checkbox" value="블로그 문의">블로그 문의<br>
		<div class="text-center">
			<button class="btn btn-primary">가입하기</button>
			<button class="btn btn-danger">가입취소</button>
		</div>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>
