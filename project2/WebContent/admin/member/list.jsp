<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/include/header1.jsp" %>


<section>
	<div style="margin:0 auto; width:1020px; height:700px; display:flex; flex-direction:column; justify-content: center">
		<div>회원정보
			
		</div>
		<div>total : ${count}</div>
		<div>
			<div style="display:flex; width:1040px;">
				<div style="width:30px;text-align:center;"><input type="checkbox" name="check" onclick="selectAll(this)"></div>
				<div class="member_list" style="width:60px;">파일</div>
				<div class="member_list" style="width:120px;">아이디</div>
				<div class="member_list" style="width:120px;">비밀번호</div>
				<div class="member_list" style="width:80px;">이름</div>
				<div class="member_list" style="width:200px;">메일주소</div>
				<div class="member_list" style="width:50px;">성별</div>
				<div class="member_list" style="width:70px;">생년월일</div>
				<div class="member_list" style="width:50px;">레벨</div>
				<div style="width:120px;">탈퇴일자</div>
				<div style="width:80px;">탈퇴//복구</div>
			</div>
		</div>
		<form action="/delete_check/member" method="post" onsubmit="return check_del_mem()">
		<c:set var="num" value="${number}"></c:set>
		<c:forEach var="m" items="${v}">
			<div style="display:flex; width:1040px; height:60px; align-items:center;">
				<div  style="width:30px;text-align:center;"><input type="checkbox" name="check" id="check" value="${m.id}"></div>
			 	<div class="member_list2" style="width:60px;">
		 			<c:choose>
			 			<c:when test="${m.file1_s !=null && m.file1_s ne '' }">
				 			<img src="/member/upload/${m.file1_s}" style="width:30px;">
				 		</c:when>
			 			<c:otherwise>
				 			없음
				 		</c:otherwise>						 		
		 			</c:choose>
			 	</div>
				<div class="member_list2" style="width:120px;"><a href="view?id=${m.id}">${m.id}</a></div>
				<div class="member_list2" style="width:120px;">${m.pass }</div>
				<div class="member_list2" style="width:80px;">${m.names }</div>
				 	<c:choose>
				 		<c:when test="${m.mail1 eq '' && m.mail2 eq ''}">
				 			<div class="member_list2" style="width:200px;">없음</div>
				 		</c:when>
				 		<c:otherwise>
				 			<div class="member_list2" style="width:200px;">${m.mail1 }@${m.mail2 }</div>
				 		</c:otherwise>
				 	</c:choose>		
				 			
				
				
				<div class="member_list2" style="width:50px;">${m.gender }</div>
				<div class="member_list2" style="width:70px;">${m.ssn }</div>
				<div class="member_list2" style="width:50px;">${m.level }</div>
				
				
				<div style="width:120px;">
					<c:choose>
						<c:when test="${fn:length(m.quitdate) gt 11}">
							<c:out value="${fn:substring(m.quitdate, 0, 10)}">
								
							</c:out>
						</c:when>
						<c:otherwise>
							<c:out value="${m.quitdate}">
							</c:out>
						</c:otherwise>
					</c:choose>	
				</div>
				
				
				<div style="width:80px;"><a href="/delete/member?id=${m.id}">탈퇴</a>//<a href="/member_recover?id=${m.id}">복구</a></div>
				
			</div>
			<c:set var="num" value="${num-1}"></c:set>

		</c:forEach>
		<div style="display:flex; width:1040px;">
			
			<div><button>선택된 회원 삭제</button></div>
		</div>
		</form>
		<!-- 페이징 처리 -->
			<div align=center style="margin-bottom:15px;">
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
						<a href="list?mem=${mem}&pageNum=${startPage - pageBlock }">[이전] </a>
					</c:if>
				
					<!-- 페이징 링크 -->
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<c:choose>
							<c:when test="${pageNum == i }">		
								<a href="list?mem=${mem}&pageNum=${i }">
								<font color="red"><b> [${i }]</b></font>
								</a>
									
							</c:when>
							<c:otherwise>
								<a href="list?mem=${mem}&pageNum=${i }">[${i }] </a>
							</c:otherwise>
						</c:choose>		
					</c:forEach>
				
					<!-- 다음 링크 -->
					<c:if test="${endPage < pageCount }">
						<a href="list?mem=${mem}&pageNum=${startPage + pageBlock }">[다음] </a>
					</c:if>
				</c:if>
			</div>
		<form method="get">
		<input type="hidden" name="pageNum" value="${pageNum}">
		<input type="hidden" name="mem" value="${mem}">
			<div style="width:500px; margin:0 auto;border:none; text-align:center;">
				<div>
					<select name="field">
						<option value="id" <c:if test="${field eq 'id' }">selected</c:if>>아이디</option>
						<option value="nickname" <c:if test="${field eq 'nickname' }">selected</c:if>>닉네임</option>
					</select>
					<input type="text" name="search" class="search_input"value="${search}">
					<button>검색</button>
				</div>
			</div>
	</form>
	</div>
</section>
<script>
function selectAll(aaa)  {
	   const checkboxes = document.getElementsByName('check');
	     
	   checkboxes.forEach((checkbox) => {checkbox.checked = aaa.checked;})
	   console.log(checkboxes);
}

function check_del_mem(){
	var cc = confirm("체크된 회원을 삭제하시겠습니까?");
	if(cc){
		return true;
	}else{
		return false;
	}
}
</script>
<%@ include file="/include/footer.jsp" %>