package model;

public class Cart {
	int uid;
	int tb_uid;
	String tb_table;
	String cart_name;
	String buy_id;
	String sell_id;
	String subject;
	int price;
	int number;
	int baesong;
	String baesong_msg;
	String baesong_com;
	String baesong_num;
	String baesong_date;
	String baesong_status;
	String file1_s;
	String signdate;
	String category;
	String status;
	String s_zipcode;
	String s_zip1;
	String s_zip2;
	String s_zip3;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getTb_uid() {
		return tb_uid;
	}
	public void setTb_uid(int tb_uid) {
		this.tb_uid = tb_uid;
	}
	public String getTb_table() {
		return tb_table;
	}
	public void setTb_table(String tb_table) {
		this.tb_table = tb_table;
	}
	public String getCart_name() {
		return cart_name;
	}
	public void setCart_name(String cart_name) {
		this.cart_name = cart_name;
	}
	public String getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(String buy_id) {
		this.buy_id = buy_id;
	}
	public String getSell_id() {
		return sell_id;
	}
	public void setSell_id(String sell_id) {
		this.sell_id = sell_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getBaesong() {
		return baesong;
	}
	public void setBaesong(int baesong) {
		this.baesong = baesong;
	}
	public String getBaesong_msg() {
		return baesong_msg;
	}
	public void setBaesong_msg(String baesong_msg) {
		this.baesong_msg = baesong_msg;
	}
	public String getBaesong_com() {
		return baesong_com;
	}
	public void setBaesong_com(String baesong_com) {
		this.baesong_com = baesong_com;
	}
	public String getBaesong_num() {
		return baesong_num;
	}
	public void setBaesong_num(String baesong_num) {
		this.baesong_num = baesong_num;
	}
	public String getBaesong_date() {
		return baesong_date;
	}
	public void setBaesong_date(String baesong_date) {
		this.baesong_date = baesong_date;
	}
	public String getBaesong_status() {
		return baesong_status;
	}
	public void setBaesong_status(String baesong_status) {
		this.baesong_status = baesong_status;
	}
	public String getFile1_s() {
		return file1_s;
	}
	public void setFile1_s(String file1_s) {
		this.file1_s = file1_s;
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getS_zipcode() {
		return s_zipcode;
	}
	public void setS_zipcode(String s_zipcode) {
		this.s_zipcode = s_zipcode;
	}
	public String getS_zip1() {
		return s_zip1;
	}
	public void setS_zip1(String s_zip1) {
		this.s_zip1 = s_zip1;
	}
	public String getS_zip2() {
		return s_zip2;
	}
	public void setS_zip2(String s_zip2) {
		this.s_zip2 = s_zip2;
	}
	public String getS_zip3() {
		return s_zip3;
	}
	public void setS_zip3(String s_zip3) {
		this.s_zip3 = s_zip3;
	}
	@Override
	public String toString() {
		return "Cart [uid=" + uid + ", tb_uid=" + tb_uid + ", tb_table=" + tb_table + ", cart_name=" + cart_name
				+ ", buy_id=" + buy_id + ", sell_id=" + sell_id + ", subject=" + subject + ", price=" + price
				+ ", number=" + number + ", baesong=" + baesong + ", baesong_msg=" + baesong_msg + ", baesong_com="
				+ baesong_com + ", baesong_num=" + baesong_num + ", baesong_date=" + baesong_date + ", baesong_status="
				+ baesong_status + ", file1_s=" + file1_s + ", signdate=" + signdate + ", category=" + category
				+ ", status=" + status + ", s_zipcode=" + s_zipcode + ", s_zip1=" + s_zip1 + ", s_zip2=" + s_zip2
				+ ", s_zip3=" + s_zip3 + "]";
	}


}
