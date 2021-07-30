package control_admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import dao.Member_adminDAO;

@WebServlet("/admin/bbs/delete_bbs_admin")
public class Bbs_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Bbs_delete() {
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
		String code = request.getParameter("code");
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		Member_adminDAO dao = new Member_adminDAO();
		dao.deleteBbs_admin(code, uid);
		
		response.sendRedirect("/admin/bbs/list?code="+code);
	}	
}
