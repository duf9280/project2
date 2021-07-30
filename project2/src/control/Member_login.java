package control;

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

import dao.MemberDAO;
import model.Member;

@WebServlet("/login")
public class Member_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Member_login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Member mem = new Member();
		//�α��ν� �Է� ���� ���̵�,��й�ȣ
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String level = request.getParameter("level");

		
		Date now = new Date();
		String str1 = now.toString();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmmss");	
		String signdate = s.format(now);
		
//		mem.setId(request.getParameter("id"));
//		mem.setPass(request.getParameter("Pass"));
		//dao�� ����
		MemberDAO dao = new MemberDAO();
		//ȸ�� ���翩��
		//V��� ���� String id, pass�� �ѱ����� �ּ��� �ִ� mem.��׵��� �Ѱܵ���
//////////////////dao��Ű���� �ִ�//loginMember���� num�� �޾Ҵ�.

//		System.out.println(num);
		String referer = request.getHeader("referer");
		int num1 = dao.loginMember_no(id, pass);
		if(num1 == 1) {
			Member mem = dao.loginSelect_no(id,pass);
			if(mem.getQuitdate() != null) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('Ż���� ȸ���� �α��� �Ҽ� �����ϴ�.');");
				out.print("history.back();");
				out.print("</script>");	
			}else {
			HttpSession session = request.getSession();
			session.setAttribute("id", mem.getId());
			session.setAttribute("nickname", mem.getNickname());
			session.setAttribute("level", mem.getLevel());
			session.setAttribute("cart_name", signdate+id);

			
			response.sendRedirect(referer);
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
//			out.print("<script>");
//			out.print("alert(url);");
//			out.print("location.href="+referer+";");
//			out.print("</script>");	
			}
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�');");
			out.print("history.back();");
			out.print("</script>");	
		}
	
		
		
				
			
			

	}		
}

