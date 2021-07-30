package buy_control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BuyDAO;

@WebServlet("/delete_cart_checkbox")
public class Cart_delete_check extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Cart_delete_check() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buy_id=request.getParameter("buy_id");
		String[] check=request.getParameterValues("check");
		String cart_name=request.getParameter("cart_name");
		if(check!=null){
			for(int i=0; i<check.length; i++){
				String checked_uid = check[i];
				
				BuyDAO dao = new BuyDAO();
				
				dao.deleteCart_check(buy_id, checked_uid);
			}
			response.sendRedirect("/buy/cart_page?id="+buy_id+"&cn="+cart_name+"");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('체크된 상품이 없습니다..');");
			out.println("history.back();");
			out.println("</script>");
		}
		
	}
}
