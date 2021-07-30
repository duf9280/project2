<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/footer.jsp" %>

<%
session.invalidate();
%>

<script>
	alert("로그아웃 되었다.");
	location.href="/";
</script>
