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



@WebServlet("/member/member_up")
public class Member_up extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Member_up() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String session_id = request.getParameter("session_id");
			String tab = request.getParameter("tab");
			MemberDAO dao = new MemberDAO();
			Member m = dao.memberup(session_id);
			
			if(tab != null && tab.equals("a")) {//프로필,별명
				request.setAttribute("M", m);
				request.setAttribute("tab", tab);
				RequestDispatcher dis = request.getRequestDispatcher("/member/member_up_img.jsp");
				dis.forward(request, response);
			}else if(tab != null && tab.equals("b")) {//개인정보 , 이름,성별 등등...
				request.setAttribute("M", m);
				request.setAttribute("tab", tab);
				RequestDispatcher dis = request.getRequestDispatcher("/member/member_up_names.jsp");
				dis.forward(request, response);
			}else if(tab != null && tab.equals("c")) {//메일,주소
				request.setAttribute("M", m);
				request.setAttribute("tab", tab);
				RequestDispatcher dis = request.getRequestDispatcher("/member/member_up_mail.jsp");
				dis.forward(request, response);
			}else if(tab != null && tab.equals("d")) {//비밀번호
				request.setAttribute("M", m);
				request.setAttribute("tab", tab);
				RequestDispatcher dis = request.getRequestDispatcher("/member/m_up_passck.jsp");
				dis.forward(request, response);
			}else{////////////////////////////////////이건 그냥 수정 눌렀을때
				request.setAttribute("M", m);
				RequestDispatcher dis = request.getRequestDispatcher("/member/member_up_go.jsp");
				dis.forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
