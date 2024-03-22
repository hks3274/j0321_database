package t4_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class heowonDAO2 {
	private Connection conn = null; // 데이터베이스를 찾고 연결
	private Statement stmt = null; // 테이블을 찾고 연결 sql명령을 입력하기 위해서는 사용해야 함
	private ResultSet rs = null; //
	private String sql = null;

	public heowonDAO2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/javaclass";
//			String url = "jdbc:mysql://127.0.0.1:3306/javaclass";
//			String url = "jdbc:mysql://192.168.50.51:3306/javaclass";
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
		} catch (SQLException e) {
		}
	}

	public void stmtClose() {
		try {
			if (stmt != null)
				stmt.close(); // null 일때는 닫지 말아달라
		} catch (SQLException e) {
		}
	}

	public void rsClose() {
		try {
			if (rs != null)
				rs.close(); // null 일때는 닫지 말아달라
			stmtClose();
		} catch (SQLException e) {
		}
	}

	// 전체회원조회
	public ArrayList<HeowonVO> getList() {
		HeowonVO vo = null;
		ArrayList<HeowonVO> vos = new ArrayList<HeowonVO>();

		try {
			stmt = conn.createStatement(); // 테이블 제어하기 위해 처음 해야 할 것 => statement
			sql = "select * from heowon";
			rs = stmt.executeQuery(sql); // ResultSet이 레코드 관리하는 객체 , sql 명령이 쿼리라 함
			// 레코드 포인트가 rs이다.

			while (rs.next()) {
				vo = new HeowonVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				vos.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
	}

	// 개별검색처리
	public HeowonVO getSearch(String name) {
		HeowonVO vo = new HeowonVO();

		try {
			stmt = conn.createStatement(); // 테이블 제어하기 위해 처음 해야 할 것 => statement
			sql = "select * from heowon where name = '" + name + "'";
			rs = stmt.executeQuery(sql); // ResultSet이 레코드 관리하는 객체 , sql 명령이 쿼리라 함
			// 레코드 포인트가 rs이다.

			if (rs.next()) {
				vo = new HeowonVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}

		return vo;
	}
	/*
	 * 
	 * public void setUpDate(int idx, int choice, String content) { try { stmt =
	 * conn.createStatement(); sql = ""; if (choice == 1) { sql =
	 * "update heowon set name = '" + content + "' where idx = " + idx; } else if
	 * (choice == 2) { sql = "update heowon set age = " + Integer.parseInt(content)
	 * + " where idx = " + idx; } else if (choice == 3) { sql =
	 * "update heowon set gender = '" + content + "' where idx = " + idx; } else if
	 * (choice == 4) { sql = "update heowon set address = '" + content +
	 * "' where idx = " + idx; } stmt.executeUpdate(sql); // excuteQuery는 결과를 넘겨받는
	 * 경우에 사용 그래서 excuteQuery만 select 에만 사용. // 나머지는 모조건 excuteUpdata 사용 } catch
	 * (SQLException e) { System.out.println("SQL 오류 : " + e.getMessage()); }
	 * finally { stmtClose(); } }
	 * 
	 */

	public void setDelete(String name) {
		try {
			stmt = conn.createStatement();
			sql = "delete from heowon where name = '" + name + "'";
			stmt.executeUpdate(sql);
			// excuteQuery는 결과를 넘겨받는 경우에 사용 그래서 excuteQuery만 select 에만 사용.
			// 나머지는 모조건 excuteUpdata 사용
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose();
		}
	}

	// 회원등록
	public void setInput(HeowonVO vo) {
		try {
			stmt = conn.createStatement();
			sql = "insert into heowon values (default ,'" + vo.getName() + "'," + vo.getAge() + ",'" + vo.getGender() + "','"
					+ vo.getAddress() + "')";
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose();
		}
	}

	// 회원 정보수정하기

	public int setUpDate(HeowonVO vo) {
		int res = 0;
		try {
			stmt = conn.createStatement();

			sql = "update heowon set name = '"+vo.getName()+"', age = "+vo.getAge()+", gender = '"+vo.getGender()+"',address = '"+vo.getAddress()+"' where idx ="+vo.getIdx();
			res = stmt.executeUpdate(sql);
//			System.out.println("res : "+res);
			
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose();
		}
		return res;
	}

}
