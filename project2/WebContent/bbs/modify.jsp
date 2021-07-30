<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<%
request.setCharacterEncoding("utf-8");
code = request.getParameter("code");



%>

<c:choose>
	<c:when test="${sessionScope.id ne view.id }">
		<script>
			alert("작성자/관리자만 수정 가능합니다.");
			history.back();
		</script>
	</c:when>
</c:choose>

<section style="height:84%; width:79%; float:left;">
	<div style="clear:both; display:flex; width:100%; ">
		<div style="margin:0 auto;">
			공지 글 수정
		</div>
	</div>
	<div style="width:100%;">
	<div style="margin:0 auto; width:900px; display:flex;">
	</div>
		<form action="/update.do" method="post" enctype="multipart/form-data">
		<c:set var="a" value="${view}"/>
		<input type="hidden" name="code" id="code" value="<%=code%>">
		<input type="hidden" name="uid" id="uid" value="${view.uid}">
		<input type="hidden" name="id" id="id" value="${id}">
		<input type="hidden" name="nickname" id="nickname" value="${nickname }">

		<div style="margin:0 auto; width:800px; border-bottom:1px solid gray; display:flex; flex-direction:column; align-items:center;">
			<div style="display:inherit; width:800px;">
				글 유형 : 
				<c:choose>
					<c:when test="${sessionScope.level eq '5' && view.thread.length() ==1}">
						<label><input type="radio" name="gongji" id="gongji" value="1" 
						<c:choose>
							<c:when test="${a.gongji eq '1' }">
								checked
							</c:when>
							<c:otherwise>
								
							</c:otherwise>
						</c:choose>
						>공지</label>					
					</c:when>
				
				
				</c:choose>

				<label><input type="radio" name="gongji" id="gongji" value="2"
				<c:choose>
					<c:when test="${a.gongji eq '2' }">
						checked
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
					
				</c:choose>
				>일반</label>
				<label><input type="radio" name="gongji" id="gongji" value="3"
				<c:choose>
					<c:when test="${a.gongji eq '3' }">
						checked
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
				>비밀</label>
			</div>
			<div>
				제목 : <br>
				<input type="text" name="subject" id="subject" value="${a.subject }"style="width:800px;">
			</div>
			<div>
				내용 : <br>
				<textarea name="comment" id="comment" style="width:800px; height:400px; resize:none;">${a.comment }</textarea>
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
		
				<div><button>[수정하기]</button></div>
			</div>
		</div>
		</form>
	</div>
<script>

</script>
<!-- 

 -->
</section>


<%@ include file="/include/footer.jsp" %>