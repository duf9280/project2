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

@WebServlet("/reply_comment")
public class Bbs_reply_comment_insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Bbs_reply_comment_insert() {
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
		
		int o_fid=Integer.parseInt(request.getParameter("o_fid"));
		int tb_uid=Integer.parseInt(request.getParameter("tb_uid"));
		String ori_thread=request.getParameter("o_thread");
		String tb_table=request.getParameter("tb_table");
		String tb_id = request.getParameter("tb_id");
		String tb_nickname = request.getParameter("tb_nickname");
		String tb_comment = request.getParameter("rep_com");
		
		Date now = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		String signdate = s.format(now);
		
		
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
			out.println("alert('대댓글 내용을 작성해주십시오.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			
			Comment c = new Comment();
			c.setTb_uid(tb_uid);
			c.setTb_comment(tb_comment);
			c.setTb_id(tb_id);
			c.setTb_nickname(tb_nickname);
			c.setTb_table(tb_table);
			c.setTb_date(signdate);
			BbsDAO dao = new BbsDAO();
			dao.insert_replyCom(c,ori_thread,o_fid);
			
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('대댓글이 등록 되었습니다');");
			out.print("window.opener.location.reload();");
			out.print("self.close();");		
			out.print("</script>");
	//		out.println("<script>alert('대댓글이 등록 되었습니다');</script>");
	//		
	//		response.sendRedirect("/bbs/list?code="+tb_table);
		}
	}
}
