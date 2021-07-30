package buy_control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BuyDAO;
import model.Cart;

@WebServlet("/buy/submit_request")
public class Submit_request extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Submit_request() {
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
		String buy_id=request.getParameter("buy_id");
		String cart_name=request.getParameter("cart_name");
		String subject=request.getParameter("subject");
		String id=request.getParameter("id");
		String baesong_status=request.getParameter("baesong_status");
		int price=Integer.parseInt(request.getParameter("price"));
		int baesong=Integer.parseInt(request.getParameter("baesong"));
//		System.out.println(baesong_status);
		
		BuyDAO dao=new BuyDAO();
		dao.submit_request(buy_id,cart_name,subject,baesong_status,price,baesong);
		
		response.sendRedirect("order_detail?id="+id+"&list="+cart_name+"");
		
	}	
}
