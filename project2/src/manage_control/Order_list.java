package manage_control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BuyDAO;
import model.Cart;

@WebServlet("/manage/order_list/order_list")
public class Order_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Order_list() {
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
		
//		System.out.println(id);
		BuyDAO dao = new BuyDAO();
		int count=0;

		ArrayList<Cart> a = dao.allOrder_items_manage(id);
		count = dao.getAllCount_cart_manage(id);//»óÇ°¼ö				
	
		
		request.setAttribute("count", count);
		request.setAttribute("a", a);
		RequestDispatcher dis = request.getRequestDispatcher("/manage/order_list/order_list.jsp");
		dis.forward(request, response);			
	}
}
