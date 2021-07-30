package control_admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_adminDAO;
import model.Member;

@WebServlet("/admin/member/modify")
public class Member_modify extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Member_modify() {
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
		String mem = request.getParameter("mem");
		
		Member_adminDAO dao = new Member_adminDAO();

			
			Member m = dao.viewMember_no(id);
			
			request.setAttribute("m", m);
			request.setAttribute("mem", mem);
			RequestDispatcher dis = request.getRequestDispatcher("modify.jsp");
			dis.forward(request, response);

		

	}

}
