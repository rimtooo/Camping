package co.camping.vo;

public class AreaVO {
	private String cpNo; //자리번호(p)
	private String cpPerson;//최대인원
	private String cpPrice;//가격
	
	
	public String getCpNo() {
		return cpNo;
	}
	public void setCpNo(String cpNo) {
		this.cpNo = cpNo;
	}
	public String getCpPerson() {
		return cpPerson;
	}
	public void setCpPerson(String cpPerson) {
		this.cpPerson = cpPerson;
	}
	public String getCpPrice() {
		return cpPrice;
	}
	public void setCpPrice(String cpPrice) {
		this.cpPrice = cpPrice;
	}
	
	
	public String briefShow() {
		return String.format("   %-8s %-10s %-10s", cpNo, cpPerson, cpPrice);

//		return cpNo + "       " + cpPerson + "         " + cpPrice;
	}
	
}
