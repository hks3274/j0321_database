package t3_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class heowonDAO2 {
	private Connection conn = null;

	public heowonDAO2() {
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
		} catch (SQLException e) {
		}
	}

	// 전체회원조회
	public ArrayList<HeowonVO> getList() {
		HeowonVO vo = null;
		ArrayList<HeowonVO> vos = new ArrayList<HeowonVO>();

		try {
			Statement stmt = conn.createStatement(); // 테이블 제어하기 위해 처음 해야 할 것 => statement
			String sql = "select * from heowon";
			ResultSet rs = stmt.executeQuery(sql); // ResultSet이 레코드 관리하는 객체 , sql 명령이 쿼리라 함
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
		}
		return vos;
	}

	// 개별검색처리
	public HeowonVO getSearch(String name) {
		HeowonVO vo = new HeowonVO();

		try {
			Statement stmt = conn.createStatement(); // 테이블 제어하기 위해 처음 해야 할 것 => statement
			String sql = "select * from heowon where name = '" + name + "'";
			ResultSet rs = stmt.executeQuery(sql); // ResultSet이 레코드 관리하는 객체 , sql 명령이 쿼리라 함
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
		}

		return vo;
	}

	public void setUpDate(int idx, int choice, String content) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "";
			if (choice == 1) {
				sql = "update heowon set name = '" + content + "' where idx = " + idx;
			} else if (choice == 2) {
				sql = "update heowon set age = " + Integer.parseInt(content) + " where idx = " + idx;
			} else if (choice == 3) {
				sql = "update heowon set gender = '" + content + "' where idx = " + idx;
			} else if (choice == 4) {
				sql = "update heowon set address = '" + content + "' where idx = " + idx;
			}
			stmt.executeUpdate(sql);
			// excuteQuery는 결과를 넘겨받는 경우에 사용 그래서 excuteQuery만 select 에만 사용.
			// 나머지는 모조건 excuteUpdata 사용
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}

	public void setDelete(String name) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "delete from heowon where name = '" + name + "'";
			stmt.executeUpdate(sql);
			// excuteQuery는 결과를 넘겨받는 경우에 사용 그래서 excuteQuery만 select 에만 사용.
			// 나머지는 모조건 excuteUpdata 사용
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}

	// 회원등록
	public void setInput(HeowonVO vo) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "insert into heowon values (default ,'" + vo.getName() + "'," + vo.getAge() + ",'" + vo.getGender()+ "','" + vo.getAddress() + "')";
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}

}
