<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<%@ include file="/include/header.jsp" %>

<%@include file="/bbs/bbs_title.jsp"%>

<c:choose>
	<c:when test="${view.gongji eq '3'}">
		<c:choose>
			<c:when test="${view.id ne sessionScope.id && sessionScope.level ne '5'}">
				<script>
					alert("비밀글은 작성자, 관리자만 열람가능합니다.");
					history.back();
				</script>
			</c:when>
		</c:choose>
	</c:when>
</c:choose>
<div class="view_section">

	<div style="clear:both; display:flex; width:100%; ">
		<div style="margin:0 auto; height:90px; font-size:40px;">
			<%=code_title%> 글 쓰기
		</div>
	</div>
	<div style="display:flex;">
		<div style="width:100%; margin:0 auto; margin-bottom:10px;">
			<div style="margin:0 auto; width:800px;">
				<div style="display:inherit; width:800px;">
					글 유형 : 
					<c:choose>
						<c:when test="${view.gongji eq '1'}">
							공지
						</c:when>
						<c:when test="${view.gongji eq '2'}">
							일반
						</c:when>
						<c:when test="${view.gongji eq '3'}">
							비밀
						</c:when>
					</c:choose>
					<div style="float:right;">
						조회수 : ${view.ref }
					</div>
				</div>
				<div>
			<c:set var="a" value="${view.thread.length()}"/>				
			<c:choose>
				<c:when test="${a > 1}">
					<c:choose>
						<c:when test="${view.gongji eq '3'}">
						작성자 : 관리자	
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>					
				</c:when>
				<c:otherwise>
					작성자 : ${view.nickname }(${view.id })
				</c:otherwise>			
			</c:choose>		
					
					
				</div>
				<div style="float:right;">
					<c:choose>
						<c:when test="${view.file1_o eq null || view.file1_o eq ''}">
						</c:when>
						<c:otherwise>
							첨부 :<a href="/bbs/upload/${view.file1_o }" download>${view.file1_o }</a><br>				
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${view.file2_o eq null || view.file2_o eq ''}">
						</c:when>
						<c:otherwise>
							첨부 :<a href="/bbs/upload/${view.file2_o }" download>${view.file2_o }</a>					
						</c:otherwise>
					</c:choose>
				</div>
				<div>
					제목 : ${view.subject}
				</div>
	
				<div style="margin:0 auto; width:800px; border-bottom:1px solid gray; ">
					내용 : <br> 
					
					<div>
					<div  style="margin:0 auto; width:700px; display:flex; justify-content:space-between; flex-direction:column;">
					<!-- 여기부터 이미지 없을시 엑박 안뜨게 -->
						<c:choose>
							<c:when test="${view.file1_o eq null || view.file1_o eq ''}">
							</c:when>
							<c:otherwise>
								<div style="margin:0 auto;">
									<img src="/bbs/upload/${view.file1_o}" style="max-width:600px;">
									<br><span onclick="origin_img(1)" style="cursor:pointer;" class="jbtn">원본보기</span>
								</div>
							</c:otherwise>
						</c:choose>	
						<c:choose>
							<c:when test="${view.file2_o eq null || view.file2_o eq ''}">
							</c:when>
							<c:otherwise>
								<div style="margin:0 auto; clear:both;">			
									<img src="/bbs/upload/${view.file2_o}" style="max-width:600px; max-height:400px;">
									<br><span onclick="origin_img(2)" style="cursor:pointer;" class="jbtn">원본보기</span>
								</div>
							</c:otherwise>
						</c:choose>		
					<!-- 여기까지 이미지 없을시 엑박 안뜨게 -->		
					</div>
					</div>
					<br><br>	
					
					<%//개행처리 부분
					pageContext.setAttribute("nn","\n");
					%>				
					${fn:replace(cmt,nn,'<br>')}
					${fn:replace(view.comment, nn, "<br/>")}
				</div>
				
			<c:choose>
				<c:when test="${r_count != 0 }">
					<div style="width:800px; margin:0 auto; text-align:center;"><span>추천/비추천 하셨습니다.</span></div>				
				</c:when>
				<c:otherwise>
					<div style="display:flex; margin:0 auto; width:800px; border-bottom:1px solid gray; ">
						<div style="display:flex; margin:0 auto; width:250px; align-items: center; justify-content:space-evenly;">
							<form action="/reco.do" name="reco" method="post" style="margin:0 auto;" onsubmit="return recoy()">
								<input type="hidden" name="uid" value="${view.uid}">
								<input type="hidden" name="id" value="${view.id}">
								<input type="hidden" name="tb_id" value="${sessionScope.id}">
								<input type="hidden" name="nickname" value="${sessionScope.nickname}">
								<input type="hidden" name="tb_table" value="<%=code%>">
								<input type="hidden" name="pageNum" value="${pageNum}">
								<input type="hidden" name="pageNum_view" id="pageNum_view" value="1">
								<input type="hidden" name="tb_reco" value="Y">
								<button>추천▲</button>
									
							</form>
							<div style="height:33px; border-left:1px solid #265543;"></div>	
							<form action="/reco.do" name="reco" method="post" style="margin:0 auto;" onsubmit="return recon()">
								<input type="hidden" name="uid" value="${view.uid}">
								<input type="hidden" name="id" value="${view.id}">
								<input type="hidden" name="tb_id" value="${sessionScope.id}">
								<input type="hidden" name="nickname" value="${sessionScope.nickname}">
								<input type="hidden" name="tb_table" value="<%=code%>">
								<input type="hidden" name="tb_reco" value="N">
								<input type="hidden" name="pageNum" value="${pageNum}">
								<input type="hidden" name="pageNum_view" id="pageNum_view" value="1">
								<button>비추천▼</button>
							</form>
						</div>
						
					</div>	
				</c:otherwise>
			</c:choose>				
				
				<div style="margin:0 auto; width:800px; display:flex; justify-content:space-between;">
					<div><a href="list?code=<%=code%>&pageNum=${pageNum}&field=${field}&search=${search}">[목록]</a></div>
					<div style="display:flex;">
						<c:choose>
							<c:when test="${sessionScope.id ne view.id && !empty sessionScope.id}">
<!-- 							작성자id 와 세션아이디가 다를 때 -->
								<c:choose>
									<c:when test="${param.code eq 'notice' }">
<!-- 									공지게시판일때 -->
										<c:choose>
											<c:when test="${sessionScope.level eq '5'}">
<!-- 											세션레벨이 5일때 (관리자일때) -->
												<c:choose>
													<c:when test="${view.gongji eq '1'}">
<!-- 													공지글일때 -->
														<div><a href="modify?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[수정]</a></div>
														<div><a href="/bbs_del?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[삭제]</a></div>														
													</c:when>
													<c:otherwise>
														<div><a href="reply?code=<%=code%>&o_uid=${view.uid}&o_id=${view.id}&pageNum=${pageNum}">[답글]</a></div>
														<div><a href="modify?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[수정]</a></div>
														<div><a href="/bbs_del?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[삭제]</a></div>															
													</c:otherwise>
												</c:choose>		
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${view.gongji eq '1'}">
	<!-- 													공지글일때 -->													
													</c:when>
												
													<c:otherwise>
														<div><a href="reply?code=<%=code%>&o_uid=${view.uid}&o_id=${view.id}&pageNum=${pageNum}">[답글]</a></div>													
													</c:otherwise>	
												</c:choose>																						
											</c:otherwise>
										</c:choose>	
										
										
									</c:when>
									<c:otherwise>
<!-- 									공지게시판이아닐때 -->
										<c:choose>
											<c:when test="${sessionScope.level eq '5'}">
<!-- 											세션레벨이 5일때 (관리자일때) -->
												<c:choose>
													<c:when test="${view.gongji eq '1'}">
<!-- 													공지글일때 -->
														<div><a href="modify?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[수정]</a></div>
														<div><a href="/bbs_del?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[삭제]</a></div>														
													</c:when>
													<c:otherwise>
														<div><a href="reply?code=<%=code%>&o_uid=${view.uid}&o_id=${view.id}&pageNum=${pageNum}">[답글]</a></div>
														<div><a href="modify?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[수정]</a></div>
														<div><a href="/bbs_del?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[삭제]</a></div>															
													</c:otherwise>
												</c:choose>		
											</c:when>
											<c:otherwise>
<!-- 											관리자가 아닐때 -->
												<c:choose>
													<c:when test="${view.gongji eq '1'}">
<!-- 													공지글일때 -->													
													</c:when>
													<c:otherwise>
														<div><a href="reply?code=<%=code%>&o_uid=${view.uid}&o_id=${view.id}&pageNum=${pageNum}">[답글]</a></div>													
													</c:otherwise>	
												</c:choose>																						
											</c:otherwise>
										</c:choose>										
									</c:otherwise>
								</c:choose>	
							</c:when>	
							<c:when test="${sessionScope.id eq view.id && !empty sessionScope.id}">
<!-- 							작성자id 와 세션아이디가 같을 때 -->
								<c:choose>
									<c:when test="${param.code eq 'notice' }">
<!-- 									공지게시판일때 -->
										<c:choose>
											<c:when test="${sessionScope.level eq '5'}">
<!-- 											세션레벨이 5일때 (관리자일때) -->
												<c:choose>
													<c:when test="${view.gongji eq '1'}">
<!-- 													공지글일때 -->
														<div><a href="modify?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[수정]</a></div>
														<div><a href="/bbs_del?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[삭제]</a></div>														
													</c:when>
													<c:otherwise>
														<div><a href="modify?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[수정]</a></div>
														<div><a href="/bbs_del?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[삭제]</a></div>															
													</c:otherwise>
												</c:choose>		
											</c:when>
											<c:otherwise>
<!-- 											관리자 아닐때 -->
												<c:choose>
													<c:when test="${view.gongji eq '1'}">
<!-- 												공지글일때 -->													
													</c:when>	
													<c:otherwise>
<!-- 												작성자와 세션아이디가 같고/관리자 아니고/공지글이 아닐때 -->
														<div><a href="modify?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[수정]</a></div>
														<div><a href="/bbs_del?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[삭제]</a></div>													
													</c:otherwise>		
												</c:choose>																				
											</c:otherwise>
										</c:choose>	
									</c:when>
									<c:otherwise>
<!-- 									공지게시판이아닐때 -->
										<c:choose>
											<c:when test="${sessionScope.level eq '5'}">
<!-- 											세션레벨이 5일때 (관리자일때) -->
												<c:choose>
													<c:when test="${view.gongji eq '1'}">
<!-- 													공지글일때 -->
														<div><a href="modify?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[수정]</a></div>
														<div><a href="/bbs_del?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[삭제]</a></div>														
													</c:when>
													<c:otherwise>
														<div><a href="reply?code=<%=code%>&o_uid=${view.uid}&o_id=${view.id}&pageNum=${pageNum}">[답글]</a></div>
														<div><a href="modify?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[수정]</a></div>
														<div><a href="/bbs_del?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[삭제]</a></div>															
													</c:otherwise>
												</c:choose>		
											</c:when>
											<c:otherwise>
<!-- 											관리자가 아닐때 -->
												<c:choose>
													<c:when test="${view.gongji eq '1'}">
	<!-- 													공지글일때 -->													
													</c:when>	
													<c:otherwise>
														<div><a href="modify?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[수정]</a></div>
														<div><a href="/bbs_del?code=<%=code%>&uid=${view.uid}&id=${view.id}&pageNum=${pageNum}">[삭제]</a></div>														
													</c:otherwise>
												</c:choose>																						
											</c:otherwise>
										</c:choose>										
									</c:otherwise>
								</c:choose>	
							</c:when>
							<c:when test="${empty sessionScope.id }">
							</c:when>
						</c:choose>
					</div>
				</div>
			</div>
		
			
			

			
			
<!-- 댓글부분		 -->
			<div style="width:800px; margin:0 auto;">
				<form name="view_comment" action="/bbs_view_comment" method="post">
					<input type="hidden" name="uid" value="${view.uid }">
					<input type="hidden" name="id" value="${view.id}">
					<input type="hidden" name="tb_id" value="${sessionScope.id}">
					<input type="hidden" name="s_id" value="${sessionScope.id}">
					<input type="hidden" name="tb_nickname" value="${sessionScope.nickname}">
					<input type="hidden" name="code" value="${param.code}">
					<input type="hidden" name="pageNum" value="${pageNum }">
					<input type="hidden" name="pageNum_view" id="pageNum_view" value="1">
				<div>
					<div style="padding-top:10px;">댓글</div>
					<div>
						<input name="tb_comment" id="tb_comment" class="view_comment_input">
						<button>댓글작성</button>
					</div>
				</div>
				</form>
			</div>
			
			<div style="width:800px; margin:0 auto; display:flex; text-align:center; border-top:1px solid gray; border-bottom:1px solid gray;">
				<div style="width:130px; border-right:1px solid gray;">작성자</div>
				<div style="width:460px; border-right:1px solid gray;">내용</div>
				<div style="width:120px; border-right:1px solid gray;">작성일자</div>
				<div style="width:90px;">삭제/수정</div>
			</div>
<%-- 	pageNum_view=${param.pageNum_view}<br> --%>
<%-- 	pageSize=${pageSize }<br> --%>
<%-- 	number_view=${number_view }<br> --%>
<%-- 	count_v=${count_v }<br> --%>
			<c:set var="num" value="${number_view}"/>
			<c:forEach var="com" items="${com}">
			<div style="width:800px; margin:0 auto; display:flex;">
				<div style="width:130px; text-align:center;border-right:1px solid gray;">${com.tb_nickname }(${com.tb_id })</div>
				<div style="width:460px;border-right:1px solid gray;">
				
					<c:set var="bb" value="${com.thread.length()}"/>
					<c:choose>
						<c:when test="${bb > 1}">
							<c:forEach var="i" begin="2" end="${bb }">
								<span>&nbsp;&nbsp;</span>
							</c:forEach>
							<img src='/bbs/img/icon_reply.gif'>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>			
				
					${com.tb_comment }
				
				
					<div style="float:right;">
						<span onclick="return rep_comment(${com.uid})">[대댓]</span>
					</div>
				
				
				</div>
				<div style="width:120px;border-right:1px solid gray; text-align:center;">
					<c:choose>
						<c:when test="${fn:length(com.tb_date) gt 11}">
							<c:out value="${fn:substring(com.tb_date, 0, 10)}">...
							</c:out>
						</c:when>
						<c:otherwise>
							<c:out value="${com.tb_date}">
							</c:out>
						</c:otherwise>
					</c:choose>				
				</div>
					<c:choose>
						<c:when test="${sessionScope.id eq com.tb_id }">
							<c:choose>
								<c:when test="${sessionScope.id ne null}">
									<div style="width:90px; text-align:center;">
										<a href="/del_comment?code=${com.tb_table }&uid=${com.uid}&tb_uid=${com.tb_uid}&tb_id=${com.tb_id}&id=${view.id}&s_id=${sessionScope.id }&pageNum=${pageNum}">
											삭제
										</a>
 										<span onclick="mod_com(${com.uid})">수정
 										</span>
									</div>
								</c:when>
							</c:choose>
						</c:when>
						<c:otherwise>
								<div style="width:90px;"></div>
							</c:otherwise>
						</c:choose>
				</div>
				<div style="margin:0 auto; width:800px; border-bottom:1px solid gray; "></div>
			<c:set var="num" value="${num-1 }"/>	
			</c:forEach>
			
		</div>
	</div>
	
	
<!-- 페이징 처리 -->
<div>
	<div align=center>
		<c:if test="${count_v>0 }">
		<c:set var="pageCount" value="${count_v / pageSize + (count_v % pageSize == 0 ? 0 : 1) }" />
		<fmt:parseNumber var="pageCount" value="${pageCount }" integerOnly="true" />
		
		<!-- 2개의 변수 초기화 -->
		<c:set var="startPage" value="${1 }" />
		<c:set var="pageBlock" value="${3 }" />
		
		<!-- 다음 페이지 블럭이 존재 할 경우 startPage 값 변경 부분-->
		<c:if test="${pageNum_view > pageBlock }">
			<!-- 결과를 정수형으로 리턴 받아야 하기 대문에 fmt -->
			<fmt:parseNumber var="result" value="${pageNum_view / pageBlock - (pageNum_view % pageBlock == 0 ? 1:0) }" integerOnly="true"/>
			<c:set var="startPage" value="${result * pageBlock + 1 }" />
		</c:if>
		
		<!-- endPage 값 설정 부분 -->
		<c:set var="endPage" value="${startPage + pageBlock - 1 }" />
		<c:if test="${endPage > pageCount }">
			<c:set var="endPage" value="${pageCount }" />
		</c:if>		

		<!-- 이전 링크 -->
		<c:if test="${startPage > pageBlock }">
			<a href="view?code=<%=code %>&uid=${view.uid}&pageNum_view=${startPage - pageBlock }&id=${view.id }&tb_id=${sessionScope.id }&pageNum=${pageNum}&field_view=${field_view}&search_view=${search_view}">[이전] </a>
		</c:if>	

		<!-- 페이징 링크 -->
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:choose>
				<c:when test="${pageNum_view== i} ">					
					<a href="view?code=<%=code %>&uid=${view.uid}&pageNum_view=${i }&id=${view.id }&tb_id=${sessionScope.id }&pageNum=${pageNum}&field_view=${field_view}&search_view=${search_view}" style="font-weight:bold; color:red;">[${i }]</a>					
				</c:when>
				<c:otherwise>
					<a href="view?code=<%=code %>&uid=${view.uid}&pageNum_view=${i }&id=${view.id }&tb_id=${sessionScope.id }&pageNum=${pageNum}&field_view=${field_view}&search_view=${search_view}">[${i }] </a>
				</c:otherwise>
			</c:choose>
		</c:forEach>	
		<!-- 다음 링크 -->
		<c:if test="${endPage < pageCount }">
			<a href="view?code=<%=code %>&uid=${view.uid}&pageNum_view=${startPage + pageBlock }&id=${view.id }&tb_id=${sessionScope.id }&pageNum=${pageNum}&field_view=${field_view}&search_view=${search_view}">[다음] </a>
		</c:if>
		</c:if>
	</div>
</div>	
	
	
	
<form method="get">
<input type="hidden" name="code" id="code" value="<%=code%>">
<input type="hidden" name="uid" id="uid" value="${param.uid }">
<input type="hidden" name="id" id="id" value="${view.id }">
<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}">
<input type="hidden" name="pageNum_view" id="pageNum_view" value="1">
<input type="hidden" name="tb_id" id="id_view" value="${sessionScope.id}">
<div style="width:500px; margin:0 auto;border:none; text-align:center; margin-bottom:10px;">
	<div>
		<select name="field_view">
			<option value="tb_id" <c:if test="${field_view eq 'id' }">selected</c:if>>아이디</option>
			<option value="tb_nickname" <c:if test="${field_view eq 'nickname' }">selected</c:if>>닉네임</option>
			<option value="tb_comment" <c:if test="${field_view eq 'tb_comment' }">selected</c:if>>내용</option>			
		</select>
		<input type="text" name="search_view" class="search_input"value="${search_view}">
		<button>검색</button>
	</div>
</div>
</form>	
</div>



<script>
function mod_com(a){
	const uid=a;
	window.open("modify_com?uid="+uid,'댓글 수정',"width=570,height=350");
}
function rep_comment(aa){
	const uid=aa;
	window.open("/bbs/rep_comment?uid="+uid,'대댓',"width=570,height=350");	
}
function origin_img(i){
	var img = i;
	if(img==1){
		window.open('origin_img.jsp?code=<%=code%>&uid=${view.uid}&img=1&pic=${view.file1_o}','원본','');
	}
	if(img==2){
		window.open('origin_img.jsp?code=<%=code%>&uid=${view.uid}&img=2&pic=${view.file1_o}','원본','');
	}
	
}
function recoy(){
	if(${r_count == 0 }){
		return true;
	}else{
		alert("zzz");
		return false;		
	}
}

function recon(){
	if(${r_count == 0 }){
		return true;
	}else{
		alert("z");
		return false;	
	}
}
</script>


<%@ include file="/include/footer.jsp" %>