package model;

public class Lecture {
	int uid;
	String id;
	String subject;
	String comment;
	String category;
	String p_name1;
	int price1;
	int number1;
	String p_name2;
	int price2;
	int number2;
	int baesong;
	int baesong_free;
	String gongji;
	String file1_o;
	String file1_s;
	String file2_o;
	String file3_o;
	String account_name;
	String account_num;
	String zipcode;
	String zip1;
	String zip2;
	String zip3;	
	String signdate;
	int review_num;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getP_name1() {
		return p_name1;
	}
	public void setP_name1(String p_name1) {
		this.p_name1 = p_name1;
	}
	public int getPrice1() {
		return price1;
	}
	public void setPrice1(int price1) {
		this.price1 = price1;
	}
	public int getNumber1() {
		return number1;
	}
	public void setNumber1(int number1) {
		this.number1 = number1;
	}
	public String getP_name2() {
		return p_name2;
	}
	public void setP_name2(String p_name2) {
		this.p_name2 = p_name2;
	}
	public int getPrice2() {
		return price2;
	}
	public void setPrice2(int price2) {
		this.price2 = price2;
	}
	public int getNumber2() {
		return number2;
	}
	public void setNumber2(int number2) {
		this.number2 = number2;
	}
	public int getBaesong() {
		return baesong;
	}
	public void setBaesong(int baesong) {
		this.baesong = baesong;
	}
	public int getBaesong_free() {
		return baesong_free;
	}
	public void setBaesong_free(int baesong_free) {
		this.baesong_free = baesong_free;
	}
	public String getGongji() {
		return gongji;
	}
	public void setGongji(String gongji) {
		this.gongji = gongji;
	}
	public String getFile1_o() {
		return file1_o;
	}
	public void setFile1_o(String file1_o) {
		this.file1_o = file1_o;
	}
	public String getFile1_s() {
		return file1_s;
	}
	public void setFile1_s(String file1_s) {
		this.file1_s = file1_s;
	}
	public String getFile2_o() {
		return file2_o;
	}
	public void setFile2_o(String file2_o) {
		this.file2_o = file2_o;
	}
	public String getFile3_o() {
		return file3_o;
	}
	public void setFile3_o(String file3_o) {
		this.file3_o = file3_o;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_num() {
		return account_num;
	}
	public void setAccount_num(String account_num) {
		this.account_num = account_num;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getZip1() {
		return zip1;
	}
	public void setZip1(String zip1) {
		this.zip1 = zip1;
	}
	public String getZip2() {
		return zip2;
	}
	public void setZip2(String zip2) {
		this.zip2 = zip2;
	}
	public String getZip3() {
		return zip3;
	}
	public void setZip3(String zip3) {
		this.zip3 = zip3;
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	@Override
	public String toString() {
		return "Lecture [uid=" + uid + ", id=" + id + ", subject=" + subject + ", comment=" + comment + ", category="
				+ category + ", p_name1=" + p_name1 + ", price1=" + price1 + ", number1=" + number1 + ", p_name2="
				+ p_name2 + ", price2=" + price2 + ", number2=" + number2 + ", baesong=" + baesong + ", baesong_free="
				+ baesong_free + ", gongji=" + gongji + ", file1_o=" + file1_o + ", file1_s=" + file1_s + ", file2_o="
				+ file2_o + ", file3_o=" + file3_o + ", account_name=" + account_name + ", account_num=" + account_num
				+ ", zipcode=" + zipcode + ", zip1=" + zip1 + ", zip2=" + zip2 + ", zip3=" + zip3 + ", signdate="
				+ signdate + ", review_num=" + review_num + ", getUid()=" + getUid() + ", getId()=" + getId()
				+ ", getSubject()=" + getSubject() + ", getComment()=" + getComment() + ", getCategory()="
				+ getCategory() + ", getP_name1()=" + getP_name1() + ", getPrice1()=" + getPrice1() + ", getNumber1()="
				+ getNumber1() + ", getP_name2()=" + getP_name2() + ", getPrice2()=" + getPrice2() + ", getNumber2()="
				+ getNumber2() + ", getBaesong()=" + getBaesong() + ", getBaesong_free()=" + getBaesong_free()
				+ ", getGongji()=" + getGongji() + ", getFile1_o()=" + getFile1_o() + ", getFile1_s()=" + getFile1_s()
				+ ", getFile2_o()=" + getFile2_o() + ", getFile3_o()=" + getFile3_o() + ", getAccount_name()="
				+ getAccount_name() + ", getAccount_num()=" + getAccount_num() + ", getZipcode()=" + getZipcode()
				+ ", getZip1()=" + getZip1() + ", getZip2()=" + getZip2() + ", getZip3()=" + getZip3()
				+ ", getSigndate()=" + getSigndate() + ", getReview_num()=" + getReview_num() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
