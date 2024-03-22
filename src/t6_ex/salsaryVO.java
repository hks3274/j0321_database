package t6_ex;

public class salsaryVO {
	private char jikkub;
	private int bonbong;
	
	public char getJikkub() {
		return jikkub;
	}
	public void setJikkub(char jikkub) {
		this.jikkub = jikkub;
	}
	public int getBonbong() {
		return bonbong;
	}
	public void setBonbong(int bonbong) {
		this.bonbong = bonbong;
	}
	
	@Override
	public String toString() {
		return "VO [jikkub=" + jikkub + ", bonbong=" + bonbong + "]";
	}
}
