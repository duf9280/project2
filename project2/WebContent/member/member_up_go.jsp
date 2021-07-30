<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<section style="height:79%; float:left; margin:0px auto;">
	<div>
		프로필
		<img src="/member/upload/${M.file1_s}">
		닉네임 : ${M.nickname}
		<a href="/member/member_up?session_id=${sessionScope.id}&tab=a">[변경]</a>
	</div>
	<div>
		개인정보
		이름 : ${M.names}
		성별	: <c:if test="${M.gender.equals('M')}">남자</c:if>
			<c:if test="${M.gender.equals('F')}">여자</c:if>
			<c:if test="${M.gender.equals('S')}">선택안함</c:if>
		생년월일 : ${M.ssn}
		전화번호 : ${M.tel}
		<a href="/member/member_up?session_id=${sessionScope.id}&tab=b">[변경]</a>
	</div>
	<div>
		메일,주소
		메일 : ${M.mail1}@${M.mail2}
		?번호 : ${M.zipcode}
		주소 : ${M.zip1}${M.zip2}
		참고사항 : ${M.zip3}
		<a href="/member/member_up?session_id=${sessionScope.id}&tab=c">[변경]</a>
	</div>
	<div>
		비밀번호
		<a href="/member/member_up?session_id=${sessionScope.id}&tab=d">[변경]</a>
	</div>
	<div>
		<a href="/buy/order_list?id=${sessionScope.id}">구매내역</a>
	</div>	
</section>
<%@ include file="/include/footer.jsp" %>