<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<section style="height:79%; float:left; margin:0px auto;">
<form action="member_update2" method="post">
<input type="hidden" name="tab" value="${requestScope.tab}">
<input type="hidden" name="id" value="${M.id}">
<div style="clear: both;">
	<div>회원수정</div>
	<div>
		<div>이름 : <input name="names" id="names" value="${M.names}"></div>			
		<div>
			성별 : 
			<label>
			<input type="radio" name="gender" id="gender" value="M" <c:if test="${M.gender.equals('M')}">checked</c:if>>남성
			</label>
			<label>
			<input type="radio" name="gender" id="gender" value="F" <c:if test="${M.gender.equals('F')}">checked</c:if>>여자
			</label>
			<label>
			<input type="radio" name="gender" id="gender" value="S" <c:if test="${M.gender.equals('S')}">checked</c:if>>선택안함
			</label>
		</div>
		<div>생년월일<input name="ssn" id="ssn" value="${M.ssn}"></div>
		<div>전화번호<input name="tel" id="tel" value="${M.tel}"></div>
	</div>
	<div><input type="submit" value="변경"></div>
</div>
</form>
</section>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<%@ include file="/include/footer.jsp" %>