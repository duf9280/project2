<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside class="aside2">
<div style="display:flex;">
<form name="login" action="/login" method="post" onsubmit="return login()">
<c:choose>
	<c:when test="${sessionScope.id == null}">
		<div class="id_input">
			<input id="id" name="id" style="background:#cad2d9">
		</div>
		
		<div class="pass_input">
			<input id="pass" name="pass" style="background:#cad2d9">
		</div>
		
			<button class="login_btn">로그인</button>
		<div style="font-size:14px;">
			<a href="/member/join_.jsp">회원가입</a>
			<a href="/member/search_id.jsp">아이디</a>/
			<a href="/member/search_pass.jsp">비밀번호찾기</a>
		</div>
	</c:when>
	<c:otherwise>
		<div style="float:right;">
			${sessionScope.id } <br>
				<a href="/member/member_up?session_id=${sessionScope.id}">[회원수정1]</a>
			<c:choose>
				<c:when test="${sessionScope.level == '2' }">
				
				
					<a href="/manage/?id=${sessionScope.id }">[상점관리]</a>
					
					
				</c:when>
			</c:choose>
			<br>
			<a href="/logout.do?id=${sessionScope.id}&cart_name=${sessionScope.cart_name}">[로그아웃]</a>
		</div>	
	</c:otherwise>
</c:choose>
</form>
</div>
</aside>

<footer>
	
		<div id="footdiv">
			<span class="footerletter">
			이곳은 Footer입니다.
			</span>
		</div>
</footer>
</div>
<script>
	function login(){
		if(!login.id.value){
			alert("아이디를 입력해주세요.");
			login.id.focus();
			return false;
		}
		if(!login.pass.value){
			alert("비밀번호를 입력해주세요.");
			login.pass.focus();
			return false;
		}
	}
</script>
</body>
</html>