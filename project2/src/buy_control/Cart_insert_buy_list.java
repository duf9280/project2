package buy_control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BuyDAO;
import model.Buy_list;

@WebServlet("/insert_buy_list")
public class Cart_insert_buy_list extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Cart_insert_buy_list() {
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
		
		Date now = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		String signdate = s.format(now);
		
		
	
		SimpleDateFormat a = new SimpleDateFormat("yyyyMMddhhmmss");	
		String signdate_cname = a.format(now);
		
		String id = request.getParameter("buy_id");
		String cart_name=request.getParameter("cart_name");
		int total_price= Integer.parseInt(request.getParameter("total_price"));
		int total_bae= Integer.parseInt(request.getParameter("total_bae"));
		double total_point= Double.parseDouble(request.getParameter("total_point"));
		String b_zipcode= request.getParameter("b_zipcode");
		String b_zip1= request.getParameter("b_zip1");
		String b_zip2= request.getParameter("b_zip2");
		String b_zip3= request.getParameter("b_zip3");
		String baesong_msg = request.getParameter("baesong_msg");
		String pay_way=request.getParameter("pay_way");
		String a_name=request.getParameter("a_name");
		String b_name=request.getParameter("b_name");
		String b_tel=request.getParameter("b_tel");
		String account = request.getParameter("account");
		
		if(cart_name!=null){
			BuyDAO dao = new BuyDAO();

			Buy_list m = new Buy_list();
			m.setBuy_id(id);
			m.setA_name(a_name);
			m.setB_name(b_name);
			m.setB_tel(b_tel);
			m.setB_zip1(b_zip1);
			m.setB_zip2(b_zip2);
			m.setB_zip3(b_zip3);
			m.setB_zipcode(b_zipcode);
			m.setBaesong(total_bae);
			m.setList_name(cart_name);
			m.setPay_way(pay_way);
			m.setPrice(total_price);
			m.setSigndate(signdate);
			m.setAccount(account);
			
			dao.insert_buy_list_status(m);
			dao.update_cart_status(cart_name,baesong_msg);
			
			
			
			HttpSession session = request.getSession();
			session.setAttribute("cart_name", signdate_cname+id);
			
			response.sendRedirect("/");
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('상품이 없습니다..');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
		
		
	}
}
