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


@WebServlet("/reply.do")
public class Bbs_reply_insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Bbs_reply_insert() {
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
		response.setContentType("text/html; charset=utf-8");
		try {

			String uploadPath="C:\\jsp\\project2\\WebContent\\bbs\\upload";
			int size=10*1024*1024;//10Mbyte용량제한
			
			MultipartRequest multi = new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());	
			
			Enumeration files = multi.getFileNames();	
	
			String o_id=multi.getParameter("o_id");
			
			
//			System.out.println(multi.getParameter("o_id"));			
			
			
			String file1 = (String)files.nextElement();
			String file1_rename = multi.getFilesystemName(file1);
			String file2 = (String)files.nextElement();
			String file2_rename = multi.getFilesystemName(file2);	
			
			Date now = new Date();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
			String signdate = s.format(now);
			
			request.setCharacterEncoding("utf-8");
			String id= multi.getParameter("id");
			String gongji = multi.getParameter("gongji");
			String code = multi.getParameter("code");
			String ori_thread = multi.getParameter("thread");
			int ori_fid = Integer.parseInt(multi.getParameter("fid"));
			Bbs m = new Bbs();
				if(gongji.equals("3")) {
					m.setId(multi.getParameter("o_id"));
				}else{
					m.setId(multi.getParameter("id"));
				}
				m.setNickname(multi.getParameter("nickname"));
				m.setSubject(multi.getParameter("subject"));
				m.setComment(multi.getParameter("comment"));
				m.setSigndate(signdate);
				m.setGongji(multi.getParameter("gongji"));
				m.setFile1_o(file1_rename);
				m.setFile2_o(file2_rename);
			
			
//			System.out.println("짜잔\n"+m.toString()+"\n끼룩");
			
			BbsDAO dao = new BbsDAO();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if(file1_rename ==null && file2_rename ==null) {
				//첨부 둘 다 없을 때
				dao.insert_replyBbs2(m,code,ori_thread,ori_fid);
			}else if(file1_rename !=null && !file1_rename.equals("") && (file2_rename ==null || file2_rename.equals(""))) {
				//1만 있을 때
				dao.insert_replyBbs3(m,code,ori_thread,ori_fid);
			}else if(file2_rename !=null && !file2_rename.equals("") && (file1_rename ==null || file1_rename.equals(""))) {
				//2만 있을 때
				dao.insert_replyBbs4(m,code,ori_thread,ori_fid);
			}else {//둘다있
				dao.insert_replyBbs1(m,code,ori_thread,ori_fid);
			}
			
			
			out.println("<script>alert('게시글이 등록 되었습니다');</script>");
			
			response.sendRedirect("/bbs/list?code="+code);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
