package bbs_control;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.BbsDAO;
import model.Bbs;

@WebServlet("/bbs/modify")
public class Bbs_modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Bbs_modify() {
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
		
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
	
		
		
		BbsDAO dao = new BbsDAO();
		Bbs view = dao.viewBbs(id,uid,code);
		
		request.setAttribute("view", view);
		RequestDispatcher dis = request.getRequestDispatcher("modify.jsp");
		dis.forward(request, response);
	}

}
