package tpost.msgRcve.vo;

public class msgRcveVO {
	
	//공통으로 쓰는 애들
	private String transDt;
	private String sndnOgan;
	private String tmpltCd;
	private String ciNum;
	private String mappingYn;
	
	private String anocInfo;
	private String tmpltHtml;
	private String itemNm;
	private String itemLen;
	private String itemLocSeqn;
	
	private String anocInfoUrl;
	private String histSeq;
	
	private String a;  /*url받는 랜덤 함수값*/
	private String b;  /*열람일시 관련*/
	private String hexKey;
	
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	
	public String getHexKey() {
		return hexKey;
	}
	public void setHexKey(String hexKey) {
		this.hexKey = hexKey;
	}
	
	public String getAnocInfoUrl() {
		return anocInfoUrl;
	}
	public void setAnocInfoUrl(String anocInfoUrl) {
		this.anocInfoUrl = anocInfoUrl;
	}
	public String getHistSeq() {
		return histSeq;
	}
	public void setHistSeq(String histSeq) {
		this.histSeq = histSeq;
	}
	
	public String getTransDt() {
		return transDt;
	}
	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}
	
	public String getSndnOgan() {
		return sndnOgan;
	}
	public void setSndnOgan(String sndnOgan) {
		this.sndnOgan = sndnOgan;
	}
	
	public String getTmpltCd() {
		return tmpltCd;
	}
	public void setTmpltCd(String tmpltCd) {
		this.tmpltCd = tmpltCd;
	}
	
	public String getCiNum() {
		return ciNum;
	}
	public void setCiNum(String ciNum) {
		this.ciNum = ciNum;
	}
	
	public String getMappingYn() {
		return mappingYn;
	}
	public void setMappingYn(String mappingYn) {
		this.mappingYn = mappingYn;
	}
	
	public String getAnocInfo() {
		return anocInfo;
	}
	public void setAnocInfo(String anocInfo) {
		this.anocInfo = anocInfo;
	}
	
	public String getTmpltHtml() {
		return tmpltHtml;
	}
	public void setTmpltHtml(String tmpltHtml) {
		this.tmpltHtml = tmpltHtml;
	}
	
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	
	public String getItemLen() {
		return itemLen;
	}
	public void setItemLen(String itemLen) {
		this.itemLen = itemLen;
	}
	
	public String getItemLocSeqn() {
		return itemLocSeqn;
	}
	public void setItemLocSeqn(String itemLocSeqn) {
		this.itemLocSeqn = itemLocSeqn;
	}
	
	
	private String name;
	private String brdt;
	private String sexCls;
	private String ofapElctAddr;
	private String sndnRfsl;
	private String seqNo;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBrdt() {
		return brdt;
	}
	public void setBrdt(String brdt) {
		this.brdt = brdt;
	}
	
	public String getSexCls() {
		return sexCls;
	}
	public void setSexCls(String sexCls) {
		this.sexCls = sexCls;
	}
	
	public String getOfapElctAddr() {
		return ofapElctAddr;
	}
	public void setOfapElctAddr(String ofapElctAddr) {
		this.ofapElctAddr = ofapElctAddr;
	}
	public String getSndnRfsl() {
		return sndnRfsl;
	}
	public void setSndnRfsl(String sndnRfsl) {
		this.sndnRfsl = sndnRfsl;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
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