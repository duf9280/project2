package control_admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_adminDAO;

@WebServlet("/admin/bbs/admin_del_market")
public class Market_del extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Market_del() {
        super();

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code=request.getParameter("code");
		String category=request.getParameter("category");
		String uid=request.getParameter("uid");
		
		Member_adminDAO dao = new Member_adminDAO();
		dao.deleteMarket(code,uid,category);
		
		response.sendRedirect("market_list?code="+code+"&category="+category+"");
	}
		
}
