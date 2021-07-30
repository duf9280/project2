package market_control;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
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
import dao.MarketDAO;
import model.Product;

@WebServlet("/market/product_update.do")
public class Market_update_product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Market_update_product() {
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
			
			String uploadPath="C:\\jsp\\project2\\WebContent\\market\\upload";
			int size=10*1024*1024;//10Mbyte용량제한
			
			MultipartRequest multi = new MultipartRequest(request,uploadPath,size,"utf-8",new DefaultFileRenamePolicy());	
			
			Enumeration files = multi.getFileNames();	
			String cat=multi.getParameter("cat");
			String file1 = (String)files.nextElement();
			String file1_rename = multi.getFilesystemName(file1);
			String file2 = (String)files.nextElement();
			String file2_rename = multi.getFilesystemName(file2);	
			String file3 = (String)files.nextElement();
			String file3_rename = multi.getFilesystemName(file3);				
			
			//썸네일
			String filePath = uploadPath;
			
			String orgImg = filePath+"\\"+file1_rename;//원본파일명
			String thum_name="thum_"+ file1_rename;//썸네일 파일명
			String thumbImg = filePath+"\\"+thum_name;//경로 + 썸네일파일명
			
			int thumbWidth = 120 ;//썸네일 가로
			int thumbHeight = 80 ;//썸네일 세로
			
			Image thumbnail = JimiUtils.getThumbnail(orgImg, thumbWidth, thumbHeight, Jimi.IN_MEMORY);// 썸네일 설정
			
			request.setCharacterEncoding("utf-8");
			String code= multi.getParameter("code");
			
			String zipcode=multi.getParameter("zipcode");
			String zip1=multi.getParameter("zip1");
			String zip2=multi.getParameter("zip2");
			String zip3=multi.getParameter("zip3");
			
			String p_name2 = multi.getParameter("p_name2");
			int price2=Integer.parseInt(multi.getParameter("price2"));
			int number2=Integer.parseInt(multi.getParameter("number2"));
			
			if(multi.getParameter("p_name2").equals("") || multi.getParameter("p_name2")==null) {
				p_name2="";
				price2=0;
				number2=0;
			}
			String p_name3 = multi.getParameter("p_name3");
			int price3=Integer.parseInt(multi.getParameter("price3"));
			int number3=Integer.parseInt(multi.getParameter("number3"));
			
			if(multi.getParameter("p_name3").equals("") || multi.getParameter("p_name3")==null) {
				p_name3="";
				price3=0;
				number3=0;
			}			
			
			Product m = new Product();
			
			
				m.setUid(Integer.parseInt(multi.getParameter("uid")));
				m.setId(multi.getParameter("id"));
				m.setSubject(multi.getParameter("subject"));
				m.setComment(multi.getParameter("comment"));
				m.setCategory(multi.getParameter("category"));
				m.setP_name1(multi.getParameter("p_name1"));
				m.setPrice1(Integer.parseInt(multi.getParameter("price1")));
				m.setNumber1(Integer.parseInt(multi.getParameter("number1")));
				m.setP_name2(p_name2);
				m.setPrice2(price2);
				m.setNumber2(number2);	
				m.setP_name3(p_name3);
				m.setPrice3(price3);
				m.setNumber3(number3);	
				m.setBaesong(Integer.parseInt(multi.getParameter("baesong")));
				m.setBaesong_free(Integer.parseInt(multi.getParameter("baesong_free")));
				m.setGongji(multi.getParameter("gongji"));
				m.setFile1_o(file1_rename);
				m.setFile1_s(thum_name);
				m.setFile2_o(file2_rename);
				m.setFile3_o(file3_rename);
				m.setAccount_name(multi.getParameter("account_name"));
				m.setAccount_num(multi.getParameter("account_num"));
				m.setZipcode(zipcode);
				m.setZip1(zip1);
				m.setZip2(zip2);
				m.setZip3(zip3);
//			System.out.println("짜잔\n"+m.toString()+"\n끼룩");
				
			MarketDAO dao = new MarketDAO();
			response.setContentType("text/html; charset=utf-8");
			if(file1_rename ==null && file2_rename ==null) {
				//첨부 둘 다 없을 때
				dao.updateProduct4(m);
			}else if(file1_rename !=null && !file1_rename.equals("") && (file2_rename ==null || file2_rename.equals(""))) {
				//1만 있을 때
				dao.updateProduct2(m);
			}else if(file2_rename !=null && !file2_rename.equals("") && (file1_rename ==null || file1_rename.equals(""))) {
				//2만 있을 때
				dao.updateProduct3(m);
			}else {
				dao.updateProduct1(m);
			}
			
			PrintWriter out = response.getWriter();
			
			response.setContentType("text/html; charset=utf-8");
			if(!cat.equals("2")) {
				response.sendRedirect("/market/list?code="+code+"&category="+multi.getParameter("category"));
			}else {
				response.sendRedirect("/manage/bbs/my_items?id="+multi.getParameter("id")+"&code="+code+"&category="+multi.getParameter("category"));
			}
			out.close();
			
			
			
//			response.sendRedirect("/bbs/list?code="+code);
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
