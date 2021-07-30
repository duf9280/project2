<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>


<section style="height:79%; float:left; margin:0px auto;">
	<div>
		<div align=center>아이디찾기</div>
		<c:choose>
			<c:when test="${mem.id != null }">
				<div>아이디:${mem.id }</div>
				<div>
					<a href="/member/search_pass.jsp">비밀번호 찾기</a>
					<a href="/">홈으로 이동</a>
				</div>
			</c:when>
			<c:otherwise>
				<div>아이디 없음</div>
				<div><a href="/member/search_id.jsp">아이디 다시찾기</a></div>
			</c:otherwise>
		</c:choose>
	</div>
</section>

<%@ include file="/include/footer.jsp" %>