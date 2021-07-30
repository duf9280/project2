package market_control;

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
import model.Lecture;

@WebServlet("/market/lecturewrite.do")
public class Market_write_lecture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Market_write_lecture() {
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
			
			String uploadPath="C:\\jsp\\project2\\WebContent\\market\\upload";
			int size=10*1024*1024;//10Mbyte용량제한
			
			MultipartRequest multi = new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());	
			
			Enumeration files = multi.getFileNames();	
			
			String file1 = (String)files.nextElement();
			String file1_rename = multi.getFilesystemName(file1);
			String file2 = (String)files.nextElement();
			String file2_rename = multi.getFilesystemName(file2);	
			String file3 = (String)files.nextElement();
			String file3_rename = multi.getFilesystemName(file3);	
			
			Date now = new Date();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
			String signdate = s.format(now);
			
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String subject=multi.getParameter("subject");
			String comment =multi.getParameter("comment");
			
			if(subject.equals("")) {
				
				out.println("<script>");
				out.println("alert('제목을 작성해주십시오.');");
				out.println("history.back();");
				out.println("</script>");
				
			}else if(comment.equals("")) {
				
				out.println("<script>");
				out.println("alert('내용을 작성해주십시오.');");
				out.println("history.back();");
				out.println("</script>");				
				
			}else {
				String thum_name="";
				if(file1_rename!=null) {
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
				
				request.setCharacterEncoding("utf-8");
				String code = multi.getParameter("code");
				
				String zipcode=multi.getParameter("zipcode");
				String zip1=multi.getParameter("zip1");
				String zip2=multi.getParameter("zip2");
				String zip3=multi.getParameter("zip3");
				
				String p_name2 = "";
				int price2=0;
				int number2=0;
				
								
				if(!multi.getParameter("p_name2").equals("")) {
					p_name2=multi.getParameter("p_name2");
					price2=Integer.parseInt(multi.getParameter("price2"));
					number2=Integer.parseInt(multi.getParameter("number2"));
				}
				Lecture m = new Lecture();
				
				m.setId(multi.getParameter("id"));
				m.setSubject(multi.getParameter("subject"));
				m.setComment(multi.getParameter("comment"));
				m.setCategory(multi.getParameter("category"));
				m.setP_name1(multi.getParameter("p_name1"));
				m.setPrice1(Integer.parseInt(multi.getParameter("price1")));
				m.setNumber1(Integer.parseInt(multi.getParameter("number1")));
				m.setP_name2(p_name2);
				m.setPrice2(price2);
				m.setNumber2(number2);	
				m.setBaesong(Integer.parseInt(multi.getParameter("baesong")));
				m.setBaesong_free(Integer.parseInt(multi.getParameter("baesong_free")));
				m.setGongji(multi.getParameter("gongji"));
				m.setFile1_o(file1_rename);
				m.setFile1_s(thum_name);
				m.setFile2_o(file2_rename);
				m.setFile3_o(file3_rename);
				m.setAccount_name(multi.getParameter("account_name"));
				m.setAccount_num(multi.getParameter("account_num"));
				m.setZipcode(zipcode);
				m.setZip1(zip1);
				m.setZip2(zip2);
				m.setZip3(zip3);
				m.setSigndate(signdate);

	//			System.out.println("짜잔\n"+m.toString()+"\n끼룩");
					
				BbsDAO dao = new BbsDAO();
				
				if(file2_rename ==null && file3_rename ==null) {
					//첨부 둘 다 없을 때 ,1은 필수로 받을 예정
					dao.insertLecture2(m,code);
				}else if(file2_rename !=null && !file2_rename.equals("") && (file3_rename ==null || file3_rename.equals(""))) {
					//2만 있을 때
					dao.insertLecture3(m,code);
				}else if(file3_rename !=null && !file3_rename.equals("") && (file2_rename ==null || file2_rename.equals(""))) {
					//3만 있을 때
					dao.insertLecture4(m,code);
				}else {
					dao.insertLecture1(m,code);
				}
				
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>alert('상품이 등록 되었습니다');</script>");
				response.sendRedirect("list?code="+code+"&category="+multi.getParameter("category"));
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}			
	}
}
