<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<%
request.setCharacterEncoding("utf-8");
code = request.getParameter("code");
%>

<div class="view_section">
	<div style="width:950px; margin:0 auto;">
		<div>
			상세 정보 기입
		</div>
		<div>
			<div style="width:950px; display:flex;">
				<div style="width:260px;">상품명</div>
				<div style="width:150px;">판매자</div>
				<div style="width:100px;">개당가격</div>
				<div style="width:100px">수량</div>
				<div style="width:80px;">배송비</div>
				<div style="width:100px">총금액</div>
				<div style="width:80px;">적립금</div>
			</div>
			<!-- 이부분이 장바구니 뽑는부분 el로 쓴 부분은 그냥 자리만-->	
			<form action="/insert_buy_list" method="post" onsubmit="return pay()" name="credit">
			
				<c:forEach var="c" items="${v }">
							
				<div style="width:950px; display:flex;">
					<input type="hidden" name="buy_id" id="buy_id" value="${c.buy_id }">
					<div style="width:260px;">${c.subject}</div>
					<div style="width:150px;">${c.sell_id}</div>
					<div style="width:100px;"><fmt:formatNumber value="${c.price}" pattern="###,###"/></div>

					<div style="width:100px">
						${c.number} 개
					</div>
					<div style="width:80px;"><fmt:formatNumber value="${c.baesong}" pattern="###,###"/></div>
					<div style="width:100px;"><fmt:formatNumber value="${c.price*c.number}" pattern="###,###"/></div>
					<div style="width:80px"><fmt:formatNumber value="${c.price*c.number/20}" pattern="###,###"/></div>
					
				</div>
				<c:set var="total_point" value="${total_point+ (c.price*c.number/20)}"/>
				<c:set var="total_bae" value="${total_bae + c.baesong}"/>
				<c:set var="total_price" value="${total_price + c.price*c.number}"/>
				</c:forEach>
				<div>
					구매 정보 기입
				</div>
				<div>
					<div>주소</div>
					<div>받으실분 성함 : <input type="text" name="b_name" id="b_name"></div>
					<div>받으실분 연락처 : <input type="text" name="b_tel" id="b_tel"></div>
					<div><input type="text" id="sample6_postcode" placeholder="우편번호" name="b_zipcode" > <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></div>
					<div><input type="text" id="sample6_address" placeholder="주소" name="b_zip1" ></div>
					<div>
						<input type="text" id="sample6_extraAddress" placeholder="참고항목" name="b_zip2" value="">
						<input type="text" id="sample6_detailAddress" placeholder="상세주소" name="b_zip3" >
					</div>
					<div>배송 메세지 : <input type="text" name="baesong_msg" id="baesong_msg"></div>
				</div>
				
			<!-- 			여기까지가 장바구니 뽑는부분 -->
			<div style="display:flex; margin:0 auto; width:950px; text-align:center;">
				<div style="width:950px; display:flex; flex-direction:column;">
					<div style="display:flex; margin:0 auto;">
						<div style="width:240px;">총 상품 금액</div>
						<div style="width:70px;"> 총 배송비</div>
						<div style="width:200px;">예정 적립금</div>
						<div style="width:240px;">결제예정 금액</div>	
						<div style="width:400px;">결제방법</div>
					</div>
					<div style="display:flex; margin:0 auto;">
						<div style="width:240px;"><fmt:formatNumber value="${total_price }" pattern="###,###,###,###"/></div>
						<div style="width:70px;"><fmt:formatNumber value="${total_bae}" pattern="###,###,###,###"/></div>
						<div style="width:200px;"><fmt:formatNumber value="${total_point }" pattern="###,###,###,###"/></div>
						<div style="width:240px;"><fmt:formatNumber value="${total_price + total_bae }" pattern="###,###,###,###"/></div>	
						<div style="width:400px;">
							<div>
								<label><input type="radio" name="pay_way" id="pay_way" value="Card" checked> 카드 </label>
								<label><input type="radio" name="pay_way" id="pay_way" value="Account"> 무통장입금</label>
							</div>
							<div>
								예금주 : <input type="text" name="a_name" id="a_name" placeholder="예금주"><br>
								
								가상계좌번호 : 
							<select onchange="account.value=this.value">
								<option value="" selected disabled>=은행선택=</option>
								<option value="신한 110-212121211">신한은행</option>
								<option value="국민 242-566464632">국민은행</option>
								<option value="우리 364-644364336">우리은행</option>
							</select>
							<input type="text" name="account" id="account" style="width:269px;">
								
								이부분은 level5인애 정보에 계좌넣고 모든상품은 이 계좌로 받고 입금 확인,전체 관리자가 판매자 계좌에 송금
							</div>
						</div>
					</div>	
				</div>
			</div>
			<div><button>상품 구매</button></div>
			
			<input type="hidden" name="cart_name" id="cart_name" value="${sessionScope.cart_name}">
			<input type="hidden" name="total_price" id="total_price" value="${total_price }">
			<input type="hidden" name="total_bae" id="total_bae" value="${total_bae }">
			<input type="hidden" name="total_point" id="total_point" value="${total_point}">
			</form>	
		</div>
			

	</div>
</div>
</div>

<script>

	if(session_id==null){
		alert("로그인 후 이용 가능합니다.");
		history.go(-1);
	}
	function pay(){

		if(credit.pay_way.value=="Card"){
			alert("카드");
			return false;
		}else{
			alert("aa");
			return true;
		}
	}
</script>
<%@ include file="/include/footer.jsp" %>