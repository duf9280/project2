package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Bbs;
import model.Comment;
import model.Lecture;
import model.Product;
import model.Reco;
import model.Review;

public class BbsDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO d = new DAO();
	//모든 공지수 - 검색
	public int getAllCount_gong1(String search, String field, String code) {
		d.getCon();
		int num_gong = 0;
		try {
			String sql = "select count(*) from "+code+" where gongji='1' and "+field+" like ?";
			

			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num_gong=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미
			}
//			System.out.println(num);
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
		return num_gong;
	}
	
	//모든 공지수 - 검색이 아닐 때
	public int getAllCount_gong(String code){
		d.getCon();
		int num_gong = 0;

		try {
						 

			String sql = "select count(*) from "+code+" where gongji='1'";
		

			
			pstmt = d.conn.prepareStatement(sql);
					
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num_gong=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미
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
		return num_gong;
	}	
	
	//모든 공지가 아닌 게시글 수 - 검색
	public int getAllCount(String search, String field, String code) {
		d.getCon();
		int num = 0;
		try {
			String sql = "select count(*) from "+code+" where gongji !='1' and "+field+" like ?";
			
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미

			}
//			System.out.println("dao = "+num);
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
	
	//모든 공지가 아닌 게시글 수 - 검색이 아닐 때
	public int getAllCount1(String code){
		d.getCon();
		int num = 0;

		try {
			String sql = "select count(*) from "+code+" where gongji!='1'";
			pstmt = d.conn.prepareStatement(sql);
					
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
	


	
	//모든 게시글 목록 - 검색+ 공지글 아닐때
	public ArrayList<Bbs> allBbs(int startNum, int endNum, String search, String field, String code) {
		d.getCon();
		ArrayList<Bbs> v = new ArrayList<Bbs>();
		
		try {
			String sql = "select *,(select count(*) from comment where "+code+".uid = comment.tb_uid) as comment_num from "+code+" where gongji!='1' and "+field+" like ? order by fid,thread asc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
//			System.out.println(startNum);
//			System.out.println(endNum);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				Bbs all = new Bbs();
				all.setUid(rs.getInt("uid"));
				all.setId(rs.getString("id"));
				all.setNickname(rs.getString("nickname"));
				all.setSubject(rs.getString("subject"));
				all.setComment(rs.getString("comment"));
				all.setSigndate(rs.getString("signdate"));
				all.setRef(rs.getInt("ref"));
				all.setGongji(rs.getString("gongji"));
				all.setFile1_o(rs.getString("file1_o"));
				all.setFile2_o(rs.getString("file2_o"));
				all.setFid(rs.getInt("fid"));
				all.setThread(rs.getString("thread"));
				all.setComment_num(rs.getInt("comment_num"));
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
	//모든 게시글 목록 - 검색ㄴㄴ+ 공지글 아닐때
	public ArrayList<Bbs> allBbs2(int startNum, int endNum, String code) {
		d.getCon();
		ArrayList<Bbs> v = new ArrayList<Bbs>();
		try {
			//이거는 부분 조인 - 게시글 목록과 가상컬럼을 추가해서 댓글 수를 출력하기 위해 컬럼에 각 게시글의 댓글 수를 받는 구문
			String sql = "select *,(select count(*) from comment where "+code+".uid = comment.tb_uid) as comment_num from "+code+" where gongji!='1' order by fid desc, thread asc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);

			
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Bbs all = new Bbs();
				all.setUid(rs.getInt("uid"));
				all.setId(rs.getString("id"));
				all.setNickname(rs.getString("nickname"));
				all.setSubject(rs.getString("subject"));
				all.setComment(rs.getString("comment"));
				all.setSigndate(rs.getString("signdate"));
				all.setRef(rs.getInt("ref"));
				all.setGongji(rs.getString("gongji"));
				all.setFile1_o(rs.getString("file1_o"));
				all.setFile2_o(rs.getString("file2_o"));
				all.setFid(rs.getInt("fid"));
				all.setThread(rs.getString("thread"));
				all.setComment_num(rs.getInt("comment_num"));
					
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
	//모든 게시글 목록 - 검색+ 공지글
	public ArrayList<Bbs> allBbs3(int startNum, int endNum, String search, String field, String code) {
		d.getCon();
		ArrayList<Bbs> gong = new ArrayList<Bbs>();
		try {
			String sql = "select * from "+code+" where gongji='1' and "+field+" like ? order by fid,thread asc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Bbs all = new Bbs();

				all.setId(rs.getString("id"));
				all.setNickname(rs.getString("nickname"));
				all.setSubject(rs.getString("subject"));
				all.setComment(rs.getString("comment"));
				all.setSigndate(rs.getString("signdate"));
				all.setRef(rs.getInt("ref"));
				all.setGongji(rs.getString("gongji"));
				all.setFile1_o(rs.getString("file1_o"));
				all.setFile2_o(rs.getString("file2_o"));
				all.setFid(rs.getInt("fid"));
				all.setThread(rs.getString("thread"));
				
					
				gong.add(all);
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
		return gong;
	}
	//모든 게시글 목록 - 검색ㄴㄴ+ 공지글
	public ArrayList<Bbs> allBbs4(int startNum, int endNum, String code) {
		d.getCon();
		ArrayList<Bbs> gong = new ArrayList<Bbs>();
		try {
			String sql = "select * from "+code+" where gongji='1' order by fid desc, thread asc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);

			
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Bbs all = new Bbs();
				all.setUid(rs.getInt("uid"));
				all.setId(rs.getString("id"));
				all.setNickname(rs.getString("nickname"));
				all.setSubject(rs.getString("subject"));
				all.setComment(rs.getString("comment"));
				all.setSigndate(rs.getString("signdate"));
				all.setRef(rs.getInt("ref"));
				all.setGongji(rs.getString("gongji"));
				all.setFile1_o(rs.getString("file1_o"));
				all.setFile2_o(rs.getString("file2_o"));
				all.setFid(rs.getInt("fid"));
				all.setThread(rs.getString("thread"));
					
				gong.add(all);
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
		return gong;
	}	
	
	//모든 강의,상품수 - 검색
	public int getAllCount_lect_prod1(String search, String field, String code, String category) {
		d.getCon();
		int num = 0;
		try {

			String sql = "select count(*) from "+code+" where category=? and "+field+" like ?";
			
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, "%"+search+"%");
			
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
	
	//모든 강의,상품수 - 검색이 아닐 때
	public int getAllCount_lect_prod2(String code, String category){
		d.getCon();
		int num = 0;

		try {
//			System.out.println(num);
//			System.out.println(code);
			String sql = "select count(*) from "+code+" where category=?";
		
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, category);	
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
	public ArrayList<Lecture> allBbs_lecture1(int startNum, int endNum, String search, String field, String code, String category) {
		d.getCon();
		ArrayList<Lecture> v = new ArrayList<Lecture>();
		
		try {
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where category=? and gongji!='1' and "+field+" like ? order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);
			
			pstmt.setString(1, category);
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, startNum);
			pstmt.setInt(4, endNum);
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
	public ArrayList<Lecture> allBbs_lecture2(int startNum, int endNum, String code, String category) {
		d.getCon();
		ArrayList<Lecture> v = new ArrayList<Lecture>();
		try {
			//이거는 부분 조인 - 게시글 목록과 가상컬럼을 추가해서 댓글 수를 출력하기 위해 컬럼에 각 게시글의 댓글 수를 받는 구문
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where category=? and gongji='1' order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, category);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			
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
	public ArrayList<Product> allBbs_product1(int startNum, int endNum, String search, String field, String code, String category) {
		d.getCon();
		ArrayList<Product> v = new ArrayList<Product>();
		
		try {
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where category=? and gongji='1' and "+field+" like ? order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);
			
			pstmt.setString(1, category);
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, startNum);
			pstmt.setInt(4, endNum);
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
	public ArrayList<Product> allBbs_product2(int startNum, int endNum, String code, String category) {
		d.getCon();
		ArrayList<Product> v = new ArrayList<Product>();
		try {
			//이거는 부분 조인 - 게시글 목록과 가상컬럼을 추가해서 댓글 수를 출력하기 위해 컬럼에 각 게시글의 댓글 수를 받는 구문
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where category=? and gongji='1' order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, category);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			
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
	
	//모든 강의 목록 - 검색
	public ArrayList<Lecture> allBbs_lecture1_ad(int startNum, int endNum, String search, String field, String code, String category) {
		d.getCon();
		ArrayList<Lecture> v = new ArrayList<Lecture>();
		
		try {
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where category=? and "+field+" like ? order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);
			
			pstmt.setString(1, category);
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, startNum);
			pstmt.setInt(4, endNum);
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
	public ArrayList<Lecture> allBbs_lecture2_ad(int startNum, int endNum, String code, String category) {
		d.getCon();
		ArrayList<Lecture> v = new ArrayList<Lecture>();
		try {
			//이거는 부분 조인 - 게시글 목록과 가상컬럼을 추가해서 댓글 수를 출력하기 위해 컬럼에 각 게시글의 댓글 수를 받는 구문
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where category=? order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, category);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			
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
	public ArrayList<Product> allBbs_product1_ad(int startNum, int endNum, String search, String field, String code, String category) {
		d.getCon();
		ArrayList<Product> v = new ArrayList<Product>();
		
		try {
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where category=? and "+field+" like ? order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);
			
			pstmt.setString(1, category);
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, startNum);
			pstmt.setInt(4, endNum);
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
	public ArrayList<Product> allBbs_product2_ad(int startNum, int endNum, String code, String category) {
		d.getCon();
		ArrayList<Product> v = new ArrayList<Product>();
		try {
			//이거는 부분 조인 - 게시글 목록과 가상컬럼을 추가해서 댓글 수를 출력하기 위해 컬럼에 각 게시글의 댓글 수를 받는 구문
			String sql = "select *,(select count(*) from review where "+code+".uid = review.tb_uid) as review_num from "+code+" where category=? order by uid desc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, category);
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			
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
	
	///////댓글 출력////나중에 댓글페이징 처리할때 sql구문에 리밋처리 해주면됨 - 검색x
	public ArrayList<Comment> allComment(int startNum,int endNum,String code, int tb_uid) {
		d.getCon();
		ArrayList<Comment> com = new ArrayList<Comment>();
		try {
			String sql = "select * from comment where tb_table=? and tb_uid=? order by fid desc, thread asc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);		 

			
			pstmt.setString(1, code);
			pstmt.setInt(2, tb_uid);
			pstmt.setInt(3, startNum);
			pstmt.setInt(4, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Comment all = new Comment();
				
				all.setUid(rs.getInt("uid"));
				all.setTb_uid(rs.getInt("tb_uid"));
				all.setTb_table(rs.getString("tb_table"));
				all.setTb_id(rs.getString("tb_id"));
				all.setTb_nickname(rs.getString("tb_nickname"));
				all.setTb_comment(rs.getString("tb_comment"));
				all.setTb_date(rs.getString("tb_date"));	
				all.setFid(rs.getInt("fid"));
				all.setThread(rs.getString("thread"));
				com.add(all);
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
		return com;
	}	
	
	///////댓글 출력////나중에 댓글페이징 처리할때 sql구문에 리밋처리 해주면됨 - 검색
	public ArrayList<Comment> allComment1(int startNum,int endNum,String search_view,String field_view,String code, int tb_uid) {
		d.getCon();
		ArrayList<Comment> com = new ArrayList<Comment>();
		try {
			String sql = "select * from comment where tb_table=? and tb_uid=? and "+field_view+" like ? order by fid desc,thread asc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);								 
			
			pstmt.setString(1, code);
			pstmt.setInt(2, tb_uid);
			pstmt.setString(3, "%"+search_view+"%");
			pstmt.setInt(4, startNum);
			pstmt.setInt(5, endNum);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Comment all = new Comment();
				
				all.setUid(rs.getInt("uid"));
				all.setTb_uid(rs.getInt("tb_uid"));
				all.setTb_table(rs.getString("tb_table"));
				all.setTb_id(rs.getString("tb_id"));
				all.setTb_nickname(rs.getString("tb_nickname"));
				all.setTb_comment(rs.getString("tb_comment"));
				all.setTb_date(rs.getString("tb_date"));	
				all.setFid(rs.getInt("fid"));
				all.setThread(rs.getString("thread"));
				com.add(all);
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
		return com;
	}	
	
	
	public void deleteComment(String code,int uid,int tb_uid,String tb_id) {
		d.getCon();
		try {
			String sql="delete from comment where uid=? and tb_table=? and tb_uid=? and tb_id=?";
			
			pstmt = d.conn.prepareStatement(sql);	
			
			pstmt.setInt(1, uid);
			pstmt.setString(2, code);
			pstmt.setInt(3, tb_uid);
			pstmt.setString(4, tb_id);
			
//			System.out.println("uid"+uid);
//			System.out.println("code"+code);
//			System.out.println("tb_uid"+tb_uid);
//			System.out.println("tb_id"+tb_id);
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
	
	///////리뷰 출력////- 검색x
	public ArrayList<Review> allReview(int startNum,int endNum,String code, int tb_uid) {
		d.getCon();
		ArrayList<Review> com = new ArrayList<Review>();
		try {
			String sql = "select * from review where tb_table=? and tb_uid=? order by fid desc, thread asc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);		 

			
			pstmt.setString(1, code);
			pstmt.setInt(2, tb_uid);
			pstmt.setInt(3, startNum);
			pstmt.setInt(4, endNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Review all = new Review();
				
				all.setUid(rs.getInt("uid"));
				all.setTb_uid(rs.getInt("tb_uid"));
				all.setTb_table(rs.getString("tb_table"));
				all.setTb_id(rs.getString("tb_id"));
				all.setTb_subject(rs.getString("tb_subject"));
				all.setTb_comment(rs.getString("tb_comment"));
				all.setTb_score(rs.getInt("tb_score"));
				all.setFile1_o(rs.getString("file1_o"));
				all.setFile1_s(rs.getString("file1_s"));
				all.setTb_date(rs.getString("tb_date"));	
				all.setFid(rs.getInt("fid"));
				all.setThread(rs.getString("thread"));
				com.add(all);
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
		return com;
	}	
	
	///////리뷰 출력//// - 검색
	public ArrayList<Review> allReview1(int startNum,int endNum,String search_view,String field_view,String code, int tb_uid) {
		d.getCon();
		ArrayList<Review> com = new ArrayList<Review>();
		try {
			String sql = "select * from review where tb_table=? and tb_uid=? and "+field_view+" like ? order by fid desc,thread asc limit ?,?";
			pstmt=d.conn.prepareStatement(sql);								 
			
			pstmt.setString(1, code);
			pstmt.setInt(2, tb_uid);
			pstmt.setString(3, "%"+search_view+"%");
			pstmt.setInt(4, startNum);
			pstmt.setInt(5, endNum);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Review all = new Review();
				
				all.setUid(rs.getInt("uid"));
				all.setTb_uid(rs.getInt("tb_uid"));
				all.setTb_table(rs.getString("tb_table"));
				all.setTb_id(rs.getString("tb_id"));
				all.setTb_subject(rs.getString("tb_subject"));
				all.setTb_comment(rs.getString("tb_comment"));
				all.setTb_score(rs.getInt("tb_score"));
				all.setFile1_o(rs.getString("file1_o"));
				all.setFile1_s(rs.getString("file1_s"));
				all.setTb_date(rs.getString("tb_date"));	
				all.setFid(rs.getInt("fid"));
				all.setThread(rs.getString("thread"));
				com.add(all);
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
		return com;
	}
	
	public void insertLecture1(Lecture m,String code) {//첨부가 둘다 있을 경우
		d.getCon();
		try {
			
			String sql = "insert into lecture(id,subject,comment,category,p_name1,price1,number1,p_name2,price2,number2,baesong,baesong_free,gongji,file1_o,file1_s,file2_o,file3_o,account_name,account_num,zipcode,zip1,zip2,zip3,signdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getSubject());
			pstmt.setString(3, m.getComment());
			pstmt.setString(4, m.getCategory());
			pstmt.setString(5, m.getP_name1());
			pstmt.setInt(6, m.getPrice1());
			pstmt.setInt(7, m.getNumber1());
			pstmt.setString(8, m.getP_name2());
			pstmt.setInt(9, m.getPrice2());
			pstmt.setInt(10, m.getNumber2());
			pstmt.setInt(11, m.getBaesong());
			pstmt.setInt(12, m.getBaesong_free());
			pstmt.setString(13, m.getGongji());
			pstmt.setString(14, m.getFile1_o());
			pstmt.setString(15, m.getFile1_s());
			pstmt.setString(16, m.getFile2_o());
			pstmt.setString(17, m.getFile3_o());
			pstmt.setString(18, m.getAccount_name());
			pstmt.setString(19, m.getAccount_num());
			pstmt.setString(20, m.getZipcode());
			pstmt.setString(21, m.getZip1());
			pstmt.setString(22, m.getZip2());
			pstmt.setString(23, m.getZip3());			
			pstmt.setString(24, m.getSigndate());

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
	public void insertLecture2(Lecture m,String code) {//첨부가 둘 다 없을 경우
		d.getCon();
		
		try {
			String sql = "insert into lecture(id,subject,comment,category,p_name1,price1,number1,p_name2,price2,number2,baesong,baesong_free,gongji,file1_o,file1_s,account_name,account_num,zipcode,zip1,zip2,zip3,signdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getSubject());
			pstmt.setString(3, m.getComment());
			pstmt.setString(4, m.getCategory());
			pstmt.setString(5, m.getP_name1());
			pstmt.setInt(6, m.getPrice1());
			pstmt.setInt(7, m.getNumber1());
			pstmt.setString(8, m.getP_name2());
			pstmt.setInt(9, m.getPrice2());
			pstmt.setInt(10, m.getNumber2());
			pstmt.setInt(11, m.getBaesong());
			pstmt.setInt(12, m.getBaesong_free());
			pstmt.setString(13, m.getGongji());
			pstmt.setString(14, m.getFile1_o());
			pstmt.setString(15, m.getFile1_s());
			pstmt.setString(16, m.getAccount_name());
			pstmt.setString(17, m.getAccount_num());
			pstmt.setString(18, m.getZipcode());
			pstmt.setString(19, m.getZip1());
			pstmt.setString(20, m.getZip2());
			pstmt.setString(21, m.getZip3());			
			pstmt.setString(22, m.getSigndate());
//			
//			System.out.println(m.getId());
//			System.out.println(m.getSubject());
//			System.out.println(m.getComment());
//			System.out.println(m.getCategory());
//			System.out.println( m.getPrice1());
//			System.out.println(m.getGongji());
//			System.out.println(m.getFile1_o());
//			System.out.println(m.getFile1_s());
//			System.out.println(m.getSigndate());
//			System.out.println(m.getAccount_name());
//			System.out.println(m.getAccount_num());
			
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
	public void insertLecture3(Lecture m,String code) {//첨부 2만 있을 경우
		d.getCon();
		try {
			String sql = "insert into lecture(id,subject,comment,category,p_name1,price1,number1,p_name2,price2,number2,baesong,baesong_free,gongji,file1_o,file1_s,file2_o,account_name,account_num,zipcode,zip1,zip2,zip3,signdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getSubject());
			pstmt.setString(3, m.getComment());
			pstmt.setString(4, m.getCategory());
			pstmt.setString(5, m.getP_name1());
			pstmt.setInt(6, m.getPrice1());
			pstmt.setInt(7, m.getNumber1());
			pstmt.setString(8, m.getP_name2());
			pstmt.setInt(9, m.getPrice2());
			pstmt.setInt(10, m.getNumber2());
			pstmt.setInt(11, m.getBaesong());
			pstmt.setInt(12, m.getBaesong_free());			
			pstmt.setString(13, m.getGongji());
			pstmt.setString(14, m.getFile1_o());
			pstmt.setString(15, m.getFile1_s());
			pstmt.setString(16, m.getFile2_o());
			pstmt.setString(17, m.getAccount_name());
			pstmt.setString(18, m.getAccount_num());
			pstmt.setString(19, m.getZipcode());
			pstmt.setString(20, m.getZip1());
			pstmt.setString(21, m.getZip2());
			pstmt.setString(22, m.getZip3());			
			pstmt.setString(23, m.getSigndate());
			
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
	public void insertLecture4(Lecture m,String code) { //첨부가 3만 있을 경우
		d.getCon();
		try {
			String sql = "insert into lecture(id,subject,comment,category,p_name1,price1,number1,p_name2,price2,number2,baesong,baesong_free,gongji,file1_o,file1_s,file3_o,account_name,account_num,zipcode,zip1,zip2,zip3,signdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getSubject());
			pstmt.setString(3, m.getComment());
			pstmt.setString(4, m.getCategory());
			pstmt.setString(5, m.getP_name1());
			pstmt.setInt(6, m.getPrice1());
			pstmt.setInt(7, m.getNumber1());
			pstmt.setString(8, m.getP_name2());
			pstmt.setInt(9, m.getPrice2());
			pstmt.setInt(10, m.getNumber2());
			pstmt.setInt(11, m.getBaesong());
			pstmt.setInt(12, m.getBaesong_free());
			pstmt.setString(13, m.getGongji());
			pstmt.setString(14, m.getFile1_o());
			pstmt.setString(15, m.getFile1_s());
			pstmt.setString(16, m.getFile3_o());
			pstmt.setString(17, m.getAccount_name());
			pstmt.setString(18, m.getAccount_num());
			pstmt.setString(19, m.getZipcode());
			pstmt.setString(20, m.getZip1());
			pstmt.setString(21, m.getZip2());
			pstmt.setString(22, m.getZip3());
			pstmt.setString(23, m.getSigndate());
			
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
	
	public void insertBbs(Bbs m,String code) {//첨부가 둘다 있을 경우
		d.getCon();
		try {
			int fid = 1;
			
			String sql_fid="select max(fid) as fid_max from "+code+"";
			
			pstmt = d.conn.prepareStatement(sql_fid);			

			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt("fid_max") > 0){
					fid = rs.getInt("fid_max") + 1;
				}else{
					fid = 1;
				}
			}
			///////
//			System.out.println("1");
//			System.out.println(m.getId());
//			System.out.println(m.getSubject());
//			System.out.println(m.getComment());
//			System.out.println(m.getSigndate());
//			System.out.println(m.getGongji());
//			System.out.println("파일은1"+m.getFile1_o()+"2"+m.getFile2_o() );
//			System.out.println("여기까지");
			////////
			String sql = "insert into "+code+"(id,nickname,subject,comment,signdate,gongji,ref,file1_o,file2_o,fid,thread) values(?,?,?,?,?,?,0,?,?,"+fid+",'A')";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getNickname());
			pstmt.setString(3, m.getSubject());
			pstmt.setString(4, m.getComment());
			pstmt.setString(5, m.getSigndate());
			pstmt.setString(6, m.getGongji());
			pstmt.setString(7, m.getFile1_o());
			pstmt.setString(8, m.getFile2_o());
			
			pstmt.executeUpdate();
		

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
	}	
	public void insertBbs2(Bbs m,String code) {//첨부가 둘 다 없을 경우
		d.getCon();
		
		try {
			int fid = 1;
			String sql_fid="select max(fid) as fid_max from "+code+"";

			pstmt = d.conn.prepareStatement(sql_fid);			

			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt("fid_max") > 0){
					fid = rs.getInt("fid_max") + 1;
				}else{
					fid = 1;
				}
			}
			///////
//			System.out.println("2");
//			System.out.println(m.getId());
//			System.out.println(m.getNickname());
//			System.out.println(m.getSubject());
//			System.out.println(m.getComment());
//			System.out.println(m.getSigndate());
//			System.out.println(m.getGongji());
//			System.out.println("파일은1"+m.getFile1_o()+"2"+m.getFile2_o() );
//			System.out.println("여기까지");
			////////

			String sql = "insert into "+code+"(id,nickname,subject,comment,signdate,gongji,ref,fid,thread) values(?,?,?,?,?,?,0,"+fid+",'A')";
			pstmt = d.conn.prepareStatement(sql);

			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getNickname());
			pstmt.setString(3, m.getSubject());
			pstmt.setString(4, m.getComment());
			pstmt.setString(5, m.getSigndate());
			pstmt.setString(6, m.getGongji());

			pstmt.executeUpdate();


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
	}	
	public void insertBbs3(Bbs m,String code) {//첨부 1만 있을 경우
		d.getCon();
		try {
			int fid = 1;
			String sql_fid="select max(fid) as fid_max from "+code+"";
			
			pstmt = d.conn.prepareStatement(sql_fid);			

			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt("fid_max") > 0){
					fid = rs.getInt("fid_max") + 1;
				}else{
					fid = 1;
				}
			}
			///////
//			System.out.println("3");
//			System.out.println(m.getId());
//			System.out.println(m.getNickname());
//			System.out.println(m.getSubject());
//			System.out.println(m.getComment());
//			System.out.println(m.getSigndate());
//			System.out.println(m.getGongji());
//			System.out.println("파일은1"+m.getFile1_o()+"2"+m.getFile2_o() );
			
//			System.out.println("여기까지");
			////////
			String sql = "insert into "+code+"(id,nickname,subject,comment,signdate,gongji,ref,file1_o,fid,thread) values(?,?,?,?,?,?,0,?,"+fid+",'A')";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getNickname());
			pstmt.setString(3, m.getSubject());
			pstmt.setString(4, m.getComment());
			pstmt.setString(5, m.getSigndate());
			pstmt.setString(6, m.getGongji());
			pstmt.setString(7, m.getFile1_o());

			pstmt.executeUpdate();
		

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
	}	
	public void insertBbs4(Bbs m,String code) { //첨부가 2만 있을 경우
		d.getCon();
		try {
			int fid = 1;
			String sql_fid="select max(fid) as fid_max from "+code+"";
			
			pstmt = d.conn.prepareStatement(sql_fid);			

			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt("fid_max") > 0){
					fid = rs.getInt("fid_max") + 1;
				}else{
					fid = 1;
				}
			}
			///////
//			System.out.println("4");
//			System.out.println(m.getId());
//			System.out.println(m.getNickname());
//			System.out.println(m.getSubject());
//			System.out.println(m.getComment());
//			System.out.println(m.getSigndate());
//			System.out.println("파일은1"+m.getFile1_o()+"2"+m.getFile2_o() );
			
//			System.out.println(m.getGongji());
//			System.out.println("여기까지");
			////////
			String sql = "insert into "+code+"(id,nickname,subject,comment,signdate,gongji,ref,file2_o,fid,thread) values(?,?,?,?,?,?,0,?,"+fid+",'A')";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getNickname());
			pstmt.setString(3, m.getSubject());
			pstmt.setString(4, m.getComment());
			pstmt.setString(5, m.getSigndate());
			pstmt.setString(6, m.getGongji());
			pstmt.setString(7, m.getFile2_o());
			
			pstmt.executeUpdate();
		

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
	}	
	
	
	//게시글 view펭이지
	public Bbs viewBbs(String id, int uid, String code) {
		d.getCon();
		Bbs view = new Bbs();
		try {
			String sql_ref ="update "+code+" set ref=ref+1 where uid=?";
			pstmt = d.conn.prepareStatement(sql_ref);		
			pstmt.setInt(1, uid);		
			
			pstmt.executeUpdate();
			
			
			String sql = "select * from "+code+" where uid=? and id=?";
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				view.setUid(rs.getInt("uid"));
				view.setId(rs.getString("id"));
				view.setNickname(rs.getString("nickname"));
				view.setSubject(rs.getString("subject"));
				view.setComment(rs.getString("comment"));
				view.setSigndate(rs.getString("signdate"));
				view.setRef(rs.getInt("ref"));
				view.setGongji(rs.getString("gongji"));
				view.setFile1_o(rs.getString("file1_o"));
				view.setFile2_o(rs.getString("file2_o"));
				view.setFid(rs.getInt("fid"));
				view.setThread(rs.getString("thread"));
				
				
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
	
	//강의 view펭이지
	public Lecture viewLecture(String id, int uid, String code, String category) {
		d.getCon();
		Lecture view = new Lecture();
		try {			
			
			String sql = "select * from lecture where uid=? and id=? and category=?";
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
	
	//게시글 수정처리
	//리스트로 보내는경우 반환이 필요 없으니 보이드 가능
	//파일이 있는,없는 경우로 나눌시 컨트롤러에서 파일값이 있는지 없는지를 다루고 메소드를 두개 만들어서 있없 구분
	public void updateBbs1(Bbs m,String code) {//파일 둘 다있
		d.getCon();
		try {
			String sql = "update "+code+" set subject=?,comment=?,gongji=?,file1_o=?,file2_o=? where uid=?";
			
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getSubject());
			pstmt.setString(2, m.getComment());
			pstmt.setString(3, m.getGongji());
			pstmt.setString(4, m.getFile1_o());
			pstmt.setString(5, m.getFile2_o());
			pstmt.setInt(6, m.getUid());
			
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
	public void updateBbs2(Bbs m,String code) {//1만
		d.getCon();
		try {
			String sql = "update "+code+" set subject=?,comment=?,gongji=?,file1_o=? where uid=?";
			
			
			pstmt = d.conn.prepareStatement(sql);
			
			
			pstmt.setString(1, m.getSubject());
			pstmt.setString(2, m.getComment());
			pstmt.setString(3, m.getGongji());
			pstmt.setString(4, m.getFile1_o());
			pstmt.setInt(5, m.getUid());
					
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
	public void updateBbs3(Bbs m,String code) {//2만
		d.getCon();
		try {
			String sql = "update "+code+" set subject=?,comment=?,gongji=?,file2_o=? where uid=?";
			

			
			pstmt = d.conn.prepareStatement(sql);
			
			
			pstmt.setString(1, m.getSubject());
			pstmt.setString(2, m.getComment());
			pstmt.setString(3, m.getGongji());
			pstmt.setString(4, m.getFile2_o());
			pstmt.setInt(5, m.getUid());
					
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
	public void updateBbs4(Bbs m,String code) {//파일없
		d.getCon();
		try {
			String sql = "update "+code+" set subject=?,comment=?,gongji=? where uid=?";

//			System.out.println("4");
//			System.out.println(m.getUid());
//			System.out.println(m.getSubject());
//			System.out.println(m.getComment());
//			System.out.println(m.getGongji());
//			System.out.println("파일은1"+m.getFile1_o()+"2"+m.getFile2_o() );
			
			pstmt = d.conn.prepareStatement(sql);
			
			
			pstmt.setString(1, m.getSubject());
			pstmt.setString(2, m.getComment());
			pstmt.setString(3, m.getGongji());
			pstmt.setInt(4, m.getUid());
					
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
	
	//강의 수정처리
	//리스트로 보내는경우 반환이 필요 없으니 보이드 가능
	//파일이 있는,없는 경우로 나눌시 컨트롤러에서 파일값이 있는지 없는지를 다루고 메소드를 두개 만들어서 있없 구분
	public void updateLecture1(Lecture m) {//파일 둘 다있
		d.getCon();
		try {
			String sql = "update lecture set subject=?,comment=?,category=?,p_name1=?,price1=?,number1=?,p_name2=?,price2=?,number2=?,baesong=?,baesong_free=?,gongji=?,file1_o=?,file1_s=?,file2_o=?,file3_o=?,account_name=?,account_num=?,zipcode=?,zip1=?,zip2=?,zip3=? where uid=?";
			
			
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
			pstmt.setInt(10, m.getBaesong());
			pstmt.setInt(11, m.getBaesong_free());
			
			pstmt.setString(12, m.getGongji());
			pstmt.setString(13, m.getFile1_o());
			pstmt.setString(14, m.getFile1_s());
			pstmt.setString(15, m.getFile2_o());
			pstmt.setString(16, m.getFile3_o());
			pstmt.setString(17, m.getAccount_name());
			pstmt.setString(18, m.getAccount_num());
			pstmt.setString(19, m.getZipcode());
			pstmt.setString(20, m.getZip1());
			pstmt.setString(21, m.getZip2());
			pstmt.setString(22, m.getZip3());
			
			
			
			pstmt.setInt(23, m.getUid());
			
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
	public void updateLecture2(Lecture m) {//2만
		d.getCon();
		try {
			String sql = "update lecture set subject=?,comment=?,category=?,p_name1=?,price1=?,number1=?,p_name2=?,price2=?,number2=?,baesong=?,baesong_free=?,gongji=?,file1_o=?,file1_s=?,file2_o=?,account_name=?,account_num=?,zipcode=?,zip1=?,zip2=?,zip3=? where uid=?";
			
			
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
			pstmt.setInt(10, m.getBaesong());
			pstmt.setInt(11, m.getBaesong_free());
			pstmt.setString(12, m.getGongji());
			pstmt.setString(13, m.getFile1_o());
			pstmt.setString(14, m.getFile1_s());
			pstmt.setString(15, m.getFile2_o());
			pstmt.setString(16, m.getAccount_name());
			pstmt.setString(17, m.getAccount_num());
			pstmt.setString(18, m.getZipcode());
			pstmt.setString(19, m.getZip1());
			pstmt.setString(20, m.getZip2());
			pstmt.setString(21, m.getZip3());
			pstmt.setInt(22, m.getUid());
					
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
	public void updateLecture3(Lecture m) {//3만
		d.getCon();
		try {
			String sql = "update lecture set subject=?,comment=?,category=?,p_name1=?,price1=?,number1=?,p_name2=?,price2=?,number2=?,baesong=?,baesong_free=?,gongji=?,file1_o=?,file1_s=?,file3_o=?,account_name=?,account_num=?,zipcode=?,zip1=?,zip2=?,zip3=? where uid=?";
			

			
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
			pstmt.setInt(10, m.getBaesong());
			pstmt.setInt(11, m.getBaesong_free());
			pstmt.setString(12, m.getGongji());
			pstmt.setString(13, m.getFile1_o());
			pstmt.setString(14, m.getFile1_s());
			pstmt.setString(15, m.getFile3_o());
			pstmt.setString(16, m.getAccount_name());
			pstmt.setString(17, m.getAccount_num());
			pstmt.setString(18, m.getZipcode());
			pstmt.setString(19, m.getZip1());
			pstmt.setString(20, m.getZip2());
			pstmt.setString(21, m.getZip3());
			pstmt.setInt(22, m.getUid());
					
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
	public void updateLecture4(Lecture m) {//파일없
		d.getCon();
		try {
			String sql = "update lecture set subject=?,comment=?,category=?,p_name1=?,price1=?,number1=?,p_name2=?,price2=?,number2=?,baesong=?,baesong_free=?,gongji=?,file1_o=?,file1_s=?,account_name=?,account_num=?,zipcode=?,zip1=?,zip2=?,zip3=? where uid=?";

//			System.out.println("4");
//			System.out.println(m.getUid());
//			System.out.println(m.getSubject());
//			System.out.println(m.getComment());
//			System.out.println(m.getGongji());
//			System.out.println("파일은1"+m.getFile1_o()+"2"+m.getFile2_o() );
			
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
			pstmt.setInt(10, m.getBaesong());
			pstmt.setInt(11, m.getBaesong_free());
			pstmt.setString(12, m.getGongji());
			pstmt.setString(13, m.getFile1_o());
			pstmt.setString(14, m.getFile1_s());
			pstmt.setString(15, m.getAccount_name());
			pstmt.setString(16, m.getAccount_num());
			pstmt.setString(17, m.getZipcode());
			pstmt.setString(18, m.getZip1());
			pstmt.setString(19, m.getZip2());
			pstmt.setString(20, m.getZip3());
			pstmt.setInt(21, m.getUid());
					
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
	
	public void writeComment(Comment m) {//디비에 댓글 입력
		d.getCon();
		try {
			int fid = 1;
			String sql_fid="select max(fid) as fid_max from comment";

			pstmt = d.conn.prepareStatement(sql_fid);			

			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt("fid_max") > 0){
					fid = rs.getInt("fid_max") + 1;
				}else{
					fid = 1;
				}
			}			
			
			
			String sql = "insert into comment(tb_table,tb_uid,tb_id,tb_nickname,tb_comment,tb_date,fid,thread) values(?,?,?,?,?,?,"+fid+",'A')";
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getTb_table());
			pstmt.setInt(2, m.getTb_uid());
			pstmt.setString(3, m.getTb_id());
			pstmt.setString(4, m.getTb_nickname());
			pstmt.setString(5, m.getTb_comment());
			pstmt.setString(6, m.getTb_date());

			pstmt.executeUpdate();
			

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
	}
	public Bbs replyCont(int o_uid,String code){
		d.getCon();
		Bbs rep = new Bbs();
		try {
			String sql = "select * from "+code+" where uid=?";
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setInt(1, o_uid);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				rep.setUid(rs.getInt("uid"));
				rep.setId(rs.getString("id"));
				rep.setNickname(rs.getString("nickname"));
				rep.setSubject(rs.getString("subject"));
				rep.setGongji(rs.getString("gongji"));
				rep.setComment(rs.getString("comment"));
				rep.setFid(rs.getInt("fid"));
				rep.setThread(rs.getString("thread"));
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

		return rep;
		
	}
	public void insert_replyBbs1(Bbs m, String code, String ori_thread,int ori_fid) {//첨부 둘 다 있고
		d.getCon();
		try {
			String sql_fid = "select thread,right(thread,1) as thread_right from "+code+" where fid = "+ori_fid+" AND length(thread) = length('"+ori_thread+"')+1 and locate('"+ori_thread+"',thread) =1 ORDER BY thread DESC limit 1";

			pstmt = d.conn.prepareStatement(sql_fid);
			
			rs = pstmt.executeQuery();
			
			String o_thread = "";
			String r_thread = "";
			String thread_head = "";
			String new_thread = "";
			char thread_foot = 'a';

			if(rs.next()){
				o_thread = rs.getString("thread");
				//끝자리 +1 처리
				r_thread = rs.getString("thread_right");
				thread_foot = (char)(r_thread.charAt(0) + 1);
				//out.print(thread_foot);
			}

			if(o_thread != null && o_thread != ""){
				thread_head = o_thread.substring(0,o_thread.length()-1);
				new_thread = thread_head + thread_foot;
			}else{
				new_thread = ori_thread+"A";
			}
			
			String sql="insert into "+code+"(id,nickname,subject,comment,signdate,gongji,ref,file1_o,file2_o,fid,thread) values(?,?,?,?,?,?,0,?,?,?,?)";
			
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getNickname());
			pstmt.setString(3, m.getSubject());
			pstmt.setString(4, m.getComment());
			pstmt.setString(5, m.getSigndate());
			pstmt.setString(6, m.getGongji());
			pstmt.setString(7, m.getFile1_o());
			pstmt.setString(8, m.getFile2_o());
			pstmt.setInt(9, ori_fid);
			pstmt.setString(10, new_thread);
			
			pstmt.executeUpdate();
			////////

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
	}
	public void insert_replyBbs2(Bbs m, String code, String ori_thread,int ori_fid) {//첨부 둘 다 없고
		d.getCon();
		try {
			String sql_fid = "select thread,right(thread,1) as thread_right from "+code+" where fid = "+ori_fid+" AND length(thread) = length('"+ori_thread+"')+1 and locate('"+ori_thread+"',thread) =1 ORDER BY thread DESC limit 1";

			pstmt = d.conn.prepareStatement(sql_fid);
			
			
			rs = pstmt.executeQuery();
			
			String o_thread = "";
			String r_thread = "";
			String thread_head = "";
			String new_thread = "";
			char thread_foot = 'a';

			if(rs.next()){
				o_thread = rs.getString("thread");
				//끝자리 +1 처리
				r_thread = rs.getString("thread_right");
				thread_foot = (char)(r_thread.charAt(0) + 1);
				//out.print(thread_foot);
			}

			if(o_thread != null && o_thread != ""){
				thread_head = o_thread.substring(0,o_thread.length()-1);
				new_thread = thread_head + thread_foot;
			}else{
				new_thread = ori_thread+"A";
			}
			
			String sql="insert into "+code+"(id,nickname,subject,comment,signdate,gongji,ref,fid,thread) values(?,?,?,?,?,?,0,?,?)";
			
			pstmt = d.conn.prepareStatement(sql);
/*		
			System.out.println("=================");
			System.out.println(m.getId());
			System.out.println(m.getNickname());
			System.out.println(m.getSubject());
			System.out.println(m.getComment());
			System.out.println(m.getSigndate());
			System.out.println(m.getGongji());
			System.out.println(ori_fid);
			System.out.println(new_thread);
			System.out.println("=================");			
*/	
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getNickname());
			pstmt.setString(3, m.getSubject());
			pstmt.setString(4, m.getComment());
			pstmt.setString(5, m.getSigndate());
			pstmt.setString(6, m.getGongji());
			pstmt.setInt(7, ori_fid);
			pstmt.setString(8, new_thread);
			
			pstmt.executeUpdate();
			

			
			////////
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
	}
	public void insert_replyBbs3(Bbs m, String code, String ori_thread,int ori_fid) {//첨부 1만 있고
		d.getCon();
		try {
			String sql_fid = "select thread,right(thread,1) as thread_right from "+code+" where fid = "+ori_fid+" AND length(thread) = length('"+ori_thread+"')+1 and locate('"+ori_thread+"',thread) =1 ORDER BY thread DESC limit 1";

			pstmt = d.conn.prepareStatement(sql_fid);
			
			
			rs = pstmt.executeQuery();
			
			String o_thread = "";
			String r_thread = "";
			String thread_head = "";
			String new_thread = "";
			char thread_foot = 'a';

			if(rs.next()){
				o_thread = rs.getString("thread");
				//끝자리 +1 처리
				r_thread = rs.getString("thread_right");
				thread_foot = (char)(r_thread.charAt(0) + 1);
				//out.print(thread_foot);
			}

			if(o_thread != null && o_thread != ""){
				thread_head = o_thread.substring(0,o_thread.length()-1);
				new_thread = thread_head + thread_foot;
			}else{
				new_thread = ori_thread+"A";
			}
			
			String sql="insert into "+code+"(id,nickname,subject,comment,signdate,gongji,ref,file1_o,fid,thread) values(?,?,?,?,?,?,0,?,?,?)";
			
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getNickname());
			pstmt.setString(3, m.getSubject());
			pstmt.setString(4, m.getComment());
			pstmt.setString(5, m.getSigndate());
			pstmt.setString(6, m.getGongji());
			pstmt.setString(7, m.getFile1_o());
			pstmt.setInt(8, ori_fid);
			pstmt.setString(9, new_thread);
			
			pstmt.executeUpdate();
			////////
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
	}
	public void insert_replyBbs4(Bbs m, String code, String ori_thread,int ori_fid) {//첨부 2만 있고
		d.getCon();
		try {
			String sql_fid = "select thread,right(thread,1) as thread_right from "+code+" where fid = "+ori_fid+" AND length(thread) = length('"+ori_thread+"')+1 and locate('"+ori_thread+"',thread) =1 ORDER BY thread DESC limit 1";

			pstmt = d.conn.prepareStatement(sql_fid);
			
			
			rs = pstmt.executeQuery();
			
			String o_thread = "";
			String r_thread = "";
			String thread_head = "";
			String new_thread = "";
			char thread_foot = 'a';

			if(rs.next()){
				o_thread = rs.getString("thread");
				//끝자리 +1 처리
				r_thread = rs.getString("thread_right");
				thread_foot = (char)(r_thread.charAt(0) + 1);
				//out.print(thread_foot);
			}

			if(o_thread != null && o_thread != ""){
				thread_head = o_thread.substring(0,o_thread.length()-1);
				new_thread = thread_head + thread_foot;
			}else{
				new_thread = ori_thread+"A";
			}
			
			String sql="insert into "+code+"(id,nickname,subject,comment,signdate,gongji,ref,file2_o,fid,thread) values(?,?,?,?,?,?,0,?,?,?)";
			
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getNickname());
			pstmt.setString(3, m.getSubject());
			pstmt.setString(4, m.getComment());
			pstmt.setString(5, m.getSigndate());
			pstmt.setString(6, m.getGongji());
			pstmt.setString(7, m.getFile2_o());
			pstmt.setInt(8, ori_fid);
			pstmt.setString(9, new_thread);
			
			pstmt.executeUpdate();
			////////
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
	}
	
	///게시글 삭제
	public void deleteBbs(String code, String id, int uid) {
		d.getCon();
		try {
			String sql = "delete from "+code+" where id=? and uid=?";
			

			pstmt = d.conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setInt(2, uid);
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
	

	
	public int getRecoCount_Y(String tb_table,int tb_uid,String tb_id) {
		d.getCon();
		int r_count = 0;
		
		try {
			String sql = "select count(*) as r_count from reco where tb_uid=? and tb_id=?";
	
			pstmt = d.conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, tb_uid);
			pstmt.setString(2, tb_id);
			
			rs = pstmt.executeQuery();
//			System.out.println("YYYYYYYYYYYYYYYYYYYYY");
//			System.out.println("tb_table=  "+tb_table);
//			System.out.println("tb_uid=  "+tb_uid);
//			System.out.println("tb_id=  "+tb_id);
			
//			System.out.println("YYYYYYYYYYYYYYYYYYYYY");
			if(rs.next()) {
				r_count=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미
				
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
		return r_count;
		
	}

	public void insertReco(Reco r) {
		d.getCon();
		try {
			String sql = "insert into reco(tb_table,tb_uid,tb_id,tb_name,tb_reco,tb_date) values(?,?,?,?,?,?)";
			
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, r.getTb_table());
			pstmt.setInt(2, r.getTb_uid());
			pstmt.setString(3, r.getTb_id());
			pstmt.setString(4, r.getTb_name());
			pstmt.setString(5, r.getTb_reco());
			pstmt.setString(6, r.getTb_date());
			
			
//			System.out.println(r.getTb_table());
//			System.out.println(r.getTb_uid());
//			System.out.println(r.getTb_id());
//			System.out.println(r.getTb_name());
//			System.out.println(r.getTb_reco());			
//			System.out.println(r.getTb_date());
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

	public Comment modifyComment(int uid) {
		d.getCon();
		Comment modc = new Comment();
		try {
			String sql = "select * from comment where uid=?";
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				modc.setUid(rs.getInt("uid"));
				modc.setTb_uid(rs.getInt("tb_uid"));
				modc.setTb_table(rs.getString("tb_table"));
				modc.setTb_id(rs.getString("tb_id"));
				modc.setTb_nickname(rs.getString("tb_nickname"));
				modc.setTb_comment(rs.getString("tb_comment"));
				modc.setTb_date(rs.getString("tb_date"));
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
		}return modc;
	}
	public void updateComment(Comment com) {
		d.getCon();
		try {
			
			String sql = "update comment set tb_comment=? where uid= ?";
			pstmt= d.conn.prepareStatement(sql);
			
			pstmt.setString(1, com.getTb_comment());
			pstmt.setInt(2, com.getUid());
//			System.out.println(sql);
//			System.out.println(com.getTb_comment());
//			System.out.println(com.getUid());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				d.conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
	}
	public Comment repComment(int uid) {
		d.getCon();
		Comment repc = new Comment();
		try {
			String sql = "select * from comment where uid=?";
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				repc.setUid(rs.getInt("uid"));
				repc.setTb_uid(rs.getInt("tb_uid"));
				repc.setTb_table(rs.getString("tb_table"));
				repc.setTb_id(rs.getString("tb_id"));
				repc.setTb_nickname(rs.getString("tb_nickname"));
				repc.setTb_comment(rs.getString("tb_comment"));
				repc.setTb_date(rs.getString("tb_date"));
				repc.setFid(rs.getInt("fid"));
				repc.setThread(rs.getString("thread"));
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
		}return repc;
	}
	public void insert_replyCom(Comment c,String ori_thread,int ori_fid) {
		d.getCon();
		try {
			String sql_fid = "select thread,right(thread,1) as thread_right from comment where fid = "+ori_fid+" AND length(thread) = length('"+ori_thread+"')+1 and locate('"+ori_thread+"',thread) =1 ORDER BY thread DESC limit 1";

			pstmt = d.conn.prepareStatement(sql_fid);
			
			rs = pstmt.executeQuery();
			
			String o_thread = "";
			String r_thread = "";
			String thread_head = "";
			String new_thread = "";
			char thread_foot = 'a';

			if(rs.next()){
				o_thread = rs.getString("thread");
				//끝자리 +1 처리
				r_thread = rs.getString("thread_right");
				thread_foot = (char)(r_thread.charAt(0) + 1);
				//out.print(thread_foot);
			}

			if(o_thread != null && o_thread != ""){
				thread_head = o_thread.substring(0,o_thread.length()-1);
				new_thread = thread_head + thread_foot;
			}else{
				new_thread = ori_thread+"A";
			}
			
			String sql="insert into comment(tb_table,tb_uid,tb_id,tb_nickname,tb_comment,tb_date,fid,thread) values(?,?,?,?,?,?,?,?)";
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getTb_table());
			pstmt.setInt(2, c.getTb_uid());
			pstmt.setString(3, c.getTb_id());
			pstmt.setString(4, c.getTb_nickname());
			pstmt.setString(5, c.getTb_comment());
			pstmt.setString(6, c.getTb_date());
			pstmt.setInt(7, ori_fid);
			pstmt.setString(8, new_thread);
			
			pstmt.executeUpdate();
			////////			
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
	//모든 댓글수 - 검색
	public int getAllCount_com(String search_view, String field_view, String code, int tb_uid) {
		d.getCon();
		int count = 0;
		try {
			String sql = "select count(*) from comment where tb_table =? and tb_uid=? and "+field_view+" like ?";
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setInt(2, tb_uid);
			pstmt.setString(3, "%"+search_view+"%");
//			System.out.println(code);
//			System.out.println(tb_uid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미

			}
//			System.out.println("dao = "+num);
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
		return count;
	}	
	
	public int getAllCount1_com(String code, int tb_uid){
		d.getCon();
		int count = 0;

		try {
			String sql = "select count(*) from comment where tb_table=? and tb_uid=?";
			pstmt = d.conn.prepareStatement(sql);
//					System.out.println(code);
//					System.out.println(tb_uid);
			pstmt.setString(1, code);
			pstmt.setInt(2, tb_uid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);//칼럼 인덱스로 1을 사용해도도미
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
		return count;
	}		

}
