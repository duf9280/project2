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

@WebServlet("/bbs/rep_comment")
public class Bbs_reply_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Bbs_reply_comment() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		///대댓작성클릭했을때 팝업띄우는 서블릿?
		request.setCharacterEncoding("utf-8");
		
		String code=request.getParameter("code");
		String id= request.getParameter("id");
		int uid=Integer.parseInt(request.getParameter("uid"));
		
		
		int pageNum = 1;//현재 페이지
		
		if(request.getParameter("pageNum") !=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}else {
			pageNum=1;
		}
		
		
		response.setContentType("text/html; charset=utf-8");
		BbsDAO dao = new BbsDAO();
		Comment repc = dao.repComment(uid);
		
		request.setAttribute("repc", repc);
		
		RequestDispatcher dis = request.getRequestDispatcher("reply_comment.jsp");
		dis.forward(request, response);	
	}		
		
		
		
	
}
