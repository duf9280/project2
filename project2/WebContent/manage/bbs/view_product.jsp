<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/manage/include/header.jsp" %>

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
				</div>
			<c:choose>
				<c:when test="${view.p_name2 ne ''}">
					<div>
						<div>상품 ${view.p_name2}의 가격 : <fmt:formatNumber value="${view.price2 }" pattern="###,###"/> 원</div>
					</div>				
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${view.p_name3 ne ''}">
					<div>
						<div>상품 ${view.p_name3}의 가격 : <fmt:formatNumber value="${view.price3 }" pattern="###,###"/> 원</div>
					</div>			
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			</div>		
			<div style="margin:0 auto; width:950px;">
				<div style="float:left">
					<a href="/manage/bbs/my_items?code=<%=code%>&category=${param.category}&id=${sessionScope.id }&pageNum=${pageNum}&field=${field}&search=${search}">
						[상품 목록]
					</a>
		
			</div>
			<div style="float:right"><a href="/market/product_modify.do?cat=${sessionScope.level}&code=<%=code%>&category=${param.category}&uid=${view.uid }&id=${view.id }&pageNum=${pageNum}">[상품 수정]</a></div>
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