package t5_CRUD;

import java.util.ArrayList;
import java.util.Scanner;

public class SungjukService {
	Scanner sc = new Scanner(System.in);
	SungjukDAO dao = new SungjukDAO();
	SungjukVO vo = null;
	int res;
	String ans = "N";
	int choice = 0;

	public void setSungjukInput() {
		String name = "";
		int kor = 0, eng = 0, mat = 0;

		while (true) {
			System.out.println("\n** 성정 입력처리 **");
			while (true) {
				System.out.print("성명 : ");
				name = sc.next();
				// 동명이인 처리...
				vo = dao.getSungjukSearch(name);
				if (vo == null)
					break;
				else
					System.out.println("같은 이름이 존재합니다. 다시 입력해 주세요\n");
			}

			System.out.print("국어 : ");
			kor = sc.nextInt();
			System.out.print("영어 : ");
			eng = sc.nextInt();
			System.out.print("수학 : ");
			mat = sc.nextInt();

			vo = new SungjukVO();
			vo.setName(name);
			vo.setKor(kor);
			vo.setEng(eng);
			vo.setMat(mat);

			res = dao.setSungjukInput(vo);

			if (res != 0)
				System.out.println("성적자료가 등록되었습니다");
			else
				System.out.println("성적자료 등록 실패");

			System.out.print("계속하시겠습니까?(y/n) ==>");
			ans = sc.next();
			if (!ans.toUpperCase().equals("Y"))
				break;
		}

	}

	public void getSungjukList() { // 여러건이 존재할때는 ArrayList룰 사용
		ArrayList<SungjukVO> vos = dao.getSungjukList();

		System.out.println("\n\t*** 성 적 리 스 트 ***");
		System.out.println("==============================================================");
		System.out.println("번호\t성명\t국어\t영어\t총점\t평균\t학점");
		System.out.println("--------------------------------------------------------------");
		for (int i = 0; i < vos.size(); i++) {
			vo = vos.get(i);
			calculator(vo);
			System.out.print(" " + (i + 1) + "\t");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getKor() + "\t");
			System.out.print(vo.getEng() + "\t");
			System.out.print(vo.getMat() + "\t");
			System.out.print(vo.getTot() + "\t");
			System.out.print(String.format("%.1f", vo.getAvg()) + "\t");
			System.out.print(vo.getGrade() + "\n");
		}
		System.out.println("==============================================================");
		System.out.println("\t\t\t\t\t\t총인원: " + vos.size());
		System.out.println("==============================================================");
	}

	// 계산(총점, 평균, 학점)
	private void calculator(SungjukVO vo) {
		vo.setTot(vo.getKor() + vo.getEng() + vo.getEng());
		vo.setAvg(vo.getTot() / 3.0);

		if (vo.getAvg() >= 90)
			vo.setGrade('A');
		else if (vo.getAvg() >= 80)
			vo.setGrade('B');
		else if (vo.getAvg() >= 70)
			vo.setGrade('C');
		else if (vo.getAvg() >= 60)
			vo.setGrade('D');
		else
			vo.setGrade('F');
	}

	// 개별자료 검색
	public void getSungjukSearch() {
		while (true) {
			sungjukBasicSearch();

			System.out.print("계속하시겠습니까?(y/n) ==>");
			ans = sc.next();
			if (!ans.toUpperCase().equals("Y"))
				break;
		}

	}

	// 회원정보 수정하기
	public void setSungjukUpdate() {
		sungjukBasicSearch();

		boolean run = true;
		while (run) {

			System.out.print("수정항목선택 : 1.성명 2.국어 3.영어 4.수학 0.종료 ==>");
			choice = sc.nextInt();
			System.out.print("수정할 내용을 입력하세요 : ");

			switch (choice) {
				case 1:
					vo.setName(sc.next());
					break;
				case 2:
					vo.setKor(sc.nextInt());
					break;
				case 3:
					vo.setEng(sc.nextInt());
					break;
				case 4:
					vo.setMat(sc.nextInt());
					break;
				default:
					run = false;
			}
		}
		res = dao.setSungjukUpdate();

		if (res != 0) {
			System.out.println("성적내역이 수정되었습니다.");
		} else {
			System.out.println("수정된 내역이 없습니다.");
		}
	}

	private void sungjukBasicSearch() {
		System.out.print("\n조회할 성명을 입력하세요 : ");
		String name = sc.next();

		vo = dao.getSungjukSearch(name);

		if (vo != null) {
			calculator(vo);
			System.out.println("\n고유번호 : " + vo.getIdx());
			System.out.println("이름 : " + vo.getName());
			System.out.println("국어 : " + vo.getKor());
			System.out.println("영어 : " + vo.getEng());
			System.out.println("수학 : " + vo.getMat());
			System.out.println("총점 : " + vo.getTot());
			System.out.println("평균 : " + String.format("%.1f", vo.getAvg()));
			System.out.println("학점 : " + vo.getGrade() + "\n");
		} else {
			System.out.println("검색하신 " + name + "은 없습니다.");
		}
	}

	// 점수 삭제하기
	public void setSungjukDelete() {
		sungjukBasicSearch();

		if (vo != null) {
			System.out.println("삭제하시겠습니까?");
			ans = sc.next();

			if (ans.toUpperCase().equals("Y")) {
				res = dao.setSungjukDelete(vo.getIdx());
				if(res != 0) System.out.println(vo.getName()+"삭제되었습니다.");
			} else {
				System.out.println("삭제가 취소 되었습니다.");				
			}
		}
	}

}
