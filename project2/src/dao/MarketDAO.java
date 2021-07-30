package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Lecture;
import model.Product;

public class MarketDAO {
	
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO d = new DAO();

	//상품 등록
	public void productInsert(Product p) {
		d.getCon();
		
		try {
			String sql = "insert into product(id,subject,comment,category,p_name1,price1,number1,p_name2,price2,number2,p_name3,price3,number3,baesong,baesong_free,gongji,file1_o,file1_s,file2_o,file3_o,account_name,account_num,zipcode,zip1,zip2,zip3,signdate) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, p.getId());
			pstmt.setString(2, p.getSubject());
			pstmt.setString(3, p.getComment());
			pstmt.setString(4, p.getCategory());
			pstmt.setString(5, p.getP_name1());
			pstmt.setInt(6, p.getPrice1());
			pstmt.setInt(7, p.getNumber1());
			pstmt.setString(8, p.getP_name2());
			pstmt.setInt(9, p.getPrice2());
			pstmt.setInt(10, p.getNumber2());
			pstmt.setString(11, p.getP_name3());
			pstmt.setInt(12, p.getPrice3());
			pstmt.setInt(13, p.getNumber3());
			pstmt.setInt(14, p.getBaesong());
			pstmt.setInt(15, p.getBaesong_free());
			
			pstmt.setString(16, p.getFile1_o());
			pstmt.setString(17, p.getFile1_s());
			pstmt.setString(18, p.getFile2_o());
			pstmt.setString(19, p.getFile3_o());
			pstmt.setString(20, p.getAccount_name());
			pstmt.setString(21, p.getAccount_num());
			pstmt.setString(22, p.getZipcode());
			pstmt.setString(23, p.getZip1());
			pstmt.setString(24, p.getZip2());
			pstmt.setString(25, p.getZip3());
			pstmt.setString(26, p.getSigndate());
			
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				d.conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//일반 상품 view펭이지
	public Product viewProduct(String id, int uid, String code, String category) {
		d.getCon();
		Product view = new Product();
		try {			
			
			String sql = "select * from "+code+" where uid=? and id=? and category=?";
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setString(2, id);
			pstmt.setString(3, category);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				view.setUid(rs.getInt("uid"));
				view.setId(rs.getString("id"));
				view.setSubject(rs.getString("subject"));
				view.setComment(rs.getString("comment"));
				view.setCategory(rs.getString("category"));
				view.setP_name1(rs.getString("p_name1"));
				view.setPrice1(rs.getInt("price1"));
				view.setNumber1(rs.getInt("number1"));
				view.setP_name2(rs.getString("p_name2"));
				view.setPrice2(rs.getInt("price2"));
				view.setNumber2(rs.getInt("number2"));
				view.setP_name3(rs.getString("p_name3"));
				view.setPrice3(rs.getInt("price3"));
				view.setNumber3(rs.getInt("number3"));
				view.setBaesong(rs.getInt("baesong"));
				view.setBaesong_free(rs.getInt("baesong_free"));
				view.setGongji(rs.getString("gongji"));
				view.setFile1_o(rs.getString("file1_o"));
				view.setFile1_s(rs.getString("file1_s"));
				view.setFile2_o(rs.getString("file2_o"));
				view.setFile3_o(rs.getString("file3_o"));		
				view.setAccount_name(rs.getString("account_name"));
				view.setAccount_num(rs.getString("account_num"));	
				view.setZipcode(rs.getString("zipcode"));
				view.setZip1(rs.getString("zip1"));
				view.setZip2(rs.getString("zip2"));
				view.setZip3(rs.getString("zip3"));
				view.setSigndate(rs.getString("signdate"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				d.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return view;
	}
	public void updateProduct1(Product m) {
		d.getCon();
		try {
			String sql = "update product set subject=?,comment=?,category=?,p_name1=?,price1=?,number1=?,p_name2=?,price2=?,number2=?,p_name3=?,price3=?,number3=?,baesong=?,baesong_free=?,gongji=?,file1_o=?,file1_s=?,file2_o=?,file3_o=?,account_name=?,account_num=?,zipcode=?,zip1=?,zip2=?,zip3=? where uid=?";
//			System.out.println("1");
			System.out.println(m.getFile1_o());
			System.out.println(m.getFile1_s());
			System.out.println(m.getFile2_o());
			System.out.println(m.getFile3_o());
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getSubject());
			pstmt.setString(2, m.getComment());
			pstmt.setString(3, m.getCategory());
			
			pstmt.setString(4, m.getP_name1());
			pstmt.setInt(5, m.getPrice1());
			pstmt.setInt(6, m.getNumber1());
			pstmt.setString(7, m.getP_name2());
			pstmt.setInt(8, m.getPrice2());
			pstmt.setInt(9, m.getNumber2());
			
			pstmt.setString(10, m.getP_name3());
			pstmt.setInt(11, m.getPrice3());
			pstmt.setInt(12, m.getNumber3());	
			
			pstmt.setInt(13, m.getBaesong());
			pstmt.setInt(14, m.getBaesong_free());
			pstmt.setString(15, m.getGongji());
			pstmt.setString(16, m.getFile1_o());
			pstmt.setString(17, m.getFile1_s());
			pstmt.setString(18, m.getFile2_o());
			pstmt.setString(19, m.getFile3_o());
			pstmt.setString(20, m.getAccount_name());
			pstmt.setString(21, m.getAccount_num());
			pstmt.setString(22, m.getZipcode());
			pstmt.setString(23, m.getZip1());
			pstmt.setString(24, m.getZip2());
			pstmt.setString(25, m.getZip3());
			
			
			
			pstmt.setInt(26, m.getUid());
			
			pstmt.executeUpdate();
			

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				d.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void updateProduct2(Product m) {
		d.getCon();
		try {
			String sql = "update product set subject=?,comment=?,category=?,p_name1=?,price1=?,number1=?,p_name2=?,price2=?,number2=?,p_name3=?,price3=?,number3=?,baesong=?,baesong_free=?,gongji=?,file1_o=?,file1_s=?,file2_o=?,account_name=?,account_num=?,zipcode=?,zip1=?,zip2=?,zip3=? where uid=?";
			
//			System.out.println("2");
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getSubject());
			pstmt.setString(2, m.getComment());
			pstmt.setString(3, m.getCategory());
			
			pstmt.setString(4, m.getP_name1());
			pstmt.setInt(5, m.getPrice1());
			pstmt.setInt(6, m.getNumber1());
			pstmt.setString(7, m.getP_name2());
			pstmt.setInt(8, m.getPrice2());
			pstmt.setInt(9, m.getNumber2());
			
			pstmt.setString(10, m.getP_name3());
			pstmt.setInt(11, m.getPrice3());
			pstmt.setInt(12, m.getNumber3());	
			
			pstmt.setInt(13, m.getBaesong());
			pstmt.setInt(14, m.getBaesong_free());
			pstmt.setString(15, m.getGongji());
			pstmt.setString(16, m.getFile1_o());
			pstmt.setString(17, m.getFile1_s());
			pstmt.setString(18, m.getFile2_o());
			pstmt.setString(19, m.getAccount_name());
			pstmt.setString(20, m.getAccount_num());
			pstmt.setString(21, m.getZipcode());
			pstmt.setString(22, m.getZip1());
			pstmt.setString(23, m.getZip2());
			pstmt.setString(24, m.getZip3());
			
			pstmt.setInt(25, m.getUid());
			
			pstmt.executeUpdate();
			

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				d.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void updateProduct3(Product m) {
		d.getCon();
		try {
			String sql = "update product set subject=?,comment=?,category=?,p_name1=?,price1=?,number1=?,p_name2=?,price2=?,number2=?,p_name3=?,price3=?,number3=?,baesong=?,baesong_free=?,gongji=?,file1_o=?,file1_s=?,file3_o=?,account_name=?,account_num=?,zipcode=?,zip1=?,zip2=?,zip3=? where uid=?";
//			System.out.println("3");
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getSubject());
			pstmt.setString(2, m.getComment());
			pstmt.setString(3, m.getCategory());
			
			pstmt.setString(4, m.getP_name1());
			pstmt.setInt(5, m.getPrice1());
			pstmt.setInt(6, m.getNumber1());
			pstmt.setString(7, m.getP_name2());
			pstmt.setInt(8, m.getPrice2());
			pstmt.setInt(9, m.getNumber2());
			
			pstmt.setString(10, m.getP_name3());
			pstmt.setInt(11, m.getPrice3());
			pstmt.setInt(12, m.getNumber3());	
			
			pstmt.setInt(13, m.getBaesong());
			pstmt.setInt(14, m.getBaesong_free());
			pstmt.setString(15, m.getGongji());
			pstmt.setString(16, m.getFile1_o());
			pstmt.setString(17, m.getFile1_s());
			pstmt.setString(18, m.getFile3_o());
			pstmt.setString(19, m.getAccount_name());
			pstmt.setString(20, m.getAccount_num());
			pstmt.setString(21, m.getZipcode());
			pstmt.setString(22, m.getZip1());
			pstmt.setString(23, m.getZip2());
			pstmt.setString(24, m.getZip3());
			
			pstmt.setInt(25, m.getUid());
			
			pstmt.executeUpdate();
			

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				d.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void updateProduct4(Product m) {
		d.getCon();
		try {
			String sql = "update product set subject=?,comment=?,category=?,p_name1=?,price1=?,number1=?,p_name2=?,price2=?,number2=?,p_name3=?,price3=?,number3=?,baesong=?,baesong_free=?,gongji=?,file1_o=?,file1_s=?,account_name=?,account_num=?,zipcode=?,zip1=?,zip2=?,zip3=? where uid=?";
			
//			System.out.println("4");
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getSubject());
			pstmt.setString(2, m.getComment());
			pstmt.setString(3, m.getCategory());
			
			pstmt.setString(4, m.getP_name1());
			pstmt.setInt(5, m.getPrice1());
			pstmt.setInt(6, m.getNumber1());
			pstmt.setString(7, m.getP_name2());
			pstmt.setInt(8, m.getPrice2());
			pstmt.setInt(9, m.getNumber2());
			
			pstmt.setString(10, m.getP_name3());
			pstmt.setInt(11, m.getPrice3());
			pstmt.setInt(12, m.getNumber3());	
			
			pstmt.setInt(13, m.getBaesong());
			pstmt.setInt(14, m.getBaesong_free());
			pstmt.setString(15, m.getGongji());
			pstmt.setString(16, m.getFile1_o());
			pstmt.setString(17, m.getFile1_s());
			pstmt.setString(18, m.getAccount_name());
			pstmt.setString(19, m.getAccount_num());
			pstmt.setString(20, m.getZipcode());
			pstmt.setString(21, m.getZip1());
			pstmt.setString(22, m.getZip2());
			pstmt.setString(23, m.getZip3());
			
			pstmt.setInt(24, m.getUid());
			
			pstmt.executeUpdate();
			

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				d.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
