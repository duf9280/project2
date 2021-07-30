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
import model.Member;


@WebServlet("/delete/member")
public class Member_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Member_delete() {
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
		
		Date now = new Date();
		String str1 = now.toString();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		String signdate = s.format(now);
		
		Member_adminDAO dao = new Member_adminDAO();
	
		dao.deleteMember_no(id,signdate);
		
		
		response.sendRedirect("/admin/member/list");
	}
}
