<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<form action="/mod_com" method="post" onsubmit="return modco()">
<c:set var="c" value="${modc}"/>
<input type="hidden" id="uid" name="uid" value="${c.uid}">
<input type="" id="s_id" name="s_id" value="${sessionScope.id}">
<div>님의 댓글 수정</div>
<input type="text" name="comments" id="comments" value="${c.tb_comment }">
<button>수정하기</button>

</form>

<script>
function modco(){
	if(!comments){
		alert("댓글을 작성해주세요");
		return false;
	}
}

</script>
</body>

</html>