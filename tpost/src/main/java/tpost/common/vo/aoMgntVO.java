package tpost.common.vo;

import tpost.egovcomm.vo.PagingDefaultVO;

@SuppressWarnings("serial")
public class aoMgntVO extends PagingDefaultVO {   /*페이징 vo 확장시켜서 사용*/

	/*조회조건 */
	private String aoId ="";
	private String aoNm ="";
	private String pathNm ="";
	private String aoParam ="";
	private String aoDesc ="";
	private String useYn ="";
	
	private String aoIdSel ="";
	private String aoNmSel ="";
	
	private String crtDate    =""    ;
	private String crtr       =""    ;
	private String mdfDate    =""   ;
	private String amdr       =""     ;
	
	private String aoIdDel       =""     ;
	
	private String dispNo ="";
	/*권한그룹 고객 ID*/
	private String athGrpIdLink       =""     ;
	
	/*삭제체크한것만 삭제*/
	private String inputChk       =""     ;
	
	private String grpNm       =""     ;
	
	public aoMgntVO() {
	  }

	  public aoMgntVO(
			  			  String aoId
			  			, String aoNm
			            , String pathNm
			            , String aoParam
			            , String aoDesc
			            , String useYn
			            , String aoIdSel
			            , String aoNmSel
			            ) {
		  this.aoId	= aoId		;
		  this.aoNm	= aoNm		;
		  this.pathNm    = pathNm        ;
		  this.aoParam         = aoParam        ;
		  this.aoDesc      = aoDesc     ;
		  this.useYn      = useYn     ;
		  this.aoIdSel	= aoIdSel		;
		  this.aoNmSel	= aoNmSel		;
	  }



	@Override
	public String toString() {
	  return "aoMgntVO{" +
			  "aoId		='"+aoId		+'\''+
			  ",aoNm		='"+aoNm		+'\''+
			  ", pathNm     = '"+pathNm     + '\''+
			", aoParam  = '"+aoParam  + '\''+
			", aoDesc  = '"+aoDesc  + '\''+
			", useYn     = '"+useYn     + '\''+
			"aoIdSel		='"+aoIdSel		+'\''+
			  ",aoNmSel		='"+aoNmSel		+'\''+
	      '}';
	}

	
	

	public String getDispNo() {
		return dispNo;
	}

	public void setDispNo(String dispNo) {
		this.dispNo = dispNo;
	}

	public String getGrpNm() {
		return grpNm;
	}

	public void setGrpNm(String grpNm) {
		this.grpNm = grpNm;
	}

	public String getAoIdDel() {
		return aoIdDel;
	}

	public void setAoIdDel(String aoIdDel) {
		this.aoIdDel = aoIdDel;
	}

	public String getInputChk() {
		return inputChk;
	}

	public void setInputChk(String inputChk) {
		this.inputChk = inputChk;
	}

	public String getAthGrpIdLink() {
		return athGrpIdLink;
	}

	public void setAthGrpIdLink(String athGrpIdLink) {
		this.athGrpIdLink = athGrpIdLink;
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

	public String getAoIdSel() {
		return aoIdSel;
	}

	public void setAoIdSel(String aoIdSel) {
		this.aoIdSel = aoIdSel;
	}

	public String getAoNmSel() {
		return aoNmSel;
	}

	public void setAoNmSel(String aoNmSel) {
		this.aoNmSel = aoNmSel;
	}

	public String getAoId() {
		return aoId;
	}

	public void setAoId(String aoId) {
		this.aoId = aoId;
	}

	public String getAoNm() {
		return aoNm;
	}

	public void setAoNm(String aoNm) {
		this.aoNm = aoNm;
	}

	public String getPathNm() {
		return pathNm;
	}

	public void setPathNm(String pathNm) {
		this.pathNm = pathNm;
	}

	public String getAoParam() {
		return aoParam;
	}

	public void setAoParam(String aoParam) {
		this.aoParam = aoParam;
	}

	public String getAoDesc() {
		return aoDesc;
	}

	public void setAoDesc(String aoDesc) {
		this.aoDesc = aoDesc;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	
	
	
}
