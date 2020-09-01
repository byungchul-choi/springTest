package tpost.elctDoc.vo;

import tpost.egovcomm.vo.PagingDefaultVO;

public class msgTmpltVO extends PagingDefaultVO{
	
	private String inputOganCd;				/* 기관(회사)명 인풋값 */
	private String inputTmpltNm;			/* 템플릿 이름 인풋값 */
	private String inputTmpltUseYn;			/* 사용여부  인풋값 */
	
	public String getInputOganCd() {
		return inputOganCd;
	}
	public void setInputOganCd(String inputOganCd) {
		this.inputOganCd = inputOganCd;
	}
	
	public String getInputTmpltNm() {
		return inputTmpltNm;
	}
	public void setInputTmpltNm(String inputTmpltNm) {
		this.inputTmpltNm = inputTmpltNm;
	}
	
	public String getInputTmpltUseYn() {
		return inputTmpltUseYn;
	}
	public void setInputTmpltUseYn(String inputTmpltUseYn) {
		this.inputTmpltUseYn = inputTmpltUseYn;
	}
	
	
	private String oganCd;
	private String oganNm;
	private String tmpltCd;
	private String tmpltNm;
	private String msgTmpltYn;
	private String tmpltUseYn;
	
	
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
	
	public String getTmpltCd() {
		return tmpltCd;
	}
	public void setTmpltCd(String tmpltCd) {
		this.tmpltCd = tmpltCd;
	}
	
	public String getTmpltNm() {
		return tmpltNm;
	}
	public void setTmpltNm(String tmpltNm) {
		this.tmpltNm = tmpltNm;
	}
	
	public String getMsgTmpltYn() {
		return msgTmpltYn;
	}
	public void setMsgTmpltYn(String msgTmpltYn) {
		this.msgTmpltYn = msgTmpltYn;
	}
	
	public String getTmpltUseYn() {
		return tmpltUseYn;
	}
	public void setTmpltUseYn(String tmpltUseYn) {
		this.tmpltUseYn = tmpltUseYn;
	}
	
	
	
	private String msgTitle;
	private String msgText;
	private String msgImg;
	private String msgImgSize;
	private String saveFn;
	
	public String getMsgTitle() {
		return msgTitle;
	}
	
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	
	public String getMsgText() {
		return msgText;
	}
	
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	
	public String getMsgImg() {
		return msgImg;
	}
	
	public void setMsgImg(String msgImg) {
		this.msgImg = msgImg;
	}
	
	public String getMsgImgSize() {
		return msgImgSize;
	}
	
	public void setMsgImgSize(String msgImgSize) {
		this.msgImgSize = msgImgSize;
	}
	
	public String getSaveFn() {
		return saveFn;
	}
	
	public void setSaveFn(String saveFn) {
		this.saveFn = saveFn;
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