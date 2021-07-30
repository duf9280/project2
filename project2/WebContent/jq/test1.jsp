<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String subject = request.getParameter("subject");
%>
<select  id="teacher" name="teacher" onchange="change02()">
	<option value="">선택2</option>
	<%if(subject.equals("k")){%>
	<option value="a">국1</option>
	<option value="s">국2</option>
	<%}else{%>
	<option value="">d1</option>
	<option value="">d2</option>
	<%}%>
</select>