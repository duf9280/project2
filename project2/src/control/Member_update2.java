package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.MemberDAO;
import model.Member;


@WebServlet("/member/member_update2")
public class Member_update2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Member_update2() {
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
		
		try {	
			String tab = request.getParameter("tab");
			Member member = new Member();
			
			member.setId(request.getParameter("id"));
			member.setPass(request.getParameter("pass"));
			member.setNames(request.getParameter("names"));
			member.setMail1(request.getParameter("mail1"));	
			member.setMail2(request.getParameter("mail2"));
			member.setGender(request.getParameter("gender"));
			member.setSsn(request.getParameter("ssn"));
			member.setTel(request.getParameter("tel"));
			member.setZipcode(request.getParameter("zipcode"));
			member.setZip1(request.getParameter("zip1"));	
			member.setZip2(request.getParameter("zip2"));
			member.setZip3(request.getParameter("zip3"));
			
			
			MemberDAO dao = new MemberDAO();
			
			if(tab != null && tab.equals("a")) {
				dao.updateMemberImg(member);
				response.sendRedirect("member_up?session_id="+request.getParameter("id"));
			}else if(tab != null && tab.equals("b")) {
				dao.updateMemberNames(member);
				response.sendRedirect("member_up?session_id="+request.getParameter("id"));
			}else if(tab != null && tab.equals("c")) {
				dao.updateMemberMail(member);
				response.sendRedirect("member_up?session_id="+request.getParameter("id"));
			}else if(tab != null && tab.equals("d")) {
				dao.updateMemberPass(member);
				response.sendRedirect("member_up?session_id="+request.getParameter("id"));
			}
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
