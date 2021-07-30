package manage_control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageDAO;
import model.Cart;

@WebServlet("/manage/order_list/baesong.do")
public class Baesong extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Baesong() {
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
		String subject=request.getParameter("subject");
		int price=Integer.parseInt(request.getParameter("price"));
		String baesong_com=request.getParameter("baesong_com");
		String baesong_num=request.getParameter("baesong_num");
		String id=request.getParameter("id");
		
		Date now = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		String signdate = s.format(now);
		
		
		Cart c = new Cart();
		c.setBaesong_com(baesong_com);
		c.setBaesong_num(baesong_num);
		c.setBaesong_date(signdate);
		
		ManageDAO dao = new ManageDAO();
		
		dao.goBaesong(cart_name,subject,price,c);
		
		response.sendRedirect("/manage/order_list/order_list?id="+id+"");
	}
}
