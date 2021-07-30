package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Cart;
import model.Lecture;
import model.Product;

public class ManageDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO d = new DAO();
	
	
	//모든 강의,상품수 - 검색 판매자용
	public int getAllCount_my_lect_prod1(String search, String field, String code, String category,String session_id) {
		d.getCon();
		int num = 0;
		try {

			String sql = "select count(*) from "+code+" where category=? and id=? and "+field+" like ?";
			
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, session_id);
			pstmt.setString(2, category);
			pstmt.setString(3, "%"+search+"%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미
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
		return num;
	}
	
	//모든 강의,상품수 - 검색이 아닐 때 판매자용
	public int getAllCount_my_lect_prod2(String code, String category,String session_id){
		d.getCon();
		int num = 0;

		try {
//			System.out.println(num);
//			System.out.println(code);
			String sql = "select count(*) from "+code+" where id=? and category=?";
		
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, session_id);
			pstmt.setString(2, category);	
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미
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
		return num;
	}	
	
	
	
	//모든 강의 목록 - 검색
	public ArrayList<Lecture> allItem_lecture1(int startNum, int endNum, String search, String field, String code, String category,String session_id) {
		d.getCon();
		ArrayList<Lecture> v = new ArrayList<Lecture>();
		
		try {
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where id=? and category=? and gongji!='3' and "+field+" like ? order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);
			
			pstmt.setString(1, session_id);
			pstmt.setString(2, category);
			pstmt.setString(3, "%"+search+"%");
			pstmt.setInt(4, startNum);
			pstmt.setInt(5, endNum);
//			System.out.println(startNum);
//			System.out.println(endNum);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				Lecture all = new Lecture();
				all.setUid(rs.getInt("uid"));
				all.setId(rs.getString("id"));
				all.setSubject(rs.getString("subject"));
				all.setComment(rs.getString("comment"));
				all.setCategory(rs.getString("category"));
				all.setPrice1(rs.getInt("price1"));
				all.setGongji(rs.getString("gongji"));
				all.setFile1_o(rs.getString("file1_o"));
				all.setFile1_s(rs.getString("file1_s"));
				all.setFile2_o(rs.getString("file2_o"));
				all.setFile3_o(rs.getString("file3_o"));
				all.setAccount_name(rs.getString("account_name"));
				all.setAccount_num(rs.getString("account_num"));
				all.setSigndate(rs.getString("signdate"));
				all.setReview_num(rs.getInt("review_num"));
				v.add(all);
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
		return v;
	}
	//모든 강의 목록 - 검색ㄴㄴ
	public ArrayList<Lecture> allItem_lecture2(int startNum, int endNum, String code, String category,String session_id) {
		d.getCon();
		ArrayList<Lecture> v = new ArrayList<Lecture>();
		try {
			//이거는 부분 조인 - 게시글 목록과 가상컬럼을 추가해서 댓글 수를 출력하기 위해 컬럼에 각 게시글의 댓글 수를 받는 구문
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where id=? and category=? and gongji!='3' order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);
			pstmt.setString(1, session_id);
			pstmt.setString(2, category);
			pstmt.setInt(3, startNum);
			pstmt.setInt(4, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Lecture all = new Lecture();
				all.setUid(rs.getInt("uid"));
				all.setId(rs.getString("id"));
				all.setSubject(rs.getString("subject"));
				all.setComment(rs.getString("comment"));
				all.setCategory(rs.getString("category"));
				all.setPrice1(rs.getInt("price1"));
				all.setGongji(rs.getString("gongji"));
				all.setFile1_o(rs.getString("file1_o"));
				all.setFile1_s(rs.getString("file1_s"));
				all.setFile2_o(rs.getString("file2_o"));
				all.setFile3_o(rs.getString("file3_o"));
				all.setAccount_name(rs.getString("account_name"));
				all.setAccount_num(rs.getString("account_num"));
				all.setSigndate(rs.getString("signdate"));
				all.setReview_num(rs.getInt("review_num"));
				v.add(all);
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
		return v;
	}	

	//모든 상품 목록 - 검색
	public ArrayList<Product> allItem_product1(int startNum, int endNum, String search, String field, String code, String category,String session_id) {
		d.getCon();
		ArrayList<Product> v = new ArrayList<Product>();
		
		try {
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where id=? and category=? and "+field+" like ? order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);
			pstmt.setString(1, session_id);
			pstmt.setString(2, category);
			pstmt.setString(3, "%"+search+"%");
			pstmt.setInt(4, startNum);
			pstmt.setInt(5, endNum);
//			System.out.println(startNum);
//			System.out.println(endNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product all = new Product();
				all.setUid(rs.getInt("uid"));
				all.setId(rs.getString("id"));
				all.setSubject(rs.getString("subject"));
				all.setComment(rs.getString("comment"));
				all.setCategory(rs.getString("category"));
				all.setPrice1(rs.getInt("price1"));
				all.setGongji(rs.getString("gongji"));
				all.setFile1_o(rs.getString("file1_o"));
				all.setFile1_s(rs.getString("file1_s"));
				all.setFile2_o(rs.getString("file2_o"));
				all.setFile3_o(rs.getString("file3_o"));
				all.setAccount_name(rs.getString("account_name"));
				all.setAccount_num(rs.getString("account_num"));
				all.setSigndate(rs.getString("signdate"));
				all.setReview_num(rs.getInt("review_num"));
				v.add(all);
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
		return v;
	}
	//모든 강의 목록 - 검색ㄴㄴ
	public ArrayList<Product> allItem_product2(int startNum, int endNum, String code, String category,String session_id) {
		d.getCon();
		ArrayList<Product> v = new ArrayList<Product>();
		try {
			//이거는 부분 조인 - 게시글 목록과 가상컬럼을 추가해서 댓글 수를 출력하기 위해 컬럼에 각 게시글의 댓글 수를 받는 구문
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where id=? and category=? order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, session_id);
			pstmt.setString(2, category);
			pstmt.setInt(3, startNum);
			pstmt.setInt(4, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product all = new Product();
				all.setUid(rs.getInt("uid"));
				all.setId(rs.getString("id"));
				all.setSubject(rs.getString("subject"));
				all.setComment(rs.getString("comment"));
				all.setCategory(rs.getString("category"));
				all.setPrice1(rs.getInt("price1"));
				all.setGongji(rs.getString("gongji"));
				all.setFile1_o(rs.getString("file1_o"));
				all.setFile1_s(rs.getString("file1_s"));
				all.setFile2_o(rs.getString("file2_o"));
				all.setFile3_o(rs.getString("file3_o"));
				all.setAccount_name(rs.getString("account_name"));
				all.setAccount_num(rs.getString("account_num"));
				all.setSigndate(rs.getString("signdate"));
				all.setReview_num(rs.getInt("review_num"));
				v.add(all);
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
		return v;
	}
	
	public void goBaesong(String cart_name,String subject,int price,Cart c) {
		d.getCon();
		try {

		String sql="update cart set baesong_com=?, baesong_num=?, baesong_date=?, baesong_status='3' where cart_name=? and subject=? and price=?";

		pstmt=d.conn.prepareStatement(sql);
		pstmt.setString(1, c.getBaesong_com());
		pstmt.setString(2, c.getBaesong_num());
		pstmt.setString(3, c.getBaesong_date());
		pstmt.setString(4, cart_name);
		pstmt.setString(5, subject);
		pstmt.setInt(6, price);
//		System.out.println(sql);
//		System.out.println(cart_name);
//		System.out.println(subject);
//		System.out.println(price);
//		System.out.println(c.getBaesong_com());
//		System.out.println(c.getBaesong_num());
//		System.out.println(c.getBaesong_date());
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
	
	public void manageBaesong_st(Cart c, String baesong_status) {
		d.getCon();
		try {
			String sql="update cart set baesong_status=? where cart_name=? and buy_id=? and subject=? and price=? and baesong=?";
			pstmt=d.conn.prepareStatement(sql);
			pstmt.setString(1, baesong_status);
			pstmt.setString(2, c.getCart_name());
			pstmt.setString(3, c.getBuy_id());
			pstmt.setString(4, c.getSubject());
			pstmt.setInt(5, c.getPrice());
			pstmt.setInt(6, c.getBaesong());
			
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
