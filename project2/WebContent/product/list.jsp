<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>


<style>
.item_box {
	width:200px;
}
.list_wrap {
	margin-left:20px;
	margin-bottom:20px;
}
.list_box {
	width:100%
	height:auto;
}
.product_list {
	display: grid;
	 grid-template-columns: repeat(5, 1fr);
}
.list {
	padding-left:40px;
	padding-bottom:20px;
}
ul,li {
	padding:0px;
	margin:0px;
}
</style>

<section>
	<div>상품목록</div>
	<a href="write.jsp">글쓰기</a>
	<div class="list_box">
		<div class="product_list">
			<c:forEach var="p" items="${p}">
				<div class="list">
					<img src="/memberSajang/upload/${m.file1_s}" style="width:200px;height:100px;">
					<ul>
						<li><b>제목</b></li>
						<li>${m.nickname}</li>
						<li>&#92;12000</li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- 페이징 처리 -->
	<div width=1000 align=center>
		<div>
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
						<a href="list?category=${category}&pageNum=${startPage - pageBlock }">[이전] </a>
					</c:if>
				
					<!-- 페이징 링크 -->
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:choose>
							<c:when test="${pageNum == i }">		
								<a href="list?category=${category}&pageNum=${i }">
								<font color="red"><b> [${i }]</b></font>
								</a>
									
							</c:when>
							<c:otherwise>
								<a href="list?category=${category}&pageNum=${i }">[${i }] </a>
							</c:otherwise>
						</c:choose>		
					</c:forEach>
				
					<!-- 다음 링크 -->
					<c:if test="${endPage < pageCount }">
						<a href="list?category=${category}&pageNum=${startPage + pageBlock }">[다음] </a>
					</c:if>
				</c:if>
			</div>
		</div>
	</div>
	<form method="get">
	<input type="hidden" name="pageNum" value="${pageNum}">
	<input type="hidden" name="category" value="${category}">
	<div width=1000 align=center>
		<div>
			<div align=center>
				<select name="field">
					<option value="subject">제목</option>
					<option value="name">이름</option>
				</select>
				<input name="search">
				<button>검색</button>
			</div>
		</div>
	</div>
	</form>
</section>


<%@ include file="/include/footer.jsp" %>