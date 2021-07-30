package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Buy_list;
import model.Cart;
import model.Lecture;

public class BuyDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO d = new DAO();

	//장바구니에 상품 넣기
	public void insertCart(Cart c) {
		d.getCon();
		try {
			String sql = "insert into cart(tb_uid,tb_table,category,cart_name,buy_id,sell_id,subject,price,number,baesong,file1_s,s_zipcode,s_zip1,s_zip2,s_zip3,signdate,status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y')";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setInt(1, c.getTb_uid());
			pstmt.setString(2, c.getTb_table());
			pstmt.setString(3, c.getCategory());	
			pstmt.setString(4, c.getCart_name());
			pstmt.setString(5, c.getBuy_id());
			pstmt.setString(6, c.getSell_id());
			pstmt.setString(7, c.getSubject());
			pstmt.setInt(8, c.getPrice());
			pstmt.setInt(9, c.getNumber());
			pstmt.setInt(10, c.getBaesong());
			pstmt.setString(11, c.getFile1_s());
			pstmt.setString(12, c.getS_zipcode());
			pstmt.setString(13, c.getS_zip1());
			pstmt.setString(14, c.getS_zip2());
			pstmt.setString(15, c.getS_zip3());
			pstmt.setString(16, c.getSigndate());
			
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
	
	
	public void insertCart_product1(Cart cc) {
		d.getCon();
		try {
			String sql = "insert into cart(tb_uid,tb_table,category,cart_name,buy_id,sell_id,subject,price,number,baesong,file1_s,signdate,status,s_zipcode,s_zip1,s_zip2,s_zip3) values(?,?,?,?,?,?,?,?,?,?,?,?,'Y',?,?,?,?)";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setInt(1, cc.getTb_uid());
			pstmt.setString(2, cc.getTb_table());
			pstmt.setString(3, cc.getCategory());
			pstmt.setString(4, cc.getCart_name());
			pstmt.setString(5, cc.getBuy_id());
			pstmt.setString(6, cc.getSell_id());
			pstmt.setString(7, cc.getSubject());
			pstmt.setInt(8, cc.getPrice());
			pstmt.setInt(9, cc.getNumber());
			pstmt.setInt(10, cc.getBaesong());
			pstmt.setString(11, cc.getFile1_s());
			pstmt.setString(12, cc.getSigndate());
			pstmt.setString(13, cc.getS_zipcode());
			pstmt.setString(14, cc.getS_zip1());
			pstmt.setString(15, cc.getS_zip2());
			pstmt.setString(16, cc.getS_zip3());
			
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
	public void insertCart_product2(Cart ccc) {
		d.getCon();
		try {
			String sql = "insert into cart(tb_uid,tb_table,category,cart_name,buy_id,sell_id,subject,price,number,baesong,file1_s,signdate,status,s_zipcode,s_zip1,s_zip2,s_zip3) values(?,?,?,?,?,?,?,?,?,?,?,?,'Y',?,?,?,?)";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setInt(1, ccc.getTb_uid());
			pstmt.setString(2, ccc.getTb_table());
			pstmt.setString(3, ccc.getCategory());
			pstmt.setString(4, ccc.getCart_name());
			pstmt.setString(5, ccc.getBuy_id());
			pstmt.setString(6, ccc.getSell_id());
			pstmt.setString(7, ccc.getSubject());
			pstmt.setInt(8, ccc.getPrice());
			pstmt.setInt(9, ccc.getNumber());
			pstmt.setInt(10, ccc.getBaesong());
			pstmt.setString(11, ccc.getFile1_s());
			pstmt.setString(12, ccc.getSigndate());
			pstmt.setString(13, ccc.getS_zipcode());
			pstmt.setString(14, ccc.getS_zip1());
			pstmt.setString(15, ccc.getS_zip2());
			pstmt.setString(16, ccc.getS_zip3());
			
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
	public void insertCart_product3(Cart cccc) {
		d.getCon();
		try {
			String sql = "insert into cart(tb_uid,tb_table,category,cart_name,buy_id,sell_id,subject,price,number,baesong,file1_s,signdate,status,s_zipcode,s_zip1,s_zip2,s_zip3) values(?,?,?,?,?,?,?,?,?,?,?,?,'Y',?,?,?,?)";
			pstmt = d.conn.prepareStatement(sql);
			
			pstmt.setInt(1, cccc.getTb_uid());
			pstmt.setString(2, cccc.getTb_table());
			pstmt.setString(3, cccc.getCategory());
			pstmt.setString(4, cccc.getCart_name());
			pstmt.setString(5, cccc.getBuy_id());
			pstmt.setString(6, cccc.getSell_id());
			pstmt.setString(7, cccc.getSubject());
			pstmt.setInt(8, cccc.getPrice());
			pstmt.setInt(9, cccc.getNumber());
			pstmt.setInt(10, cccc.getBaesong());
			pstmt.setString(11, cccc.getFile1_s());
			pstmt.setString(12, cccc.getSigndate());
			pstmt.setString(13, cccc.getS_zipcode());
			pstmt.setString(14, cccc.getS_zip1());
			pstmt.setString(15, cccc.getS_zip2());
			pstmt.setString(16, cccc.getS_zip3());
			
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
	
	//장바구니에 있는 상품 수
	public int getAllCount_cart2(String id,String cart_name) {
		d.getCon();
		int num_cart = 0;
		try {
			String sql = "select count(*) from cart where buy_id=? and cart_name=?";
			

			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, cart_name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num_cart=rs.getInt(1);
			}
//			System.out.println(num_cart);
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
		return num_cart;
	}	
	
	//장바구니에 있는 상품 수 //세션비교
	public int getAllCount_cart3(String id,String cart_name) {
		d.getCon();
		int num_cart = 0;
		try {
			String sql = "select count(*) from cart where buy_id=? and cart_name=?";

			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, cart_name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num_cart=rs.getInt(1);
			}
//			System.out.println(num_cart);
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
		return num_cart;
	}	
	
	

	
	public ArrayList<Cart> allCart2(String id,String cart_name) {
		d.getCon();
		ArrayList<Cart> v = new ArrayList<Cart>();
		try {
			//
			String sql = "select * from cart where buy_id=? and cart_name=? order by uid desc";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, cart_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Cart c = new Cart();
				c.setUid(rs.getInt("uid"));
				c.setTb_uid(rs.getInt("tb_uid"));
				c.setTb_table(rs.getString("tb_table"));
				c.setCart_name(rs.getString("cart_name"));
				c.setBuy_id(rs.getString("buy_id"));
				c.setSell_id(rs.getString("sell_id"));
				c.setSubject(rs.getString("subject"));
				c.setPrice(rs.getInt("price"));
				c.setNumber(rs.getInt("number"));
				c.setBaesong(rs.getInt("baesong"));
				c.setFile1_s(rs.getString("file1_s"));
				c.setSigndate(rs.getString("signdate"));
				c.setStatus(rs.getString("status"));
				v.add(c);
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
	
	public ArrayList<Cart> allCart3(String id ,String cart_name) {
		d.getCon();
		ArrayList<Cart> v = new ArrayList<Cart>();
		try {
			//
			String sql = "select * from cart where buy_id=? and cart_name=? order by uid desc";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, cart_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Cart c = new Cart();
				c.setUid(rs.getInt("uid"));
				c.setTb_uid(rs.getInt("tb_uid"));
				c.setTb_table(rs.getString("tb_table"));
				c.setCart_name("cart_name");
				c.setBuy_id(rs.getString("buy_id"));
				c.setSell_id(rs.getString("sell_id"));
				c.setSubject(rs.getString("subject"));
				c.setPrice(rs.getInt("price"));
				c.setNumber(rs.getInt("number"));
				c.setBaesong(rs.getInt("baesong"));
				c.setFile1_s(rs.getString("file1_s"));
				c.setSigndate(rs.getString("signdate"));
				v.add(c);
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
	public void deleteCart(String id, int uid) {
		d.getCon();
		try {
			String sql="delete from cart where uid=? and buy_id=?";
			
			pstmt = d.conn.prepareStatement(sql);	
			
			pstmt.setInt(1, uid);
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
	
	public void deleteCart_check(String buy_id,String checked_uid) {
		d.getCon();
		int uid = Integer.parseInt(checked_uid);
		try {
			String sql="delete from cart where uid=? and buy_id=?";
			
			pstmt = d.conn.prepareStatement(sql);	
			
			pstmt.setInt(1, uid);
			pstmt.setString(2, buy_id);
			
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
	
	public void del_cart_name(String cart_name, String id) {
		d.getCon();
		try {
			
			String sql="delete from cart where cart_name=? and buy_id=?";
			
			pstmt = d.conn.prepareStatement(sql);	
			
			pstmt.setString(1, cart_name);
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
	
	
	//구매내역에 있는 상품 수
	public int getAllCount_buylist(String id) {
		d.getCon();
		int num = 0;
		try {
			String sql = "select count(*) from buy_list where buy_id=?";
			

			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
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
		return num;
	}
	//구매내역에 있는 상품 수
	public int getAllCount_cart_manage(String id) {
		d.getCon();
		int num = 0;
		try {
			String sql = "select count(*) from cart where sell_id=?";
			

			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
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
		return num;
	}
	//구매내역에 있는 상품 수 -관리자
	public int getAllCount_buylist_admin() {
		d.getCon();
		int num = 0;
		try {
			String sql = "select count(*) from buy_list";
			

			pstmt = d.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
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
		return num;
	}	
	
	//구매내역에 있는 정보 불러와서 구매전 상세정보 기입페이지에 뿌려주기
	public ArrayList<Buy_list> allBuy_list(String id,String cart_name) {
		d.getCon();
		ArrayList<Buy_list> v = new ArrayList<Buy_list>();
		try {
			//
			String sql = "select * from buy_list where buy_id=? and list_name=? order by uid desc";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, cart_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Buy_list c = new Buy_list();
				c.setUid(rs.getInt("uid"));
				c.setBuy_id(rs.getString("buy_id"));
				c.setList_name("list_name");
				c.setPrice(rs.getInt("price"));
				c.setBaesong(rs.getInt("baesong"));
				c.setSigndate(rs.getString("signdate"));
				v.add(c);
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
	//장바구니에서 구매 클릭 후 상세정보 기입페이지에서 정보 기입 후 다음페이지로 넘어갈 때
	public void insert_buy_list_details(String id, String v_uid,String b_zipcode,String b_zip1,String b_zip2,String b_zip3,String baesong_msg,String pay_way,String cart_name,String signdate,String a_name,String b_name, String b_tel) {
		d.getCon();
		int uid=Integer.parseInt(v_uid);
		try {
			String sql = "update Buy_list set status='Y',baesong_msg=?,pay_way=?,pay='N',a_name=?,b_name=?,b_tel=?,b_zipcode=?,b_zip1=?,b_zip2=?,b_zip3=?,signdate=? where uid=? and list_name=?";
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, baesong_msg);
			pstmt.setString(2, pay_way);
			pstmt.setString(3, a_name);
			pstmt.setString(4, b_name);
			pstmt.setString(5, b_tel);
			pstmt.setString(6, b_zipcode);
			pstmt.setString(7, b_zip1);
			pstmt.setString(8, b_zip2);
			pstmt.setString(9, b_zip3);
			pstmt.setString(10, signdate);
			pstmt.setInt(11, uid);
			pstmt.setString(12, cart_name);

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
	//상세기입페이지에서 기입하고 넘어갈때 구매목록 생성
	public void insert_buy_list_status(Buy_list m){
		d.getCon();
		try {
			String sql = "insert into buy_list(buy_id,list_name,price,status,baesong,pay_way,pay,a_name,account,b_name,b_tel,b_zipcode,b_zip1,b_zip2,b_zip3,signdate) values(?,?,?,'N',?,?,'N',?,?,?,?,?,?,?,?,?)";
				pstmt = d.conn.prepareStatement(sql);
				pstmt.setString(1, m.getBuy_id());
				pstmt.setString(2, m.getList_name());
				pstmt.setInt(3, m.getPrice());
				pstmt.setInt(4, m.getBaesong());
				pstmt.setString(5, m.getPay_way());
				pstmt.setString(6, m.getA_name());
				pstmt.setString(7, m.getAccount());
				pstmt.setString(8, m.getB_name());
				pstmt.setString(9, m.getB_tel());
				pstmt.setString(10, m.getB_zipcode());
				pstmt.setString(11, m.getB_zip1());
				pstmt.setString(12, m.getB_zip2());
				pstmt.setString(13, m.getB_zip3());
				pstmt.setString(14, m.getSigndate());
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
	public void update_cart_status(String cart_name,String baesong_msg) {
		d.getCon();
		try {
			String sql="update cart set status='N', baesong_msg=? where cart_name=?";
			pstmt = d.conn.prepareStatement(sql);
			pstmt.setString(1, baesong_msg);
			pstmt.setString(2, cart_name);
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
	
	
	public ArrayList<Buy_list> allOrder_list(String id) {
		d.getCon();
		ArrayList<Buy_list> a = new ArrayList<Buy_list>();
		try {
			//
						
			
			String sql = "select * from buy_list where buy_id=? order by uid desc";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Buy_list c = new Buy_list();
				c.setUid(rs.getInt("uid"));
				c.setBuy_id(rs.getString("buy_id"));
				c.setList_name(rs.getString("list_name"));
				c.setPrice(rs.getInt("price"));
				c.setStatus(rs.getString("status"));
				c.setBaesong(rs.getInt("baesong"));
				c.setPay_way(rs.getString("pay_way"));
				c.setPay(rs.getString("pay"));
				c.setA_name(rs.getString("a_name"));
				c.setB_name(rs.getString("b_name"));
				c.setB_zipcode(rs.getString("b_zipcode"));
				c.setB_zip1(rs.getString("b_zip1"));
				c.setB_zip2(rs.getString("b_zip2"));
				c.setB_zip3(rs.getString("b_zip3"));
				c.setSigndate(rs.getString("signdate"));
				a.add(c);
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
		return a;
	}
	
	public ArrayList<Buy_list> allOrder_list_manage(String id) {
		d.getCon();
		ArrayList<Buy_list> a = new ArrayList<Buy_list>();
		try {
			//
						
			
			String sql = "select * from buy_list where sell_id=? order by uid desc";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Buy_list c = new Buy_list();
				c.setUid(rs.getInt("uid"));
				c.setBuy_id(rs.getString("buy_id"));
				c.setList_name(rs.getString("list_name"));
				c.setPrice(rs.getInt("price"));
				c.setStatus(rs.getString("status"));
				c.setBaesong(rs.getInt("baesong"));
				c.setPay_way(rs.getString("pay_way"));
				c.setPay(rs.getString("pay"));
				c.setA_name(rs.getString("a_name"));
				c.setB_name(rs.getString("b_name"));
				c.setB_zipcode(rs.getString("b_zipcode"));
				c.setB_zip1(rs.getString("b_zip1"));
				c.setB_zip2(rs.getString("b_zip2"));
				c.setB_zip3(rs.getString("b_zip3"));
				c.setSigndate(rs.getString("signdate"));
				a.add(c);
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
		return a;
	}
	
	public ArrayList<Buy_list> allOrder_list_admin() {
		d.getCon();
		ArrayList<Buy_list> a = new ArrayList<Buy_list>();
		try {
			//
						
			
			String sql = "select * from buy_list order by uid desc";
			pstmt=d.conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Buy_list c = new Buy_list();
				c.setUid(rs.getInt("uid"));
				c.setBuy_id(rs.getString("buy_id"));
				c.setList_name(rs.getString("list_name"));
				c.setPrice(rs.getInt("price"));
				c.setStatus(rs.getString("status"));
				c.setBaesong(rs.getInt("baesong"));
				c.setPay_way(rs.getString("pay_way"));
				c.setPay(rs.getString("pay"));
				c.setA_name(rs.getString("a_name"));
				c.setB_name(rs.getString("b_name"));
				c.setB_zipcode(rs.getString("b_zipcode"));
				c.setB_zip1(rs.getString("b_zip1"));
				c.setB_zip2(rs.getString("b_zip2"));
				c.setB_zip3(rs.getString("b_zip3"));
				c.setSigndate(rs.getString("signdate"));
				a.add(c);
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
		return a;
	}	
	
	public ArrayList<Cart> allOrder_items(String id,String cart_name) {
		d.getCon();
		ArrayList<Cart> a = new ArrayList<Cart>();
		try {
			String sql="select * from cart where buy_id=? and cart_name=?";
			pstmt=d.conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, cart_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Cart c = new Cart();
				c.setUid(rs.getInt("uid"));
				c.setTb_uid(rs.getInt("tb_uid"));
				c.setTb_table(rs.getString("tb_table"));
				c.setCategory(rs.getString("category"));
				c.setCart_name(rs.getString("cart_name"));
				c.setBuy_id(rs.getString("buy_id"));
				c.setSell_id(rs.getString("sell_id"));
				c.setSubject(rs.getString("subject"));
				c.setPrice(rs.getInt("price"));
				c.setNumber(rs.getInt("number"));
				c.setBaesong(rs.getInt("baesong"));
				c.setBaesong_msg(rs.getString("baesong_msg"));
				c.setBaesong_com(rs.getString("baesong_com"));
				c.setBaesong_num(rs.getString("baesong_num"));
				c.setBaesong_date(rs.getString("baesong_date"));	
				c.setBaesong_status(rs.getString("baesong_status"));					
				c.setFile1_s(rs.getString("file1_s"));
				c.setSigndate(rs.getString("signdate"));
				c.setStatus(rs.getString("status"));
				c.setS_zipcode(rs.getString("s_zipcode"));
				c.setS_zip1(rs.getString("s_zip1"));
				c.setS_zip2(rs.getString("s_zip2"));
				c.setS_zip3(rs.getString("s_zip3"));
				a.add(c);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return a;
	}
	
	
	public ArrayList<Cart> allOrder_items_manage(String id) {
		d.getCon();
		ArrayList<Cart> a = new ArrayList<Cart>();
		try {
			String sql="select * from cart where sell_id=? and status='Y'";
			pstmt=d.conn.prepareStatement(sql);
	
			pstmt.setString(1, id);
//			System.out.println(id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Cart c = new Cart();
				c.setUid(rs.getInt("uid"));
				c.setTb_uid(rs.getInt("tb_uid"));
				c.setTb_table(rs.getString("tb_table"));
				c.setCategory(rs.getString("category"));
				c.setCart_name(rs.getString("cart_name"));
				c.setBuy_id(rs.getString("buy_id"));
				c.setSell_id(rs.getString("sell_id"));
				c.setSubject(rs.getString("subject"));
				c.setPrice(rs.getInt("price"));
				c.setNumber(rs.getInt("number"));
				c.setBaesong(rs.getInt("baesong"));
				c.setBaesong_msg(rs.getString("baesong_msg"));
				c.setBaesong_com(rs.getString("baesong_com"));
				c.setBaesong_num(rs.getString("baesong_num"));
				c.setBaesong_date(rs.getString("baesong_date"));	
				c.setBaesong_status(rs.getString("baesong_status"));					
				c.setFile1_s(rs.getString("file1_s"));
				c.setSigndate(rs.getString("signdate"));
				c.setStatus(rs.getString("status"));
				c.setS_zipcode(rs.getString("s_zipcode"));
				c.setS_zip1(rs.getString("s_zip1"));
				c.setS_zip2(rs.getString("s_zip2"));
				c.setS_zip3(rs.getString("s_zip3"));
				a.add(c);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return a;
	}	
	public Buy_list detail_buy_list(String id, String cart_name) {
		d.getCon();
		Buy_list a = new Buy_list();
		try {
			String sql="select * from buy_list where buy_id=? and list_name=?";
			pstmt=d.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, cart_name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				a.setUid(rs.getInt("uid"));
				a.setBuy_id(rs.getString("buy_id"));
				a.setPrice(rs.getInt("price"));
				a.setBaesong(rs.getInt("baesong"));
				a.setPay_way(rs.getString("pay_way"));
				a.setPay(rs.getString("pay"));
				a.setA_name(rs.getString("a_name"));
				a.setAccount(rs.getString("account"));	
				a.setB_name(rs.getString("b_name"));
				a.setB_tel(rs.getString("b_tel"));
				a.setB_zipcode(rs.getString("b_zipcode"));
				a.setB_zip1(rs.getString("b_zip1"));
				a.setB_zip2(rs.getString("b_zip2"));
				a.setB_zip3(rs.getString("b_zip3"));
				a.setSigndate(rs.getString("signdate"));
			}
			rs.close();
			pstmt.close();
			d.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return a;
	}
	public void ok_pay_cart(String list_name) {
		
		d.getCon();
		try {
			String sql="update cart set baesong_status='1', status='Y' where cart_name=?";
			pstmt=d.conn.prepareStatement(sql);


			pstmt.setString(1, list_name);
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
	
	public void ok_pay_buy_list(String list_name) {
		d.getCon();
		try {
			String sql="update buy_list set pay='Y' where list_name=?";
			pstmt=d.conn.prepareStatement(sql);


			pstmt.setString(1, list_name);
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
	
	public void submit_request(String buy_id,String cart_name, String subject,String baesong_status,int price,int baesong) {
		d.getCon();
		try {
			String sql="update cart set baesong_status=? where buy_id=? and cart_name=? and subject=? and price=? and baesong=?";
			pstmt=d.conn.prepareStatement(sql);
			pstmt.setString(1, baesong_status);
			pstmt.setString(2, buy_id);
			pstmt.setString(3, cart_name);
			pstmt.setString(4, subject);
			pstmt.setInt(5, price);
			pstmt.setInt(6, baesong);
			
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
