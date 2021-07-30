package control_admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_adminDAO;

@WebServlet("/member_recover")
public class Member_recovery extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Member_recovery() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		Member_adminDAO dao = new Member_adminDAO();
	
		dao.recoveryMember(id);
		
		
		response.sendRedirect("/admin/member/list");
	}
}
