package bbs_control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import model.Comment;

@WebServlet("/bbs/modify_com")
public class Bbs_modify_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Bbs_modify_comment() {
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
		
		int uid=Integer.parseInt(request.getParameter("uid"));
		
		
		int pageNum = 1;//현재 페이지
		
		if(request.getParameter("pageNum") !=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}else {
			pageNum=1;
		}
		response.setContentType("text/html; charset=utf-8");
		BbsDAO dao = new BbsDAO();
		Comment modc = dao.modifyComment(uid);
		
		request.setAttribute("modc", modc);
		
		RequestDispatcher dis = request.getRequestDispatcher("modify_com.jsp");
		dis.forward(request, response);	
	}
	

}
