<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>


<section style="height:79%; float:left; margin:0px auto;">
	<form action="search_pass" method="post">
		<div>
			<div align=center>비밀번호찾기</div>
			<div>아이디:<input name="id" id=""></div>
			<div>이름: <input name="names" id=""></div>
			<div>메일:<input name="mail1" id="">@<input name="mail2" id="">
				<select onchange="mail2.value=this.value"  class="mailselect">
					<option value="">=직접입력=</option>
					<option value="gmail.com">gmail.com</option>
					<option value="naver.com">naver.com</option>
					<option value="nate.com">nate.com</option>
					<option value="daum.net">daum.net</option>
				</select>
			</div>
			<div>전화번호:<input name="tel" id=""></div>
			<div align=center><input type="submit" value="찾기"></div>
		</div>
	</form>
</section>

<%@ include file="/include/footer.jsp" %>