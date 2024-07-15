package co.camping.vo;

public class CampingVO {
	
	private String resNo; //자리번호
	private String resDate; //날짜
	private String resPrice; //금액
	private String resName; //예약자이름
	private String resId; //프라이머리키
	private String resEtc; //요청사항
	
	
	
	public String getResNo() {
		return resNo;
	}

	public void setResNo(String resNo) {
		this.resNo = resNo;
	}

	public String getResDate() {
		return resDate;
	}

	public void setResDate(String resDate) {
		this.resDate = resDate;
	}

	public String getResPrice() {
		return resPrice;
	}

	public void setResPrice(String resPrice) {
		this.resPrice = resPrice;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResEtc() {
		return resEtc;
	}

	public void setResEtc(String resEtc) {
		this.resEtc = resEtc;
	}

	@Override
	public String toString() {
		return "CampingVO [resId=" + resId + ", resName=" + resName + ", resNo=" + resNo + ", resPrice=" + resPrice
				+ ", resDate=" + resDate + ", resEtc=" + resEtc + "]";
	}
	
	public String briefShow() {
		return String.format("   %-6s %-14s %-12s %-10s %-10s", resNo, resDate, resPrice, resName, resEtc);
//		return "   " + resNo + "     " + resDate + "    " + resPrice + "       " + resName + "         " + resEtc;
	}
	
//	System.out.println(String.format("%-10s %-15s %-25s %-20s", "번호", "제목", "업로드 날짜", "읽음"));
	
}
