package t1_Conectioin;

public class Test3Run {
	public static void main(String[] args) {
		Test3DAO dao = new Test3DAO();
		
		System.out.println("데이터베이스 연결 후 작업 중입니다.~~~~~~");
		
		Test3Service t3 = new Test3Service();
		
		t3.getTestMethod();
		
		// DB 연결 종료
		dao.connclose();
		
	}
}
