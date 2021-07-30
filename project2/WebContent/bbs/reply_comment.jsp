<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<form action="/reply_comment" method="post" onsubmit="return repco()">

<c:set var="r" value="${repc}"/>
<input type="hidden" name="o_fid" id="o_fid" value="${r.fid }">
<input type="hidden" name="o_thread" id="o_thread" value="${r.thread }">
<input type="hidden" name="tb_table" id="tb_table" value="${r.tb_table }">
<input type="hidden" name="tb_uid" id="tb_uid" value="${r.tb_uid }">
<input type="hidden" name="tb_id" id="tb_id" value="${sessionScope.id }">
<input type="hidden" name="tb_nickname" id="tb_nickname" value="${sessionScope.nickname }">

원댓글 내용
<br>
<input type="text" name="ori_com" id="ori_com" value="${r.tb_comment}">
<br>
대댓내용
<br>
<input type="text" name="rep_com" id="rep_com" value="">
<input type="submit" value="대댓글 작성">
</form>

<script>
function repco(){
	if(!comments){
		alert("내용을 작성해주세요");
		return false;
	}
}

</script>


</body>
</html>