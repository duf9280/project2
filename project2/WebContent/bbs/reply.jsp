<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<%
int o_uid= Integer.parseInt(request.getParameter("o_uid"));
code= request.getParameter("code");
%>
<!-- 자신의 게시글에는 답글을 달 수 없도록 -->
<c:choose>
	<c:when test="${sessionScope.id eq rep.id }">
		<script>
			alert("자신의 게시글에는 답글을 쓸수 없습니다.");
			history.back();
		</script>
	</c:when>
</c:choose>


<section style="height:84%; width:79%; float:left;">

	<div style="clear:both; display:flex; width:100%; ">
		<div style="margin:0 auto;">
			공지
		</div>
	</div>
	<div style="width:100%;">
	<div style="margin:0 auto; width:900px; display:flex;">

	</div>
		<c:set var="r" value="${rep }"/>
		<form action="/reply.do?code=<%=code%>" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" value="${sessionScope.id }">
			<input type="hidden" name="nickname" id="nickname" value="${sessionScope.nickname }">
			<input type="hidden" name="fid" value="${rep.fid }">
			<input type="hidden" name="thread" value="${rep.thread }">
			<input type="hidden" name="o_id" id="o_id" value="${o_id}">
			<input type="hidden" name="gongji" id="gongji" value="${rep.gongji }">
		<div style="margin:0 auto; width:800px; border-bottom:1px solid gray; display:flex; flex-direction:column; align-items:center;">
			<div style="display:inherit; width:800px;">
				답변 글 유형 : 
				
				<label><input type="radio" name="gongji" id="gongji" value="2"
					<c:choose>
						<c:when test="${r.gongji eq '2' }">
							checked
						</c:when>
					</c:choose>	
				>일반</label>
				
				<c:choose>
					<c:when test="${sessionScope.level eq '5' }">
					
				<label><input type="radio" name="gongji" id="gongji" value="3"
					<c:choose>
						<c:when test="${r.gongji eq '3' }">
							checked
						</c:when>
				</c:choose>	
				
				>비밀</label>
				
					</c:when>	
				</c:choose>
			</div>
				<div>
				제목 : <br>
				<input type="text" name="subject" id="subject" style="width:800px;" value="${rep.subject}">
			</div>
			<div>
				내용 : <br>
				<textarea name="comment" id="comment" style="width:800px; height:400px; resize:none;">${rep.comment}</textarea>
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
		
				<div><button>[답글 쓰기]</button></div>
			</div>
		</div>
		</form>
	</div>


<!-- 

 -->
</section>


<%@ include file="/include/footer.jsp" %>