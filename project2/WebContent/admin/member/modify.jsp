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
<form>
<div>
	<ul class="member_view">
		<li>대표사진:</li>
		<li><img src="/member/upload/${m.file1_o }" style="width:80px;hegith:60px;"><input type="file" name="file1_o" id="file1_o"></li>
	</ul>
	<ul class="member_view">
		<li>아이디:</li>
		<li><input name="" value="${m.id}"></li>
	</ul >
	<ul class="member_view">
		<li>비밀번호</li>
		<li><input name="" value="${m.pass}"></li>
	</ul>
	<ul class="member_view">
		<li>이름:</li>
		<li><input name="" value="${m.names}"></li>
	</ul>
	<ul class="member_view">
		<li>별명:</li>
		<li><input name="" value="${m.nickname}"></li>
	</ul>
	<ul class="member_view">
		<li>메일주소:</li>
		<li><div><input type="text" id="sample6_postcode" placeholder="우편번호" name="zipcode" value="${M.zipcode}"><input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></div>
		<div>	
			<input type="text" id="sample6_address" placeholder="주소" name="zip1" value="${M.zip1}">
			<input type="text" id="sample6_detailAddress" placeholder="상세주소" name="zip2" value="${M.zip2}">
		</div>
		<div><input type="text" id="sample6_extraAddress" placeholder="참고항목" name="zip3" value="${M.zip3}"></div>	
		</li>
	</ul>
	<ul class="member_view">
		<li>성별:</li>
		<li><input name="" value="${m.gender}"></li>
	</ul>
	<ul class="member_view">
		<li>전화번호:</li>
		<li><input name="" value="${m.tel}"></li>
	</ul>
	<ul class="member_view">
		<li>주소:</li>
		<li>
			<input name="" value="${m.zip1}">
			<input name="" value="${m.zip3}">
			<input name="" value="${m.zip2}">
			<input name="" value="${m.zipcode}">
		</li>
	</ul>
	<ul class="member_view">
		<li>레벨:</li>
		<li><input name="" value="${m.level} "></li>
	</ul>
	<ul class="member_view">
		<li>가입날짜:</li>
		<li>${m.signdate}</li>
	</ul>

			<ul class="member_view">
				<li>파일:</li>
				<li style="width:200px;"><img src="/memberSajang/upload/${m.b_regist_file1}" style="width:80px;hegith:60px;"><input type="file" name="b_regist_file1" id="b_regist_file1"></li>
				<li style="width:200px;"><img src="/memberSajang/upload/${m.b_regist_file2}" style="width:80px;hegith:60px;"><input type="file" name="b_regist_file2" id="b_regist_file2"></li>
				<li style="width:200px;"><img src="/memberSajang/upload/${m.b_regist_file3}" style="width:80px;hegith:60px;"><input type="file" name="b_regist_file3" id="b_regist_file3"></li>
				<li style="width:200px;"><img src="/memberSajang/upload/${m.b_regist_file4}" style="width:80px;hegith:60px;"><input type="file" name="b_regist_file4" id="b_regist_file4"></li>
				<li style="width:200px;"><img src="/memberSajang/upload/${m.b_regist_file5}" style="width:80px;hegith:60px;"><input type="file" name="b_regist_file5" id="b_regist_file5"></li>
			</ul>

</div>
<div>
	<input type="submit" value="수정하기">
</div>
</form>
</section>

<%@ include file="/include/footer.jsp" %>