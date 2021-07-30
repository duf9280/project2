package market_control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import dao.MarketDAO;
import model.Product;
import model.Review;

@WebServlet("/market/view_product")
public class Market_view_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Market_view_product() {
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
			String category=request.getParameter("category");
			String id= request.getParameter("id");
			int uid = Integer.parseInt(request.getParameter("uid"));
			String code = request.getParameter("code");
			String cat= request.getParameter("cat");
			String tb_table = request.getParameter("code");//�Խ��� �̸�
//			String tb_name = request.getParameter("nickname");// ���ϱ� ���鶧 �����
//			String tb_id = request.getParameter("tb_id");//
//			int tb_uid = Integer.parseInt(request.getParameter("uid"));//�Խñ��� uid

			
			String search_view = request.getParameter("search_view");//�˻���
			String field_view = request.getParameter("field_view");//�˻�����(���̵�,�̸�)			
			String search = request.getParameter("search");//�˻���
			String field = request.getParameter("field");//�˻�����(���̵�,�̸�)

			if(search_view !=null && !search_view.equals("")) {		
				search_view = request.getParameter("search_view");//�˻���
				field_view = request.getParameter("field_view");//�˻�����(���̵�,�̸�)
			}else {
				search_view = "";//�˻���
				field_view = "";//�˻�����(���̵�,�̸�)			
			}	
			
			if(search !=null && !search.equals("")) {		
				search = request.getParameter("search");//�˻���
				field = request.getParameter("field");//�˻�����(���̵�,�̸�)
			}else {
				search = "";//�˻���
				field = "";//�˻�����(���̵�,�̸�)			
			}				
			
			//����¡ ó��
			int pageSize = 10;//�������� ��� ��	
			int pageNum=1;//���� ������
			int pageNum_view;//view���� ������

			if(request.getParameter("pageNum_view") !=null || request.getParameter("pageNum_view") !="") {
				pageNum_view = Integer.parseInt(request.getParameter("pageNum_view"));
			}else {
				pageNum_view=1;
			}

			if(request.getParameter("pageNum") !=null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}else {
				pageNum=1;
			}
//			System.out.println(tb_table);
//			System.out.println(code);
			
	
					
			
			MarketDAO dao = new MarketDAO();
			BbsDAO dao1 = new BbsDAO();
			Product view = dao.viewProduct(id,uid,code,category);
			
			
			int count_v=0;//�� ��� ��				
			int number_view=0;//view������ �ѹ��� ó������	 
			
			
			
			///�̰� ���� �ʿ� ���µ� ���߿� ���������� �����ﶧ ��� ���°� �����ؼ� �ϱ����� �����.
			if(search_view !=null && !search_view.equals("")) {	//�˻�	
				count_v = dao1.getAllCount_com(search_view,field_view,code,uid);//�� ���� �� ����
			}else {
				count_v = dao1.getAllCount1_com(code,uid);//�� ���� �� ���� > �˻��� �ƴ� ��
			}
						
			//limit�� ����
			int startNum = (pageNum_view - 1) * pageSize;
			int endNum = pageSize;
			
			//�ѹ��� ����
			number_view = count_v - (pageNum_view -1) * pageSize;	
			
			
			//��� ���
			ArrayList<Review> rev;
			if(search_view != null && !search_view.equals("")) {//�˻��϶�
				rev= dao1.allReview1(startNum,endNum,search_view,field_view,code,uid);
			}else {
				rev= dao1.allReview(startNum,endNum,code,uid);
			}
			
			
			
			//������ �Խ����� ��õ�� ���ְ� ������ �� �������� ���ھ �޾Ƽ� ����Ʈ,���������� ��¿���
//			System.out.println(r_count);

			request.setAttribute("count_v", count_v);	
			request.setAttribute("pageSize", pageSize);	
			request.setAttribute("pageNum_view", pageNum_view);		
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("number_view", number_view);
			
			request.setAttribute("rev", rev);			
			request.setAttribute("view", view);
			request.setAttribute("search", search);
			request.setAttribute("field", field);
			request.setAttribute("search_view", search_view);
			request.setAttribute("field_view", field_view);
			
//			System.out.println(code);
//			System.out.println(uid);
//			System.out.println(tb_id);
//			System.out.println(pageNum_view);
//			System.out.println(search_view);
//			System.out.println(field_view);
			
			
			
			if(!cat.equals("2")) {
				RequestDispatcher dis = request.getRequestDispatcher("/market/view_product.jsp?cat=1&code="+code+"&category="+category+"&uid="+uid+"&tb_id="+id+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"");
				dis.forward(request, response);	
			}else {
				RequestDispatcher dis = request.getRequestDispatcher("/manage/bbs/view_product.jsp?cat="+cat+"code="+code+"&category="+category+"&uid="+uid+"&tb_id="+id+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"");
				dis.forward(request, response);	
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
