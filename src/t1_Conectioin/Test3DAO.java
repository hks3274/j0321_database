package t1_Conectioin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test3DAO {
	Connection conn = null;
	
	public Test3DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/javaclass";
			String user = "atom";
			String password ="1234";
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패 : "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB 연결 실패 : "+e.getMessage());
		}finally {
		}
		
	}
	
	public  void connclose() {
 		try {
 			conn.close();
 			System.out.println("데이터베이스 연결 끊기 성공!");
 		} catch (SQLException e) {}
 	}
 	
	
}
