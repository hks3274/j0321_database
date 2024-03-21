package t2_CRUD;

public class heoeonRun {
	public static void main(String[] args) {
//		heowonDAO dao = new heowonDAO();
//		heowonDAO2 dao1 = new heowonDAO2();
		heowonDAO3 dao3 = new heowonDAO3();
		
		//회원자료 전체조회
		dao3.getList();
		
		
		
		
		dao3.connClose();
	}
}
