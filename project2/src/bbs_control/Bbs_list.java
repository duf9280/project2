package bbs_control;

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

@WebServlet("/bbs/list")
public class Bbs_list extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Bbs_list() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String code = request.getParameter("code");
		String search = request.getParameter("search");//검색어
		String field = request.getParameter("field");//검색조건(아이디,이름)
		
		if(search !=null && !search.equals("")) {		
			search = request.getParameter("search");//검색어
			field = request.getParameter("field");//검색조건(아이디,이름)
		}else {
			search = "";//검색어
			field = "";//검색조건(아이디,이름)			
		}	
		
		//페이징 처리
		int pageSize = 15;//페이지당 출력 수
		int pageNum = 1;//현재 페이지
		
		
		if(request.getParameter("pageNum") !=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int count=0;//총 게시물 수
		int count_gong=0;//공지 게시물 수	
		int c_count=0;//각 게시글별 총 댓글 수
	
		
		int number=0;//페이지 넘버링 처리변수
		int number1=0;//페이지 넘버링 처리변수
		
		BbsDAO dao = new BbsDAO();
		if(search !=null && !search.equals("")) {		
			count = dao.getAllCount(search,field,code);//총 게시물 수 설정
			count_gong = dao.getAllCount_gong1(search,field,code);
		}else {
			count = dao.getAllCount1(code);//총 게시물 수 설정 > 검색이 아닐 때
			count_gong = dao.getAllCount_gong(code);
		}
		
//		System.out.println(count);
//		System.out.println(count_gong);
//		c_count= dao_c.getAllComment(code,);
		
		//limit값 설정
		int startNum = (pageNum - 1) * pageSize;
		int endNum = pageSize;
		

		//넘버링 설정
		number = count - (pageNum -1) * pageSize;
		number1 =count_gong - (pageNum -1) * pageSize;
//		System.out.println(count);
//		System.out.println("aaa");

		ArrayList<Bbs> gong;
		if(search != null && !search.equals("")) {
			gong = dao.allBbs3(startNum,endNum,search,field,code);
		}else {
			gong = dao.allBbs4(startNum,endNum,code);
		}
	
		ArrayList<Bbs> v;
		if(search != null && !search.equals("")) {
			v = dao.allBbs(startNum,endNum,search,field,code);
		}else {
			v = dao.allBbs2(startNum,endNum,code);
		}
		
		
		

		request.setAttribute("v", v);
		request.setAttribute("gong", gong);
		
		request.setAttribute("number", number);
		request.setAttribute("number1", number1);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("count_gong", count_gong);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("search", search);
		request.setAttribute("field", field);
		RequestDispatcher dis = request.getRequestDispatcher("/bbs/list.jsp?code="+code+"&pageNum="+pageNum+"&search="+search+"&field="+field+"");//
		
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
