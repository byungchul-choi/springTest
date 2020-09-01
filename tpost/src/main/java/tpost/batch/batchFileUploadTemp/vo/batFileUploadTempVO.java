package tpost.batch.batchFileUploadTemp.vo;


/**
 * @author smart
 *
 */
public class batFileUploadTempVO {   /*페이징 vo 확장시켜서 사용*/

	
	String exeDt = "";
	String oganCd = "";
	String tmpltCd  = "";
	String fileCnts = "";  /*파일내용*/
	String succYn = "";  /*성공여부*/
	String failCnts = "";  /*실패내용*/
	
	String fileNm = "";  
	
	String sndnDate = ""; 
	
	String name = ""; 
	String ciNum = ""; 
	String anocInfo = "";   /*고지정보*/
	String ssnoFronBrdt = "";   /*생년월일*/
	String sexClcd = "";   /*성별*/
	
	String crtr = "";
	
	String itemSno = ""; /*순번*/
	String itemNm  = ""; /*이름*/
	int    itemLen = 0; /*길이*/
	
	
	String inputBsrpCls = "";
	String ye="";
	
	String inputPk = "";
	String inputOfapElctAddr = "";
	String bsrpCls = "";
	String regnYn = "" ;
	String ofapElctAddrPk = "";
	
	/*카카오 */
	String tx_id = "";
	String token = "";
	
	
	public batFileUploadTempVO() {
	  }

	  public batFileUploadTempVO(
			  			  String exeDt
			  			, String oganCd
			            , String tmpltCd
			            , String fileCnts
			            
			            ) {
		  this.exeDt	= exeDt		;
		  this.oganCd	= oganCd		;
		  this.tmpltCd    = tmpltCd        ;
		  this.fileCnts         = fileCnts        ;
		
	  }



	@Override
	public String toString() {
	  return "fileTestVO{" +
			  "exeDt		='"+exeDt		+'\''+
			  ",oganCd		='"+oganCd		+'\''+
			  ", tmpltCd     = '"+tmpltCd     + '\''+
			", fileCnts  = '"+fileCnts  + '\''+
			
	      '}';
	}

	
	
	public String getTx_id() {
		return tx_id;
	}

	public void setTx_id(String tx_id) {
		this.tx_id = tx_id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getBsrpCls() {
		return bsrpCls;
	}

	public void setBsrpCls(String bsrpCls) {
		this.bsrpCls = bsrpCls;
	}

	public String getRegnYn() {
		return regnYn;
	}

	public void setRegnYn(String regnYn) {
		this.regnYn = regnYn;
	}

	public String getOfapElctAddrPk() {
		return ofapElctAddrPk;
	}

	public void setOfapElctAddrPk(String ofapElctAddrPk) {
		this.ofapElctAddrPk = ofapElctAddrPk;
	}

	public String getInputPk() {
		return inputPk;
	}

	public void setInputPk(String inputPk) {
		this.inputPk = inputPk;
	}

	public String getInputOfapElctAddr() {
		return inputOfapElctAddr;
	}

	public void setInputOfapElctAddr(String inputOfapElctAddr) {
		this.inputOfapElctAddr = inputOfapElctAddr;
	}

	public String getInputBsrpCls() {
		return inputBsrpCls;
	}

	public void setInputBsrpCls(String inputBsrpCls) {
		this.inputBsrpCls = inputBsrpCls;
	}

	public String getYe() {
		return ye;
	}

	public void setYe(String ye) {
		this.ye = ye;
	}

	public String getSexClcd() {
		return sexClcd;
	}

	public void setSexClcd(String sexClcd) {
		this.sexClcd = sexClcd;
	}

	public String getSsnoFronBrdt() {
		return ssnoFronBrdt;
	}

	public void setSsnoFronBrdt(String ssnoFronBrdt) {
		this.ssnoFronBrdt = ssnoFronBrdt;
	}

	public String getFailCnts() {
		return failCnts;
	}

	public void setFailCnts(String failCnts) {
		this.failCnts = failCnts;
	}

	public String getSuccYn() {
		return succYn;
	}

	public void setSuccYn(String succYn) {
		this.succYn = succYn;
	}

	public String getSndnDate() {
		return sndnDate;
	}

	public void setSndnDate(String sndnDate) {
		this.sndnDate = sndnDate;
	}

	public String getItemSno() {
		return itemSno;
	}

	public void setItemSno(String itemSno) {
		this.itemSno = itemSno;
	}

	public String getItemNm() {
		return itemNm;
	}

	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}

	public int getItemLen() {
		return itemLen;
	}

	public void setItemLen(int itemLen) {
		this.itemLen = itemLen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCiNum() {
		return ciNum;
	}

	public void setCiNum(String ciNum) {
		this.ciNum = ciNum;
	}

	public String getAnocInfo() {
		return anocInfo;
	}

	public void setAnocInfo(String anocInfo) {
		this.anocInfo = anocInfo;
	}

	public String getExeDt() {
		return exeDt;
	}

	public void setExeDt(String exeDt) {
		this.exeDt = exeDt;
	}

	public String getOganCd() {
		return oganCd;
	}

	public void setOganCd(String oganCd) {
		this.oganCd = oganCd;
	}

	public String getTmpltCd() {
		return tmpltCd;
	}

	public void setTmpltCd(String tmpltCd) {
		this.tmpltCd = tmpltCd;
	}

	public String getFileCnts() {
		return fileCnts;
	}

	public void setFileCnts(String fileCnts) {
		this.fileCnts = fileCnts;
	}

	public String getCrtr() {
		return crtr;
	}

	public void setCrtr(String crtr) {
		this.crtr = crtr;
	}

	
	

	
	
	
}
