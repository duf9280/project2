package market_control;

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
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiUtils;

import dao.BuyDAO;
import dao.MarketDAO;
import model.Product;

@WebServlet("/market/productwrite.do")
public class Market_write_product extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Market_write_product() {
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
			String uploadPath="C:\\jsp\\project2\\WebContent\\market\\upload";
			int size=10*1024*1024;//10Mbyte용량제한
			
			MultipartRequest multi = new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());	
			
			Enumeration files = multi.getFileNames();
			
			String file1 = (String)files.nextElement();
			String file1_rename = multi.getFilesystemName(file1);
			
			String file2 = (String)files.nextElement();
			String file2_rename = multi.getFilesystemName(file2);
			
			String file3 = (String)files.nextElement();
			String file3_rename = multi.getFilesystemName(file3);
			
			request.setCharacterEncoding("utf-8");
			String code= multi.getParameter("code");
			String category = multi.getParameter("category");
			String price2 = multi.getParameter("price2");
			String number2 = multi.getParameter("number2");
			String price3 = multi.getParameter("price3");
			String number3 = multi.getParameter("number3");
			
			Date now = new Date();
			String str1 = now.toString();
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
			String signdate = s.format(now);
			
			HttpSession session = request.getSession();
			
			String session_id = (String)session.getAttribute("id");
			
			String thum_name = "";
			if(file1 != null) {
				//썸네일
				String filePath = uploadPath;
				
				String orgImg = filePath+"\\"+file1_rename;//원본파일명
				thum_name="thum_"+ file1_rename;//썸네일 파일명
				String thumbImg = filePath+"\\"+thum_name;//경로 + 썸네일파일명
				
				int thumbWidth = 120 ;//썸네일 가로
				int thumbHeight = 80 ;//썸네일 세로
				
				Image thumbnail = JimiUtils.getThumbnail(orgImg, thumbWidth, thumbHeight, Jimi.IN_MEMORY);// 썸네일 설정
				Jimi.putImage(thumbnail, thumbImg);
			}else {
				file1 = "";
				file1_rename = "";
			}
			
			Product p = new Product();
			
			p.setId(multi.getParameter("id"));
			p.setSubject(multi.getParameter("subject"));
			p.setComment(multi.getParameter("comment"));
			p.setCategory(multi.getParameter("category"));
			p.setAccount_name(multi.getParameter("account_name"));
			p.setAccount_num(multi.getParameter("account_num"));
			p.setSigndate(signdate);
			p.setId(session_id);
			p.setBaesong(Integer.parseInt(multi.getParameter("baesong")));
			p.setBaesong_free(Integer.parseInt(multi.getParameter("baesong_free")));
			p.setP_name1(multi.getParameter("p_name1"));
			p.setPrice1(Integer.parseInt(multi.getParameter("price1")));
			p.setNumber1(Integer.parseInt(multi.getParameter("number1")));
			p.setFile1_o(file1_rename);
			p.setFile1_s(thum_name);
			
			p.setP_name2(multi.getParameter("p_name2"));
			if((price2 != null && !price2.equals("")) || (number2 != null && !number2.equals(""))) {
				p.setPrice2(Integer.parseInt(multi.getParameter("price2")));
				p.setNumber2(Integer.parseInt(multi.getParameter("number2")));
			}
			p.setFile2_o(file2_rename);
				
			p.setP_name3(multi.getParameter("p_name3"));
			if((price3 != null && !price3.equals("")) || (number3 != null && !number3.equals(""))) {
				p.setPrice3(Integer.parseInt(multi.getParameter("price3")));
				p.setNumber3(Integer.parseInt(multi.getParameter("number3")));
			}
			p.setFile3_o(file3_rename);
			
			p.setZipcode(multi.getParameter("zipcode"));
			p.setZip1(multi.getParameter("zip1"));	
			p.setZip2(multi.getParameter("zip2"));
			p.setZip3(multi.getParameter("zip3"));
			
			MarketDAO dao = new MarketDAO();
			dao.productInsert(p);
			
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품이 등록 되었습니다');</script>");
			response.sendRedirect("list?code="+code+"&category="+category);
		}catch (Exception e) {
			e.printStackTrace();	
		}
	}
}
