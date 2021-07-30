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

<c:if test="${sessionScope.level ne '5' && sessionScope.level ne '2' }">
	<script>
		alert("관리자만 이용 가능합니다.");
		location.href="/";
	</script>
</c:if>
<!-- 
<%=session_id %>
<%=session_nickname %>

<%=session_level %>

<%=session_cart_name %>
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
					<c:when test="${param.code eq 'notice'}">
						<li><a href="#" style="background:gray;">[게시판 관리]</a>	
					</c:when>
					<c:otherwise>
						<li><a href="#">[게시판 관리]</a>
					</c:otherwise>
				</c:choose>
						<ul class="sub">
							<li><a href="/admin/bbs/list?code=notice">공지</a></li>
							<li><a href="/admin/bbs/img_list?code=event">이벤트</a></li>
						</ul>
					</li>
					<li><a href="#">[상점 관리]</a>
						<ul class="sub">
							<li><a href="/admin/bbs/market_list?code=lecture&category=V">[보컬]</a></li>
							<li><a href="/admin/bbs/market_list?code=lecture&category=W&pagenum=${pagenum }">[작사작곡]</a></li>
							<li><a href="/admin/bbs/market_list?code=lecture&category=I&pagenum=${pagenum }">[악기]</a></li>
							<li><a href="/admin/bbs/market_list?code=product&category=A">[스토어1]</a></li>
							<li><a href="/admin/bbs/market_list?code=product&category=B">[스토어2]</a></li>
							<li><a href="/admin/bbs/market_list?code=product&category=C">[스토어3]</a></li>
						</ul>
					</li>
					<li>
						<a href="/admin/member/list">회원관리</a>
					</li>
					<li>
						<a href="/admin/order_list/order_list">[주문내역 관리]</a>
					</li>	
					<li>
						<a href="">쪽지관리</a>
					</li>									
 				</ul>	
			</div>
		</div>
		<div style="width:20%; text-align:right; padding-right:20px;">
			렙 : ${sessionScope.level}
			<a href="/admin/">관리자페이지</a>
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