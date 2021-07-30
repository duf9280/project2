package control;

import java.awt.Image;
import java.io.IOException;
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

import dao.MemberDAO;
import model.Member;

@WebServlet("/member/join_insert_no")
public class Member_insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Member_insert() {
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
			String uploadPath="C:\\jsp\\project2\\WebContent\\member\\upload";
			int size=10*1024*1024;//10Mbyte�뷮����
			
			MultipartRequest multi = new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());	
			
			Enumeration files = multi.getFileNames();	
			
			String file1 = (String)files.nextElement();//����� ��ǥ����? �����ʻ���?
			String file1_rename = multi.getFilesystemName(file1);
			
			
			Date now = new Date();
			String str1 = now.toString();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
			String signdate = s.format(now);
			
			String thum_name = "";
			if(file1 != null) {
				//�����
				String filePath = uploadPath;
				
				String orgImg = filePath+"\\"+file1_rename;//�������ϸ�
				thum_name="thum_"+ file1_rename;//����� ���ϸ�
				String thumbImg = filePath+"\\"+thum_name;//��� + ��������ϸ�
				
				int thumbWidth = 120 ;//����� ����
				int thumbHeight = 80 ;//����� ����
				
				Image thumbnail = JimiUtils.getThumbnail(orgImg, thumbWidth, thumbHeight, Jimi.IN_MEMORY);// ����� ����			
				Jimi.putImage(thumbnail, thumbImg);
			}else {
				file1 = "";
				file1_rename = "";
			}
			
			
			Member member = new Member();
			//ȸ������ ���������� �޾Ҵ� ���� ���
			member.setId(multi.getParameter("id"));
			member.setPass(multi.getParameter("pass"));
			member.setNames(multi.getParameter("names"));
			member.setNickname(multi.getParameter("nickname"));
			member.setMail1(multi.getParameter("mail1"));	
			member.setMail2(multi.getParameter("mail2"));
			member.setGender(multi.getParameter("gender"));
			member.setSsn(multi.getParameter("ssn"));
			member.setTel(multi.getParameter("tel"));
			member.setZipcode(multi.getParameter("zipcode"));
			member.setZip1(multi.getParameter("zip1"));	
			member.setZip2(multi.getParameter("zip2"));
			member.setZip3(multi.getParameter("zip3"));
			member.setFile1_o(file1_rename);
			member.setFile1_s(thum_name);
			
			
			MemberDAO dao = new MemberDAO();
			dao.insertMember(member);//�޼ҵ�?�� ��ü��Ƽ� �Ѱܹ�������� �ű⼭ ��� �Է��Ұž�
//			System.out.println(multi.getParameter("id"));
//			System.out.println(multi.getParameter("pass"));
//			System.out.println(multi.getParameter("names"));
//			System.out.println(multi.getParameter("nickname"));
			response.sendRedirect("/");//�ε����� ��
			
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
