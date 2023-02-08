<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">

	<div class="text-left m-5">
		<h3>실시간 예약상담<br>
			<span style="font-size: 15px">문의 남겨 주시면 바로 연락드리겠습니다.(비회원가능)</span>
		</h3>
	</div>
	
	<form id="listForm">
		<table class="table">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${list}" var="r">
				<tr>
					<td>${r.mno }</td>
					<td>
						<c:choose>
							<c:when test="${auth.grade eq 'ROLE_ADMIN'}">
								<a href="${contextPath}/reserve/detail?mno=${r.mno}" class="title">${r.title }
							</c:when>
							<c:otherwise>
								<a href="${contextPath}/reserve/phChecked?mno=${r.mno}" class="title">${r.title }
								<input type="hidden" value="${r.mno}">
							</c:otherwise>
						</c:choose>
								<b>${r.reserveReplyCount != 0 ? '['+=r.reserveReplyCount +=']':''}</b></a>
					</td>
					<td>${r.writer }</td>
					<td>${r.writeDate }</td>
				</tr>
			</c:forEach> 
		</table>
	</form>
	<a href="${contextPath}/reserve/reservationForm" class="btn btn-primary">글쓰기</a>
</div>

<%@ include file="../layout/footer.jsp" %>
