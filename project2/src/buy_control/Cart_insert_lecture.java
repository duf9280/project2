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

import dao.BuyDAO;
import model.Buy_list;
import model.Cart;

@WebServlet("/buy/cart_insert")
public class Cart_insert_lecture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cart_insert_lecture() {
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
		String buy_id= request.getParameter("session_id");
		String sell_id= request.getParameter("sell_id");
		String code= request.getParameter("code");
		int tb_uid= Integer.parseInt(request.getParameter("uid"));
		String subject= request.getParameter("subject");
		
		String p_name1 = request.getParameter("p_name1");
		int price1= Integer.parseInt(request.getParameter("price1"));
		int number1= Integer.parseInt(request.getParameter("number1"));
		
		String account_name= request.getParameter("account_name");
		String account_num= request.getParameter("account_num");
		String category= request.getParameter("category");
		String file1_s= request.getParameter("file1_s");
		int pageNum= Integer.parseInt(request.getParameter("pageNum"));
		int pageNum_view= Integer.parseInt(request.getParameter("pageNum_view"));
		String field_view = request.getParameter("field_view");
		String search_view = request.getParameter("search_view");
		int buy_num1 = Integer.parseInt(request.getParameter("buy_num1"));
		String s_zipcode= request.getParameter("zipcode");
		String s_zip1= request.getParameter("zip1");
		String s_zip2= request.getParameter("zip2");
		String s_zip3= request.getParameter("zip3");
		
	
		

		String p_name2="";
		int price2= 0;
		int buy_num2= 0;
				
		if(request.getParameter("p_name2")!=null) {
			p_name2 = request.getParameter("p_name2");
			price2= Integer.parseInt(request.getParameter("price2"));
			if(request.getParameter("buy_num2")!=null) {
				buy_num2 =Integer.parseInt(request.getParameter("buy_num2"));
			}else {
				buy_num2= 0;
			}
		}		
		
		
//		System.out.println(cart_name);
		int number_a;
		//물품 가격이 일정치(baesong_free)를 넘었을때 배송비를 0으로
		int baesong=0;
		int a = price1 * buy_num1;
		int b = price2 * buy_num2;
		int baesong_free=Integer.parseInt(request.getParameter("baesong_free"));
		

		if(a >= baesong_free) {
			baesong=0;			
//			System.out.println(a);
//			System.out.println(baesong_free);
//			System.out.println(baesong);
			
		}else if(a+b >= baesong_free) {
			baesong=0;
		}else {
			baesong=Integer.parseInt(request.getParameter("baesong"));
		}
		
		
		
		Date now = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		String signdate = s.format(now);
		
		try {
			BuyDAO dao = new BuyDAO();	
			if(buy_num1 >=1 && price1 >=1) {			
				Cart c = new Cart();
				c.setBuy_id(buy_id);
				c.setSell_id(sell_id);
				c.setTb_uid(tb_uid);
				c.setTb_table(code);
				c.setCart_name(cart_name);
				c.setPrice(price1);
				c.setBaesong(baesong);
				c.setNumber(buy_num1);
				c.setFile1_s(file1_s);
				c.setSubject(subject+"(= "+p_name1+" =)");
				c.setSigndate(signdate);
				c.setCategory(category);
				c.setS_zipcode(s_zipcode);
				c.setS_zip1(s_zip1);
				c.setS_zip2(s_zip2);
				c.setS_zip3(s_zip3);
				
				dao.insertCart(c);
			}	
		
			if(buy_num2 >=1 && price2 >=1) {
				Cart c = new Cart();
				c.setBuy_id(buy_id);
				c.setSell_id(sell_id);
				c.setTb_uid(tb_uid);
				c.setTb_table(code);
				c.setCategory(category);
				c.setCart_name(cart_name);
				c.setPrice(price2);
				c.setBaesong(baesong);
				c.setNumber(buy_num2);
				c.setFile1_s(file1_s);
				c.setSubject(subject+"(= "+p_name2+" =)");
				c.setSigndate(signdate);	
				c.setS_zipcode(s_zipcode);
				c.setS_zip1(s_zip1);
				c.setS_zip2(s_zip2);
				c.setS_zip3(s_zip3);
				
				dao.insertCart(c);
				
				
			}
			if(buy_num1 ==0 && buy_num2 ==0) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>alert('구입하려는 상품의 수량을 기입해주십시오.');");
				out.println("location.href='/market/view_"+code+"?cat=1&code="+code+"&uid="+tb_uid+"&id="+sell_id+"&tb_id="+buy_id+"&category="+category+"&pageNum_view="+pageNum_view+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"';</script>");
//				out.println("history.back();</script>");
			}
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>alert('상품이 장바구니에 담겼습니다.');");
		out.println("location.href='/market/view_lecture?cat=1&code="+code+"&uid="+tb_uid+"&id="+sell_id+"&tb_id="+buy_id+"&category="+category+"&pageNum_view="+pageNum_view+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"';</script>");
		
		
//		response.sendRedirect("/bbs/view_lecture?code="+code+"&uid="+tb_uid+"&id="+sell_id+"&tb_id="+buy_id+"&g="+category+"&pageNum_view="+pageNum_view+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
