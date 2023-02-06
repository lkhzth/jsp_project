<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">
	<div class="text-center jumbotron bg-light">
		<h3>비밀글입니다.<br>등록하신 연락처를 입력하세요.<br>(관리자는 바로 조회가능)</h3>
	</div>
	<form action="${contextPath}/reserve/checking" method="post">
		<input type="hidden" value="${board.mno}" name="mno">	
		휴대폰번호 : <input type="text" name="phoneCk">
		<button class="btn btn-primary">확인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>
