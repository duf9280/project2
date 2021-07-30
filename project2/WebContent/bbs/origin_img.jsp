<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%
String code = request.getParameter("code");
String uid = request.getParameter("uid");
String img = request.getParameter("img");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form>


		<img src="/bbs/upload/${param.pic}" onclick="close_page()" style="cursor:pointer;">

</form>

<script>
function close_page(){
//	window.close();	
	self.close();
}

</script>
</body>
</html>