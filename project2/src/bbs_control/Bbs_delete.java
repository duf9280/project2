package bbs_control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;

@WebServlet("/bbs_del")
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
		
		BbsDAO dao = new BbsDAO();
		dao.deleteBbs(code, id, uid);
		
		response.sendRedirect("/bbs/list?code="+code);
	}	
}
