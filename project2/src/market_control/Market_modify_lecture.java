package market_control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import model.Lecture;

@WebServlet("/market/lecture_modify.do")
public class Market_modify_lecture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Market_modify_lecture() {
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
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		int uid = Integer.parseInt(request.getParameter("uid"));
		String category=request.getParameter("category");
		String cat=request.getParameter("cat");
//		System.out.println(id);
//		System.out.println(uid);
//		System.out.println(category);
		
		
		BbsDAO dao = new BbsDAO();
		Lecture view = dao.viewLecture(id,uid,code,category);
		request.setAttribute("cat", cat);
		request.setAttribute("view", view);
		if(cat.equals("2")) {
			RequestDispatcher dis = request.getRequestDispatcher("/manage/bbs/modify_lecture.jsp");
			dis.forward(request, response);		
		}else {
			RequestDispatcher dis = request.getRequestDispatcher("modify_lecture.jsp");
			dis.forward(request, response);				
		}
	}
}
