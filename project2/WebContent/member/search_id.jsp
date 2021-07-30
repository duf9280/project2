<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>


<section>
	<form action="search_id" method="post">
		<div>
			<div align=center>아이디찾기</div>
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