package market_control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MarketDAO;
import model.Product;

@WebServlet("/market/product_modify.do")
public class Market_modify_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Market_modify_product() {
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
		
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		int uid = Integer.parseInt(request.getParameter("uid"));
		String category=request.getParameter("category");
		String cat=request.getParameter("cat");
//		System.out.println(cat);
//		System.out.println(id);
//		System.out.println(uid);
//		System.out.println(category);
		HttpSession session = request.getSession();
		System.out.println("�ͿͿ�1"+ session.getAttribute("id"));
		System.out.println("�ͿͿ�2"+ session.getAttribute("level"));
		System.out.println("�ͿͿ�3"+ session.getAttribute("names"));
		System.out.println("�ͿͿ�4"+ session.getAttribute("nickname"));
		System.out.println("�ͿͿ�5"+ session.getAttribute("cat"));
		
		
		MarketDAO dao = new MarketDAO();
		Product view = dao.viewProduct(id,uid,code,category);
		request.setAttribute("cat", cat);
		request.setAttribute("view", view);
		if(cat != null && cat.equals("2")) {
			RequestDispatcher dis = request.getRequestDispatcher("/manage/bbs/modify_product.jsp");
			dis.forward(request, response);	
		}else {
			RequestDispatcher dis = request.getRequestDispatcher("modify_product.jsp");
			dis.forward(request, response);		
		}
	}
}
