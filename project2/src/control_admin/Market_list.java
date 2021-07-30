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
import model.Lecture;
import model.Product;

@WebServlet("/admin/bbs/market_list")
public class Market_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Market_list() {
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
			String category = request.getParameter("category");
			String session_id = request.getParameter("session_id");	
			String search = request.getParameter("search");//�˻���
			String field = request.getParameter("field");//�˻�����
			
			if(search !=null && !search.equals("")) {		
				search = request.getParameter("search");//�˻���
				field = request.getParameter("field");//�˻�����
			}else {
				search = "";//�˻���
				field = "";//�˻�����			
			}	
			
		//����¡ ó��
		int pageSize = 15;//�������� ��� ��
		int pageNum = 1;//���� ������
		
		
		if(request.getParameter("pageNum") !=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int count=0;//�� �Խù� ��
	
		
		int number=0;//������ �ѹ��� ó������
		
		BbsDAO dao = new BbsDAO();
		
		
		if(search !=null && !search.equals("")) {		
			count = dao.getAllCount_lect_prod1(search,field,code,category);//�� �Խù� �� ����
		}else {
			count = dao.getAllCount_lect_prod2(code, category);//�� �Խù� �� ���� > �˻��� �ƴ� ��
		}

//		System.out.println(count);
		
		//limit�� ����
		int startNum = (pageNum - 1) * pageSize;
		int endNum = pageSize;
		

		//�ѹ��� ����
		number = count - (pageNum -1) * pageSize;
//		System.out.println(count);
//		System.out.println("aaa");
		
		
		
		
		
		
		if(code.equals("lecture")) {
			ArrayList<Lecture> v;
			if(search != null && !search.equals("")) {
				v = dao.allBbs_lecture1_ad(startNum,endNum,search,field,code,category);
			}else {
				v = dao.allBbs_lecture2_ad(startNum,endNum,code,category);
			}		
			request.setAttribute("v", v);
		}else if(code.equals("product")){
			ArrayList<Product> v;
			if(search != null && !search.equals("")) {
				v = dao.allBbs_product1_ad(startNum,endNum,search,field,code,category);
			}else {
				v = dao.allBbs_product2_ad(startNum,endNum,code,category);
			}		
			request.setAttribute("v", v);
		}

		
		

		
		
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("search", search);
		request.setAttribute("field", field);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/bbs/market_list.jsp?code="+code+"&category="+category+"&pageNum="+pageNum+"&search="+search+"&field="+field+"");
		//		
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
