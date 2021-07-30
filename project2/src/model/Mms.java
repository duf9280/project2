package model;

public class Mms {

	int uid;
	String send_id;
	String receive_id;
	String subject;
	String comment;
	String write_day;
	String read_day;
	String file1;
	String send_del;
	String receive_del;
	String send_nickname;
	String receive_nickname;
	
	public String getSend_nickname() {
		return send_nickname;
	}

	public void setSend_nickname(String send_nickname) {
		this.send_nickname = send_nickname;
	}

	public String getReceive_nickname() {
		return receive_nickname;
	}

	public void setReceive_nickname(String receive_nickname) {
		this.receive_nickname = receive_nickname;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getSend_id() {
		return send_id;
	}

	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}

	public String getReceive_id() {
		return receive_id;
	}

	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
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

	public String getWrite_day() {
		return write_day;
	}

	public void setWrite_day(String write_day) {
		this.write_day = write_day;
	}

	public String getRead_day() {
		return read_day;
	}

	public void setRead_day(String read_day) {
		this.read_day = read_day;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getSend_del() {
		return send_del;
	}

	public void setSend_del(String send_del) {
		this.send_del = send_del;
	}

	public String getReceive_del() {
		return receive_del;
	}

	public void setReceive_del(String receive_del) {
		this.receive_del = receive_del;
	}

	@Override
	public String toString() {
		return "Mms [uid=" + uid + ", send_id=" + send_id + ", receive_id=" + receive_id + ", subject=" + subject
				+ ", comment=" + comment + ", write_day=" + write_day + ", read_day=" + read_day + ", file1=" + file1
				+ ", send_del=" + send_del + ", receive_del=" + receive_del + "]";
	}
}
