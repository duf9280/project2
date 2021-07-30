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
import model.Comment;

@WebServlet("/admin/bbs/view_admin")
public class Bbs_view extends HttpServlet {
	private static final long serialVersionUID = 1L;
     public Bbs_view() {
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
			String id= request.getParameter("id");
			int uid = Integer.parseInt(request.getParameter("uid"));
			String code = request.getParameter("code");

			String id_view = request.getParameter("id_view");
			String tb_table = request.getParameter("code");//�Խ��� �̸�
			String tb_name = request.getParameter("nickname");//��õ�ϴ� ��� ��?
			String tb_id = request.getParameter("tb_id");//��õ�ϴ� ��� ���̵�?
			int tb_uid = Integer.parseInt(request.getParameter("uid"));//��õ�� ���ŵǴ� �Խñ��� uid

			
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
			
	
					
			
			BbsDAO dao = new BbsDAO();
			Bbs view = dao.viewBbs(id,uid,code);
			
			
			int count_v=0;//�� ��� ��				
			int number_view=0;//view������ �ѹ��� ó������	 
			
			if(search_view !=null && !search_view.equals("")) {	//�˻�	
				count_v = dao.getAllCount_com(search_view,field_view,code,tb_uid);//�� ��� �� ����
			}else {
				count_v = dao.getAllCount1_com(code,tb_uid);//�� ��� �� ���� > �˻��� �ƴ� ��
			}
						
			//limit�� ����
			int startNum = (pageNum_view - 1) * pageSize;
			int endNum = pageSize;
			
			//�ѹ��� ����
			number_view = count_v - (pageNum_view -1) * pageSize;	
			
			
			//��� ���
			ArrayList<Comment> com;
			if(search_view != null && !search_view.equals("")) {//�˻��϶�
				com= dao.allComment1(startNum,endNum,search_view,field_view,code,tb_uid);
			}else {
				com= dao.allComment(startNum,endNum,code,tb_uid);
			}
			
			
			int r_count;
			
			r_count = dao.getRecoCount_Y(tb_table,tb_uid,tb_id);
//			System.out.println(r_count);

			request.setAttribute("count_v", count_v);	
			request.setAttribute("pageSize", pageSize);	
			request.setAttribute("pageNum_view", pageNum_view);		
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("number_view", number_view);
			request.setAttribute("r_count", r_count);	
			request.setAttribute("com", com);			
			request.setAttribute("view", view);
			request.setAttribute("search", search);
			request.setAttribute("field", field);
			request.setAttribute("search_view", search_view);
			request.setAttribute("field_view", field_view);
			//view �� ���� �־ �Ѱ��ֱ�?? �ϴ� �Խñ� ��ü�� �η� �νĵǴ°� ���� �� ���� �������Ű���
//			System.out.println(code);
//			System.out.println(uid);
//			System.out.println(tb_id);
//			System.out.println(pageNum_view);
//			System.out.println(search_view);
//			System.out.println(field_view);
			
			
			
			
			RequestDispatcher dis = request.getRequestDispatcher("/admin/bbs/view_admin.jsp?code="+code+"&uid="+uid+"&tb_id="+id+"&pageNum="+pageNum+"&field_view="+field_view+"&search_view="+search_view+"");
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
