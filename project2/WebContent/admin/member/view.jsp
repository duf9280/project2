<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/include/header1.jsp" %>

<style>
.member_view {
	margin:0px;
	padding:0px;
	display:flex;
}
</style>
<section>
<div>회원정보</div>
<div>
	<ul class="member_view">
		<li>대표사진:</li>
		<li><img src="/member/upload/${m.file1_o }" style="width:80px;hegith:60px;"></li>
	</ul>
	<ul class="member_view">
		<li>아이디:</li>
		<li>${m.id}</li>
	</ul >
	<ul class="member_view">
		<li>비밀번호</li>
		<li>${m.pass}</li>
	</ul>
	<ul class="member_view">
		<li>이름:</li>
		<li>${m.names}</li>
	</ul>
	<ul class="member_view">
		<li>별명:</li>
		<li>${m.nickname }</li>
	</ul>
	<ul class="member_view">
		<li>메일주소:</li>
		<li>${m.mail1 }@${m.mail2 }</li>
	</ul>
	<ul class="member_view">
		<li>성별:</li>
		<li>${m.gender}</li>
	</ul>
	<ul class="member_view">
		<li>전화번호:</li>
		<li>${m.tel }</li>
	</ul>
	<ul class="member_view">
		<li>주소:</li>
		<li>${m.zip1}${m.zip3}${m.zip2}${m.zipcode}</li>
	</ul>
	<ul class="member_view">
		<li>레벨:</li>
		<li>${m.level}</li>
	</ul>
	<ul class="member_view">
		<li>가입날짜:</li>
		<li>${m.signdate}</li>
	</ul>
	<ul class="member_view">
		<li>파일:</li>
		<li><img src="/member/upload/${m.b_regist_file1}" style="width:80px;hegith:60px;"></li>
		<li><img src="/member/upload/${m.b_regist_file2}" style="width:80px;hegith:60px;"></li>
		<li><img src="/member/upload/${m.b_regist_file3}" style="width:80px;hegith:60px;"></li>
		<li><img src="/member/upload/${m.b_regist_file4}" style="width:80px;hegith:60px;"></li>
		<li><img src="/member/upload/${m.b_regist_file5}" style="width:80px;hegith:60px;"></li>
	</ul>

</div>
<div>
	<ul class="member_view">
		<li><a href="list?">목록</a></li>
		<li><a href="modify?id=${m.id}">수정</a></li>
		<li><a href="delete?id=${m.id}">삭제</a></li>
	</ul>
</div>

</section>

<%@ include file="/include/footer.jsp" %>