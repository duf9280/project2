package control;

import java.awt.Image;
import java.io.IOException;
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


@WebServlet("/member/member_update")
public class Member_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Member_update() {
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
			int size=10*1024*1024;//10Mbyte용량제한
			
			MultipartRequest multi = new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());	
			
			Enumeration files = multi.getFileNames();	
			
			String file1 = (String)files.nextElement();//사장님 대표사진? 프로필사진?
			String file1_rename = multi.getFilesystemName(file1);
			
			
			
			String thum_name = "";
			if(file1_rename != null) {
				//썸네일
				String filePath = uploadPath;
				
				String orgImg = filePath+"\\"+file1_rename;//원본파일명
				thum_name="thum_"+ file1_rename;//썸네일 파일명
				String thumbImg = filePath+"\\"+thum_name;//경로 + 썸네일파일명
				
				int thumbWidth = 120 ;//썸네일 가로
				int thumbHeight = 80 ;//썸네일 세로
				
				Image thumbnail = JimiUtils.getThumbnail(orgImg, thumbWidth, thumbHeight, Jimi.IN_MEMORY);// 썸네일 설정
				Jimi.putImage(thumbnail, thumbImg);
			}else {
				file1 = "";
				file1_rename = "";
			}
			String tab = multi.getParameter("tab");
			Member member = new Member();
			
			member.setId(multi.getParameter("id"));
			member.setPass(multi.getParameter("pass1"));
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
			if(tab != null && tab.equals("a")) {
				dao.updateMemberImg(member);
				response.sendRedirect("member_up?session_id="+multi.getParameter("id"));
			}else if(tab != null && tab.equals("b")) {
				dao.updateMemberNames(member);
				response.sendRedirect("member_up?session_id="+multi.getParameter("id"));
			}else if(tab != null && tab.equals("c")) {
				dao.updateMemberMail(member);
				response.sendRedirect("member_up?session_id="+multi.getParameter("id"));
			}else if(tab != null && tab.equals("d")) {
				dao.updateMemberPass(member);
				response.sendRedirect("member_up?session_id="+multi.getParameter("id"));
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
