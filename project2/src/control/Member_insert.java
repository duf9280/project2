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
			int size=10*1024*1024;//10Mbyte용량제한
			
			MultipartRequest multi = new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());	
			
			Enumeration files = multi.getFileNames();	
			
			String file1 = (String)files.nextElement();//사장님 대표사진? 프로필사진?
			String file1_rename = multi.getFilesystemName(file1);
			
			
			Date now = new Date();
			String str1 = now.toString();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
			String signdate = s.format(now);
			
			String thum_name = "";
			if(file1 != null) {
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
			
			
			Member member = new Member();
			//회원가입 페이지에서 받았던 값들 담기
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
			dao.insertMember(member);//메소드?에 객체담아서 넘겨버려어어어어 거기서 디비에 입력할거야
//			System.out.println(multi.getParameter("id"));
//			System.out.println(multi.getParameter("pass"));
//			System.out.println(multi.getParameter("names"));
//			System.out.println(multi.getParameter("nickname"));
			response.sendRedirect("/");//인덱스로 ㄱ
			
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
