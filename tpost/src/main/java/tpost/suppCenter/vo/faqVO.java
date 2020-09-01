package tpost.suppCenter.vo;

import tpost.egovcomm.vo.PagingDefaultVO;

public class faqVO extends PagingDefaultVO{ 
	
	private String inputFaqCd;
	private String inputWrtgTtl;
	private String inputWrtgStDt;
	private String inputWrtgClosDt;
	
	public String getInputFaqCd() {
		return inputFaqCd;
	}
	public void setInputFaqCd(String inputFaqCd) {
		this.inputFaqCd = inputFaqCd;
	}
	
	public String getInputWrtgTtl() {
		return inputWrtgTtl;
	}
	public void setInputWrtgTtl(String inputWrtgTtl) {
		this.inputWrtgTtl = inputWrtgTtl;
	}
	
	public String getInputWrtgStDt() {
		return inputWrtgStDt;
	}
	public void setInputWrtgStDt(String inputWrtgStDt) {
		this.inputWrtgStDt = inputWrtgStDt;
	}
	
	public String getInputWrtgClosDt() {
		return inputWrtgClosDt;
	}
	public void setInputWrtgClosDt(String inputWrtgClosDt) {
		this.inputWrtgClosDt = inputWrtgClosDt;
	}
	
	
	private String wrtgNum;
	private String faqCd;
	private String faqNm;
	private String wrtgTtl;
	private String wrtgCnts;
	private String wrtDt;

	
	public String getWrtgNum() {
		return wrtgNum;
	}
	public void setWrtgNum(String wrtgNum) {
		this.wrtgNum = wrtgNum;
	}
	
	public String getFaqCd() {
		return faqCd;
	}
	public void setFaqCd(String faqCd) {
		this.faqCd = faqCd;
	}
	
	public String getFaqNm() {
		return faqNm;
	}
	public void setFaqNm(String faqNm) {
		this.faqNm = faqNm;
	}
	
	public String getWrtgTtl() {
		return wrtgTtl;
	}
	public void setWrtgTtl(String wrtgTtl) {
		this.wrtgTtl = wrtgTtl;
	}
	
	public String getWrtgCnts() {
		return wrtgCnts;
	}
	public void setWrtgCnts(String wrtgCnts) {
		this.wrtgCnts = wrtgCnts;
	}
	
	public String getWrtDt() {
		return wrtDt;
	}
	public void setWrtDt(String wrtDt) {
		this.wrtDt = wrtDt;
	}
	
	
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
