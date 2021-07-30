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
import model.Comment;

@WebServlet("/bbs/reply")
public class Bbs_reply extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Bbs_reply() {
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
		int o_uid= Integer.parseInt(request.getParameter("o_uid"));
		String o_id=request.getParameter("o_id");
		String code=request.getParameter("code");
		String id= request.getParameter("id");
		String page_now = request.getParameter("page_now");
		
		try {
			

//			System.out.println(o_id);
			BbsDAO dao = new BbsDAO();
			Bbs rep = dao.replyCont(o_uid,code);
			request.setAttribute("o_id", o_id);
			request.setAttribute("rep", rep);
			RequestDispatcher dis = request.getRequestDispatcher("reply.jsp");
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
