	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">

	<div class="text-left m-5">
		<h3>웨딩후기
			<span style="font-size: 15px">(신랑&신부님 및 방문하신 하객분들)<br>
				피드백 주신 분들께 소정의 상품을 드립니다.
			</span>
		</h3>
	</div>
	
	<form id="listForm">
		<table class="table">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>파일</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${list}" var="b">
				<tr>
					<td>${b.bno }</td>
					<td>
						<a href="${contextPath}/board/detail?bno=${b.bno}" class="title">${b.title }
						<b>${b.replyCount != 0 ? '['+=b.replyCount +=']':''}</b></a>
					</td>
					<td>${b.content }</td>
					<td>${b.writer }</td>
					<td>${b.imageFileName }</td>
					<td>${b.writeDate }</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	
	<a href="${contextPath}/board/writeForm" class="btn btn-primary">글쓰기</a>

</div>

<%@ include file="../layout/footer.jsp" %>
