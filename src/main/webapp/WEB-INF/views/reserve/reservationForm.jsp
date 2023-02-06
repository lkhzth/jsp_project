<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
	<div class="text-center jumbotron bg-light">
		<h1>글쓰기</h1>
	</div>
	
	<form action="${contextPath}/reserve/write" method="post" >
		<div class="form-group">
			이름 : <input type="text" class="form-control" name="writer">
		</div>
		<div class="form-group">
			휴대폰 : <input type="tel" class="form-control" name="phone">
		</div>
		<div class="form-group">
			이메일 : <input type="text" class="form-control" name="email">
		</div>
		<div class="form-group">
			제목 : <input type="text" class="form-control" name="title">
		</div>
		<div class="form-group">
			내용 : <textarea rows="5" class="form-control" name="content"></textarea>
		</div>
		
				
		<div class="text-center">
			<button class="btn btn-primary">예약하기</button>
		</div>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>
