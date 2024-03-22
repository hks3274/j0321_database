package t6_ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class salaryDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	String sql = "";
	
	public salaryDAO() {
		
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패");
		} catch (SQLException e) {
			System.out.println("DB 검색 실패");
		}
		
	}
	
	
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	public void pstmtClose() {
		try {
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {}
	}
	
	public void rsClose() {
		try {
			if(rs != null) rs.close();
			pstmtClose();
		} catch (SQLException e) {}
	}

	
}
