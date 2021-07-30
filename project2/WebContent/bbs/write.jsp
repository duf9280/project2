<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<%
request.setCharacterEncoding("utf-8");
code = request.getParameter("code");
%>
<script>

if(${param.code =='notice'}){
	if(${sessionScope.level !='5'}){
		if(${sessionScope.id == null}){
			alert("로그인후 이용가능합니다.");
			history.go(-1);
		}
		if(${sessionScope.id != null}){
			alert("관리자만 작성 가능합니다.");
			history.go(-1);			
		}
	}
}
</script>
<section style="height:84%; width:79%; float:left;">

	<div style="clear:both; display:flex; width:100%; ">
		<div style="margin:0 auto;">
			공지 글 쓰기
		</div>
	</div>
	<div style="width:100%;">
	<div style="margin:0 auto; width:900px; display:flex;">

	</div>
		<form name="write_bbs" action="/bbs/write.do?code=<%=code%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" value="${sessionScope.id }">
			<input type="hidden" name="nickname" id="nickname" value="${sessionScope.nickname }">
			<input type="hidden" name="file1" id="file1" value="${file1 }">
			<input type="hidden" name="file2" id="file2" value="${file2 }">		
			<%=code %>
		<div style="margin:0 auto; width:800px; border-bottom:1px solid gray; display:flex; flex-direction:column; align-items:center;">
			<div style="display:inherit; width:800px;">
				글 유형 : 
				<c:choose>
					<c:when test="${sessionScope.level eq '5' }">
				<label><input type="radio" name="gongji" id="gongji" value="1">공지</label>
					</c:when>
				</c:choose>
				<label><input type="radio" name="gongji" id="gongji" value="2" checked>일반</label>
				<label><input type="radio" name="gongji" id="gongji" value="3">비밀</label>
			</div>
			<div>
				제목 : <br>
				<input type="text" name="subject" id="subject" style="width:800px;">
			</div>
			<div>
				내용 : <br>
				<textarea name="comment" id="comment" style="width:800px; height:400px; resize:none;"></textarea>
			</div>			
			<div style="display:flex; width:800px; flex-direction:column;">
				<div>
					첨부 파일 1 : <input type="file" name="file1" id="file1">
				</div>
				<div style="clear:both;">
					첨부 파일 2 : <input type="file" name="file2" id="file2">
				</div>
			</div>
			<div style="margin:0 auto; width:800px; display:flex; justify-content:space-between;">
				<div><a href="list?code=<%=code%>">[목록]</a></div>
		
				<div><button>[글쓰기]</button></div>
			</div>
		</div>
		</form>
	</div>


<!-- 

 -->
</section>

<%@ include file="/include/footer.jsp" %>