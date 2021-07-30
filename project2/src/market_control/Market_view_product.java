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
import dao.MarketDAO;
import model.Product;
import model.Review;

@WebServlet("/market/view_product")
public class Market_view_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Market_view_product() {
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
			String category=request.getParameter("category");
			String id= request.getParameter("id");
			int uid = Integer.parseInt(request.getParameter("uid"));
			String code = request.getParameter("code");
			String cat= request.getParameter("cat");
			String tb_table = request.getParameter("code");//게시판 이름
//			String tb_name = request.getParameter("nickname");// 찜하기 만들때 써야지
//			String tb_id = request.getParameter("tb_id");//
//			int tb_uid = Integer.parseInt(request.getParameter("uid"));//게시글의 uid

			
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
			
	
					
			
			MarketDAO dao = new MarketDAO();
			BbsDAO dao1 = new BbsDAO();
			Product view = dao.viewProduct(id,uid,code,category);
			
			
			int count_v=0;//총 댓글 수				
			int number_view=0;//view페이지 넘버링 처리변수	 
			
			
			
			///이건 지금 필요 없는데 나중에 뷰페이지에 리뷰띄울때 댓글 띄우는거 참고해서 하기위해 살려둠.
			if(search_view !=null && !search_view.equals("")) {	//검색	
				count_v = dao1.getAllCount_com(search_view,field_view,code,uid);//총 리뷰 수 설정
			}else {
				count_v = dao1.getAllCount1_com(code,uid);//총 리뷰 수 설정 > 검색이 아닐 때
			}
						
			//limit값 설정
			int startNum = (pageNum_view - 1) * pageSize;
			int endNum = pageSize;
			
			//넘버링 설정
			number_view = count_v - (pageNum_view -1) * pageSize;	
			
			
			//댓글 출력
			ArrayList<Review> rev;
			if(search_view != null && !search_view.equals("")) {//검색일때
				rev= dao1.allReview1(startNum,endNum,search_view,field_view,code,uid);
			}else {
				rev= dao1.allReview(startNum,endNum,code,uid);
			}
			
			
			
			//상점쪽 게시판은 추천은 없애고 리뷰할 때 별점같은 스코어를 받아서 리스트,뷰페이지에 출력예정
//			System.out.println(r_count);

			request.setAttribute("count_v", count_v);	
			request.setAttribute("pageSize", pageSize);	
			request.setAttribute("pageNum_view", pageNum_view);		
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("number_view", number_view);
			
			request.setAttribute("rev", rev);			
			request.setAttribute("view", view);
			request.setAttribute("search", search);
			request.setAttribute("field", field);
			request.setAttribute("search_view", search_view);
			request.setAttribute("field_view", field_view);
			
//			System.out.println(code);
//			System.out.println(uid);
//			System.out.println(tb_id);
//			System.out.println(pageNum_view);
//			System.out.println(search_view);
//			System.out.println(field_view);
			
			
			
			if(!cat.equals("2")) {
				RequestDispatcher dis = request.getRequestDispatcher("/market/view_product.jsp?cat=1&code="+code+"&category="+category+"&uid="+uid+"&tb_id="+id+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"");
				dis.forward(request, response);	
			}else {
				RequestDispatcher dis = request.getRequestDispatcher("/manage/bbs/view_product.jsp?cat="+cat+"code="+code+"&category="+category+"&uid="+uid+"&tb_id="+id+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"");
				dis.forward(request, response);	
			}
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
