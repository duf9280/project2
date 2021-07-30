package control_admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BuyDAO;
import model.Buy_list;
import model.Cart;

@WebServlet("/admin/order_list/order_detail")
public class Order_list_detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Order_list_detail() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		String cart_name=request.getParameter("list");
//		System.out.println(cart_name);
		

		BuyDAO dao = new BuyDAO();
		Buy_list a = dao.detail_buy_list(id,cart_name);
		ArrayList<Cart> c = dao.allOrder_items(id,cart_name);
		
		request.setAttribute("c", c);
		request.setAttribute("a", a);
		RequestDispatcher dis = request.getRequestDispatcher("order_list_detail.jsp?id="+id+"&list="+cart_name+"");
		dis.forward(request, response);			
	}
}
