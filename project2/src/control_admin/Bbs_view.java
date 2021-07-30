package control_admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import model.Bbs;
import model.Comment;

@WebServlet("/admin/bbs/view_admin")
public class Bbs_view extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public Bbs_view() {
        super();
    }
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			String id= request.getParameter("id");
			int uid = Integer.parseInt(request.getParameter("uid"));
			String code = request.getParameter("code");

			String id_view = request.getParameter("id_view");
			String tb_table = request.getParameter("code");//게시판 이름
			String tb_name = request.getParameter("nickname");//추천하는 사람 닉?
			String tb_id = request.getParameter("tb_id");//추천하는 사람 아이디?
			int tb_uid = Integer.parseInt(request.getParameter("uid"));//추천이 갱신되는 게시글의 uid

			
			String search_view = request.getParameter("search_view");//검색어
			String field_view = request.getParameter("field_view");//검색조건(아이디,이름)			
			String search = request.getParameter("search");//검색어
			String field = request.getParameter("field");//검색조건(아이디,이름)
			
			if(search_view !=null && !search_view.equals("")) {		
				search_view = request.getParameter("search_view");//검색어
				field_view = request.getParameter("field_view");//검색조건(아이디,이름)
			}else {
				search_view = "";//검색어
				field_view = "";//검색조건(아이디,이름)			
			}	
			
			if(search !=null && !search.equals("")) {		
				search = request.getParameter("search");//검색어
				field = request.getParameter("field");//검색조건(아이디,이름)
			}else {
				search = "";//검색어
				field = "";//검색조건(아이디,이름)			
			}				
			
			//페이징 처리
			int pageSize = 10;//페이지당 출력 수	
			int pageNum=1;//현재 페이지
			int pageNum_view;//view에서 페이지
		
					
			
			
			
			if(request.getParameter("pageNum_view") !=null || request.getParameter("pageNum_view") !="") {
				pageNum_view = Integer.parseInt(request.getParameter("pageNum_view"));
			}else {
				pageNum_view=1;
			}

			if(request.getParameter("pageNum") !=null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}else {
				pageNum=1;
			}
//			System.out.println(tb_table);
//			System.out.println(code);
			
	
					
			
			BbsDAO dao = new BbsDAO();
			Bbs view = dao.viewBbs(id,uid,code);
			
			
			int count_v=0;//총 댓글 수				
			int number_view=0;//view페이지 넘버링 처리변수	 
			
			if(search_view !=null && !search_view.equals("")) {	//검색	
				count_v = dao.getAllCount_com(search_view,field_view,code,tb_uid);//총 댓글 수 설정
			}else {
				count_v = dao.getAllCount1_com(code,tb_uid);//총 댓글 수 설정 > 검색이 아닐 때
			}
						
			//limit값 설정
			int startNum = (pageNum_view - 1) * pageSize;
			int endNum = pageSize;
			
			//넘버링 설정
			number_view = count_v - (pageNum_view -1) * pageSize;	
			
			
			//댓글 출력
			ArrayList<Comment> com;
			if(search_view != null && !search_view.equals("")) {//검색일때
				com= dao.allComment1(startNum,endNum,search_view,field_view,code,tb_uid);
			}else {
				com= dao.allComment(startNum,endNum,code,tb_uid);
			}
			
			
			int r_count;
			
			r_count = dao.getRecoCount_Y(tb_table,tb_uid,tb_id);
//			System.out.println(r_count);

			request.setAttribute("count_v", count_v);	
			request.setAttribute("pageSize", pageSize);	
			request.setAttribute("pageNum_view", pageNum_view);		
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("number_view", number_view);
			request.setAttribute("r_count", r_count);	
			request.setAttribute("com", com);			
			request.setAttribute("view", view);
			request.setAttribute("search", search);
			request.setAttribute("field", field);
			request.setAttribute("search_view", search_view);
			request.setAttribute("field_view", field_view);
			//view 에 변수 넣어서 넘겨주기?? 일단 게시글 자체가 널로 인식되는거 보면 뭔 값을 못받은거같음
//			System.out.println(code);
//			System.out.println(uid);
//			System.out.println(tb_id);
//			System.out.println(pageNum_view);
//			System.out.println(search_view);
//			System.out.println(field_view);
			
			
			
			
			RequestDispatcher dis = request.getRequestDispatcher("/admin/bbs/view_admin.jsp?code="+code+"&uid="+uid+"&tb_id="+id+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"");
			dis.forward(request, response);	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}	

}
