<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ include file="/manage/include/header.jsp" %>



<%
request.setCharacterEncoding("utf-8");
code = request.getParameter("code");
%>
<script>

if(${param.code =='lecture'}){
	if(${sessionScope.level !='2'}){
		if(${sessionScope.id == null}){
			alert("로그인후 이용가능합니다.");
			history.go(-1);
		}
		if(${sessionScope.id != null}){
			alert("사업자 등록 회원만 가능합니다.");
			history.go(-1);			
		}
	}
}
</script>
<section>

	<div style="clear:both; display:flex; width:100%; ">
		<div style="margin:0 auto;">
			강의
		</div>
	</div>
	<div style="width:100%;">
	<div style="margin:0 auto; width:900px; display:flex;">

	</div>
		<form action="/market/lecture_update.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" value="${sessionScope.id }">
			<input type="hidden" name="uid" id="uid" value="${param.uid }">
			<input type="hidden" name="code" id="code" value="${param.code }">
			<input type="hidden" name="category" id="category" value="${param.category }">
			<input type="hidden" name="cat" value="${param.cat }">
		<div style="margin:0 auto; width:800px; border-bottom:1px solid gray; display:flex; flex-direction:column; align-items:center;">
			<div style=" width:800px;">
				상품 유형 : 
					<c:if test="${param.category eq 'V'}"><label><input type="radio" name="category" id="category" value="V" checked>보컬</label></c:if>
					<c:if test="${param.category eq 'W'}"><label><input type="radio" name="category" id="category" value="W" checked>작사 / 작곡</label></c:if>
					<c:if test="${param.category eq 'I'}"><label><input type="radio" name="category" id="category" value="I" checked>악기</label></c:if>
			</div>
			<div>
				판매 상태 : 
					
						<label><input type="radio" name="gongji" id="gongji" value="1" <c:if test="${view.gongji eq '1'}">checked</c:if>>판매중</label>
					
					
						<label><input type="radio" name="gongji" id="gongji" value="2"<c:if test="${view.gongji eq '2'}"> checked</c:if>>판매중지</label>
									
			</div>
			<div>
				강의명 : <br>
				<input type="text" name="subject" id="subject" style="width:800px;" value="${view.subject}">
			</div>
			<div>
				강의 설명 : <br>
				<textarea name="comment" id="comment" style="width:800px; height:400px; resize:none;">${view.comment}</textarea>
			</div>			
					<div style="display:flex; justify-content:space-between; align-items:center; width:800px;">
				
				<div>상품1 [필수]
					<div class="product_write">
						<div>상품이름1</div>
						<div>
							<input name="p_name1" id="p_name1" value="${view.p_name1 }" required>
						</div>
						<div>가격1</div>
						<div>
							<input name="price1" id="price1" placeholder="숫자입력" value="${view.price1 }" required>
						</div>
						<div>남은 수량1</div>
						<div>
							<input name="number1" id="number1" placeholder="숫자입력" value="${view.number1 }" required>
						</div>
					</div>
				</div>
				<div>상품2
					<div class="product_write">
						<div>상품이름2</div>
						<div>
							<input name="p_name2" id="p_name2" value="${view.p_name2 }">
						</div>
						<div>가격2</div>
						<div>
							<input name="price2" id="price2" placeholder="숫자입력" value="${view.price2 }">
						</div>
						<div>남은 수량2</div>
						<div>
							<input name="number2" id="number2" placeholder="숫자입력" value="${view.number2 }">
						</div>
					</div>
					<div>

				</div>
				</div>

				<div style="width:550px;">
					<div style="float:right;">첨부 파일 1 (필수) : 
						<input type="file" name="file1" id="file1" required>
					</div>
					<div style="float:right;">첨부 파일 2 (선택) : 
						<input type="file" name="file2" id="file2">
					</div>
					<div style="float:right;">첨부 파일 3 (선택) : 
						<input type="file" name="file3" id="file3">
					</div>				
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
			<div style="display:flex; width:800px;">
				<div>
					배송비 : <input type="text" name="baesong" id="baesong" value="${view.baesong}"> 원 /
					배송비 무료 : <input type="text" name="baesong_free" id="baesong_free" value="${view.baesong_free}"> 원 이상
				</div>
			
				<div>주소</div>
				<div>
					<div>
						<input type="text" id="sample6_postcode" placeholder="우편번호" name="zipcode" value="${view.zipcode }">
						<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
					</div>
					<div>	
						<input type="text" id="sample6_address" placeholder="주소" name="zip1" value="${view.zip1}">
						<input type="text" id="sample6_extraAddress" placeholder="참고항목" name="zip3" value="${view.zip2}">
					</div>
					<div>
						<input type="text" id="sample6_detailAddress" placeholder="상세주소" name="zip2" value="${view.zip3}">
					</div>	
				</div>
			</div>
			<div style="margin:0 auto; width:800px; display:flex; justify-content:space-between;">
				<c:choose>
					<c:when test="${param.cat eq '2' }">
						<div>
							<a href="/manage/bbs/my_items?code=<%=code%>&category=${param.category}&id=${sessionScope.id }&field=${field}&search=${search}">
								[강의 목록]
							</a>
						</div>
						<div>
							<button>[상품수정]</button>
						</div>	
					</c:when>
					<c:otherwise>
						<div>
							<div>
								<a href="list?code=<%=code%>&category=${param.category}">
									[목록]
								</a>
							</div>
							<div>
								<button>[상품수정]</button>
							</div>
						</div>		
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		</form>
	</div>


<!-- 

 -->
</section>

<%@ include file="/include/footer.jsp" %>