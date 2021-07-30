package control_admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_adminDAO;

@WebServlet("/admin/bbs/admin_del_market_ch")
public class Market_del_check extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Market_del_check() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String[] check = request.getParameterValues("chked");
		String code= request.getParameter("code");
		String category=request.getParameter("category");
		
//		System.out.println(category);
//		System.out.println(code);
		
		Member_adminDAO dao = new Member_adminDAO();
		if(check!=null){
			for(int i=0; i<check.length; i++){
				String checked_uid = check[i];
//				System.out.println(checked_uid);
				dao.market_del_check(code,checked_uid,category);
			}
			
			response.sendRedirect("market_list?code="+code+"&category="+category+"");
		
			
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('체크된 상품이 없습니다.');");
			out.print("history.back();");
			out.print("</script>");
		}
	}
}
