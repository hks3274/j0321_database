package t5_CRUD;

import java.util.Scanner;

public class SungjukService {
	Scanner sc = new Scanner(System.in);
	SungjukDAO dao = new SungjukDAO();
	SungjukVO vo = null;
	int res;
	String ans = "N";
	
	
	public void setSungjukInput() {
		vo = new SungjukVO();
		String name = "";
		int kor = 0, eng = 0, mat = 0;
		
		while(true) {
			System.out.println("\n** 성정 입력처리 **");
			while(true) {
				System.out.print("성명 : "); name = sc.next();
				//동명이인 처리...
				vo = dao.getSungjukSearch(name);
				if(vo == null) break;
				else System.out.println("같은 이름이 존재합니다. 다시 입력해 주세요\n");
			}
			
			System.out.print("국어 : "); kor = sc.nextInt();
			System.out.print("영어 : "); eng = sc.nextInt();
			System.out.print("수학 : "); mat = sc.nextInt();
			
			vo = new SungjukVO(); 
			vo.setName(name);
			vo.setKor(kor);
			vo.setEng(eng);
			vo.setMat(mat);
			
			res = dao.setSungjukInput(vo);
			
			if(res != 0) System.out.println("성적자료가 등록되었습니다");
			else System.out.println("성적자료 등록 실패");
			
			System.out.print("계속하시겠습니까?(y/n) ==>");
			ans = sc.next();
			if(!ans.toUpperCase().equals("Y")) break;
		}
		
		
		dao.connClose();
	}
	
}
