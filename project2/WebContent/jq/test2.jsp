<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String teacher = request.getParameter("teacher");
%>
<select>
	<option value="">선택3</option>
	<%if(teacher.equals("s")){%>
	<option value="">국3</option>
	<option value="">국3</option>
	<%}else{%>
	<option value="">d13</option>
	<option value="">d33</option>
	<%}%>
</select>