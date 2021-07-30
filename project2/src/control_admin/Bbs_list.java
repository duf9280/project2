package control_admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import model.Bbs;

@WebServlet("/admin/bbs/list")
public class Bbs_list extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Bbs_list() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String code = request.getParameter("code");
		String search = request.getParameter("search");//�˻���
		String field = request.getParameter("field");//�˻�����(���̵�,�̸�)
		
		if(search !=null && !search.equals("")) {		
			search = request.getParameter("search");//�˻���
			field = request.getParameter("field");//�˻�����(���̵�,�̸�)
		}else {
			search = "";//�˻���
			field = "";//�˻�����(���̵�,�̸�)			
		}	
		
		//����¡ ó��
		int pageSize = 15;//�������� ��� ��
		int pageNum = 1;//���� ������
		
		
		if(request.getParameter("pageNum") !=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int count=0;//�� �Խù� ��
		int count_gong=0;//���� �Խù� ��	
		int c_count=0;//�� �Խñۺ� �� ��� ��
	
		
		int number=0;//������ �ѹ��� ó������
		int number1=0;//������ �ѹ��� ó������
		
		BbsDAO dao = new BbsDAO();
		if(search !=null && !search.equals("")) {		
			count = dao.getAllCount(search,field,code);//�� �Խù� �� ����
			count_gong = dao.getAllCount_gong1(search,field,code);
		}else {
			count = dao.getAllCount1(code);//�� �Խù� �� ���� > �˻��� �ƴ� ��
			count_gong = dao.getAllCount_gong(code);
		}
		
//		System.out.println(count);
//		System.out.println(count_gong);
//		c_count= dao_c.getAllComment(code,);
		
		//limit�� ����
		int startNum = (pageNum - 1) * pageSize;
		int endNum = pageSize;
		

		//�ѹ��� ����
		number = count - (pageNum -1) * pageSize;
		number1 =count_gong - (pageNum -1) * pageSize;
//		System.out.println(count);
//		System.out.println("aaa");

		ArrayList<Bbs> gong;
		if(search != null && !search.equals("")) {
			gong = dao.allBbs3(startNum,endNum,search,field,code);
		}else {
			gong = dao.allBbs4(startNum,endNum,code);
		}
	
		ArrayList<Bbs> v;
		if(search != null && !search.equals("")) {
			v = dao.allBbs(startNum,endNum,search,field,code);
		}else {
			v = dao.allBbs2(startNum,endNum,code);
		}
		
		
		

		request.setAttribute("v", v);
		request.setAttribute("gong", gong);
		
		request.setAttribute("number", number);
		request.setAttribute("number1", number1);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("count_gong", count_gong);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("search", search);
		request.setAttribute("field", field);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/bbs/list.jsp?code="+code+"&pageNum="+pageNum+"&search="+search+"&field="+field+"");//
		
		dis.forward(request, response);		
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
