package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Member;

public class Member_adminDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO d = new DAO();
	
	//총 게시물 수 일반
	public int getAllCount_no(String field, String search) {
		d.getCon();
		
		int num = 0;
		try {
			String sql = "";
			if(field != null) {
				sql = "select count(*) from member where "+field+" like ?";
				pstmt = d.conn.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
			}else {
				sql = "select count(*) from member";
				pstmt = d.conn.prepareStatement(sql);
			}

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				d.conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return num;
	}
	

	
	//총 회원목록 일반
	public ArrayList<Member> allMember_no(int startNum, int endNum, String field, String search) {
		d.getCon();
		
		ArrayList<Member> v = new ArrayList<Member>();
		try {
			String sql = "";
			
			if(field != null) {
				sql = "select * from member where "+field+" like ? order by signdate desc limit ?,?";
				
				pstmt = d.conn.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startNum);
				pstmt.setInt(3, endNum);
			}else {	
				sql = "select * from member order by signdate desc limit ?,?";
				
				pstmt = d.conn.prepareStatement(sql);
				pstmt.setInt(1, startNum);
				pstmt.setInt(2, endNum);
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member m = new Member();
				m.setId(rs.getString("id"));
				m.setPass(rs.getString("pass"));
				m.setNames(rs.getString("names"));
				m.setMail1(rs.getString("mail1"));
				m.setMail2(rs.getString("mail2"));
				m.setGender(rs.getString("gender"));
				m.setSsn(rs.getString("ssn"));
				m.setLevel(rs.getString("level"));
				m.setFile1_o(rs.getString("file1_o"));
				m.setFile1_s(rs.getString("file1_s"));
				m.setQuitdate(rs.getString("quitdate"));
				v.add(m);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				d.conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return v;
	}
	

	
	//회원 정보 가져오기 일반
	public Member viewMember_no(String id) {
		d.getCon();
		
		Member m = new Member();
		try {
			String sql = "select * from member where id=?";
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m.setId(rs.getString("id"));
				m.setPass(rs.getString("pass"));
				m.setNames(rs.getString("names"));
				m.setNickname(rs.getString("nickname"));
				m.setMail1(rs.getString("mail1"));
				m.setMail2(rs.getString("mail2"));
				m.setSsn(rs.getString("ssn"));
				m.setTel(rs.getString("tel"));
				m.setZipcode(rs.getString("zipcode"));
				m.setGender(rs.getString("gender"));
				m.setZip1(rs.getString("zip1"));
				m.setZip2(rs.getString("zip2"));
				m.setZip3(rs.getString("zip3"));
				m.setFile1_o(rs.getString("file1_o"));
				m.setLevel(rs.getString("level"));
				m.setSigndate(rs.getString("signdate"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	

		public void deleteMember_no(String id, String signdate) {
			d.getCon();
			try {
				String sql = "update member set quitdate=? where id=?";
				
				pstmt = d.conn.prepareStatement(sql);
				
				pstmt.setString(1, signdate);
				pstmt.setString(2, id);
				
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
		
		
	
		
		public void deleteMem_no_check(String checked_id, String signdate) {
			d.getCon();
			String id=checked_id;
			
			try {
				String sql = "update member set quitdate=? where id=?";
				pstmt = d.conn.prepareStatement(sql);
				pstmt.setString(1, signdate);
				pstmt.setString(2, id);
				
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
		

		///게시글 삭제 -관리자
		public void deleteBbs_admin(String code,int uid) {
			d.getCon();
			try {
				String sql = "delete from "+code+" where uid=?";
				

				pstmt = d.conn.prepareStatement(sql);

			
				pstmt.setInt(1, uid);
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
		///게시글 삭제 -checked관리자
		public void delete_bbs_check(String code,String checked_uid) {
			d.getCon();
			
			try {
				int uid=Integer.parseInt(checked_uid);
				String sql = "delete from "+code+" where uid=?";
				

				pstmt = d.conn.prepareStatement(sql);

			
				pstmt.setInt(1, uid);
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
		
		///상품 삭제 -checked 관리자가 상품상태()gongji를 3으로 바꾸면 판매자에게도 안뜨게 설정, 2는 판매자가 설정해서 일시판매중지
		public void market_del_check(String code,String checked_uid, String category) {
			d.getCon();
			
			try {
				int uida=Integer.parseInt(checked_uid);
				String sql = "update "+code+" set gongji='3' where uid=? and category=?";
				

				pstmt = d.conn.prepareStatement(sql);

				pstmt.setInt(1, uida);
				pstmt.setString(2, category);
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
		public void deleteMarket(String code,String uid, String category) {
			d.getCon();
			int uid_a=Integer.parseInt(uid);
			try {
				String sql = "update "+code+" set gongji='3' where uid=? and category=?";
				
				pstmt = d.conn.prepareStatement(sql);

				pstmt.setInt(1, uid_a);
				pstmt.setString(2, category);
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		public void recoverMarket(String code,String uid, String category) {
			d.getCon();
			int uid_a=Integer.parseInt(uid);
			try {
				String sql = "update "+code+" set gongji='1' where uid=? and category=?";
				
				pstmt = d.conn.prepareStatement(sql);

				pstmt.setInt(1, uid_a);
				pstmt.setString(2, category);
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		public void recoveryMember(String id) {
			d.getCon();
			try {
				String sql="update member set quitdate='' where id=?";
				pstmt=d.conn.prepareStatement(sql);
				
				pstmt.setString(1,id);
				pstmt.executeUpdate();
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
