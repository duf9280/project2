<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<style>
ul,li {
	padding:0px;
	margin:0px;
}
.product_write {
	display:flex;
}
</style>

<section>
	<div>글쓰기</div>
	<form action="/product/write.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="category" value="${category}">
	<div>
		<ul>
			<li>글 종류</li>
			<li>
				<input type="radio" name="category" id="" value="A" checked>음향기기
				<input type="radio" name="category" id="" value="B">악기
				<input type="radio" name="category" id="" value="C">기타
			</li>
		</ul>
		<ul style="display:flex;margin-bottom:10px;">
			<li>제목</li>
			<li><input name="subject" id=""></li>
		</ul>
		<ul class="product_write">
			<li>내용</li>
			<li><textarea name="comment" id="" style="width:950px;height:400px;resize:none;"></textarea></li>
		</ul>
		<div>상품1</div>
		<ul class="product_write">
			<li>상품이름1</li>
			<li><input name="p_name1" id="p_name1"></li>
			<li>가격1</li>
			<li><input name="price1" id="price1" placeholder="숫자입력"></li>
			<li>판매수량1</li>
			<li><input name="number1" id="number1" placeholder="숫자입력"></li>
			<li>사진1</li>
			<li><input type="file" name="file1_o" id="file1_o" required></li>
		</ul>
		<div>상품2</div>
		<ul class="product_write">
			<li>상품이름2</li>
			<li><input name="p_name2" id="p_name2"></li>
			<li>가격2</li>
			<li><input name="price2" id="price2" placeholder="숫자입력"></li>
			<li>판매수량2</li>
			<li><input name="number2" id="number2" placeholder="숫자입력"></li>
			<li>사진2</li>
			<li><input type="file" name="file2_o" id="file2_o"></li>
		</ul>
		<div>상품3</div>
		<ul class="product_write">					
			<li>상품이름3</li>
			<li><input name="p_name3" id="p_name3"></li>
			<li>가격3</li>
			<li><input name="price3" id="price3" placeholder="숫자입력"></li>
			<li>판매수량3</li>
			<li><input name="number3" id="number3" placeholder="숫자입력"></li>
			<li>사진3</li>
			<li><input type="file" name="file3_o" id="file3_o"></li>
		</ul>
		<ul class="product_write">
			<li>주소</li>
			<li>
				<div>
					<input type="text" id="sample6_postcode" placeholder="우편번호" name="zipcode" value="${M.zipcode}">
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
				</div>
				<div>	
					<input type="text" id="sample6_address" placeholder="주소" name="zip1" value="${M.zip1}">
					<input type="text" id="sample6_extraAddress" placeholder="참고항목" name="zip3" value="${M.zip3}">
				</div>
				<div>
					<input type="text" id="sample6_detailAddress" placeholder="상세주소" name="zip2" value="${M.zip2}">
				</div>	
			</li>
		</ul>
		<ul class="product_write">
			<li>이름</li>
			<li><input name="account_name" id=""></li>
		</ul>
		<ul class="product_write">
			<li>계좌번호</li>
			<li><input name="account_num" id=""></li>
		</ul>
		<div><input type="submit" name="" id="" value="등록"></div>
	</div>
	</form>
</section>


<%@ include file="/include/footer.jsp" %>