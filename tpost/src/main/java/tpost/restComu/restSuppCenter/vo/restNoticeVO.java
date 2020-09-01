package tpost.restComu.restSuppCenter.vo;

import tpost.egovcomm.vo.PagingDefaultVO;

public class restNoticeVO  extends PagingDefaultVO { 
	
	private String inputOganCd;
	private String inputWrtgTtl;
	private String inputWrtgStDt;
	private String inputWrtgClosDt;
	
	public String getInputOganCd() {
		return inputOganCd;
	}
	public void setInputOganCd(String inputOganCd) {
		this.inputOganCd = inputOganCd;
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
	
	
	private String seq;
	private String wrtgNum;
	private String oganCd;
	private String oganNm;
	private String wrtgTtl;
	private String wrtgCnts;
	private String wrtDt;

	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}	
	
	public String getWrtgNum() {
		return wrtgNum;
	}
	public void setWrtgNum(String wrtgNum) {
		this.wrtgNum = wrtgNum;
	}
	
	public String getOganCd() {
		return oganCd;
	}
	public void setOganCd(String oganCd) {
		this.oganCd = oganCd;
	}
	
	public String getOganNm() {
		return oganNm;
	}
	public void setOganNm(String oganNm) {
		this.oganNm = oganNm;
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
	
	
	private String pOganCd;
	private String pStDt;
	private String pClosDt;
	private String pWrtgTtl;
	
	
	public String getPStDt() {
		return pStDt;
	}
	public void setPStDt(String pStDt) {
		this.pStDt = pStDt;
	}
	
	public String getPClosDt() {
		return pClosDt;
	}
	public void setPClosDt(String pClosDt) {
		this.pClosDt = pClosDt;
	}
	
	public String getPOganCd() {
		return pOganCd;
	}
	public void setPOganCd(String pOganCd) {
		this.pOganCd = pOganCd;
	}
	
	public String getPWrtgTtl() {
		return pWrtgTtl;
	}
	public void setPWrtgTtl(String pWrtgTtl) {
		this.pWrtgTtl = pWrtgTtl;
	}
}
