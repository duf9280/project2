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

@WebServlet("/admin/order_list/order_list")
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
		
		BuyDAO dao = new BuyDAO();
		int count=0;
		ArrayList<Buy_list> a = dao.allOrder_list_admin();//목록
		count = dao.getAllCount_buylist_admin();//상품수			
		
		
		request.setAttribute("a", a);
		request.setAttribute("count", count);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/order_list/order_list.jsp");//
		dis.forward(request, response);
	}
}
