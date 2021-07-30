<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>

<section>
	<div style="width:1110px; margin:0 auto;">
		<div>
			구매내역
		</div>
		<div style="width:960px; display:flex; margin:0 auto;">

			<div style="width:230px;">주문번호</div>
			<div style="width:230px;">주문일시</div>
			<div style="width:100px;">총 주문 금액</div>
			<div style="width:150px">예금주</div>
			<div style="width:150px">미입금액</div>
			<div style="width:100px;">상태</div>
		</div>
	
		<!-- 구매내역 //el로 쓴 부분은 그냥 자리만-->	

<c:choose>
	<c:when test="${count >=1 }">
	<c:forEach var="c" items="${a }">
		<div style="width:960px; display:flex; margin:0 auto;">
			<input type="hidden" name="buy_id" id="buy_id" value="${c.buy_id }">
			<div style="width:230px;">
			
			<a href="order_detail?id=${sessionScope.id}&list=${c.list_name}">
				${c.list_name}
			</a>
			
			</div>
			<div style="width:230px;">${c.signdate}</div>
			<div style="width:100px;"><fmt:formatNumber value="${c.price +c.baesong}" pattern="###,###,###,###"/></div>
			<div style="width:150px">${c.a_name }</div>
			<c:if test="${c.pay eq 'Y' }">
				<div style="width:150px;">
					0
				</div>
			</c:if>	
			<c:if test="${c.pay eq 'N' }">
				<div style="width:150px;">
					<fmt:formatNumber value="${c.price }" pattern="###,###,###,###"/>
				</div>
			</c:if>				
			<div style="width:100px">
				<c:choose>
					<c:when test="${c.pay eq 'N' }">
						입금전
					</c:when>
					<c:otherwise>
						입금완료
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</c:forEach>
	</c:when>
	<c:otherwise>
		<div style="text-align:center;">구매내역이 없습니다.</div>
	</c:otherwise>
</c:choose>
			
	<!-- 			여기까지가 구매내역 뽑는부분 -->
	

		
	</div>	
</section>






<%@ include file="/include/footer.jsp" %>