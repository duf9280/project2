package model;

public class Bbs {
	private int uid;
	private String id;
	private String nickname;
	private String subject;
	private String comment;
	private String signdate;
	private String gongji;
	private int ref;
	private String file1_o;
	private String file2_o;
	private int fid;
	private String thread;
	private int comment_num;

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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public String getGongji() {
		return gongji;
	}
	public void setGongji(String gongji) {
		this.gongji = gongji;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public String getFile1_o() {
		return file1_o;
	}
	public void setFile1_o(String file1_o) {
		this.file1_o = file1_o;
	}
	public String getFile2_o() {
		return file2_o;
	}
	public void setFile2_o(String file2_o) {
		this.file2_o = file2_o;
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
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	
	@Override
	public String toString() {
		return "Bbs [uid=" + uid + ", id=" + id + ", nickname=" + nickname + ", subject=" + subject + ", comment="
				+ comment + ", signdate=" + signdate + ", gongji=" + gongji + ", ref=" + ref + ", file1_o=" + file1_o
				+ ", file2_o=" + file2_o + ", fid=" + fid + ", thread=" + thread + ", comment_num=" + comment_num + "]";
	}	
	
}
