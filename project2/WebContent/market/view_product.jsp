<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<%
request.setCharacterEncoding("utf-8");
code = request.getParameter("code");
%>

<%@include file="/bbs/bbs_title.jsp"%>
<script>

</script>
<div class="view_section">
	<div style="width:100%;">
	<form action="/buy/cart_insert_product" method="post">
			<input type="hidden" name="id" id="id" value="${sessionScope.id }">
			<input type="hidden" name="code" id="code" value="${param.code }">
			<input type="hidden" name="cart_name" id="cart_name" value="${sessionScope.cart_name}">
			<%=code_title %>
		<div style="margin:0 auto; width:950px; border-bottom:1px solid gray; display:flex; flex-direction:column; align-items:center;">
			<div style=" width:950px;">
				상품 유형 : 
					<c:if test="${param.category eq 'A'}">음향기기</c:if>
					<c:if test="${param.category eq 'B'}">악기</c:if>
					<c:if test="${param.category eq 'C'}">기타</c:if>
			</div>
			<div style=" width:950px;">
				상품명 : ${view.subject}
			</div>
			<div style=" width:950px;">
				배송비 : <fmt:formatNumber value="${view.baesong }" pattern="###,###"/> 원 (<fmt:formatNumber value="${view.baesong_free }" pattern="###,###"/>이상 구매시 무료배송)
				
			</div>
			<div>
			<!-- 여기부터 이미지 없을시 엑박 안뜨게 -->
				<c:choose>
					<c:when test="${empty view.file1_o || view.file1_o eq ''}">
					</c:when>
					<c:otherwise>
						<div style="margin:0 auto;">
							<img src="/market/upload/${view.file1_o}" onclick="origin_imgs(1)" style="cursor:pointer; max-width:800px;">
						</div>
					</c:otherwise>
				</c:choose>	
				<c:choose>
					<c:when test="${empty view.file2_o || view.file2_o eq ''}">
					</c:when>
					<c:otherwise>
						<div style="margin:0 auto; clear:both;">			
							<img src="/market/upload/${view.file2_o}" onclick="origin_imgs(2)" style="cursor:pointer; max-width:800px;">
						</div>
					</c:otherwise>
				</c:choose>		
				<c:choose>
					<c:when test="${empty view.file3_o || view.file3_o eq ''}">
					</c:when>
					<c:otherwise>
						<div style="margin:0 auto; clear:both;">			
							<img src="/market/upload/${view.file3_o}" onclick="origin_imgs(3)" style="cursor:pointer; max-width:800px;">
						</div>
					</c:otherwise>
				</c:choose>							
			<!-- 여기까지 이미지 없을시 엑박 안뜨게 -->		
					
			</div>						
			<div style="width:950px;">
				상품 설명 : <br>
				<%//개행처리 부분
				pageContext.setAttribute("nn","\n");
				%>				
<%-- 					${fn:replace(cmt,nn,'<br>')} --%>
				${fn:replace(view.comment, nn, "<br/>")}
				<br><br>
			</div>			
					
			<div style="display:flex; justify-content:space-between; align-items:center; width:950px;">
				<div>
					<div>상품 ${view.p_name1}의 가격 : <fmt:formatNumber value="${view.price1 }" pattern="###,###"/> 원</div>
					<div>구매수량 : <input type="text" name="buy_num1" id="buy_num1" value="0"></div>
				</div>
			<c:choose>
				<c:when test="${view.p_name2 ne ''}">
					<div>
						<div>상품 ${view.p_name2}의 가격 : <fmt:formatNumber value="${view.price2 }" pattern="###,###"/> 원</div>
						<div>구매수량 : <input type="text" name="buy_num2" id="buy_num2" value="0"></div>
					</div>				
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${view.p_name3 ne ''}">
					<div>
						<div>상품 ${view.p_name3}의 가격 : <fmt:formatNumber value="${view.price3 }" pattern="###,###"/> 원</div>
						<div>구매수량 : <input type="text" name="buy_num3" id="buy_num3" value="0"></div>
					</div>			
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			</div>		
			<div style="margin:0 auto; width:950px;">
				<div style="float:left">
					<a href="list?code=<%=code%>&category=${param.category}&pageNum=${pageNum}&field=${field}&search=${search}">
						[상품 목록]
					</a>
				</div>
				<!-- 세션아이디 비교 휴 버튼 보이고 안보이고 -->
				<div style="float:right"><a href="" onclick="return logingo()">[구매]</a></div>
				
				
				<div style="float:right">
					
					<input type="hidden" name="session_id" id="session_id" value="${sessionScope.id }">
					<input type="hidden" name="code" id="code" value="${param.code}">
					<input type="hidden" name="uid" id="uid" value="${view.uid}">
					<input type="hidden" name="sell_id" id="sell_id" value="${view.id}">
					<input type="hidden" name="subject" id="subject" value="${view.subject}">

					<input type="hidden" name="p_name1" id="p_name1" value="${view.p_name1}">
					<input type="hidden" name="price1" id="price1" value="${view.price1}">
					<input type="hidden" name="number1" id="number1" value="${view.number1}">
					<input type="hidden" name="p_name2" id="p_name2" value="${view.p_name2}">
					<input type="hidden" name="price2" id="price2" value="${view.price2}">
					<input type="hidden" name="number2" id="number2" value="${view.number2}">
					<input type="hidden" name="p_name3" id="p_name3" value="${view.p_name3}">
					<input type="hidden" name="price3" id="price3" value="${view.price3}">
					<input type="hidden" name="number3" id="number3" value="${view.number3}">
					
					<input type="hidden" name="zipcode" id="zipcode" value="${view.zipcode}">
					<input type="hidden" name="zip1" id="zip1" value="${view.zip1}">
					<input type="hidden" name="zip2" id="zip2" value="${view.zip2}">
					<input type="hidden" name="zip3" id="zip3" value="${view.zip3}">
					
					<input type="hidden" name="baesong" id="baesong" value="${view.baesong}">
					<input type="hidden" name="baesong_free" id="baesong_free" value="${view.baesong_free}">
					
					<input type="hidden" name="account_name" id="account_name" value="${view.account_name}">
					<input type="hidden" name="account_num" id="account_num" value="${view.account_num}">
					<input type="hidden" name="category" id="category" value="${view.category}">
					<input type="hidden" name="file1_s" id="file1_s" value="${view.file1_s}">
					<input type="hidden" name="pageNum" id="pageNum" value="${param.pageNum}">
					<input type="hidden" name="pageNum_view" id="pageNum_view" value="${param.pageNum_view}">
					<input type="hidden" name="field_view" id="field_view" value="${param.field_view}">
					<input type="hidden" name="search_view" id="search_view" value="${param.search_view}">
					
					
					<button>장바구니 담기</button>
				
				</div>
		
		
			<div style="float:right"><a href="/market/product_modify.do?code=<%=code%>&category=${param.category}&uid=${view.uid }&id=${view.id }&pageNum=${pageNum}">[상품 수정]</a></div>
			</div>
		</div>
	</form>	
	</div>


<!-- 

 -->
</div>
<script>
function origin_imgs(i){
	var img = i;
	if(img==1){
		window.open('/bbs/origin_img.jsp?code=<%=code%>&uid=${view.uid}&img=1&pic=${view.file1_o}','원본','');
	}
	if(img==2){
		window.open('/bbs/origin_img.jsp?code=<%=code%>&uid=${view.uid}&img=2&pic=${view.file2_o}','원본','');
	}
	if(img==3){
		window.open('/bbs/origin_img.jsp?code=<%=code%>&uid=${view.uid}&img=3&pic=${view.file3_o}','원본','');
	}
}


function logingo(){
	if(session_id==null){
		alert("로그인 후 이용 가능합니다.");
		return false;
	}
}
</script>
<%@ include file="/include/footer.jsp" %>