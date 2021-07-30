<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>


<section style="height:79%; float:left; margin:0px auto;">
	<div>
		<div align=center>비밀번호찾기</div>
		<c:choose>
			<c:when test="${mem.pass != null }">
				<div>비밀번호:${mem.pass }</div>
				<div><a href="/">홈으로 이동</a></div>
			</c:when>
			<c:otherwise>
				<div>비밀번호 없음</div>
				<div><a href="/member/search_pass.jsp">비밀번호 다시찾기</a></div>
			</c:otherwise>
		</c:choose>
	</div>
</section>

<%@ include file="/include/footer.jsp" %>