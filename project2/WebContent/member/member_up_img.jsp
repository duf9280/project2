<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<section style="height:79%; float:left; margin:0px auto;">
<form action="member_update" method="post" enctype="multipart/form-data">
<input type="hidden" name="tab" value="${requestScope.tab}">
<input type="hidden" name="id" value="${M.id}">
<div style="clear: both;">
	<div>회원수정</div>
	<div>
		<div>별명<input name="nickname" id="nickname" value="${M.nickname}"></div>		
		<div>대표 사진<img src="/member/upload/${M.file1_s}"><input type="file" name="file1" id="file1"></div>
	</div>
	<div><input type="submit" value="변경"></div>
</div>
</form>
</section>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<%@ include file="/include/footer.jsp" %>