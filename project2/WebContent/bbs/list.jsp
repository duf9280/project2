<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

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
	<div style="width:100%;">
	<div style="margin:0 auto; width:910px; display:flex; justify-content:space-between;">
		<div>총 게시물 : ${count}</div>
		<div>
			게시글 종류 선택 : 
			<label><input type="radio" class="lll" name="list_select" id="list_select" value="3" onclick="list_select(this.value,'com_list','gongji_list')" checked>[전체글]</label>
			<label><input type="radio" class="lll" name="list_select" id="list_select" value="1" onclick="list_select(this.value,'com_list')">[공지]</label>
			<label><input type="radio" class="lll" name="list_select" id="list_select" value="2" onclick="list_select(this.value,'gongji_list')">[일반글]</label>
		</div>	
	</div>
	<div style="margin:0 auto;text-align:center;">
		<div style="margin:0 auto; width:900px; border-top:1px solid gray; border-bottom:1px solid gray; display:flex; text-align:center;">
			<div style="float: left; width:60px; text-align:center; border-right:1px solid gray;">
				번호
			</div>
			<div style=" padding-left:10px;float:left; width:480px; border-right:1px solid gray;">
				제목
			</div>
			<div style="float:left; width:150px; border-right:1px solid gray;">
				작성자
			</div>
			<div style="float:left; width:150px; border-right:1px solid gray;">
				작성일자
			</div>
			<div style="float:left; width:60px;">
				조회수
			</div>
		</div>
	
<!-- 공지글 출력 -->
	<div id="gongji_list">
	<c:forEach var="g" items="${gong }">	
		<div style=" display:flex; border-bottom:1px solid gray; background:#e8f0fe; width:900px; margin:0 auto;">
			<div style="height:1px; border:none; background:#265543;"></div>		
			<div style="float: left; border-right:1px solid gray; width:60px;">공지</div>
			<div style="float:left; border-right:1px solid gray; width:480px; text-align:left; padding-left:10px;">
			
			<a href="view?code=<%=code%>&uid=${g.uid }&id=${g.id }&tb_id=${sessionScope.id}&pageNum_view=1&pageNum=${pageNum}&field_view=${field_view}&search_view=${search_view}">
				<c:choose>
					<c:when test="${fn:length(g.subject) gt 27}">
						<c:out value="${fn:substring(g.subject, 0, 26)}">
							
						</c:out>
						...
					</c:when>
					<c:otherwise>
						<c:out value="${g.subject}">
						
						</c:out>
					</c:otherwise>
				</c:choose>			
			</a></div>
		
		
			<div style="float:left; border-right:1px solid gray; width:150px;">
				<c:choose>
					<c:when test="${fn:length(g.nickname)+fn:length(g.id) gt 15}">
					<c:set var="nic" value="${g.nickname}(${g.id})}"></c:set>
						<c:out value="${fn:substring(nic, 0, 14)}">
						</c:out>
						...
					</c:when>
					<c:otherwise>
						<c:out value="${g.nickname}(${g.id})">
						</c:out>
					</c:otherwise>
				</c:choose>		
			
			
			</div>
			<div style="float:left; border-right:1px solid gray; width:150px;">
				<c:choose>
					<c:when test="${fn:length(g.signdate) gt 11}">
						<c:out value="${fn:substring(g.signdate, 0, 10) }">
						</c:out>
					</c:when>
					<c:otherwise>
						<c:out value="${g.signdate}">
						</c:out>
					</c:otherwise>
				</c:choose>
			</div>
			<div style="float:left; width:60px;">${g.ref }</div>
		</div>
	
	</c:forEach>
	</div>
<%-- 	pageNum=${pageNum}<br> --%>
<%-- 	pageSize=${pageSize }<br> --%>
<%-- 	number=${number }<br> --%>
<%-- 	count=${count }<br>		 --%>
<!-- 일반글 출력 -->		
	<div id="com_list">
	<c:set var="num" value="${number}"/>
	<c:forEach var="m" items="${v }">	
		<div style=" display:flex; border-bottom:1px solid gray; width:900px; margin:0 auto;">
			<div style="height:1px; border:none; background:#265543;"></div>		
			<div style=" border-right:1px solid gray;float: left; width:60px; text-align:center;">${num }</div>
			<div style=" border-right:1px solid gray;float:left; width:480px; text-align:left; padding-left:10px;">

			<c:set var="a" value="${m.thread.length()}"/>
			<c:choose>
				<c:when test="${a > 1}">
					<c:forEach var="i" begin="2" end="${a }">
						<span>&nbsp;&nbsp;</span>
					</c:forEach>
					<img src='/bbs/img/icon_reply.gif'>
					<c:choose>
						<c:when test="${m.gongji eq '3'}">
							<img src="/bbs/img/key.png">
						</c:when>
					</c:choose>					
				</c:when>
				<c:when test="${m.gongji eq '3'}">
					<img src="/bbs/img/key.png">
				</c:when>				
			</c:choose>

				<a href="view?code=<%=code%>&uid=${m.uid }&id=${m.id}&tb_id=${sessionScope.id}&pageNum_view=1&pageNum=${pageNum}&field_view=${field_view}&search_view=${search_view}">
			<c:choose>
				<c:when test="${m.gongji eq '3'}">
					<c:choose>
						<c:when test="${sessionScope.id eq m.id || sessionScope.level eq '5'}">
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
						</c:when>
						<c:otherwise>
						비밀글입니다.
						</c:otherwise>
					</c:choose>					
				</c:when>
				<c:when test="${m.gongji ne '3'}">
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
				</c:when>
			</c:choose>						
				</a> [${m.comment_num}]
				
			</div>
				
				
			<div style=" border-right:1px solid gray;float:left; width:150px;">
				<c:choose>
					<c:when test="${fn:length(m.nickname)+fn:length(m.id) gt 15}">
					<c:set var="nic" value="${m.nickname}(${m.id})}"></c:set>
						<c:out value="${fn:substring(nic, 0, 14)}">
						</c:out>
						...
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${a > 1}">
								<c:choose>
									<c:when test="${m.gongji eq '3' }">
										관리자
									</c:when>
									<c:otherwise>
										<c:out value="${m.nickname}(${m.id})">
										</c:out>
									</c:otherwise>									
								</c:choose>
								
							</c:when>
							<c:otherwise>
								<c:out value="${m.nickname}(${m.id})">
								</c:out>
							</c:otherwise>
						</c:choose>
						
					</c:otherwise>
				</c:choose>				
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
			<div style="float:left; width:60px;">${m.ref }</div>
		</div>
	
	<c:set var="num" value="${num-1 }"/>
	</c:forEach>
	</div>
	</div>
	</div>
	<div style="margin:0 auto; width:900px; display:flex; justify-content: space-between;">
		<div><a href="list?code=<%=code%>&pageNum=${pageNum}">[목록]</a></div>
	<c:choose>
		<c:when test="${param.code eq 'notice'}">
			<c:choose>
				<c:when test="${sessionScope.level eq '5' }">
					<div><a href="write.jsp?code=<%=code%>&pageNum=${pageNum}">[글쓰기]</a></div>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>

		
			
		</c:otherwise>
	</c:choose>
	</div>
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
			<a href="list?code=<%=code %>&pageNum=${startPage - pageBlock }">[이전] </a>
		</c:if>	

		<!-- 페이징 링크 -->
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum== i} ">					
					<a href="list?code=<%=code %>&pageNum=${i }" style="font-weight:bold; color:red;">[${i }]</a>					
				</c:when>
				<c:otherwise>
					<a href="list?code=<%=code %>&pageNum=${i }">[${i }] </a>
				</c:otherwise>
			</c:choose>
		</c:forEach>	
		<!-- 다음 링크 -->
		<c:if test="${endPage < pageCount }">
			<a href="list?code=<%=code %>&pageNum=${startPage + pageBlock }">[다음] </a>
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
//버튼 클릭시 해당종류의 게시글만 보이도록 처리
function list_select(v,id,id1){
	if(v == "1"){
		document.getElementById("com_list").style.display = "none"; // 숨김
	}else{
		document.getElementById("com_list").style.display = ""; // 보여줌
	}
	
	if(v == "2"){
		document.getElementById("gongji_list").style.display = "none"; // 숨김
	}else{
		document.getElementById("gongji_list").style.display = ""; // 보여줌
	}	
	
	if(v == "3"){
		document.getElementById("gongji_list").style.display = ""; // 보여줌
		document.getElementById("com_list").style.display = ""; // 보여줌
	}	
} 
</script>

<%@ include file="/include/footer.jsp" %>