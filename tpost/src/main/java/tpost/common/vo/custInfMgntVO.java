package tpost.common.vo;

public class custInfMgntVO  {   /*페이징 vo 확장시켜서 사용*/

	/*조회조건 */
	private String custId    = "";
	private String oganCd    = "";
	private String custNum   = "";
	private String oganNm    = "";
	private String custNm    = "";
	private String deptNm    = "";
	private String useYn     = "";
	private String telNum    = "";
	private String celpNum   = "";
	private String emaiId    = "";
	private String crtDate   = "";
	private String crtr       = "";
	private String mdfDate   = "";
	private String amdr       = "";
	private String athLev = "";
	private String athLevNm = "";
	
	private String oganCdSel    = "";
	private String custNmSel    = "";
	
	/*권한관리 그룹 ID*/
	private String athGrpIdLink = "";
	
	
	/*체크박스 체크 확인*/
	private String chk_info         ="";
	private String inputChk         ="";
	
	/* 암호화키 확인*/
	private String aesKey = "";
	
	
	public custInfMgntVO() {
	  }

	  public custInfMgntVO(
			  			  String custId
			  			, String oganCd
			            , String custNum
			            , String oganNm
			            , String custNm
			            , String deptNm
			            , String useYn
			            , String telNum
			            , String celpNum
			            , String emaiId
			            
			            ) {
		  this.custId	= custId		;
		  this.oganCd	= oganCd		;
		  this.custNum  = custNum       ;
		  this.oganNm   = oganNm        ;
		  this.custNm   = custNm        ;
		  this.useYn    = useYn         ;
		  this.deptNm	= deptNm		;
		  this.telNum	= telNum		;
		  this.celpNum	= celpNum		;
		  this.emaiId	= emaiId		;
	  }



	@Override
	public String toString() {
	  return "custInfMgntVO{" +
			  "  custId		= '"+custId		+ '\''+
			  ", oganCd		= '"+oganCd		+ '\''+
			  ", custNum    = '"+custNum    + '\''+
			  ", oganNm     = '"+oganNm     + '\''+
			  ", custNm     = '"+custNm     + '\''+
			  ", useYn      = '"+useYn      + '\''+
			  ", telNum		= '"+telNum		+ '\''+
			  ",celpNum		= '"+celpNum	+ '\''+
			  ",emaiId		= '"+emaiId		+ '\''+
	      '}';
	}


	public String getAesKey() {
		return aesKey;
	}

	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}

	public String getAthLevNm() {
		return athLevNm;
	}

	public void setAthLevNm(String athLevNm) {
		this.athLevNm = athLevNm;
	}

	public String getInputChk() {
		return inputChk;
	}

	public void setInputChk(String inputChk) {
		this.inputChk = inputChk;
	}

	public String getAthLev() {
		return athLev;
	}

	public void setAthLev(String athLev) {
		this.athLev = athLev;
	}

	public String getChk_info() {
		return chk_info;
	}

	public void setChk_info(String chk_info) {
		this.chk_info = chk_info;
	}

	public String getAthGrpIdLink() {
		return athGrpIdLink;
	}

	public void setAthGrpIdLink(String athGrpIdLink) {
		this.athGrpIdLink = athGrpIdLink;
	}

	public String getOganCdSel() {
		return oganCdSel;
	}

	public void setOganCdSel(String oganCdSel) {
		this.oganCdSel = oganCdSel;
	}

	public String getCustNmSel() {
		return custNmSel;
	}

	public void setCustNmSel(String custNmSel) {
		this.custNmSel = custNmSel;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getOganCd() {
		return oganCd;
	}

	public void setOganCd(String oganCd) {
		this.oganCd = oganCd;
	}

	public String getCustNum() {
		return custNum;
	}

	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}

	public String getOganNm() {
		return oganNm;
	}

	public void setOganNm(String oganNm) {
		this.oganNm = oganNm;
	}

	public String getCustNm() {
		return custNm;
	}

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getCelpNum() {
		return celpNum;
	}

	public void setCelpNum(String celpNum) {
		this.celpNum = celpNum;
	}

	public String getEmaiId() {
		return emaiId;
	}

	public void setEmaiId(String emaiId) {
		this.emaiId = emaiId;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtr() {
		return crtr;
	}

	public void setCrtr(String crtr) {
		this.crtr = crtr;
	}

	public String getMdfDate() {
		return mdfDate;
	}

	public void setMdfDate(String mdfDate) {
		this.mdfDate = mdfDate;
	}

	public String getAmdr() {
		return amdr;
	}

	public void setAmdr(String amdr) {
		this.amdr = amdr;
	}

	
	
	
}
