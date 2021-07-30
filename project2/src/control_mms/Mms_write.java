package control_mms;

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

import dao.MmsDAO;
import model.Mms;

@WebServlet("/mms/write.do")
public class Mms_write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Mms_write() {
        super();
        // TODO Auto-generated constructor stub
    }


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doAll(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doAll(request, response);
}
protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {
		
		String uploadPath="C:\\jsp\\project2\\WebContent\\mms\\upload";
		int size=10*1024*1024;//10Mbyte용량제한
		
		MultipartRequest multi = new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());	
		
		Enumeration files = multi.getFileNames();	
		
		String file1 = (String)files.nextElement();
		String file1_rename = multi.getFilesystemName(file1);
		String session_id = multi.getParameter("session_id");
		String type = multi.getParameter("type");
		Date now = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		String signdate = s.format(now);
		
		request.setCharacterEncoding("utf-8");
		Mms m = new Mms();
			m.setSend_id(multi.getParameter("session_id"));
			m.setSend_nickname(multi.getParameter("nickname"));
			m.setReceive_id(multi.getParameter("receive_id"));
			m.setReceive_nickname(multi.getParameter("receive_nickname"));
			m.setSubject(multi.getParameter("subject"));
			m.setComment(multi.getParameter("comment"));
			m.setWrite_day(signdate);
			m.setFile1(file1_rename);

			
		MmsDAO dao = new MmsDAO();
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		if(file1_rename !=null && !file1_rename.equals("")){
			dao.insertMms1(m);
		}else {
			dao.insertMms2(m);
		}
		response.sendRedirect("list?session_id="+session_id+"&type="+type);
	}catch(Exception e) {
		e.printStackTrace();
	}		
}
	
}
