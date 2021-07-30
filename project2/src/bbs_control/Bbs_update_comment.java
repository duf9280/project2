package bbs_control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;
import model.Comment;

@WebServlet("/mod_com")
public class Bbs_update_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Bbs_update_comment() {
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
		
		String s_id = request.getParameter("s_id");
		Comment com = new Comment();
		int uid = Integer.parseInt(request.getParameter("uid"));
		String comment = request.getParameter("comments");
		
		if(s_id.equals("")) {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('로그이이이이이이인');");
			out.println("history.back();");
			out.println("</script>");
			
		}else if(comment.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('댓글 내용을 작성해주십시오.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
					
			com.setUid(uid);
			com.setTb_comment(comment);
	//		System.out.println("asdasd="+uid);
	//		System.out.println("asdasd="+comment);
			
			BbsDAO dao = new BbsDAO();
			dao.updateComment(com);
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('댓글수정 완료');");
	//		out.print("hi;");
			out.print("window.opener.location.reload();");
			out.print("self.close();");
	//		out.print("location.href='/bbs/modify_com?uid="+uid+"';");
			
			out.print("</script>");
	//		response.sendRedirect("/bbs/modify_com?uid="+uid);
		}
	}
}
