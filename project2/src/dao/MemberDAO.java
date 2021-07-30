package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Member;


public class MemberDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO d = new DAO();
	public void insertMember(Member member){
		d.getCon();
		try {
			String sql = "insert into member(id,pass,names,nickname,mail1,mail2,gender,ssn,tel,zipcode,zip1,zip2,zip3,file1_o,file1_s,level,signdate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,1,?)";	
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getNames());
			pstmt.setString(4, member.getNickname());
			pstmt.setString(5, member.getMail1());
			pstmt.setString(6, member.getMail2());
			pstmt.setString(7, member.getGender());
			pstmt.setString(8, member.getSsn());
			pstmt.setString(9, member.getTel());
			pstmt.setString(10, member.getZipcode());
			pstmt.setString(11, member.getZip1());
			pstmt.setString(12, member.getZip2());
			pstmt.setString(13, member.getZip3());
			pstmt.setString(14, member.getFile1_o());
			pstmt.setString(15, member.getFile1_s());
			pstmt.setString(16, member.getSigndate());
			
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
	public Member memberup(String id) {
		d.getCon();
		
		Member m = new Member();
		try {
			String sql = "select * from member where id=?";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m.setId(rs.getString("id"));
				m.setNames(rs.getString("names"));
				m.setNickname(rs.getString("nickname"));
				m.setMail1(rs.getString("mail1"));
				m.setMail2(rs.getString("mail2"));
				m.setGender(rs.getString("gender"));
				m.setSsn(rs.getString("ssn"));
				m.setTel(rs.getString("tel"));
				m.setZipcode(rs.getString("zipcode"));
				m.setZip1(rs.getString("zip1"));
				m.setZip2(rs.getString("zip2"));
				m.setZip3(rs.getString("zip3"));
				m.setFile1_o(rs.getString("file1_o"));
				m.setFile1_s(rs.getString("file1_s"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				d.conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return m;
	}
//////////////	
	public int loginMember_no(String id, String pass){
		d.getCon();

		int num=0;
		try {////////////////////첫번째 (가상)칼럼//////////////////두번째////세번째
			String sql = "select count(*) from member where id=? and pass=?";//A.
			//입력받은 아이디와 비번을 통해 해당하는 데이터 찾기
			pstmt = d.conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//A.이라고 적힌 방법을 사용하여 게시글의 총 갯수를 구할 수 있다고 하셨음. 나중에 확인해서 적용해보기
				num=rs.getInt(1);//컬럼 인덱스 1 대신 칼럼명인 "count(*)"로도 사용가능
			}
			rs.close();//닫기 잊지마!
			pstmt.close();
			d.conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return num;//select count해서 나온 수를 넘겨주기
	}

	//회원 로그인
	public Member loginSelect_no(String id, String pass){//여기다가 넘긴다고
		d.getCon();
		Member member = new Member();
		try {
			String sql = "select * from member where id=? and pass=?";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member.setId(rs.getString("id"));
				member.setNickname(rs.getString("nickname"));
				member.setLevel(rs.getString("level"));				
			}
			rs.close();//닫기 잊지마!
			pstmt.close();
			d.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return member;//리턴해서 dao에 있는 loginSelect로 넘김
	}
	
	public void updateMemberImg(Member m){//프로필 바꾸기
		d.getCon();
		try {
			String sql="";
			if(m.getFile1_o() !=null && !m.getFile1_o().equals("")) {
				sql = "update member set file1_o=?,file1_s=?,nickname=? where id=?";
				pstmt = d.conn.prepareStatement(sql);
						
				pstmt.setString(1, m.getFile1_o());
				pstmt.setString(2, m.getFile1_s());
				pstmt.setString(3, m.getNickname());
				pstmt.setString(4, m.getId());
			}else {
				sql = "update member set nickname=? where id=?";
				pstmt = d.conn.prepareStatement(sql);
						
				pstmt.setString(1, m.getNickname());
				pstmt.setString(2, m.getId());
			}
			pstmt.executeUpdate();
			
			pstmt.close();
			d.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
//		System.out.println("1");
	}
	
	public void updateMemberNames(Member m){// 개인정보 바꾸기
		d.getCon();
		try {
			String sql = "update member set names=?,gender=?,ssn=?,tel=? where id=?";
			pstmt = d.conn.prepareStatement(sql);
						
			pstmt.setString(1, m.getNames());
			pstmt.setString(2, m.getGender());
			pstmt.setString(3, m.getSsn());
			pstmt.setString(4, m.getTel());
			pstmt.setString(5, m.getId());
				
			pstmt.executeUpdate();
			
			pstmt.close();
			d.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMemberMail(Member m){// 주소 관리 메일,주소
		d.getCon();
		try {
			String sql = "update member set mail1=?,mail2=?,zipcode=?,zip1=?,zip2=?,zip3=? where id=?";
			pstmt = d.conn.prepareStatement(sql);
						
			pstmt.setString(1, m.getMail1());
			pstmt.setString(2, m.getMail2());
			pstmt.setString(3, m.getZipcode());
			pstmt.setString(4, m.getZip1());
			pstmt.setString(5, m.getZip2());
			pstmt.setString(6, m.getZip3());
			pstmt.setString(7, m.getId());
				
			pstmt.executeUpdate();
			
			pstmt.close();
			d.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public void updateMemberPass(Member m){//비밀번호 변경
			d.getCon();
		try {
			String sql = "update member set pass=? where id=?";
			pstmt = d.conn.prepareStatement(sql);
						
			pstmt.setString(1, m.getPass());
			pstmt.setString(2, m.getId());
				
			pstmt.executeUpdate();
			
			pstmt.close();
			d.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//일반 아이디 찾기
	public Member matchId_no(Member m) {
		d.getCon();
		
		Member mem = new Member();
		try {
			String sql = "select * from member where names=? and mail1=? and mail2=? and tel=?";
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getNames());
			pstmt.setNString(2, m.getMail1());
			pstmt.setNString(3, m.getMail2());
			pstmt.setNString(4, m.getTel());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mem.setId(rs.getString("id"));
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
		return mem;
	}
	
	//일반 비밀번호 찾기
	public Member matchpass_no(Member m) {
		d.getCon();
		
		Member mem = new Member();
		try {
			String sql = "select * from member where id=? and names=? and mail1=? and mail2=? and tel=?";
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getNames());
			pstmt.setNString(3, m.getMail1());
			pstmt.setNString(4, m.getMail2());
			pstmt.setNString(5, m.getTel());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mem.setPass(rs.getString("pass"));
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
		return mem;
	}
	
	
	//아이디 중복체크 일반일때
	public int checkId_no(String id) {
		d.getCon();
		
		int num = 0;
		try {
			String sql= "select count(*) as total_count from member where id=?";	
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				num = rs.getInt("total_count");
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

	public String matchName(String id) {
		d.getCon();
		
		String a = "";
		try {
			String sql = "select nickname from member where id=?";
			
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = rs.getString("nickname");
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
		return a;
	}
		
}
