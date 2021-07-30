package control_mms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MmsDAO;


@WebServlet("/mms/mms_delete")
public class Mms_delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Mms_delete() {
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
			String[] chck = request.getParameterValues("chck");
			String type = request.getParameter("type");
			String session_id = request.getParameter("session_id");
			MmsDAO dao = new MmsDAO();
			
			String c_uid = "";
			
			if(chck != null) {
				if(type.equals("send")) {
					for(int i=0; i<chck.length;i++) {
						c_uid = chck[i];
						
						dao.sendMmsDel(c_uid);
					}
				}else {
					for(int i=0; i<chck.length;i++) {
						c_uid = chck[i];
						
						dao.receiveMmsDel(c_uid);
					}
				}
					dao.MmsDel();//여기가 받은쪽지 보낸쪽지 둘다 삭제시 아예 삭제하는 DAO
				if(type != null && type.equals("send")) {
					response.sendRedirect("/mms/list?session_id="+session_id+"&type="+type);	
				}else {
					response.sendRedirect("/mms/list?session_id="+session_id+"&type="+type);
				}
			}else {
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("window.history.back();");
				out.println("</script>");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
