<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
//////////////////////////////////
/////////////	이미지 게시판////////
//////////////////////////////////
	//모든 게시글 수 - 검색 ////////////1=진행중 이벤트 2=종료된 이벤트
	public int getAllCount_img1(String search, String field, String code) {
		d.getCon();
		int num = 0;
		try {
			String sql = "select count(*) from "+code+" where gongji !='2' and "+field+" like ?";
			
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미

			}
//			System.out.println("dao = "+num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				d.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return num;
	}
	
	//모든 게시글 수 진행중 이벤트- 검색이 아닐 때
	public int getAllCount_img2(String code){
		d.getCon();
		int num = 0;

		try {
			String sql = "select count(*) from "+code+" where gongji!='2'";
			pstmt = d.conn.prepareStatement(sql);
					
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				d.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return num;
	}	
	//종료된 이벤트 검색시
	public int getAllCount_img_done1(String search, String field, String code) {
		d.getCon();
		int num_done = 0;
		try {
			String sql = "select count(*) from "+code+" where gongji='1' and "+field+" like ?";
			

			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num_done=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미
			}
//			System.out.println(num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				d.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return num_done;
	}
	
	//종료된 이벤트 검색이 아닐 때
	public int getAllCount_img_done2(String code){
		d.getCon();
		int num_done = 0;

		try {
						 

			String sql = "select count(*) from "+code+" where gongji='1'";
		

			
			pstmt = d.conn.prepareStatement(sql);
					
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num_done=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				d.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return num_done;
	}	
 
 ///////////////////////////////////////////////////////////////////////////////////////
 
 function selectAll(aaa)  {
   const checkboxes = document.getElementsByName('chck');
     
   checkboxes.forEach((checkbox) => {checkbox.checked = aaa.checked;})
   console.log(checkboxes);
}


 
 
 
 
 
 
 /////////////////////////
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>
<%@include file="/bbs/bbs_title.jsp"%>

<section>
	<div style="clear:both; display:flex; width:100%; ">
		<div style="margin:0 auto;">
			<%=code_title %>
		</div>
	</div>
	<div style="display:flex;">
<!-- 			이부분이 게시글 뽑는부분 el로 쓴 부분은 그냥 자리만-->


		<div style="display:flex;">
<!-- 			게시글 1번째 층 div열고 -->
			<c:set var="num" value="${number}"/>
			<c:set var="k" value=1/>
			<c:forEach var="m" items="${v }">	
				<div>
					<div>${m.file1_o}</div>
					<div>
						${m.subject}<br>
						${m.price1 }					
					</div>

	<!-- -->		<div>
						${m.id }
					</div>
				</div>
<!-- 	4번출력후 5번째 div만들때 층 나누기 위해서 choose사용해서 닫고 새로열기 -->
<c:choose>
	<c:when test="${k==5}">
		</div>
		<div style="display:flex;">
	</c:when>
</c:choose>	

			<c:set var="k" value="${k+1}"/>	
			<c:set var="num" value="${num-1 }"/>
			</c:forEach>
		</div>	

<!-- 			여기까지가 게시글 뽑는부분 -->


		<div style="display:flex; justify-content:space-between;">
<!-- 			목록, 글쓰기버튼 -->
			<div><a href="">[목록]</a></div>
			<div><a href="">[글쓰기]</a></div>			
		</div>			
	</div>	
	<div>
<!-- 		검색+페이징처리하는곳 -->
	</div>
</div>	

</section>

<%@ include file="/include/footer.jsp" %>