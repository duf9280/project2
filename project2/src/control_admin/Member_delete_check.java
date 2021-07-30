package control_admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_adminDAO;

@WebServlet("/delete_check/member")
public class Member_delete_check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Member_delete_check() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] check = request.getParameterValues("check");
		
		Date now = new Date();
		String str1 = now.toString();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		String signdate = s.format(now);
		
		Member_adminDAO dao = new Member_adminDAO();
	
			if(check!=null){
				for(int i=0; i<check.length; i++){
					String checked_id = check[i];
	//				System.out.println(checked_uid);
					dao.deleteMem_no_check(checked_id,signdate);
					
				}
				response.sendRedirect("/admin/member/list");
			}else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('체크된 회원이 없습니다.');");
				out.print("history.back();");
				out.print("</script>");
			}
	
		
		
	}
}
