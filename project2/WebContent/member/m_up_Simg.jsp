<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<section style="height:79%; float:left; margin:0px auto;">
<input type="hidden" name="tab" value="${requestScope.tab}">
<input type="hidden" name="id" value="${M.id}">
<div style="clear: both;">
	<div>회원수정</div>
	<div>요거슨 사장님 무슨 이미지 1번
		<img src="/memberSajang/upload/${M.b_regist_file1}" width=100>
	</div>
	<div>요거슨 사장님 무슨 이미지 2번
		<img src="/memberSajang/upload/${M.b_regist_file2}" width=100>
	</div>
	<div>요거슨 사장님 무슨 이미지 3번
		<img src="/memberSajang/upload/${M.b_regist_file3}" width=100>
	</div>
	<div>요거슨 사장님 무슨 이미지 4번
		<img src="/memberSajang/upload/${M.b_regist_file4}" width=100>
	</div>
	<div>요거슨 사장님 무슨 이미지 5번
		<img src="/memberSajang/upload/${M.b_regist_file5}" width=100>
	</div>
</div>
</section>

<%@ include file="/include/footer.jsp" %>