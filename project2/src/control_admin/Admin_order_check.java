package control_admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BuyDAO;

@WebServlet("/admin/order_list/admin_check")
public class Admin_order_check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Admin_order_check() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String list_name=request.getParameter("list_name");
		
		BuyDAO dao = new BuyDAO();
		dao.ok_pay_cart(list_name);
		
		dao.ok_pay_buy_list(list_name);
		
		response.sendRedirect("/admin/order_list/order_list");
	}
}
