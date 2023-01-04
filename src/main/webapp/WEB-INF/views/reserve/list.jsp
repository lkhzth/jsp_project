<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

<div class="container">

	<div class="text-center jumbotron bg-light">
		<h1>예약상담게시판</h1>
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
								<b>${r.reserveReplyCount != 0 ? '['+=r.reserveReplyCount +=']':''}</b></a>
							</c:when>
							<c:otherwise>
								<%-- <a href="${contextPath}/reserve/detail?mno=${r.mno}" class="title">${r.title } --%>	
								<a href="${contextPath}/reserve/phChecked?mno=${r.mno}" class="title">${r.title }
								<input type="hidden" value="${r.mno}">
									
							</c:otherwise>
						</c:choose>
						
						
					</td>
					<td>${r.writer }</td>
					<td>${r.writeDate }</td>
				</tr>
			</c:forEach> 
			
			<%-- <c:forEach var="r" items="${list}">
			    <tr style="text-align:center;">
			        <td><c:out value="${r.mno}"/></td>                    
			        <td><c:out value="${r.title}"/></td>
			        <td><c:out value="${r.writer}"/></td>
			        <td><c:out value="${r.writeDate}"/></td>
			        <td>    
				        <c:if test="${r.secret eq 'N'}" >
				            <img src="${contextPath}/resources/image/logo.png" alt="비밀글" />
				            <c:choose>
				                <c:when test="${board.writer eq auth.id || auth.grade eq 'ROLE_ADMIN'}">
				                    <c:out value="${r.title}"/>
				                </c:when>
				                <c:otherwise>비밀글은 작성자와 관리자만 볼 수 있습니다.</c:otherwise>
				            </c:choose>
				        </c:if>
				        <c:if test="${r.secret eq 'Y'}" >
				            <c:out value="${r.title}"/>
						        <td><c:out value="${r.mno}"/></td>            
						        <td><c:out value="${r.content}"/></td>            
						        <td><c:out value="${r.writer}"/></td>            
						        <td><c:out value="${r.writeDate}"/></td>            
				        </c:if>
			        </td>
			        <td><c:out value="${result.cs_title}"/></td>
			    </tr>
			</c:forEach> --%>
			
			
		</table>
		
		
		<%-- <c:if test="${board.secret == true}">
		    <c:choose>
		        <c:when test="${board.writer eq member.vo.userid || member.authorities eq '[ROLE_ADMIN, ROLE_MEMBER]'}"> <!-- 작성자이거나 관리자일 때 -->
		            <td><a href="get${pageMaker.cri.listLink}&bno=${board.bno}" class="text-secondary text-center"><i class="icofont-lock"></i><c:out value="${board.title}"/><span class="text-muted small"> [${board.replyCnt}]</span></a></td>
		        </c:when>
		        <c:otherwise>
		            <td class="text-secondary"><i class="icofont-lock"></i><c:out value="${board.title}"/><span class="text-muted small"> [${board.replyCnt}]</span></td>
		        </c:otherwise>
		    </c:choose>                                            
		</c:if> --%>
		
		
		
	</form>
	
	<a href="${contextPath}/reserve/reservationForm" class="btn btn-primary">글쓰기</a>

</div>

<%@ include file="../layout/footer.jsp" %>

<script>
	$(function () {
		
	});
</script>
