<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>

<!-- 세션아이디와 구매목록의 구매자와 같은지 비교 -->
<c:choose>
	<c:when test="${sessionScope.id ne a.buy_id || sessionScope.id ne a.buy_id}">
		<script>
			alert("본인만 확인 가능합니다.");
			location.href="/";			
		</script>
	</c:when>
</c:choose>
<section>
	<div style="width:1100px;margin:0 auto; text-align:center;">
		<div style="margin:0 auto; text-align:center;">
			상세 구매내역 - 주문번호 : ${param.list}
		</div>
		<div style="display:flex; width:1100px;">
				<div style="width:120px;">이미지</div>
				<div style="width:100px;">판매자</div>
				<div style="width:130px;">상품 분류</div>
				<div style="width:350px;">상품명</div>
				<div style="width:100px;">가격</div>
				<div style="width:100px;">갯수</div>
				<div style="width:100px;">총 가격</div>
				<div style="width:100px;">배송비</div>
		</div>
		<c:forEach var="c" items="${c }">
		<div style=" margin-top:20px; border:1px solid gray; width:1100px; margin:0 auto; text-align:center; display:flex;">
			<div style="">
				<div style="width:120px;"><img src="/market/upload/${c.file1_s }" style="vertical-align: middle;"></div>
			</div>
			<div>
				<div style="display:flex; margin:auto;">
					<div style="width:100px;">${c.sell_id}</div>
					<div style="width:130px;">
						<c:choose>
							<c:when test="${c.tb_table eq 'lecture'}">
								강의
								<c:if test="${c.category eq 'V' }">(보컬)</c:if>
								<c:if test="${c.category eq 'W' }">(작사/작곡)</c:if>
								<c:if test="${c.category eq 'I' }">(악기)</c:if>
							</c:when>
							<c:when test="${c.tb_table eq 'product'}">
								일반
								<c:if test="${c.category eq 'A' }">(음향기기)</c:if>
								<c:if test="${c.category eq 'B' }">(악기)</c:if>
								<c:if test="${c.category eq 'C' }">(기타)</c:if>
							</c:when>
						</c:choose>
					</div>
					<div style="width:350px;">${c.subject}</div>
					<div style="width:100px;"><fmt:formatNumber value="${c.price }" pattern="###,###,###,###"/>원</div>
					<div style="width:100px;">${c.number}</div>
					<div style="width:100px;"><fmt:formatNumber value="${c.price * c.number}" pattern="###,###,###,###"/>원</div>
					<div style="width:100px;"><fmt:formatNumber value="${c.baesong}" pattern="###,###,###,###"/></div>
				</div>
				<div style="width:980px;margin:0 auto; text-align:center; display:flex; justify-content:center;">
					<div>
						배송메세지 : ${c.baesong_msg} &nbsp;&nbsp;&nbsp;
					</div>
					<div>
						택배사 :
						<c:if test="${!empty c.baesong_com }">
						 ${c.baesong_com } &nbsp;&nbsp;&nbsp;
						</c:if>
						<c:if test="${empty c.baesong_com }">
						 ---- &nbsp;&nbsp;&nbsp;
						</c:if>					
					</div>
					<div>
						운송장번호 :
						<c:if test="${!empty c.baesong_com }">
						 ${c.baesong_num } &nbsp;&nbsp;&nbsp;
						</c:if>
						<c:if test="${empty c.baesong_num }">
						 ---- &nbsp;&nbsp;&nbsp;
						</c:if>		
						 
					</div>
					<div>
						배송상태 : 
						<c:if test="${empty c.baesong_status}">돈을 안주니 꿈쩍도 안하지!</c:if>
						<c:if test="${c.baesong_status eq '1'}">상품준비중</c:if>
						<c:if test="${c.baesong_status eq '2'}">발송완료</c:if>
						<c:if test="${c.baesong_status eq '3'}">배송중</c:if>
						<c:if test="${c.baesong_status eq '4'}">배송완료</c:if>
						<c:if test="${c.baesong_status eq '5'}">교환</c:if>
						<c:if test="${c.baesong_status eq '6'}">반품</c:if>
						<c:if test="${c.baesong_status eq '7'}">주문취소</c:if>
					</div>
				</div>
				
				<form action="submit_request" method="post">
					<div>
						<input type="hidden" name="cart_name" id="cart_name" value="${c.cart_name}">
						<input type="hidden" name="buy_id" id="buy_id" value="${sessionScope.id}">
						<input type="hidden" name="subject" id="subject" value="${c.subject}">
						<input type="hidden" name="price" id="price" value="${c.price}">
						<input type="hidden" name="baesong" id="baesong" value="${c.baesong}">
						<input type="hidden" name="id" id="id" value="${sessionScope.id}">			
						<c:choose>
							<c:when test="${c.baesong_status eq '7' || c.baesong_status eq '3' || c.baesong_status eq '6' ||c.baesong_status eq '5'}">
							
							</c:when>
							<c:when test="${c.baesong_status eq '4'}">
								<label><input type="radio" name="baesong_status" id="basong_status" value="5">교환</label>
								<label><input type="radio" name="baesong_status" id="basong_status" value="6">반품</label>
								<button>신청</button>
							</c:when>
							<c:when test="${c.baesong_status eq '1'}">
								<label><input type="radio" name="baesong_status" id="basong_status" value="7">주문취소</label>
								<button>신청</button>
							</c:when>
						</c:choose>
						
					</div>
				</form>	
				
			</div>		
		</div>	
		</c:forEach>
		<div style="width:1100px;margin:0 auto; display:flex;">
			<div>
				구매자 : ${a.buy_id} &nbsp;&nbsp;&nbsp;
			</div>
			<div>
				받으시는 분 : ${a.b_name } &nbsp;&nbsp; &nbsp;
			</div>
			<div>
				받으시는 분 연락처 : ${a.b_tel } &nbsp;&nbsp;&nbsp;
			</div>
		</div>
		<div style="width:1100px;margin:0 auto; display:flex;">
			<div>
			
				총 금액 : <fmt:formatNumber value="${a.price + a.baesong }" pattern="###,###,###,###"/>원&nbsp;&nbsp;&nbsp;
			</div>
			<div>
				입금계좌 : ${a.account } &nbsp;&nbsp;&nbsp;
			</div>
			<div>
				예금주 : ${a.a_name } &nbsp;&nbsp;&nbsp;
			</div>
			<div>
				결제방법 : <c:if test="${a.pay_way eq 'Account'}">무통장입금</c:if>
						 <c:if test="${a.pay_way eq 'Card'}">카드</c:if>
				 &nbsp;&nbsp;&nbsp;
			</div>
			<div>
				결제상태 : <c:if test="${a.pay eq 'N' }">미입금</c:if>
						 <c:if test="${a.pay eq 'Y' }">입금완료</c:if>
						 &nbsp;&nbsp;&nbsp;
			</div>			
		</div>	

		<div style="width:1100px;margin:0 auto; display:flex;">
		
			<div>
				받으시는분 주소 : ${a.b_zip1} &nbsp;&nbsp; ${a.b_zip2} &nbsp;&nbsp;  ${a.b_zip3}
			</div>
		</div>	
		<div style="width:1100px;margin:0 auto; display:flex;">
			<a href="/buy/order_list?id=${sessionScope.id}">[구매내역 목록]</a>
		</div>
		
	
</section>
<%@ include file="/include/footer.jsp"%>