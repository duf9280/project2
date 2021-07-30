<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/manage/include/header.jsp"%>
<%@include file="/bbs/bbs_title.jsp"%>

<section>
	<div style="display:flex; flex-direction: column; align-items:center;">
		<div>
			<%=code_title %>
		</div>
<!-- 			이부분이 게시글 뽑는부분 el로 쓴 부분은 그냥 자리만-->
		<div style="display:flex; width:850px;">
<!-- 			게시글 1번째 층 div열고 -->
			<c:set var="num" value="${number}"/>
			<c:set var="k" value="1"/>
			
			<c:forEach var="m" items="${v }">	
				<div style="text-align:center; width:25%;">
					<div style="display:flex; flex-direction: column;height:200px; width:200px; align-items: center;">
						<a href="/market/view_${param.code}?cat=${sessionScope.level}&code=<%=code%>&category=${param.category}&uid=${m.uid }&id=${m.id }&g=${param.g}&tb_id=${sessionScope.id}&pageNum_view=1&pageNum=${pageNum}&field_view=${field_view}&search_view=${search_view}">						
							<img src="/market/upload/${m.file1_o}" style="max-height:200px; max-width:150px;">
						</a>
					</div>
					<div>
						<a href="view_${param.code}?code=<%=code%>&category=${param.category }&uid=${m.uid }&id=${m.id }&g=${param.g}&tb_id=${sessionScope.id}&pageNum_view=1&pageNum=${pageNum}&field_view=${field_view}&search_view=${search_view}">					
							${m.subject}<br>
							<fmt:formatNumber value="${m.price1 }" pattern="###,###"/>원
						</a>
											
						</div>
	<!-- -->		<div>
						${m.id }
					</div>
				</div>
<!-- 	4번출력후 5번째 div만들때 층 나누기 위해서 닫고 새로열기 -->
			<c:if test="${k==4}">
				</div>
				<div style="display:flex; width:850px; margin-top:20px;">
			</c:if>
			<c:set var="num" value="${num-1 }"/>
			<c:set var="k" value="${k+1}"/>	
			</c:forEach>
		</div>	

<!-- 			여기까지가 게시글 뽑는부분 -->

				
	</div>
	<div style="display:flex; justify-content:space-between; margin:0 auto; width:850px;">
<!-- 			목록, 글쓰기버튼 -->
		<div><a href="/manage/bbs/my_items?code=<%=code%>&id=${sessionScope.id }&category=${param.category}&pageNum=${pageNum}">[목록]</a></div>
	</div>		
	<div>
<!-- 		검색+페이징처리하는곳 -->

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
			<a href="/manage/bbs/my_items?id=${sessionScope.id }&code=<%=code %>&pageNum=${startPage - pageBlock }&category=${param.category}">[이전] </a>
		</c:if>	

		<!-- 페이징 링크 -->
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum== i} ">					
					<a href="/manage/bbs/my_items?id=${sessionScope.id }&code=<%=code %>&pageNum=${i }&category=${param.category}" style="font-weight:bold; color:red;">[${i }]</a>					
				</c:when>
				<c:otherwise>
					<a href="/manage/bbs/my_items?id=${sessionScope.id }&code=<%=code %>&pageNum=${i }&category=${param.category}">[${i }] </a>
				</c:otherwise>
			</c:choose>
		</c:forEach>	
		<!-- 다음 링크 -->
		<c:if test="${endPage < pageCount }">
			<a href="/manage/bbs/my_items?id=${sessionScope.id }&code=<%=code %>&pageNum=${startPage + pageBlock }&category=${param.category}">[다음] </a>
		</c:if>
		</c:if>
		
<!-- 	검색처리	 -->
		<form method="get">
		<input type="hidden" name="code" id="code" value="<%=code%>">
		
		<div style="width:500px; margin:0 auto;border:none; text-align:center;">
			<div>
				<select name="field">
					<option value="id" <c:if test="${field eq 'id' }">selected</c:if>>아이디</option>
					<option value="subject" <c:if test="${field eq 'subject' }">selected</c:if>>제목</option>
					<option value="comment" <c:if test="${field eq 'comment' }">selected</c:if>>내용</option>			
				</select>
				<input type="text" name="search" class="search_input"value="${search}">
				<button>검색</button>
			</div>
		</div>
		</form>	
<!-- 		검색처리 끝 -->
		
			
	</div>
</div>

	</div>

</section>

<%@ include file="/include/footer.jsp" %>