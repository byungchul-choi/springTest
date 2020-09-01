package tpost.elctDoc.vo;

import tpost.egovcomm.vo.PagingDefaultVO;

public class elctDocVO extends PagingDefaultVO{
	//------------------------------------------------- 전자문서 송/수신 목록 input -------------------------------------------------
	
	
	private String inputOganCd;				/* 기관코드 */
	private String inputSndnCd;				/* 발송유형코드 */
	private String inputTmpltCd;			/* 템플릿코드 */
	private String inputStDt;				/* 시작날짜 */
	private String inputLastDt;				/* 종료날짜 */
	private String inputCi;				/* 연계정보(CI) */
	
	public String getInputOganCd() {
		return inputOganCd;
	}
	public void setInputOganCd(String inputOganCd) {
		this.inputOganCd = inputOganCd;
	}
	
	public String getInputSndnCd() {
		return inputSndnCd;
	}
	public void setInputSndnCd(String inputSndnCd) {
		this.inputSndnCd = inputSndnCd;
	}
	
	public String getInputTmpltCd() {
		return inputTmpltCd;
	}
	public void setInputTmpltCd(String inputTmpltCd) {
		this.inputTmpltCd = inputTmpltCd;
	}
	
	public String getInputStDt() {
		return inputStDt;
	}
	public void setInputStDt(String inputStDt) {
		this.inputStDt = inputStDt;
	}
	
	public String getInputLastDt() {
		return inputLastDt;
	}
	public void setInputLastDt(String inputLastDt) {
		this.inputLastDt = inputLastDt;
	}
	
	public String getInputCi() {
		return inputCi;
	}
	public void setInputCi(String inputCi) {
		this.inputCi = inputCi;
	}
	
	
	private String mOganCd;
	private String mTmpltCd;
	private String mSndnDate;
	
	public String getMOganCd() {
		return mOganCd;
	}
	public void setMOganCd(String mOganCd) {
		this.mOganCd = mOganCd;
	}
	
	public String getMTmpltCd() {
		return mTmpltCd;
	}
	public void setMTmpltCd(String mTmpltCd) {
		this.mTmpltCd = mTmpltCd;
	}
	
	public String getMSndnDate() {
		return mSndnDate;
	}
	public void setMSndnDate(String mSndnDate) {
		this.mSndnDate = mSndnDate;
	}
	
	
	//------------------------------------------------- 전자문서 송/수신 목록 -------------------------------------------------
	
	
	private String oganNm;				/* 발송기관 */
	private String oganCd;	
	private String sndnCd;				/* 발송유형 */
	private String tmpltCd;					/* 템플릿명 */
	private String tmpltNm;					/* 템플릿명 */
	private String allSndnNum;				/* 전체발송건수 */
	private String succNum;					/* 성공건수 */
	private String failNum;					/* 실패건수 */
	private String sndnDate;				/* 발송일자 */


	public String getOganNm() {
		return oganNm;
	}
	public void setOganNm(String oganNm) {
		this.oganNm = oganNm;
	}
	
	public String getOganCd() {
		return oganCd;
	}
	public void setOganCd(String oganCd) {
		this.oganCd = oganCd;
	}
	
	public String getSndnCd() {
		return sndnCd;
	}
	public void setSndnCd(String sndnCd) {
		this.sndnCd = sndnCd;
	}
	
	public String gettmpltCd() {
		return tmpltCd;
	}
	public void settmpltCd(String tmpltCd) {
		this.tmpltCd = tmpltCd;
	}
	
	public String getTmpltNm() {
		return tmpltNm;
	}
	public void setTmpltNm(String tmpltNm) {
		this.tmpltNm = tmpltNm;
	}
	
	public String getAllSndnNum() {
		return allSndnNum;
	}
	public void setAllSndnNum(String allSndnNum) {
		this.allSndnNum = allSndnNum;
	}
	
	public String getSuccNum() {
		return succNum;
	}
	public void setSuccNum(String succNum) {
		this.succNum = succNum;
	}
	
	public String getFailNum() {
		return failNum;
	}
	public void setFailNum(String failNum) {
		this.failNum = failNum;
	}
	
	public String getSndnDate() {
		return sndnDate;
	}
	public void setSndnDate(String sndnDate) {
		this.sndnDate = sndnDate;
	}		
	
	
	//------------------------------------------------- 전자문서 송/수신  input -------------------------------------------------
	
	
	private String pOganCd;				/* 기관코드 */
	private String pSndnCd;				/* 발송유형코드 */
	private String pTmpltCd;			/* 템플릿코드 */
	private String pStDt;				/* 시작날짜 */
	private String pLastDt;				/* 종료날짜 */
	private String pCi;				/* 연계정보(CI) */
	private String pPgIndex;				/* 연계정보(CI) */
	
	public String getPOganCd() {
		return pOganCd;
	}
	public void setPOganCd(String pOganCd) {
		this.pOganCd = pOganCd;
	}
	
	public String getPSndnCd() {
		return pSndnCd;
	}
	public void setPSndnCd(String pSndnCd) {
		this.pSndnCd = pSndnCd;
	}
	
	public String getPTmpltCd() {
		return pTmpltCd;
	}
	public void setPTmpltCd(String pTmpltCd) {
		this.pTmpltCd = pTmpltCd;
	}
	
	public String getPStDt() {
		return pStDt;
	}
	public void setPStDt(String pStDt) {
		this.pStDt = pStDt;
	}
	
	public String getPLastDt() {
		return pLastDt;
	}
	public void setPLastDt(String pLastDt) {
		this.pLastDt = pLastDt;
	}
	
	public String getPCi() {
		return pCi;
	}
	public void setPCi(String pCi) {
		this.pCi = pCi;
	}
	
	public String getPPgIndex() {
		return pPgIndex;
	}
	public void setPPgIndex(String pPgIndex) {
		this.pPgIndex = pPgIndex;
	}
	
	
	//------------------------------------------------- 전자문서 송/수신 디테일 input -------------------------------------------------
	

	private String inputSendYn;
	private String inputCiNum;
	private String inputName;
	
	public String getInputSendYn() {
		return inputSendYn;
	}
	public void setInputSendYn(String inputSendYn) {
		this.inputSendYn = inputSendYn;
	}
	
	public String getInputCiNum() {
		return inputCiNum;
	}
	public void setInputCiNum(String inputCiNum) {
		this.inputCiNum = inputCiNum;
	}
	
	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	
	
	//------------------------------------------------- 전자문서 송/수신  디테일 -------------------------------------------------
	
	
	private String ciNum;
	private String ofapElctAddr;
	private String name;
	private String trnsDate;
	private String rcveDate;
	private String rdngDate;
	private String acmdInfoCrtDate;
	private String sendUrl;
	private String sendYn;
	private String sendFailCd;
	private String acmdInfoCrtYn;
	private String acmdInfoCrtFailCd;
	private String hexKey;
	
	
	public String getCiNum() {
		return ciNum;
	}
	public void setCiNum(String ciNum) {
		this.ciNum = ciNum;
	}
	
	public String getOfapElctAddr() {
		return ofapElctAddr;
	}
	public void setOfapElctAddr(String ofapElctAddr) {
		this.ofapElctAddr = ofapElctAddr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTrnsDate() {
		return trnsDate;
	}
	public void setTrnsDate(String trnsDate) {
		this.trnsDate = trnsDate;
	}
	
	public String getRcveDate() {
		return rcveDate;
	}
	public void setRcveDate(String rcveDate) {
		this.rcveDate = rcveDate;
	}
	
	public String getRdngDate() {
		return rdngDate;
	}
	public void setRdngDate(String rdngDate) {
		this.rdngDate = rdngDate;
	}
	
	public String getAcmdInfoCrtDate() {
		return acmdInfoCrtDate;
	}
	public void setAcmdInfoCrtDate(String acmdInfoCrtDate) {
		this.acmdInfoCrtDate = acmdInfoCrtDate;
	}
	
	public String getSendUrl() {
		return sendUrl;
	}
	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}
	
	public String getSendYn() {
		return sendYn;
	}
	public void setSendYn(String sendYn) {
		this.sendYn = sendYn;
	}
	
	public String getSendFailCd() {
		return sendFailCd;
	}
	public void setSendFailCd(String sendFailCd) {
		this.sendFailCd = sendFailCd;
	}
	
	public String getAcmdInfoCrtYn() {
		return acmdInfoCrtYn;
	}
	public void setAcmdInfoCrtYn(String acmdInfoCrtYn) {
		this.acmdInfoCrtYn = acmdInfoCrtYn;
	}
	
	public String getAcmdInfoCrtFailCd() {
		return acmdInfoCrtFailCd;
	}
	public void setAcmdInfoCrtFailCd(String acmdInfoCrtFailCd) {
		this.acmdInfoCrtFailCd = acmdInfoCrtFailCd;
	}
	
	public String getHexKey() {
		return hexKey;
	}
	public void setHexKey(String hexKey) {
		this.hexKey = hexKey;
	}
	
	
	//------------------------------------------------- 전자문서 송신실패 내역 -------------------------------------------------
	
	
	private String popuSendFailCd;
	private String popuSendFailDesc;
	private String popuSendFailCount;
	
	public String getPopuSendFailCd() {
		return popuSendFailCd;
	}
	public void setPopuSendFailCd(String popuSendFailCd) {
		this.popuSendFailCd = popuSendFailCd;
	}
	
	public String getPopuSendFailDesc() {
		return popuSendFailDesc;
	}
	public void setPopuSendFailDesc(String popuSendFailDesc) {
		this.popuSendFailDesc = popuSendFailDesc;
	}
	
	public String getPopuSendFailCount() {
		return popuSendFailCount;
	}
	public void setPopuSendFailCount(String popuSendFailCount) {
		this.popuSendFailCount = popuSendFailCount;
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