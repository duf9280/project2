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
		//로그인시 입력 받은 아이디,비밀번호
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String level = request.getParameter("level");

		
		Date now = new Date();
		String str1 = now.toString();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddhhmmss");	
		String signdate = s.format(now);
		
//		mem.setId(request.getParameter("id"));
//		mem.setPass(request.getParameter("Pass"));
		//dao에 연결
		MemberDAO dao = new MemberDAO();
		//회원 존재여부
		//V얘는 지금 String id, pass를 넘기지만 주석에 있는 mem.얘네들을 넘겨도됨
//////////////////dao패키지에 있는//loginMember에서 num값 받았다.

//		System.out.println(num);
		String referer = request.getHeader("referer");
		int num1 = dao.loginMember_no(id, pass);
		if(num1 == 1) {
			Member mem = dao.loginSelect_no(id,pass);
			if(mem.getQuitdate() != null) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('탈퇴한 회원은 로그인 할수 없습니다.');");
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
			out.print("alert('아이디 또는 비밀번호가 일치하지 않습니다');");
			out.print("history.back();");
			out.print("</script>");	
		}
	
		
		
				
			
			

	}		
}

