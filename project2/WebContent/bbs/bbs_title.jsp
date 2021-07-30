<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String code_title="";
	if(code.equals("notice")){
		code_title="공지사항";
	}else if(code.equals("lecture")){
		code_title="강의";
	}else if(code.equals("product")){
		code_title="스토어";
	}
%>
