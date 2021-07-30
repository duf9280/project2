package model;

public class Review {
	int uid;
	int tb_uid;
	String tb_table;
	String tb_id;
	String tb_subject;
	String tb_comment;
	int tb_score;
	String file1_o;
	String file1_s;
	String tb_date;
	int fid;
	String thread;
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
	public String getTb_id() {
		return tb_id;
	}
	public void setTb_id(String tb_id) {
		this.tb_id = tb_id;
	}
	public String getTb_subject() {
		return tb_subject;
	}
	public void setTb_subject(String tb_subject) {
		this.tb_subject = tb_subject;
	}
	public String getTb_comment() {
		return tb_comment;
	}
	public void setTb_comment(String tb_comment) {
		this.tb_comment = tb_comment;
	}
	public int getTb_score() {
		return tb_score;
	}
	public void setTb_score(int tb_score) {
		this.tb_score = tb_score;
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
	public String getTb_date() {
		return tb_date;
	}
	public void setTb_date(String tb_date) {
		this.tb_date = tb_date;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getThread() {
		return thread;
	}
	public void setThread(String thread) {
		this.thread = thread;
	}
	@Override
	public String toString() {
		return "Review [uid=" + uid + ", tb_uid=" + tb_uid + ", tb_table=" + tb_table + ", tb_id=" + tb_id
				+ ", tb_subject=" + tb_subject + ", tb_comment=" + tb_comment + ", tb_score=" + tb_score + ", file1_o="
				+ file1_o + ", file1_s=" + file1_s + ", tb_date=" + tb_date + ", fid=" + fid + ", thread=" + thread
				+ "]";
	}
	
	
}
