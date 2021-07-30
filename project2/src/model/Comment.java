package model;

public class Comment {
	int uid;
	String tb_table;
	int tb_uid;
	String tb_id;
	String tb_nickname;
	String tb_comment;
	String tb_date;
	int fid;
	String thread;
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTb_table(){
		return tb_table;
	}
	public void setTb_table(String tb_table){
		this.tb_table = tb_table;
	}
	public int getTb_uid(){
		return tb_uid;
	}
	public void setTb_uid(int tb_uid) {
		this.tb_uid = tb_uid;
	}
	public String getTb_id() {
		return tb_id;
	}
	public void setTb_id(String tb_id) {
		this.tb_id = tb_id;
	}
	public String getTb_nickname() {
		return tb_nickname;
	}
	public void setTb_nickname(String tb_nickname) {
		this.tb_nickname = tb_nickname;
	}
	public String getTb_comment() {
		return tb_comment;
	}
	public void setTb_comment(String tb_comment) {
		this.tb_comment = tb_comment;
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
		return "Comment [uid=" + uid + ", tb_table=" + tb_table + ", tb_uid=" + tb_uid + ", tb_id=" + tb_id
				+ ", tb_nickname=" + tb_nickname + ", tb_comment=" + tb_comment + ", tb_date=" + tb_date + ", fid="
				+ fid + ", thread=" + thread + "]";
	}
	
	
	
	
}
