<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/include/header1.jsp" %>

<%
request.setCharacterEncoding("utf-8");
code = request.getParameter("code");


%>
<style>
.lll{
	display:none;
}
</style>
<%@include file="/bbs/bbs_title.jsp"%>

<section>
	<div style="clear:both; display:flex; width:100%; ">
		<div style="margin:0 auto;">
			<%=code_title %>
		</div>
	</div>
	<form action="admin_del_market_ch" method="post" onsubmit="return ch_del_bbs()">

	<div style="width:100%;">
	<div style="margin:0 auto; width:910px; display:flex; justify-content:space-between;">
		<div>총 게시물 : ${count}</div>

	</div>
	<div style="margin:0 auto;text-align:center;">
		<div style="margin:0 auto; width:1110px; border-top:1px solid gray; border-bottom:1px solid gray; display:flex; text-align:center;">
			<div style="width:30px;text-align:center;">
				<input type="checkbox" name="chked" id="chked" onclick="selectAll(this)">
			</div>
			<div style="float: left; width:60px; text-align:center; border-right:1px solid gray;">
				번호
			</div>
			<div style=" padding-left:10px;float:left; width:480px; border-right:1px solid gray;">
				상품명
			</div>
			<div style="float:left; width:150px; border-right:1px solid gray;">
				판매자
			</div>
			<div style="float:left; width:150px; border-right:1px solid gray;">
				작성일자
			</div>
			<div style="float:left; width:120px; border-right:1px solid gray;">
				판매상태
			</div>			
			<div style="width:120px; border-right:1px solid gray;">
				판매중지
			</div>
			<div style="width:120px;">
				판매재개
			</div>			
		</div>
	

<%-- 	pageNum=${pageNum}<br> --%>
<%-- 	pageSize=${pageSize }<br> --%>
<%-- 	number=${number }<br> --%>
<%-- 	count=${count }<br>		 --%>
	
	<div id="com_list">
	<c:set var="num" value="${number}"/>
	<c:forEach var="m" items="${v }">	
		<div style=" display:flex; border-bottom:1px solid gray; width:1110px; margin:0 auto;">
			<div style="width:30px;text-align:center;">
				<input type="checkbox" id="chked" name="chked" value="${m.uid}">
				<input type="hidden" name="code" id="code" value="${param.code }">
				<input type="hidden" name="category" id="category" value="${param.category }">
			</div>
			
			<div style=" border-right:1px solid gray;float: left; width:60px; text-align:center;">${num }</div>
			<div style=" border-right:1px solid gray;float:left; width:480px; text-align:left; padding-left:10px;">
				<a href="view_admin?code=<%=code%>&uid=${m.uid }&id=${m.id}&tb_id=${sessionScope.id}&pageNum_view=1&pageNum=${pageNum}&field_view=${field_view}&search_view=${search_view}">
					<c:choose>
						<c:when test="${fn:length(m.subject) gt 27}">
							<c:out value="${fn:substring(m.subject, 0, 26)}">
							</c:out>
							...
						</c:when>
						<c:otherwise>
							<c:out value="${m.subject }">
							</c:out>
						</c:otherwise>
					</c:choose>		
				</a> [${m.review_num}]
			</div>
				
				
			<div style=" border-right:1px solid gray;float:left; width:150px;">
				
			
				${m.id}
						
			</div>
			<div style=" border-right:1px solid gray;float:left; width:150px;">
				<c:choose>
					<c:when test="${fn:length(m.signdate) gt 11}">
						<c:out value="${fn:substring(m.signdate, 0, 10)}">...
						</c:out>
					</c:when>
					<c:otherwise>
						<c:out value="${m.signdate}">
						</c:out>
					</c:otherwise>
				</c:choose>
			</div>
			<div style="float:left; width:120px; border-right:1px solid gray;">
				<c:if test="${m.gongji eq '1'}">
					판매중
				</c:if>
				<c:if test="${m.gongji eq '2'}">
					판매중지(판매자)
				</c:if>
				<c:if test="${m.gongji eq '3'}">
					판매중지
				</c:if>
			</div>
			<div style="float:left; width:120px; border-right:1px solid gray;">
				<a href="admin_del_market?code=${param.code}&category=${param.category}&uid=${m.uid}">
					판매중지
				</a>
			</div>
			<div style="float:left; width:120px;">
				<a href="admin_recover_market?code=${param.code}&category=${param.category}&uid=${m.uid}">
					판매재개
				</a>
			</div>			
		</div>
	
	<c:set var="num" value="${num-1 }"/>
	</c:forEach>
	</div>
	</div>
	</div>
	<div style="margin:0 auto; width:1110px; display:flex; justify-content: space-between;">
		<div>
			<a href="list?code=<%=code%>&pageNum=${pageNum}">[목록]</a>
		</div>
		<div>
			<button>체크상품 판매중지</button>
		</div>
	</div>
	</form>
<!-- 페이징 처리 -->
<div style="width:100%;">
	<div align=center>
		<c:if test="${count>0 }">
		<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1) }" />
		<fmt:parseNumber var="pageCount" value="${pageCount }" integerOnly="true" />
		
		<!-- 2개의 변수 초기화 -->
		<c:set var="startPage" value="${1 }" />
		<c:set var="pageBlock" value="${3 }" />
		
		<!-- 다음 페이지 블럭이 존재 할 경우 startPage 값 변경 부분-->
		<c:if test="${pageNum > pageBlock }">
			<!-- 결과를 정수형으로 리턴 받아야 하기 대문에 fmt -->
			<fmt:parseNumber var="result" value="${pageNum / pageBlock - (pageNum % pageBlock == 0 ? 1:0) }" integerOnly="true"/>
			<c:set var="startPage" value="${result * pageBlock + 1 }" />
		</c:if>
		
		<!-- endPage 값 설정 부분 -->
		<c:set var="endPage" value="${startPage + pageBlock - 1 }" />
		<c:if test="${endPage > pageCount }">
			<c:set var="endPage" value="${pageCount }" />
		</c:if>		

		<!-- 이전 링크 -->
		<c:if test="${startPage > pageBlock }">
			<a href="list?code=<%=code %>&category=${param.category}&pageNum=${startPage - pageBlock }">[이전] </a>
		</c:if>	

		<!-- 페이징 링크 -->
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum== i} ">					
					<a href="list?code=<%=code %>&category=${param.category}&pageNum=${i }" style="font-weight:bold; color:red;">[${i }]</a>					
				</c:when>
				<c:otherwise>
					<a href="list?code=<%=code %>&category=${param.category}&pageNum=${i }">[${i }] </a>
				</c:otherwise>
			</c:choose>
		</c:forEach>	
		<!-- 다음 링크 -->
		<c:if test="${endPage < pageCount }">
			<a href="list?code=<%=code %>&category=${param.category}&pageNum=${startPage + pageBlock }">[다음] </a>
		</c:if>
		</c:if>
		<form method="get">
		<input type="hidden" name="code" id="code" value="<%=code%>">
		
		<div style="width:500px; margin:0 auto;border:none; text-align:center;">
			<div>
				<select name="field">
					<option value="id" <c:if test="${field eq 'id' }">selected</c:if>>아이디</option>
					<option value="nickname" <c:if test="${field eq 'nickname' }">selected</c:if>>닉네임</option>
					<option value="subject" <c:if test="${field eq 'subject' }">selected</c:if>>제목</option>
					<option value="comment" <c:if test="${field eq 'comment' }">selected</c:if>>내용</option>			
				</select>
				<input type="text" name="search" class="search_input"value="${search}">
				<button>검색</button>
			</div>
		</div>
		</form>		
	</div>
</div>


</section>
<script>
function selectAll(aaa)  {
	   const checkboxes = document.getElementsByName('chked');

	   checkboxes.forEach((checkbox) => {checkbox.checked = aaa.checked;})
	   console.log(checkboxes);
}

function ch_del_bbs(){
	var ccc = confirm("체크된 상품을 판매중지상태로 바꾸시겠습니까?");
	if(ccc){
		return true;
	}else{
		return false;
	}
}

</script>

<%@ include file="/include/footer.jsp" %>