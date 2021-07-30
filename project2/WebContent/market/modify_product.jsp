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
	<form action="product_update.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="category" value="${param.category}">
	<input type="hidden" name="code" value="${param.code }">
	<input type="hidden" name="uid" value="${param.uid }">
	<input type="hidden" name="id" value="${sessionScope.id }">
	<input type="hidden" name="cat" value="${cat }">
	<div style="width:800px; margin:0 auto;">
		<div>
			<div>상품 종류
				<c:if test="${param.category eq 'A'}"><label><input type="radio" name="category" id="" value="A" checked>음향기기</label></c:if>
				<c:if test="${param.category eq 'B'}"><label><input type="radio" name="category" id="" value="B" checked>악기</label></c:if>
				<c:if test="${param.category eq 'C'}"><label><input type="radio" name="category" id="" value="C" checked>기타</label></c:if>
			</div>
		</div>
		<div>
			판매 상태 : 
				
					<label><input type="radio" name="gongji" id="gongji" value="1" <c:if test="${view.gongji eq '1'}">checked</c:if>>판매중</label>
				
				
					<label><input type="radio" name="gongji" id="gongji" value="2"<c:if test="${view.gongji eq '2'}"> checked</c:if>>판매중지</label>
								
		</div>		
		
		
		<div style="display:flex;margin-bottom:10px;">
			<div>상품명
			<input name="subject" id="" value="${view.subject }"></div>
		</div>
		<div class="product_write">
			<div>상품 설명 <br>
			<textarea name="comment" id="" style="width:800px;height:300px;resize:none;">${view.comment }</textarea></div>
			</div>
		<div style="display:flex; justify-content:space-between; margin-bottom:20px;">
			<div>상품1
				<div class="product_write">
					<div>상품이름1</div>
					<div><input name="p_name1" id="p_name1" value="${view.p_name1 }"></div>
					<div>가격1</div>
					<div><input name="price1" id="price1" placeholder="숫자입력" value="${view.price1 }"></div>
					<div>판매수량1</div>
					<div><input name="number1" id="number1" placeholder="숫자입력" value="${view.number1 }"></div>
					<div>사진1</div>
					<div><input type="file" name="file1_o" id="file1_o" style="width: 180px;" required></div>
				</div>
			</div>
			<div>상품2
				<div class="product_write">
					<div>상품이름2</div>
					<div><input name="p_name2" id="p_name2" value="${view.p_name2 }"></div>
					<div>가격2</div>
					<div><input name="price2" id="price2" placeholder="숫자입력" value="${view.price2 }"></div>
					<div>판매수량2</div>
					<div><input name="number2" id="number2" placeholder="숫자입력" value="${view.number2 }"></div>
					<div>사진2</div>
					<div><input type="file" name="file2_o" id="file2_o" style="width: 180px;"></div>
				</div>
			</div>
			<div>상품3
				<div class="product_write">					
					<div>상품이름3</div>
					<div><input name="p_name3" id="p_name3" value="${view.p_name3 }"></div>
					<div>가격3</div>
					<div><input name="price3" id="price3" placeholder="숫자입력" value="${view.price3 }"></div>
					<div>판매수량3</div>
					<div><input name="number3" id="number3" placeholder="숫자입력" value="${view.number3 }"></div>
					<div>사진3</div>
					<div><input type="file" name="file3_o" id="file3_o" style="width: 180px;"></div>
				</div>
			</div>
		</div>
		<div>
			배송비 : <input type="text" name="baesong" id="baesong" value="${view.baesong }"> 원 /
			배송비 무료 : <input type="text" name="baesong_free" id="baesong_free" value="${view.baesong_free }"> 원 이상
		</div>
		<div style="display:flex; width:800px;">
			<div>주소</div>
			<div>
				<div>
					<input type="text" id="sample6_postcode" placeholder="우편번호" name="zipcode" value="${view.zipcode }">
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
				</div>
				<div>	
					<input type="text" id="sample6_address" placeholder="주소" name="zip1" value="${view.zip1 }">
					<input type="text" id="sample6_extraAddress" placeholder="참고항목" name="zip2"  value="${view.zip2 }">
				</div>
				<div>
					<input type="text" id="sample6_detailAddress" placeholder="상세주소" name="zip3" value="${view.zip3 }">
				</div>	
			</div>
			<div>정산받을 계좌번호 : 
				<input type="text" name="account_name" id="account_name" value="${view.account_name }" readonly>
				<select onchange="account_name.value=this.value">
					<option value="">=은행선택=</option>
					<option value="신한"<c:if test="${view.account_name eq'신한'}">selected</c:if>>신한은행</option>
					<option value="국민"<c:if test="${view.account_name eq'국민'}">selected</c:if>>국민은행</option>
					<option value="우리"<c:if test="${view.account_name eq'우리'}">selected</c:if>>우리은행</option>
				</select>
				<input type="text" name="account_num" id="account_num" value="${view.account_num}" placeholder="'-' 를 제외한 계좌번호를 적어주세요." style="width:269px;">
			</div>
			
		</div>
		<div class="product_write">
		</div>
		<div  style="float:right;margin:0 auto; width:800px; display:flex; justify-content:space-between;">
		<c:choose>
		<c:when test="${param.cat eq '2' }">
			<div>
				<a href="/manage/bbs/my_items?code=<%=code%>&category=${param.category}&id=${sessionScope.id }&field=${field}&search=${search}">
					[강의 목록]
				</a>
			</div>
			<div>
				<input type="submit" name="" id="" value="상품수정">
			</div>	
		</c:when>
		<c:otherwise>
			<div>
				<a href="list?code=<%=code%>&category=${param.category}">
					[목록]
				</a>
			</div>
			<div>				
				<input type="submit" name="" id="" value="상품수정">
			</div>		
		</c:otherwise>
	</c:choose>
		
		
		</div>
	</div>
	</form>
</section>


<%@ include file="/include/footer.jsp" %>