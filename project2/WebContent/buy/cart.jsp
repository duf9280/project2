<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp"%>

<section>
	<div style="width:950px;margin:0 auto;">
		<div>
			장바구니
		</div>
		<div style="width:1070px; display:flex;">
			<div style="width:40px;">
				<input type="checkbox" name="check" onclick="selectAll(this)">			
			</div>
			<div style="width:120px;">상품대표이미지</div>
			<div style="width:260px;">상품명</div>
			<div style="width:150px;">판매자</div>
			<div style="width:100px;">개당가격</div>
			<div style="width:100px">수량</div>
			<div style="width:80px;">배송비</div>
			<div style="width:100px">총금액</div>
			<div style="width:80px;">적립금</div>
			<div style="width:40px;">삭제</div>
		</div>
	
		<!-- 이부분이 장바구니 뽑는부분 el로 쓴 부분은 그냥 자리만-->	
		<form action="/delete_cart_checkbox" method="post" onsubmit="return check_del_cart()">
		<input type="hidden" name="cart_name" id="cart_name" value="${sessionScope.cart_name }">
			<c:set var="num" value="${number}"/>
				<c:choose>	
					<c:when test="${count >=1 }">
					<c:forEach var="c" items="${v }">
						<div style="width:1070px; display:flex;">
							<input type="hidden" name="buy_id" id="buy_id" value="${c.buy_id }">
							<div style="width:40px;"><input type="checkbox" name="check" id="check" value="${c.uid}"></div>
							<div style="width:120px;">
								<img src="/market/upload/${c.file1_s}">
							</div>
							<div style="width:260px;">${c.subject}</div>
							<div style="width:150px;">${c.sell_id}</div>
							<div style="width:100px;"><fmt:formatNumber value="${c.price}" pattern="###,###"/></div>
							
							<div style="width:100px">
								${c.number }
							</div>
								
								
							<div style="width:80px;"><fmt:formatNumber value="${c.baesong}" pattern="###,###"/></div>
							<div style="width:100px;"><fmt:formatNumber value="${c.price*c.number}" pattern="###,###"/></div>
							<div style="width:80px"><fmt:formatNumber value="${c.price*c.number/20}" pattern="###,###"/></div>
							<div style="width:40px;">
								<a href="/delete_cart?id=${c.buy_id}&uid=${c.uid}&cn=${sessionScope.cart_name}" onclick="return del_cart()">삭제</a>
							</div>
						</div>
					

						<c:set var="num" value="${num-1 }"/>
						<c:set var="total_point" value="${total_point + c.price*c.number/20}"/>
						<c:set var="total_bae" value="${total_bae + c.baesong}"/>
						<c:set var="total_price" value="${total_price + c.price}"/>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div style="text-align:center;">
							상품이 없습니다.
						</div>
					</c:otherwise>
				</c:choose>
			<div><button>선택된 상품 삭제</button></div>
		</form>	
			
	<!-- 			여기까지가 장바구니 뽑는부분 -->
	

	<div style="display:flex; margin:0 auto; width:1070px; text-align:center;">
		<div style="width:750px; display:flex; flex-direction:column;">
			<div style="display:flex; margin:0 auto;">
				<div style="width:240px;">총 상품 금액</div>
				<div style="width:70px;"> 총 배송비</div>
				<div style="width:200px;">적립 예정 포인트</div>
				<div style="width:240px;">결제예정 금액</div>	
			</div>
			<div style="display:flex; margin:0 auto;">
				<div style="width:240px;"><fmt:formatNumber value="${total_price }" pattern="###,###"/></div>
				<div style="width:70px;"><fmt:formatNumber value="${total_bae}" pattern="###,###"/></div>
				<div style="width:200px;"><fmt:formatNumber value="${total_point}" pattern="###,###"/></div>
				<div style="width:240px;"><fmt:formatNumber value="${total_price + total_bae }" pattern="###,###"/></div>	
			</div>
		</div>
		<div style="float:right; width:400px;">
			<div>
				<div><a href="before_pay_detail?id=${sessionScope.id}&cart_name=${sessionScope.cart_name}">상품 구매</a></div>	
			</div>
			
		</div>
	</div>	
	</div>	
</section>





<script>
function selectAll(aaa)  {
	   const checkboxes = document.getElementsByName('check');
	     
	   checkboxes.forEach((checkbox) => {checkbox.checked = aaa.checked;})
	   console.log(checkboxes);
}

function del_cart(){
	var d = confirm("상품을 삭제하시겠습니까?");
	if(d){
		return true;
	}else{
		return false;
	}
}
function check_del_cart(){
	var cc = confirm("체크된 상품을 삭제하시겠습니까?");
	if(cc){
		return true;
	}else{
		return false;
	}
}
</script>
<%@ include file="/include/footer.jsp" %>