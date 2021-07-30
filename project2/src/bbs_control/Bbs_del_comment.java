package bbs_control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BbsDAO;

@WebServlet("/del_comment")
public class Bbs_del_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Bbs_del_comment() {
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
		String id= request.getParameter("id");//게시글 작성자 아이디
		String code=request.getParameter("code");
		int uid=Integer.parseInt(request.getParameter("uid"));
		int tb_uid=Integer.parseInt(request.getParameter("tb_uid"));
		String tb_id=request.getParameter("tb_id");
		String s_id=request.getParameter("s_id");
		
		int pageNum = 1;//현재 페이지
		response.setContentType("text/html; charset=utf-8");
		if(request.getParameter("pageNum") !=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}else {
			pageNum=1;
		}
		int pageNum_view = 1;//현재 페이지
		if(request.getParameter("pageNum_view") !=null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum_view"));
		}else {
			pageNum_view=1;
		}		
		
		
		BbsDAO dao = new BbsDAO();
		dao.deleteComment(code,uid,tb_uid,tb_id);

		response.sendRedirect("/bbs/view?code="+code+"&uid="+tb_uid+"&id="+id+"&tb_id="+s_id+"&pageNum="+pageNum+"&pageNum_view="+pageNum_view);
	}
}
