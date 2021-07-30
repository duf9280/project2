<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<style>
ul,li {
	padding:0px;
	margin:0px;
}
.product_write {
}
</style>

<section>
	<form action="productwrite.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="category" value="${param.category}">
	<input type="hidden" name="code" value="${param.code }">
	<div style="width:800px; margin:0 auto;">
		<div>
			<div>상품 종류
				<c:if test="${param.category eq 'A'}"><label><input type="radio" name="category" id="" value="A" checked>음향기기</label></c:if>
				<c:if test="${param.category eq 'B'}"><label><input type="radio" name="category" id="" value="B" checked>악기</label></c:if>
				<c:if test="${param.category eq 'C'}"><label><input type="radio" name="category" id="" value="C" checked>기타</label></c:if>
			</div>
		</div>
		<div style="display:flex;margin-bottom:10px;">
			<div>상품명
			<input name="subject" id=""></div>
		</div>
		<div class="product_write">
			<div>상품 설명 <br>
			<textarea name="comment" id="" style="width:800px;height:300px;resize:none;"></textarea></div>
			</div>
		<div style="display:flex; justify-content:space-between; margin-bottom:20px;">
			<div>상품1
				<div class="product_write">
					<div>상품이름1</div>
					<div><input name="p_name1" id="p_name1"></div>
					<div>가격1</div>
					<div><input name="price1" id="price1" placeholder="숫자입력"></div>
					<div>판매수량1</div>
					<div><input name="number1" id="number1" placeholder="숫자입력"></div>
					<div>사진1</div>
					<div><input type="file" name="file1_o" id="file1_o" style="width: 180px;" required></div>
				</div>
			</div>
			<div>상품2
				<div class="product_write">
					<div>상품이름2</div>
					<div><input name="p_name2" id="p_name2"></div>
					<div>가격2</div>
					<div><input name="price2" id="price2" placeholder="숫자입력"></div>
					<div>판매수량2</div>
					<div><input name="number2" id="number2" placeholder="숫자입력"></div>
					<div>사진2</div>
					<div><input type="file" name="file2_o" id="file2_o" style="width: 180px;"></div>
				</div>
			</div>
			<div>상품3
				<div class="product_write">					
					<div>상품이름3</div>
					<div><input name="p_name3" id="p_name3"></div>
					<div>가격3</div>
					<div><input name="price3" id="price3" placeholder="숫자입력"></div>
					<div>판매수량3</div>
					<div><input name="number3" id="number3" placeholder="숫자입력"></div>
					<div>사진3</div>
					<div><input type="file" name="file3_o" id="file3_o" style="width: 180px;"></div>
				</div>
			</div>
		</div>
		<div>
			배송비 : <input type="text" name="baesong" id="baesong"> 원 /
			배송비 무료 : <input type="text" name="baesong_free" id="baesong_free"> 원 이상
		</div>
		<div style="display:flex; width:800px;">
			<div>주소</div>
			<div>
				<div>
					<input type="text" id="sample6_postcode" placeholder="우편번호" name="zipcode" value="">
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
				</div>
				<div>	
					<input type="text" id="sample6_address" placeholder="주소" name="zip1" value="">
					<input type="text" id="sample6_extraAddress" placeholder="참고항목" name="zip2" value="">
				</div>
				<div>
					<input type="text" id="sample6_detailAddress" placeholder="상세주소" name="zip3" value="">
				</div>	
			</div>
			<div style="width:400px; margin-left:50px;">무통장 입금시 계좌번호 : <br>
				<input type="text" name="account_name" id="account_name" readonly>
				<select onchange="account_name.value=this.value">
					<option value="">=은행선택=</option>
					<option value="신한">신한은행</option>
					<option value="국민">국민은행</option>
					<option value="우리">우리은행</option>
				</select>
				<input type="text" name="account_num" id="account_num" placeholder="'-' 를 제외한 계좌번호를 적어주세요." style="width:269px;">
			</div>
			
		</div>
		<div class="product_write">
		</div>
		<div style="float:right;"><input type="submit" name="" id="" value="상품등록"></div>
	</div>
	</form>
</section>


<%@ include file="/include/footer.jsp" %>