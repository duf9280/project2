<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<section style="height:79%; float:left; margin:0px auto;">
<form action="member_update2" method="post" onsubmit="return pass_ok()">
<input type="hidden" name="tab" value="${requestScope.tab}">
<input type="hidden" name="id" value="${M.id}">
<div style="clear: both;">
	<div>회원수정</div>
	<div>
		<div>새 비밀번호<input type="password" name="pass1" id="pass1"></div>
		<div>새 비밀번호 확인<input type="password" name="pass" id="pass" required></div>
	</div>
	<div><input type="submit" value="변경"></div>
</div>
</form>
</section>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function pass_ok() {
	if(pass2.value != pass.value){
		pass.focus();
		return false;
	}
}
</script>
<%@ include file="/include/footer.jsp" %>