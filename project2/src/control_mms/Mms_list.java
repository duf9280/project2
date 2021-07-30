package control_mms;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MmsDAO;
import model.Mms;


@WebServlet("/mms/list")
public class Mms_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Mms_list() {
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
			String session_id = request.getParameter("session_id");
			String search = request.getParameter("search");//검색어
			String field = request.getParameter("field");//검색조건(아이디,이름)
			String type = request.getParameter("type");
			
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
		
			
			int number=0;//페이지 넘버링 처리변수
			int number1=0;//페이지 넘버링 처리변수
			
			MmsDAO dao = new MmsDAO();
			if(type.equals("send")) {
				if(search !=null && !search.equals("")) {//보낸 쪽지
					count = dao.getAllCount(search,field,session_id);//총 게시물 수 설정
				}else {
					count = dao.getAllCount(session_id);//총 게시물 수 설정 > 검색이 아닐 때
				}
			}else {
				if(search !=null && !search.equals("")) {//받은 쪽지
					count = dao.getAllCount2(search,field,session_id);//총 게시물 수 설정
				}else {
					count = dao.getAllCount2(session_id);//총 게시물 수 설정 > 검색이 아닐 때
				}
			}
		
			//limit값 설정
			int startNum = (pageNum - 1) * pageSize;
			int endNum = pageSize;
			

			//넘버링 설정
			number = count - (pageNum -1) * pageSize;
			ArrayList<Mms> v;
			if(type != null && type.equals("send")){	
				if(search != null && !search.equals("")) {//보낸쪽지
					v = dao.allMms(startNum,endNum,search,field,session_id);
				}else {
					v = dao.allMms2(startNum,endNum,session_id);
				}
			}else{	
				if(search != null && !search.equals("")) {//보낸쪽지
					v = dao.allMms3(startNum,endNum,search,field,session_id);
				}else {
					v = dao.allMms4(startNum,endNum,session_id);
				}
				
			}

			
			
			request.setAttribute("v", v);
			request.setAttribute("number", number);
			request.setAttribute("number1", number1);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("count", count);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("search", search);
			request.setAttribute("field", field);
			request.setAttribute("session_id", session_id);
			
			if(type != null && type.equals("send")) {
				RequestDispatcher dis = request.getRequestDispatcher("/mms/list.jsp?pageNum="+pageNum);
				dis.forward(request, response);
			}else {
				RequestDispatcher dis = request.getRequestDispatcher("/mms/list2.jsp?pageNum="+pageNum);
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