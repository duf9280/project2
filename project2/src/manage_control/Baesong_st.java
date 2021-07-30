package manage_control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageDAO;
import model.Cart;

@WebServlet("/manage/order_list/baesong_st")
public class Baesong_st extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Baesong_st() {
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
		
		String cart_name=request.getParameter("cart_name");
		String buy_id=request.getParameter("buy_id");
		String subject=request.getParameter("subject");
		int price=Integer.parseInt(request.getParameter("price"));
		int baesong=Integer.parseInt(request.getParameter("baesong"));
		String baesong_status=request.getParameter("baesong_status");
		String id=request.getParameter("id");
		Cart c = new Cart();
		c.setBaesong(baesong);
		c.setPrice(price);
		c.setBuy_id(buy_id);
		c.setSubject(subject);
		c.setCart_name(cart_name);
		
		ManageDAO dao = new ManageDAO();
		dao.manageBaesong_st(c,baesong_status);
		response.sendRedirect("/manage/order_list/order_list?id="+id+"");
	}
}
