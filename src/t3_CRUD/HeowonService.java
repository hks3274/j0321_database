package t3_CRUD;

import java.util.ArrayList;
import java.util.Scanner;

public class HeowonService {
	Scanner sc = new Scanner(System.in); 
	
	// 전체조회
	public void getList() {
//		heowonDAO dao = new heowonDAO();
		heowonDAO2 dao = new heowonDAO2();

		ArrayList<HeowonVO> vos = dao.getList();
		HeowonVO vo = new HeowonVO();

		System.out.println("============================================");
		System.out.println("번호\t성명\t나이\t성별\t주소");
		System.out.println("============================================");
		for (int i = 0; i < vos.size(); i++) {
			vo = vos.get(i);
			System.out.print((i+1) + "\t");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getAge() + "\t");
			System.out.print(vo.getGender() + "\t");
			System.out.print(vo.getAddress() + "\n");
		}
		System.out.println("============================================");
		System.out.println("\t\t\t\t총 : " + vos.size() + " 건");

		dao.connClose();
	}

	// 개별 검색처리
	public void getSearch(String name) {
		heowonDAO2 dao = new heowonDAO2();

		HeowonVO vo = dao.getSearch(name);

		System.out.println(name + "으로 검색된 자료?");
		if (vo.getName() != null) {

			System.out.println("번호 : " + vo.getIdx());
			System.out.println("성명 : " + vo.getName());
			System.out.println("나이 : " + vo.getAge());
			System.out.println("성별 : " + vo.getGender());
			System.out.println("주소 : " + vo.getAddress());
		} else {
			System.out.println("검색된 자료가 없습니다.");
		}

		dao.connClose();
	}

	// 회원정보수정
	public void setUpDate(String name) {
		heowonDAO2 dao = new heowonDAO2();
		HeowonVO vo = dao.getSearch(name);

		System.out.println(name + "으로 검색된 자료?");
		if (vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx());
			System.out.println("성명 : " + vo.getName());
			System.out.println("나이 : " + vo.getAge());
			System.out.println("성별 : " + vo.getGender());
			System.out.println("주소 : " + vo.getAddress());
			
			System.out.println("------------------------");
			System.out.print("수정할 항목? 1.성명 2.나이 3.성별 4.주소 ==>");
			int choice = sc.nextInt();
			System.out.print("수정할 내용을 입력해주세요 ==>");
			String content = sc.next();
			
			dao.setUpDate(vo.getIdx(), choice, content);
			System.out.println("자료가 수정되었습니다");
			
		} else {
			System.out.println("검색된 자료가 없습니다.");
		}
		
		dao.connClose();
	}
	
	//삭제처리
	public void setDelete(String name) {
		heowonDAO2 dao = new heowonDAO2();
		HeowonVO vo = dao.getSearch(name);

		System.out.println(name + "으로 검색된 자료?");
		if (vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx());
			System.out.println("성명 : " + vo.getName());
			System.out.println("나이 : " + vo.getAge());
			System.out.println("성별 : " + vo.getGender());
			System.out.println("주소 : " + vo.getAddress());
			
			System.out.println("------------------------");
			System.out.print("삭제하시겠습니까? (Y/N)==>");
			String choice = sc.next();
			
			if(choice.toUpperCase().equals("Y")) {
				dao.setDelete(name);
				System.out.println("자료가 삭제되었습니다");
			}else {
				System.out.println("삭제가 취소되었습니다");
			}
		}else {
			System.out.println("검색된 자료가 없습니다.");
		}
		dao.connClose();
	}

	public void setInput() {
		heowonDAO2 dao = new heowonDAO2();
		
		String name, gender, address;
		int age;
		
		System.out.println("==> 회원 정보 등록하기");
		System.out.print("성명: ");
		name = sc.next();
		System.out.print("나이: ");
		age = sc.nextInt();
		System.out.print("성별: ");
		gender = sc.next();
		System.out.print("주소: ");
		address = sc.next();
		
		HeowonVO vo = new HeowonVO();
		
		vo.setName(name);
		vo.setAge(age);
		vo.setGender(gender);
		vo.setAddress(address);
		
		dao.setInput(vo);
		System.out.println("자료 등록 완료!!!");
		
		dao.connClose();
	}
}
		
