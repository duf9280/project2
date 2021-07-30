package bbs_control;

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

import dao.BbsDAO;
import model.Bbs;

@WebServlet("/bbs/write.do")
public class Bbs_write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Bbs_write() {
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
			
			String uploadPath="C:\\jsp\\project2\\WebContent\\bbs\\upload";
			int size=10*1024*1024;//10Mbyte용량제한
			
			MultipartRequest multi = new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());	
			
			Enumeration files = multi.getFileNames();	
			
			String file1 = (String)files.nextElement();
			String file1_rename = multi.getFilesystemName(file1);
			String file2 = (String)files.nextElement();
			String file2_rename = multi.getFilesystemName(file2);	
			
			
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
				request.setCharacterEncoding("utf-8");
				String code = multi.getParameter("code");
				Bbs m = new Bbs();
					m.setId(multi.getParameter("id"));
					m.setNickname(multi.getParameter("nickname"));
					m.setSubject(multi.getParameter("subject"));
					m.setComment(multi.getParameter("comment"));
					m.setSigndate(signdate);
					m.setGongji(multi.getParameter("gongji"));
					m.setFile1_o(file1_rename);
					m.setFile2_o(file2_rename);
					
	//			System.out.println("짜잔\n"+m.toString()+"\n끼룩");
					
				BbsDAO dao = new BbsDAO();
				
				if(file1_rename ==null && file2_rename ==null) {
					//첨부 둘 다 없을 때
					dao.insertBbs2(m,code);
				}else if(file1_rename !=null && !file1_rename.equals("") && (file2_rename ==null || file2_rename.equals(""))) {
					//1만 있을 때
					dao.insertBbs3(m,code);
				}else if(file2_rename !=null && !file2_rename.equals("") && (file1_rename ==null || file1_rename.equals(""))) {
					//2만 있을 때
					dao.insertBbs4(m,code);
				}else {
					dao.insertBbs(m,code);
				}
				
				
				out.println("<script>alert('게시글이 등록 되었습니다');</script>");
				response.sendRedirect("list?code="+code);
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
		
}

