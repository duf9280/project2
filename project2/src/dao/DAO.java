package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {//얘가 이전 프로젝트에서 쓰던 bdcon
	
	Connection conn;
	
	public void getCon() {//데이터베이스 낙낙
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
