<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Request Test3</title>
<style>
h1 {
	text-align: center;
}
table {
	margin: auto;
	width: 100%;
	border: 1px solid red;
}
</style>
</head>
<body>
<h1>쿠키, URL/URI, 요청방식에 관련된 정보 예제</h1>
<table border="1">
	<tr>
		<td>쿠키정보</td>
<%
Cookie[] cookie=request.getCookies();
if(cookie==null){
%>
		<td>쿠기가 존재하지 않습니다</td>
<%
} else {
	for(int i=0; i<cookie.length;i++){
		%>
		<td><%=cookie[i].getName()%>(<%=cookie[i].getValue()%>)&nbsp;&nbsp;</td>
		<%
	}
}
%>
	</tr>
	<tr>
		<td>서버 도메인명</td>
		<td><%=request.getServerName() %>
	</td>
	<tr>
		<td>서버 포트번호</td>
		<td><%=request.getServerPort() %>
	</td>
	<tr>
		<td>요청 URL</td>
		<td><%=request.getRequestURL() %>
			<!-- 무슨 게시글을 보려는데 로그인하라는 창 떴고 그 후 로그인 했는데 해당 게시글로 이동되는 기능을 쓸 때 사용 가능 요청쿼리 밑줄 주석에 방법있 -->
	</td>
	<tr>
		<td>요청 URI</td>
		<td><%=request.getRequestURI() %>
	</td>
	<tr>
		<td>요청 쿼리</td>
		<td><%=request.getQueryString() %>
			<!-- 요청url+"?"+요청uri+"/"+요청쿼리를 변수에 담으면 로그인 후 그 페이지로 이동시킬때 사용 가능  -->
	</td>
	<tr>
		<td>클라이언트 호스트명</td>
		<td><%=request.getRemoteHost() %>
	</td>
	<tr>
		<td>클라이언트 IP 주소</td>
		<td><%=request.getRemoteAddr() %>
		<!-- 얘도 쓸곳이 있다. 글씅니 ip주소받기! -->
	</td>
	<tr>
		<td>프로토콜</td>
		<td><%=request.getProtocol() %>
	</td>
	<tr>
		<td>요청방식</td>
		<td><%=request.getMethod() %>
	</td>
	<tr>
		<td>컨텍스트 경로</td>
		<td><%=request.getContextPath() %>
	</td>
</table>
<%
String url="";
if(request.getQueryString()!=null){
	url =request.getRequestURL()+"?"+request.getQueryString();
}else{
	url =request.getRequestURL()+"";
}
//디스패쳐- 주소는 바뀌지않고 컨트롤러가 갖고있음, url로 쓰면 경로를 못찾음. uri로ㄱㄱ
//String url="";
//if(request.getQueryString()!=null){
//	url =request.getRequestURI()+"?"+request.getQueryString();
//}else{
//	url =request.getRequestURI()+"";
//}
%>


url = <%=url %>
<!-- testurl 서블릿 이동후 현재 페이지 이동 -->
<form action="testurl" method="post">
<input type="text" name="url" value="<%=url%>" style="width:500px;">
<input type="submit" value="aa">

</form>
</body>
</html>