package t2_CRUD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class heowonDAO3 {
	private Connection conn = null;
	
	public heowonDAO3() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/javaclass";
			String user = "atom";
			String padssword = "1234";
			
			conn = DriverManager.getConnection(url, user, padssword);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패 ");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
		}
	}
	
	public void connClose() {
		try {
			conn.close();
			System.out.println("DB 연결 끊기");
		} catch (SQLException e) {}
	}
	
	//전체회원조회
	public void getList() {
		try {
			 Statement stmt = conn.createStatement(); //테이블 제어하기 위해 처음 해야 할 것 => statement
			 String sql = "select * from heowon";
			 ResultSet rs = stmt.executeQuery(sql);  //ResultSet이 레코드 관리하는 객체 , sql 명령이 쿼리라 함
			 //레코드 포인트가 rs이다.
			 
			 System.out.println("=============================================================");
			 System.out.println("번호\t성명\t나이\t성별\t주소");
			 System.out.println("=============================================================");
			 while(rs.next()){
				 System.out.print(rs.getInt("idx")+"\t");
				 System.out.print(rs.getString("name")+"\t");
				 System.out.print(rs.getInt("age")+"\t");
				 System.out.print(rs.getString("gender")+"\t");
				 System.out.print(rs.getString("Address")+"\n");
			 };
			 System.out.println("=============================================================");
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}
	
	
}
