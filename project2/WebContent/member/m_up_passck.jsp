<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<section style="height:79%; float:left; margin:0px auto;">
<form action="pass_ok" method="post" onsubmit="return pass_ok()">
	<input type="hidden" name="tab" value="${requestScope.tab}">
	<input type="hidden" name="id" value="${M.id}">
	<input type="hidden" name="session_id" value="${sessionScope.id}">
	<div style="clear: both;">
		<div>회원수정</div>
		<div>
			<div>기존 비밀번호<input type="password" name="pass" id="pass" required></div>	
		</div>
		<div><input type="submit" value="변경"></div>
	</div>
</form>
</section>
<script>

</script>
<%@ include file="/include/footer.jsp" %>