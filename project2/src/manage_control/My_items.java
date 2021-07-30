package manage_control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import dao.ManageDAO;
import model.Lecture;
import model.Product;

@WebServlet("/manage/bbs/my_items")
public class My_items extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public My_items() {
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
			String session_id = request.getParameter("id");	
			String search = request.getParameter("search");//검색어
			String field = request.getParameter("field");//검색조건
//			System.out.println(code);
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
		
		ManageDAO dao = new ManageDAO();
		
		
		if(search !=null && !search.equals("")) {		
			count = dao.getAllCount_my_lect_prod1(search,field,code,category,session_id);//총 게시물 수 설정
		}else {
			count = dao.getAllCount_my_lect_prod2(code, category,session_id);//총 게시물 수 설정 > 검색이 아닐 때
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
				v = dao.allItem_lecture1(startNum,endNum,search,field,code,category,session_id);
			}else {
				v = dao.allItem_lecture2(startNum,endNum,code,category,session_id);
			}		
			request.setAttribute("v", v);
		}else if(code.equals("product")){
			ArrayList<Product> v;
			if(search != null && !search.equals("")) {
				v = dao.allItem_product1(startNum,endNum,search,field,code,category,session_id);
			}else {
				v = dao.allItem_product2(startNum,endNum,code,category,session_id);
			}		
			request.setAttribute("v", v);
		}

		
		

		
		
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("search", search);
		request.setAttribute("field", field);
		RequestDispatcher dis = request.getRequestDispatcher("my_items.jsp?id="+session_id+"code="+code+"&category="+category+"&pageNum="+pageNum+"&search="+search+"&field="+field+"");
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
