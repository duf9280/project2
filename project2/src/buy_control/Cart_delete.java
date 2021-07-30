package buy_control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BuyDAO;


@WebServlet("/delete_cart")
public class Cart_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cart_delete() {
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
		String cart_name= request.getParameter("cn");
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		BuyDAO dao = new BuyDAO();
		dao.deleteCart(id, uid);
		
		response.sendRedirect("/buy/cart_page?id="+id+"&cn="+cart_name+"");
	}
}
