package model;

public class Reco {
	String tb_table;
	int tb_uid;
	String tb_id;
	String tb_name;
	String tb_reco;
	String tb_date;
	public String getTb_table() {
		return tb_table;
	}
	public void setTb_table(String tb_table) {
		this.tb_table = tb_table;
	}
	public int getTb_uid() {
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
	public String getTb_name() {
		return tb_name;
	}
	public void setTb_name(String tb_name) {
		this.tb_name = tb_name;
	}
	public String getTb_reco() {
		return tb_reco;
	}
	public void setTb_reco(String tb_reco) {
		this.tb_reco = tb_reco;
	}
	public String getTb_date() {
		return tb_date;
	}
	public void setTb_date(String tb_date) {
		this.tb_date = tb_date;
	}
	@Override
	public String toString() {
		return "Reco [tb_table=" + tb_table + ", tb_uid=" + tb_uid + ", tb_id=" + tb_id + ", tb_name=" + tb_name
				+ ", tb_reco=" + tb_reco + ", tb_date=" + tb_date + "]";
	}
	
	
}
