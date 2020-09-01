package tpost.elctDoc.vo;

import tpost.egovcomm.vo.PagingDefaultVO;

public class elctDocSndnVO extends PagingDefaultVO{
	
	//------------------------------------------------- 전자문서 발송 Input -------------------------------------------------
	
	
	private String iSndnStDt;
	private String iSndnClosDt;
	private String iOganCd;
	private String iTmpltCd;
	private String iSndnCd;
	private String iRcveDt;
	private String iMsgTitle;
	
	public String getISndnStDt() {
		return iSndnStDt;
	}
	public void setISndnStDt(String iSndnStDt) {
		this.iSndnStDt = iSndnStDt;
	}
	
	public String getISndnClosDt() {
		return iSndnClosDt;
	}
	public void setISndnClosDt(String iSndnClosDt) {
		this.iSndnClosDt = iSndnClosDt;
	}
	
	public String getIOganCd() {
		return iOganCd;
	}
	public void setIOganCd(String iOganCd) {
		this.iOganCd = iOganCd;
	}
	
	public String getITmpltCd() {
		return iTmpltCd;
	}
	public void setITmpltCd(String iTmpltCd) {
		this.iTmpltCd = iTmpltCd;
	}
	
	public String getISndnCd() {
		return iSndnCd;
	}
	public void setISndnCd(String iSndnCd) {
		this.iSndnCd = iSndnCd;
	}
	
	public String getIRcveDt() {
		return iRcveDt;
	}
	public void setIRcveDt(String iRcveDt) {
		this.iRcveDt = iRcveDt;
	}
	
	public String getIMsgTitle() {
		return iMsgTitle;
	}
	public void setIMsgTitle(String iMsgTitle) {
		this.iMsgTitle = iMsgTitle;
	}
	
	
	
	//------------------------------------------------- 전자문서 발송 -------------------------------------------------
	
	
	private String oganCd;
	private String oganNm;
	private String tmpltCd;
	private String tmpltNm;
	private String transDt;
	private String sndnD;
	private String sndnH;
	private String sndnM;
	private String sndnDtm;
	private String sndnCd;
	private String multiMblPrcGbn;
	private String msgSndnGbn;
	private String msgTitle;
	private String msgText;
	private String msgSize;
	private String srcCallNo;
	private String urlYn;
	private String aprYn;
	private String mmsImg;
	private String imgSize;
	private String saveFn;
	private String allStts;
	
	
	
	public String getImgSize() {
		return imgSize;
	}
	public void setImgSize(String imgSize) {
		this.imgSize = imgSize;
	}
	public String getMmsImg() {
		return mmsImg;
	}
	public void setMmsImg(String mmsImg) {
		this.mmsImg = mmsImg;
	}
	public String getOganCd(){
		return oganCd;
	}
	public void setOganCd(String oganCd) {
		this.oganCd = oganCd;
	}
	
	public String getOganNm(){
		return oganNm;
	}
	public void setOganNm(String oganNm) {
		this.oganNm = oganNm;
	}
	
	public String getTmpltCd(){
		return tmpltCd;
	}
	public void setTmpltCd(String tmpltCd) {
		this.tmpltCd = tmpltCd;
	}
	
	public String getTmpltNm(){
		return tmpltNm;
	}
	public void setTmpltNm(String tmpltNm) {
		this.tmpltNm = tmpltNm;
	}
	
	public String getTransDt(){
		return transDt;
	}
	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}
	
	public String getSndnDtm(){
		return sndnDtm;
	}
	public void setSndnDtm(String sndnDtm) {
		this.sndnDtm = sndnDtm;
	}
	
	public String getSndnD(){
		return sndnD;
	}
	public void setSndnD(String sndnD) {
		this.sndnD = sndnD;
	}
	
	public String getSndnH(){
		return sndnH;
	}
	public void setSndnH(String sndnH) {
		this.sndnH = sndnH;
	}
	
	public String getSndnM(){
		return sndnM;
	}
	public void setSndnM(String sndnM) {
		this.sndnM = sndnM;
	}
	
	public String getSndnCd(){
		return sndnCd;
	}
	public void setSndnCd(String sndnCd) {
		this.sndnCd = sndnCd;
	}
	
	public String getMultiMblPrcGbn(){
		return multiMblPrcGbn;
	}
	public void setMultiMblPrcGbn(String multiMblPrcGbn) {
		this.multiMblPrcGbn = multiMblPrcGbn;
	}
	
	public String getMsgSndnGbn(){
		return msgSndnGbn;
	}
	public void setMsgSndnGbn(String msgSndnGbn) {
		this.msgSndnGbn = msgSndnGbn;
	}
	
	public String getMsgTitle(){
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	
	public String getMsgText(){
		return msgText;
	}
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	
	public String getMsgSize(){
		return msgSize;
	}
	public void setMsgSize(String msgSize) {
		this.msgSize = msgSize;
	}
	
	public String getSrcCallNo(){
		return srcCallNo;
	}
	public void setSrcCallNo(String srcCallNo) {
		this.srcCallNo = srcCallNo;
	}

	public String getUrlYn(){
		return urlYn;
	}
	public void setUrlYn(String urlYn) {
		this.urlYn = urlYn;
	}
	
	public String getAprYn(){
		return aprYn;
	}
	public void setAprYn(String aprYn) {
		this.aprYn = aprYn;
	}
	
	public String getSaveFn(){
		return saveFn;
	}
	public void setSaveFn(String saveFn) {
		this.saveFn = saveFn;
	}
	
	public String getAllStts(){
		return allStts;
	}
	public void setAllStts(String allStts) {
		this.allStts = allStts;
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