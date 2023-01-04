<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<div class="text-center jumbotron bg-light">
		<h1>이 글은 비밀글입니다. 등록하신 폰번호를 입력하세요.</h1>
	</div>
	${board.mno}
	${board.phone}
	<form action="${contextPath}/reserve/checking" method="post">
		
		<div class="form-group">
			휴대폰번호 : <input type="text" class="form-control" name="phone">
		</div>
		<button class="btn btn-primary">확인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>
