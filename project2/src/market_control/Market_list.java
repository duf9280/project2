package market_control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import model.Lecture;
import model.Product;

@WebServlet("/market/list")
public class Market_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Market_list() {
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
			String category = request.getParameter("category");
			String session_id = request.getParameter("session_id");	
			String search = request.getParameter("search");//검색어
			String field = request.getParameter("field");//검색조건
			
			if(search !=null && !search.equals("")) {		
				search = request.getParameter("search");//검색어
				field = request.getParameter("field");//검색조건
			}else {
				search = "";//검색어
				field = "";//검색조건			
			}	
			
		//페이징 처리
		int pageSize = 8;//페이지당 출력 수
		int pageNum = 1;//현재 페이지
		
		
		if(request.getParameter("pageNum") !=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int count=0;//총 게시물 수
	
		
		int number=0;//페이지 넘버링 처리변수
		
		BbsDAO dao = new BbsDAO();
		
		
		if(search !=null && !search.equals("")) {		
			count = dao.getAllCount_lect_prod1(search,field,code,category);//총 게시물 수 설정
		}else {
			count = dao.getAllCount_lect_prod2(code, category);//총 게시물 수 설정 > 검색이 아닐 때
		}

//		System.out.println(count);
		
		//limit값 설정
		int startNum = (pageNum - 1) * pageSize;
		int endNum = pageSize;
		

		//넘버링 설정
		number = count - (pageNum -1) * pageSize;
//		System.out.println(count);
//		System.out.println("aaa");
		
		
		
		
		
		
		if(code.equals("lecture")) {
			ArrayList<Lecture> v;
			if(search != null && !search.equals("")) {
				v = dao.allBbs_lecture1(startNum,endNum,search,field,code,category);
			}else {
				v = dao.allBbs_lecture2(startNum,endNum,code,category);
			}		
			request.setAttribute("v", v);
		}else if(code.equals("product")){
			ArrayList<Product> v;
			if(search != null && !search.equals("")) {
				v = dao.allBbs_product1(startNum,endNum,search,field,code,category);
			}else {
				v = dao.allBbs_product2(startNum,endNum,code,category);
			}		
			request.setAttribute("v", v);
		}

		
		

		
		
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("search", search);
		request.setAttribute("field", field);
		RequestDispatcher dis = request.getRequestDispatcher("/market/list.jsp?code="+code+"&category="+category+"&pageNum="+pageNum+"&search="+search+"&field="+field+"");
		//		
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
