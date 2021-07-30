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
import model.Reco;

@WebServlet("/reco.do")
public class Bbs_view_reco extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Bbs_view_reco() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String code = request.getParameter("code");
		String id=request.getParameter("id");
		String tb_table = request.getParameter("tb_table");//게시판 이름
		String tb_name = request.getParameter("nickname");//추천하는 사람 닉?
		String tb_id = request.getParameter("tb_id");//추천하는 사람 아이디?
		int tb_uid = Integer.parseInt(request.getParameter("uid"));//추천이 갱신되는 게시글의 uid
		int pageNum =Integer.parseInt(request.getParameter("pageNum"));
		String field = request.getParameter("field");
		String search = request.getParameter("search");
		String reco_ok= request.getParameter("tb_reco");//y,n
		int pageNum_view = Integer.parseInt(request.getParameter("pageNum_view"));
		System.out.println(pageNum_view);
		
		
		
		if(tb_id.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			
			out.println("<script>");
			out.println("alert('로그인 필요');");
			out.println("history.back();");
			out.println("</script>");
		
		}else {
		
		
		//////////////
		//시간
		Date now = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		String signdate = s.format(now);

		BbsDAO dao = new BbsDAO();

		
		
		Reco r = new Reco();
		r.setTb_table(tb_table);
		r.setTb_uid(tb_uid);
		r.setTb_id(tb_id);
		r.setTb_name(tb_name);
		r.setTb_reco(reco_ok);
		r.setTb_date(signdate);
		dao.insertReco(r);
//		System.out.println(tb_table);

		response.sendRedirect("/bbs/view?code="+tb_table+"&uid="+tb_uid+"&id="+id+"&tb_id="+tb_id+"&pageNum="+pageNum+"&pageNum_view="+pageNum_view);
//		String loc= "/bbs/view?code="+tb_table+"&uid="+tb_uid+"&id="+id+"&tb_id="+tb_id;
//		PrintWriter out = response.getWriter();
//		out.println("<script>");
//		out.println("alert('aaa');");
//		out.println("location.href="+loc);
//		out.println("</script>");
//		out.close();
		}
	}

}
