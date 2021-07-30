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

@WebServlet("/buy/cart_insert_product")
public class Cart_insert_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cart_insert_product() {
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
		String category= request.getParameter("category");
		String buy_id= request.getParameter("session_id");
		String sell_id= request.getParameter("sell_id");
		String code= request.getParameter("code");
		int tb_uid= Integer.parseInt(request.getParameter("uid"));
		String subject= request.getParameter("subject");
		String cart_name=request.getParameter("cart_name");
		
		String p_name1 = request.getParameter("p_name1");
		int price1= Integer.parseInt(request.getParameter("price1"));
		int number1= Integer.parseInt(request.getParameter("number1"));
		String s_zipcode= request.getParameter("zipcode");
		String s_zip1= request.getParameter("zip1");
		String s_zip2= request.getParameter("zip2");
		String s_zip3= request.getParameter("zip3");
//		System.out.println(s_zipcode);
//		System.out.println(s_zip1);
//		System.out.println(s_zip2);
//		System.out.println(s_zip3);
		
//		System.out.println(category);
		
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
		
		String p_name3="";
		int price3= 0;
		int buy_num3= 0;	
		
		if(request.getParameter("p_name3")!=null) {
			p_name3 = request.getParameter("p_name3");
			price3= Integer.parseInt(request.getParameter("price3"));
			if(request.getParameter("buy_num3")!=null) {
				buy_num3 =Integer.parseInt(request.getParameter("buy_num3"));
			}else {
				buy_num3= 0;
			}
		}

		int buy_num1 = Integer.parseInt(request.getParameter("buy_num1"));

		//물품 가격이 일정치(baesong_free)를 넘었을때 배송비를 0으로
		int baesong=0;
		int a = price1 * buy_num1;
		int b = price2 * buy_num2;
		int c = price3 * buy_num3;
		int baesong_free=Integer.parseInt(request.getParameter("baesong_free"));
		

		if(a >= baesong_free) {
			baesong=0;			
//			System.out.println(a);
//			System.out.println(baesong_free);
//			System.out.println(baesong);
			
		}else if(a+b >= baesong_free) {
			baesong=0;
		}else if(a+b+c >= baesong_free) {

			baesong=0;
		}else {
			baesong=Integer.parseInt(request.getParameter("baesong"));
		}
		
		
		String account_name= request.getParameter("account_name");
		String account_num= request.getParameter("account_num");
		
		String file1_s= request.getParameter("file1_s");
		int pageNum= Integer.parseInt(request.getParameter("pageNum"));
		int pageNum_view= Integer.parseInt(request.getParameter("pageNum_view"));
		String field_view = request.getParameter("field_view");
		String search_view = request.getParameter("search_view");
//		System.out.println(cart_name);
		
		
		Date now = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		String signdate = s.format(now);
		BuyDAO dao = new BuyDAO();
		
		if(buy_num1 >=1 && price1 >=1) {		
			Cart cc = new Cart();
			cc.setBuy_id(buy_id);
			cc.setSell_id(sell_id);
			cc.setTb_uid(tb_uid);
			cc.setTb_table(code);
			cc.setCategory(category);
			cc.setCart_name(cart_name);
			cc.setPrice(price1);
			cc.setBaesong(baesong);
			cc.setNumber(buy_num1);
			cc.setFile1_s(file1_s);
			cc.setSubject(subject+"("+p_name1+")");
			cc.setSigndate(signdate);
			cc.setS_zipcode(s_zipcode);
			cc.setS_zip1(s_zip1);
			cc.setS_zip2(s_zip2);
			cc.setS_zip3(s_zip3);
			
			dao.insertCart_product1(cc);
			
		}
		if(buy_num2 >=1 && price2 >=1) {
			Cart ccc = new Cart();
			ccc.setBuy_id(buy_id);
			ccc.setSell_id(sell_id);
			ccc.setTb_uid(tb_uid);
			ccc.setTb_table(code);
			ccc.setCategory(category);
			ccc.setCart_name(cart_name);
			ccc.setPrice(price2);
			ccc.setBaesong(baesong);
			ccc.setNumber(buy_num2);
			ccc.setFile1_s(file1_s);
			ccc.setSubject(subject+"("+p_name2+")");
			ccc.setSigndate(signdate);	
			ccc.setS_zipcode(s_zipcode);
			ccc.setS_zip1(s_zip1);
			ccc.setS_zip2(s_zip2);
			ccc.setS_zip3(s_zip3);
			
			dao.insertCart_product2(ccc);
			
			
		}
		if(buy_num3 >=1 && price3 >=1) {
			Cart cccc = new Cart();
			cccc.setBuy_id(buy_id);
			cccc.setSell_id(sell_id);
			cccc.setTb_uid(tb_uid);
			cccc.setTb_table(code);
			cccc.setCategory(category);
			cccc.setCart_name(cart_name);
			cccc.setPrice(price3);
			cccc.setBaesong(baesong);
			cccc.setNumber(buy_num3);
			cccc.setFile1_s(file1_s);
			cccc.setSubject(subject+"("+p_name3+")");
			cccc.setSigndate(signdate);	
			cccc.setS_zipcode(s_zipcode);
			cccc.setS_zip1(s_zip1);
			cccc.setS_zip2(s_zip2);
			cccc.setS_zip3(s_zip3);
			
			dao.insertCart_product3(cccc);
			
		}
		if(buy_num1 ==0 && buy_num2 ==0 && buy_num3 ==0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>alert('구입하려는 상품의 수량을 기입해주십시오.');");
			out.println("location.href='/market/view_"+code+"?cat=1&code="+code+"&uid="+tb_uid+"&id="+sell_id+"&tb_id="+buy_id+"&category="+category+"&pageNum_view="+pageNum_view+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"';</script>");

		}
		
		
		
		
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script>alert('상품이 장바구니에 담겼습니다.');");
		out.println("location.href='/market/view_"+code+"?cat=1&code="+code+"&uid="+tb_uid+"&id="+sell_id+"&tb_id="+buy_id+"&category="+category+"&pageNum_view="+pageNum_view+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"';</script>");
		
		
//		response.sendRedirect("/bbs/view_lecture?code="+code+"&uid="+tb_uid+"&id="+sell_id+"&tb_id="+buy_id+"&g="+category+"&pageNum_view="+pageNum_view+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"");
		
	}
}
