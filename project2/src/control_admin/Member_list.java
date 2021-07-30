package control_admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_adminDAO;
import model.Member;


@WebServlet("/admin/member/list")
public class Member_list extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Member_list() {
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
		
		String field = request.getParameter("field");
		String search = request.getParameter("search");
		String mem = request.getParameter("mem");
		
		//����¡ ó��
		int pageSize = 10;//�������� ��� ����
		int pageNum = 1;//���� ������
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		int count = 0;//�� �Խù� ��
		int number = 0;//������  �ѹ��� ó�� ����
		
		Member_adminDAO dao = new Member_adminDAO();
		//limit �� ����
		int startNum = (pageNum - 1) * pageSize;
		int endNum = pageSize;
		
	
		//�� ��
		count = dao.getAllCount_no(field,search);
		
		//ȸ������
		ArrayList<Member> v = dao.allMember_no(startNum,endNum,field,search);
		
		//�ѹ��� ����
		number = count - (pageNum - 1) * pageSize;
		
		request.setAttribute("mem", mem);
		request.setAttribute("v", v);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/member/list.jsp");
		dis.forward(request, response);

		
		
	}

}
