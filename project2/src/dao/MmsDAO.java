package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import model.Mms;

public class MmsDAO {

	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO d = new DAO();
	
		public int getAllCount(String id){
			d.getCon();
			int num = 0;

			try {
							 

				String sql = "select count(*) from mms where send_id=? and send_del = 'N'";

				pstmt = d.conn.prepareStatement(sql);
						
				pstmt.setString(1, id);
				
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
		
		public int getAllCount2(String id){
			d.getCon();
			int num = 0;

			try {
				String sql = "select count(*) from mms where receive_id=? and receive_del='N'";

				pstmt = d.conn.prepareStatement(sql);
						
				pstmt.setString(1, id);
				
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
		
		public int getAllCount(String search, String field, String id) {
			d.getCon();
			int num = 0;
			try {
				String sql = "select count(*) from mms where send_id=? and instr("+field+",?)";
				
				pstmt = d.conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2,search);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					num=rs.getInt(1);

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
		
		public int getAllCount2(String search, String field, String id) {
			d.getCon();
			int num = 0;
			try {
				String sql = "select count(*) from mms where receive_id=? and instr("+field+",?)";
				
				pstmt = d.conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2,search);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					num=rs.getInt(1);

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
		
		public ArrayList<Mms> allMms(int startNum, int endNum, String search, String field, String id) {
			d.getCon();
			ArrayList<Mms> v = new ArrayList<Mms>();
			
			try {
				String sql = "select * from mms where send_id = ? and send_del = 'N' and instr("+field+",?) limit ?,?";
				pstmt=d.conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setString(2,search);
				pstmt.setInt(3, startNum);
				pstmt.setInt(4, endNum);
				
				rs = pstmt.executeQuery();
				
				
				
				while(rs.next()) {
					Mms all = new Mms();
					all.setUid(rs.getInt("uid"));
					all.setSend_id(rs.getString("send_id"));
					all.setReceive_id(rs.getString("receive_id"));
					all.setSend_nickname(rs.getString("send_nickname"));
					all.setReceive_nickname(rs.getString("receive_nickname"));
					all.setSubject(rs.getString("subject"));
					all.setComment(rs.getString("comment"));
					all.setWrite_day(rs.getString("write_day"));
					all.setRead_day(rs.getString("read_day"));
					all.setFile1(rs.getString("file1"));
					all.setSend_del(rs.getString("send_del"));
					all.setReceive_del(rs.getString("receive_del"));
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
		public ArrayList<Mms> allMms2(int startNum, int endNum, String id) {
			d.getCon();
			ArrayList<Mms> v = new ArrayList<Mms>();
			try {
				String sql = "select * from mms where send_id = ? and send_del = 'N' order by uid desc limit ?,?";
				pstmt=d.conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setInt(2, startNum);
				pstmt.setInt(3, endNum);
				
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Mms all = new Mms();
					all.setUid(rs.getInt("uid"));
					all.setSend_id(rs.getString("send_id"));
					all.setReceive_id(rs.getString("receive_id"));
					all.setSend_nickname(rs.getString("send_nickname"));
					all.setReceive_nickname(rs.getString("receive_nickname"));
					all.setSubject(rs.getString("subject"));
					all.setComment(rs.getString("comment"));
					all.setWrite_day(rs.getString("write_day"));
					all.setRead_day(rs.getString("read_day"));
					all.setFile1(rs.getString("file1"));
					all.setSend_del(rs.getString("send_del"));
					all.setReceive_del(rs.getString("receive_del"));
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
		
		public ArrayList<Mms> allMms3(int startNum, int endNum, String search, String field, String id) {
			d.getCon();
			ArrayList<Mms> v = new ArrayList<Mms>();
			
			try {
				String sql = "select * from mms where receive_id = ? and receive_del = 'N' and instr("+field+",?) limit ?,?";
				pstmt=d.conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setString(2,search);
				pstmt.setInt(3, startNum);
				pstmt.setInt(4, endNum);
				
				rs = pstmt.executeQuery();
				
				
				
				while(rs.next()) {
					Mms all = new Mms();
					all.setUid(rs.getInt("uid"));
					all.setSend_id(rs.getString("send_id"));
					all.setReceive_id(rs.getString("receive_id"));
					all.setSend_nickname(rs.getString("send_nickname"));
					all.setReceive_nickname(rs.getString("receive_nickname"));
					all.setSubject(rs.getString("subject"));
					all.setComment(rs.getString("comment"));
					all.setWrite_day(rs.getString("write_day"));
					all.setRead_day(rs.getString("read_day"));
					all.setFile1(rs.getString("file1"));
					all.setSend_del(rs.getString("send_del"));
					all.setReceive_del(rs.getString("receive_del"));
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
		public ArrayList<Mms> allMms4(int startNum, int endNum, String id) {
			d.getCon();
			ArrayList<Mms> v = new ArrayList<Mms>();
			try {
				String sql = "select * from mms where receive_id = ? and receive_del = 'N' order by uid desc limit ?,?";
				pstmt=d.conn.prepareStatement(sql);

				pstmt.setString(1, id);
				pstmt.setInt(2, startNum);
				pstmt.setInt(3, endNum);
				
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Mms all = new Mms();
					all.setUid(rs.getInt("uid"));
					all.setSend_id(rs.getString("send_id"));
					all.setReceive_id(rs.getString("receive_id"));
					all.setSend_nickname(rs.getString("send_nickname"));
					all.setReceive_nickname(rs.getString("receive_nickname"));
					all.setSubject(rs.getString("subject"));
					all.setComment(rs.getString("comment"));
					all.setWrite_day(rs.getString("write_day"));
					all.setRead_day(rs.getString("read_day"));
					all.setFile1(rs.getString("file1"));
					all.setSend_del(rs.getString("send_del"));
					all.setReceive_del(rs.getString("receive_del"));
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
		
		public void insertMms1(Mms m) {
			d.getCon();
			try {
				String sql = "insert into mms(send_id,receive_id,send_nickname,receive_nickname,subject,comment,write_day,file1) values(?,?,?,?,?,?,?,?)";
				
				pstmt = d.conn.prepareStatement(sql);
				
				pstmt.setString(1, m.getSend_id());
				pstmt.setString(2, m.getReceive_id());
				pstmt.setString(3, m.getSend_nickname());
				pstmt.setString(4, m.getReceive_nickname());
				pstmt.setString(5, m.getSubject());
				pstmt.setString(6, m.getComment());
				pstmt.setString(7, m.getWrite_day());
				pstmt.setString(8, m.getFile1());
				
				pstmt.executeUpdate();
			} catch (Exception e) {
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
		
		public void insertMms2(Mms m) {//파일이 없는거
			d.getCon();
			System.out.println("파일 없이");
			try {
				String sql = "insert into mms(send_id,receive_id,send_nickname,receive_nickname,subject,comment,write_day) values(?,?,?,?,?,?,?)";
				
				pstmt = d.conn.prepareStatement(sql);
				
				pstmt.setString(1, m.getSend_id());
				pstmt.setString(2, m.getReceive_id());
				pstmt.setString(3, m.getSend_nickname());
				pstmt.setString(4, m.getReceive_nickname());
				pstmt.setString(5, m.getSubject());
				pstmt.setString(6, m.getComment());
				pstmt.setString(7, m.getWrite_day());
				
				pstmt.executeUpdate();
			} catch (Exception e) {
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
		
		public Mms viewMms(int uid) {
			d.getCon();
			
			Mms view = new Mms();
			try {
				String sql = "select * from mms where uid=?";
				
				pstmt = d.conn.prepareStatement(sql);
				
				pstmt.setInt(1, uid);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					view.setUid(rs.getInt("uid"));
					view.setSend_id(rs.getString("send_id"));
					view.setReceive_id(rs.getString("receive_id"));
					view.setSend_nickname(rs.getString("send_nickname"));
					view.setReceive_nickname(rs.getString("receive_nickname"));
					view.setSubject(rs.getString("subject"));
					view.setComment(rs.getString("comment"));
					view.setWrite_day(rs.getString("write_day"));
					view.setRead_day(rs.getString("Read_day"));
					view.setFile1(rs.getString("file1"));
					view.setSend_del(rs.getString("send_del"));
					view.setReceive_del(rs.getString("receive_del"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return view;
		}
		public void sendMmsDel(String uid) {
			d.getCon();
			try {
				String sql = "update mms set send_del = 'Y' where uid=?";
				
				pstmt = d.conn.prepareStatement(sql);
				
				pstmt.setString(1, uid);
				
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
		public void receiveMmsDel(String uid) {
			d.getCon();
			try {
				String sql = "update mms set receive_del = 'Y' where uid=?";
				
				pstmt = d.conn.prepareStatement(sql);
				
				pstmt.setString(1, uid);
				
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
		public void MmsDel() {
			d.getCon();
			try {
				String sql = "delete from mms where send_del = 'Y' and receive_del = 'Y'";
				
				pstmt = d.conn.prepareStatement(sql);

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
		
		public void readMms(int uid,String signdate) {
			d.getCon();
			try {
				String sql = "update mms set read_day = ? where uid = ?";
				
				pstmt = d.conn.prepareStatement(sql);

				pstmt.setString(1, signdate);
				pstmt.setInt(2, uid);
				
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
}
