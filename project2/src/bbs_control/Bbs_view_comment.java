package bbs_control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import model.Comment;

@WebServlet("/bbs_view_comment")
public class Bbs_view_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Bbs_view_comment() {
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
		try {//이 컨트롤러는 댓글 작성
			String id= request.getParameter("id");//게시글 작성자 아이디
			int uid = Integer.parseInt(request.getParameter("uid"));
			int tb_uid = Integer.parseInt(request.getParameter("uid"));
			String tb_id = request.getParameter("tb_id");
			String tb_nickname =request.getParameter("tb_nickname");
			String tb_comment = request.getParameter("tb_comment");
			String tb_table = request.getParameter("code");
			String s_id=request.getParameter("s_id");
			int pageNum_view = Integer.parseInt(request.getParameter("pageNum_view"));
			
			Date now = new Date();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
			String signdate = s.format(now);
			
			int pageNum = 1;//현재 페이지
			
			
			if(request.getParameter("pageNum") !=null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}else {
				pageNum=1;
			}
			
			if(tb_id.equals("")) {
				
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('로그이이이이이이인');");
				out.println("history.back();");
				out.println("</script>");
				
			}else if(tb_comment.equals("")) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('댓글 내용을 작성해주십시오.');");
				out.println("history.back();");
				out.println("</script>");
			}else {			
				
			BbsDAO dao = new BbsDAO();		
			Comment m = new Comment();
			m.setTb_table(tb_table);
			m.setTb_uid(tb_uid);
			m.setTb_id(tb_id);
			m.setTb_nickname(tb_nickname);
			m.setTb_comment(tb_comment);
			m.setTb_date(signdate);
			dao.writeComment(m);
			
			
			response.sendRedirect("/bbs/view?code="+tb_table+"&uid="+uid+"&id="+id+"&tb_id="+s_id+"&pageNum="+pageNum+"&pageNum_view="+pageNum_view);
////		RequestDispatcher dis = request.getRequestDispatcher("/bbs/view");
////		dis.forward(request, response);
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
