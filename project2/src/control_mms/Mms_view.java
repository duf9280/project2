package control_mms;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MmsDAO;
import model.Mms;


@WebServlet("/mms/view")
public class Mms_view extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Mms_view() {
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
			String type = request.getParameter("type");
			int uid = Integer.parseInt(request.getParameter("uid"));
			String pageNum = request.getParameter("pageNum");
			String session_id = request.getParameter("id");
			
			Date now = new Date();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
			String signdate = s.format(now);
			
			
			MmsDAO dao = new MmsDAO();
			
			
			Mms view = dao.viewMms(uid);
			
			request.setAttribute("view", view);
			request.setAttribute("uid", uid);
			request.setAttribute("pageNum", pageNum);
			
			String re_id = view.getReceive_id();
			
			if(session_id.equals(re_id)) {
				dao.readMms(uid,signdate);
			}
			
			
			RequestDispatcher dis = request.getRequestDispatcher("view.jsp");
			dis.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
