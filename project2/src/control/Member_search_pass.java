package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import model.Member;


@WebServlet("/member/search_pass")
public class Member_search_pass extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Member_search_pass() {
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
		
		//일반 비밀번호찾기
		Member m = new Member();
		m.setLevel(request.getParameter("level"));
		m.setId(request.getParameter("id"));
		m.setNames(request.getParameter("names"));
		m.setMail1(request.getParameter("mail1"));
		m.setMail2(request.getParameter("mail2"));
		m.setTel(request.getParameter("tel"));
		
		//사장 비밀번호찾기
		
		MemberDAO dao = new MemberDAO();
		

			Member mem = dao.matchpass_no(m);
			
			request.setAttribute("mem", mem);
			RequestDispatcher dis = request.getRequestDispatcher("/member/match_pass.jsp");
			dis.forward(request, response);

	}

}
