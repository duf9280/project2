package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {//�갡 ���� ������Ʈ���� ���� bdcon
	
	Connection conn;
	
	public void getCon() {//�����ͺ��̽� ����
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/project2";	
			String user="root";
			String password="1111";
			
			conn=DriverManager.getConnection(url, user, password);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
