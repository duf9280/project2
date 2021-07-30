<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/manage/include/header.jsp"%>

<!-- 세션아이디와 구매목록같은지 비교 -->
<c:choose>
	<c:when test="${sessionScope.level ne '2'}">
		<script>
			alert("판매자만 확인 가능합니다.");
			location.href="/";			
		</script>
	</c:when>
</c:choose>
<section>
	<div style="width:1100px;margin:0 auto; text-align:center;">
		<div style="margin:0 auto; text-align:center;">
<%-- 			상세 구매내역 - 주문번호 : ${param.list} --%>
		</div>
		<div style="display:flex; width:1100px;">
				<div style="width:120px;">이미지</div>
				<div style="width:100px;">구매자</div>
				<div style="width:130px;">상품 분류</div>
				<div style="width:350px;">상품명</div>
				<div style="width:100px;">가격</div>
				<div style="width:100px;">갯수</div>
				<div style="width:100px;">총 가격</div>
				<div style="width:100px;">배송비</div>
		</div>
		<c:forEach var="c" items="${a }">
		<c:choose>
		<c:when test="${c.status eq 'Y' }">
		<div style=" border:1px solid gray; width:1100px; margin:0 auto; text-align:center;">
			<div style="display:flex;  width:1100px;flex-direction:column; margin:auto;">
				
				<div style="width:120px;"><img src="/market/upload/${c.file1_s }" style="vertical-align: middle;">
				</div>
				
				<div style="display:flex; margin:auto;">
					<div style="width:100px;">${c.buy_id}</div>
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
			</div>
			<div style="display:flex; width:1100px">
				<div>
					결제상태 : 입금완료
							 &nbsp;&nbsp;&nbsp;
				</div>									

				<form name="baesonggogo" action="baesong.do" method="post">
				<input type="hidden" name="cart_name" id="cart_name" value="${c.cart_name}">
				<input type="hidden" name="subject" id="subject" value="${c.subject }">
				<input type="hidden" name="price" id="price" value="${c.price }">
				<input type="hidden" name="id" id="id" value="${sessionScope.id}">
				<div style=" text-align:center; display:flex; justify-content:space-evenly;">
					<div>
						택배사 :
						<c:if test="${!empty c.baesong_num }">
						 ${c.baesong_com } &nbsp;&nbsp;&nbsp;
						 </c:if>
						<c:if test="${empty c.baesong_com }">
							<input type="text" name="baesong_com" id="baesong_com">
						</c:if>
	
					</div>
					<div>
						배송메세지 : ${c.baesong_msg} &nbsp;&nbsp;&nbsp;
					</div>					
					<div>
						운송장번호 :
				<c:if test="${!empty c.baesong_num }">
					${c.baesong_num } &nbsp;&nbsp;&nbsp;
				</c:if>
					</div>
				<c:if test="${empty c.baesong_num }">
				<input type="text" name="baesong_num" id="baesong_num">
					
					<div>
						<button>상품발송</button>
					</div>		
				</c:if>	
				</div>			
				</form>	
						

						
			</div>	
			<c:if test="${!empty c.baesong_com }">
			<div style="display:flex; justify-content:space-evenly;">			
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
				<div style="clear:both;">
					<form name="stst" action="baesong_st" method="post">
						<input type="hidden" name="cart_name" id="cart_name" value="${c.cart_name}">
						<input type="hidden" name="buy_id" id="buy_id" value="${c.buy_id}">
						<input type="hidden" name="subject" id="subject" value="${c.subject}">
						<input type="hidden" name="price" id="price" value="${c.price}">
						<input type="hidden" name="baesong" id="baesong" value="${c.baesong}">
						<input type="hidden" name="id" id="id" value="${sessionScope.id}">
						<label><input type="radio" name="baesong_status" id="baesong_status" value="4">배송완료</label>
						<label><input type="radio" name="baesong_status" id="baesong_status" value="5">교환</label>
						<label><input type="radio" name="baesong_status" id="baesong_status" value="6">반품</label>
						<button>배송상태 변경</button>
					</form>
				</div>
			</div>	
			</c:if>		
		</div>
			
		</c:when>
		<c:otherwise>
				
		</c:otherwise>
	</c:choose>
	
	</c:forEach>	
		
	</div>


		
	

		

	


	
		
	
</section>
<%@ include file="/include/footer.jsp"%>