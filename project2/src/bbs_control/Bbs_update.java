package bbs_control;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiUtils;

import dao.BbsDAO;
import model.Bbs;

@WebServlet("/update.do")
public class Bbs_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Bbs_update() {
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
			
			String uploadPath="C:\\jsp\\project2\\WebContent\\bbs\\upload";
			int size=10*1024*1024;//10Mbyte�뷮����
			
			MultipartRequest multi = new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());	
			
			Enumeration files = multi.getFileNames();	
			
			String file1 = (String)files.nextElement();
			String file1_rename = multi.getFilesystemName(file1);
			String file2 = (String)files.nextElement();
			String file2_rename = multi.getFilesystemName(file2);	
			
			
			//�����
			String filePath = uploadPath;
			
			String orgImg = filePath+"\\"+file1_rename;//�������ϸ�
			String thum_name="thum_"+ file1_rename;//����� ���ϸ�
			String thumbImg = filePath+"\\"+thum_name;//��� + ��������ϸ�
			
			int thumbWidth = 120 ;//����� ����
			int thumbHeight = 80 ;//����� ����
			
			Image thumbnail = JimiUtils.getThumbnail(orgImg, thumbWidth, thumbHeight, Jimi.IN_MEMORY);// ����� ����
			
			request.setCharacterEncoding("utf-8");
			String code= multi.getParameter("code");
			
			Bbs m = new Bbs();
				m.setUid(Integer.parseInt(multi.getParameter("uid")));
				m.setId(multi.getParameter("id"));
				m.setNickname(multi.getParameter("nickname"));
				m.setSubject(multi.getParameter("subject"));
				m.setComment(multi.getParameter("comment"));
				m.setGongji(multi.getParameter("gongji"));
				m.setFile1_o(file1_rename);
				m.setFile2_o(file2_rename);	
			
//			System.out.println("¥��\n"+m.toString()+"\n����");
				
			BbsDAO dao = new BbsDAO();
			response.setContentType("text/html; charset=utf-8");
			if(file1_rename ==null && file2_rename ==null) {
				//÷�� �� �� ���� ��
				dao.updateBbs4(m,code);
			}else if(file1_rename !=null && !file1_rename.equals("") && (file2_rename ==null || file2_rename.equals(""))) {
				//1�� ���� ��
				dao.updateBbs2(m,code);
			}else if(file2_rename !=null && !file2_rename.equals("") && (file1_rename ==null || file1_rename.equals(""))) {
				//2�� ���� ��
				dao.updateBbs3(m,code);
			}else {
				dao.updateBbs1(m,code);
			}
			
			PrintWriter out = response.getWriter();
			
			response.setContentType("text/html; charset=utf-8");
//			out.println("<script>alert('�Խñ��� ���� �Ǿ����ϴ�'); location.href='/list?code='"+code+";</script>");
//			out.println("<script>");
//			out.println("");
//			out.println();
//			out.println("</script>");
			
			
//			out.println("<script>");
//			out.println("alert('�Խñ��� ���� �Ǿ����ϴ�.');");
//			out.println("location.href='/bbs/list?code='"+code+";");
//			out.println("location.href ='/list?code='"+code+"';");
//			out.println("</script>");
			

			response.sendRedirect("/bbs/list?code="+code);

			out.close();
			
			
			
//			response.sendRedirect("/bbs/list?code="+code);
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
