package t5_CRUD;

public class SungjukVO {
	private int idx;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	// 날짜는 String 타입으로 정해주면 편하다. boolean은 char타입으로 주면 편하다.

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	@Override
	public String toString() {
		return "SungjukVO [idx=" + idx + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + "]";
	}

}
