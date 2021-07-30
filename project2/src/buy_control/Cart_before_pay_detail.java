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
import model.Buy_list;
import model.Cart;

@WebServlet("/buy/before_pay_detail")
public class Cart_before_pay_detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cart_before_pay_detail() {
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
		String cart_name=request.getParameter("cart_name");
		
		if(id.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�α����� �̿� ���ɤ��ٴϴ�.');");
			out.println("history.back();");
			out.println("</script>");
		}else{
		
			
			int count=0;//
			if(count==0) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('��ǰ�� ����.');");
				out.println("history.back();");
				out.println("</script>");
			}
			
			//īƮ�� �ִ� ��ǰ ��, ���
			BuyDAO dao = new BuyDAO();
			count = dao.getAllCount_buylist(id);//��ǰ��	
			
			ArrayList<Cart> v = dao.allCart2(id,cart_name);//���
			request.setAttribute("v", v);
			request.setAttribute("count", count);
			
			RequestDispatcher dis = request.getRequestDispatcher("before_pay_detail.jsp?id="+id+"");//
			dis.forward(request, response);	
		}
	}
}
