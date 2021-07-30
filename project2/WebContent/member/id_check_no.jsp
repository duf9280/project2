<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body,html{
		margin:0 auto;
		vertical-align:middle;
		justify-content:center;		
		padding:0;
		height:100%;
		width:100%;		
		background:#c8e8e3;
	}
	.boundary{
		margin-top:0px;
		height:100%;
		width:100%;	
		clear:both;

		justify-content:center;
	}
	div{
		text-align:center;
	}
	.popup_form{
		clear:both;
	}
	
	.find_id_button{
		background:#fecccb;
		color:#265543;
		height:25px;
		border-radius:5px;
		margin-left:5px;
		margin-top:3px;
		cursor:pointer;
		border:none;
	}	
	.pp{
		width:100%;
		margin-bottom:10px;
		padding-top: 10px;
	}
	.p{
		margin: 0 auto;
		width:160px;
		padding-top: 10px;
	}
	
	.p p{
		padding-top:3px;
		margin:0 auto;
		padding:0;
	}
</style>
</head>
<body>
<%@page import="java.sql.*"%>
<%@page import="dao.MemberDAO"%>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String ids = request.getParameter("ids");

MemberDAO dao = new MemberDAO();

int num = dao.checkId_no(id);
%>
<script>
function ck_ok_no(){
	window.opener.join.id.value = popup.ids.value;
	window.opener.join.check_id.value = 1;
//	window.close();	
	self.close();
}
function idchk() {
	if(!id.value) {
		alert("아이디 입력");
		return false;
	}
}
</script>

<div class="boundary">
	<div>
	</div>
	<div class="popup_form">
	<form name="popup" method="get" onsubmit="return idchk()">
			아이디 : <input name="ids" id="ids" value="<%=id%>" readonly>
	
			<%if(num==0 && !id.equals("")){%>
			<div class="pp">
				사용하실수 있는 아이디 입니다.
				<div class="p">
					<p onclick="ck_ok_no()" class="find_id_button">이 아이디 사용하기</p>
				</div>
			</div>
			
			<%}else{%>
				<div>중복된 아이디 혹은 사용하실수 없는 아이디입니다.</div>		
			<%}%>
			<input name="id" id="id" value="">
		<button class="find_id_button">아이디 검색하기</button>
	</form>	
	</div>		
</div>
</body>
</html>