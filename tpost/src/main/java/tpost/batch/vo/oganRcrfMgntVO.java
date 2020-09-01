package tpost.batch.vo;


public class oganRcrfMgntVO {
	
	private String transDt;			//발송일자
	private String oganCd;			//기관코드
	private String ofapElctAddr;	//공인전자주소
	private String bfcsSndnYn;		//사전문자 발송 여부
	
	public String getTransDt() {
		return transDt;
	}
	
	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}
	
	public String getOganCd() {
		return oganCd;
	}
	
	public void setOganCd(String oganCd) {
		this.oganCd = oganCd;
	}
	
	public String getOfapElctAddr() {
		return ofapElctAddr;
	}
	
	public void setOfapElctAddr(String ofapElctAddr) {
		this.ofapElctAddr = ofapElctAddr;
	}
	
	public String getBfcsSndnYn() {
		return bfcsSndnYn;
	}
	
	public void setBfcsSndnYn(String bfcsSndnYn) {
		this.bfcsSndnYn = bfcsSndnYn;
	}
	
	
	//------------------------------------------------- 생성자/수정자 -------------------------------------------------
	
	
	private String crtr;
	private String amdr;
	
	public String getCrtr() {
		return crtr;
	}
	public void setCrtr(String crtr) {
		this.crtr = crtr;
	}
	
	public String getAmdr() {
		return amdr;
	}
	public void setAmdr(String amdr) {
		this.amdr = amdr;
	}
	
}
