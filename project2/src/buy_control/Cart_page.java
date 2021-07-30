package buy_control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BuyDAO;
import model.Cart;

@WebServlet("/buy/cart_page")
public class Cart_page extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Cart_page() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String cart_name=request.getParameter("cn");
//		System.out.println(cart_name);
		if(id.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인후 이용 가능ㅎ바니다.');");
			out.println("history.back();");
			out.println("</script>");
		}else{
		
			
			int count=0;//총 게시물 수
			
			
			//카트에 있는 상품 수, 목록
			BuyDAO dao = new BuyDAO();
			count = dao.getAllCount_cart2(id,cart_name);//상품수		
//			System.out.println(count);
			
			ArrayList<Cart> v = dao.allCart2(id,cart_name);//목록
	
			request.setAttribute("v", v);
			request.setAttribute("count", count);
			
			RequestDispatcher dis = request.getRequestDispatcher("cart.jsp?id="+id+"");//
			dis.forward(request, response);
		}
	}
}
