<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%
String session_id=(String)session.getAttribute("id");
String session_nickname=(String)session.getAttribute("nickname");
String session_level= (String)session.getAttribute("level");
String session_cart_name= (String)session.getAttribute("cart_name");


request.setCharacterEncoding("utf-8");
String code = request.getParameter("code");


%>

<!-- 
<%=session_id %>
<%=session_nickname %>
<%=session_cart_name %>
<%=session_level %>

 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC패턴2</title>
<link rel="stylesheet" href="/css/project2.css" type="text/css">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="/js/js.js"></script>
<style>

	
	
</style>

</head>

<body>
<div class="wrap">
<header>
		<div style="left:0; width:20%;"><a href="/">[logo]</a></div>
		<div style="width:60%;">	
<!-- <퍼센트로 만드는 if,else구문과 같음 when이 if역할 otherwise가 else라고 보면됨 -->			
			<div id="menus">
				<ul class="main">
				<c:choose>
					<c:when test="${param.code eq 'notice' }">
					
					<li><a href="#" style="background:#ffffff;">[공지 / 이벤트]</a>	
					
					</c:when>
					<c:otherwise>
					
					<li><a href="#">[공지 / 이벤트]</a>
					
					</c:otherwise>
				</c:choose>
						<ul class="sub">
							<li><a href="/bbs/list?code=notice">공지</a></li>
							<li><a href="/bbs/img_list?code=event">이벤트</a></li>
						</ul>
					</li>
					<li><a href="#">[강의]</a>
						<ul class="sub">
							<li><a href="/market/list?code=lecture&category=V&pagenum=${pagenum }">[보컬]</a></li>
							<li><a href="/market/list?code=lecture&category=W&pagenum=${pagenum }">[작사작곡]</a></li>
							<li><a href="/market/list?code=lecture&category=I&pagenum=${pagenum }">[악기]</a></li>
						</ul>
					</li>
					<li><a href="#">[메뉴1]</a>
						<ul class="sub">
							<li><a href="#">[보컬]</a></li>
							<li><a href="#">[작사작곡]</a></li>
							<li><a href="#">[악기]</a></li>
						</ul>
					</li>					
					<li><a href="#">[메뉴2]</a>
						<ul class="sub">
							<li><a href="#">[보컬]</a></li>
							<li><a href="#">[작사작곡]</a></li>
							<li><a href="#">[악기]</a></li>
						</ul>
					</li>
					<li><a href="#">[스토어]</a>
						<ul class="sub">
							<li><a href="/market/list?code=product&category=A">[스토어1]</a></li>
							<li><a href="/market/list?code=product&category=B">[스토어2]</a></li>
							<li><a href="/market/list?code=product&category=C">[스토어3]</a></li>
						</ul>
					</li>										
 				</ul>	
			</div>
		</div>
		<div style="width:20%; text-align:right; padding-right:20px;">
		렙 : ${sessionScope.level}
		<c:choose>
			<c:when test="${sessionScope.level eq '5' }">
				<a href="/admin/">관리자페이지</a>
			</c:when>	
			<c:otherwise>
			</c:otherwise>
		</c:choose>
		<a href="/member/member_up?session_id=${sessionScope.id}">[마이페이지]</a><br>
		<a href="/buy/cart_page?id=${sessionScope.id}&cn=${sessionScope.cart_name}">[장바구니]</a>
		<c:choose>
			<c:when test="${!empty sessionScope.id }">	
				<a href="/logout.do?id=${sessionScope.id}&cart_name=${sessionScope.cart_name}">[로그아웃]</a>
			</c:when>
		</c:choose>	
		</div>
</header>

<aside class="aside1">
aside
</aside>