package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import model.Member;


@WebServlet("/member/pass_ok")
public class Member_passck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Member_passck() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MemberDAO dao = new MemberDAO();
			
			String pass = request.getParameter("pass");
			String id = request.getParameter("id");
			String tab = request.getParameter("tab");
			
			int num = dao.loginMember_no(id, pass);
			
			if(num == 1) {
				String session_id = request.getParameter("session_id");
				Member m = dao.memberup(session_id);
				
				request.setAttribute("M", m);
				request.setAttribute("tab", tab);
				RequestDispatcher dis = request.getRequestDispatcher("/member/member_up_pass.jsp");
				dis.forward(request, response);
				
			}else {
				response.setContentType("text/html; charset=utf-8");
				
				PrintWriter out = response.getWriter();
				
				out.print("<script>");
				out.print("alert('비밀번호가 일치하지 않습니다');");
				out.print("history.back();");
				out.print("</script>");	
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
