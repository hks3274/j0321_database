package t5_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SungjukDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	SungjukVO vo = null;
	private String sql = "";

	public SungjukDAO() {
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom";
		String password = "1234";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			System.out.println("Driver 검색 실패" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB 연결 실패" + e.getMessage());
		}
	}

	// connClose()
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {
		}
	}

	public void pstmtClose() {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
		}
	}

	public void rsClose() {
		try {
			if (rs != null)
				rs.close();
			pstmtClose();
		} catch (Exception e) {
		}
	}

	// 성적자료 입력처리
	public int setSungjukInput(SungjukVO vo) {
		int res = 0;

		// PreparedStatement 들어오는 변수를 ?를 넣음으로써 보안이 높다 ==> ? 명령자체에는 ''가 붙어있는것이다. 아주 편하다.
		// sql을 먼저 만들고 생성할때 넣어버린다.
		// 순서는 sql먼저 작성 => PreparedStatement생성 => sql 실행이다.

		try {
			sql = "insert into Sungjuk values (default,?,?,?,?)"; // default를 제외한 모든 것을 ?로 하면 된다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}

		return res;
	}

	// 성명조회(개별검색/동명2인검색)
	public SungjukVO getSungjukSearch(String name) {
		SungjukVO vo = new SungjukVO();
		try {
			sql = "select * from Sungjuk where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
			} else
				vo = null;

		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}

		return vo;
	}

	public ArrayList<SungjukVO> getSungjukList() {
		ArrayList<SungjukVO> vos = new ArrayList<SungjukVO>();
		
		try {
			sql = "select * from Sungjuk order by name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new SungjukVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
				vos.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		
		return vos;
	}
	
	//성적자료 수정하기
	public int setSungjukUpdate() {
		int res =0;
		try {
			sql = "update sungjuk set name =?, kor =?, eng =?, mat =? where idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			pstmt.setInt(5, vo.getIdx());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
	}

	public int setSungjukDelete(int idx) {
		int res = 0;
		try {
			sql = "delete from sungjuk where idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
}
