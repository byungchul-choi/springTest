package tpost.common.vo;

import tpost.egovcomm.vo.PagingDefaultVO;

@SuppressWarnings("serial")
public class athGrpMgntVO  extends PagingDefaultVO {   /*페이징 vo 확장시켜서 사용*/

	/*출력내용*/
	private String athGrpId       ="";
	private String athGrpNm       ="";
	private String athGrpDesc     ="";
	
	
	
	private String crtDate         ="";
	private String crtr             ="";
	private String mdfDate         ="";
	private String amdr             ="";
	
	/*조회조건 */
	private String athGrpIdSel       ="";
	private String athGrpNmSel       ="";
	private String gubunSelcd         ="";
	private String gubunSelNm         ="";
	
	/*팝업호출 시사용하는 권한 ID*/
	private String athGrpIdLink         ="";
	
	/*체크박스 체크 확인*/
	private String inputChk         ="";
	private String chk_info         ="";
	
	
	public athGrpMgntVO() {
	  }

	  public athGrpMgntVO(
			  			  String athGrpId
			  			, String athGrpNm
			            , String athGrpDesc
			            , String crtDate
			            , String crtr
			            , String mdfDate
			            , String amdr
			            ) {
		  this.athGrpId	   = athGrpId		;
		  this.athGrpNm	   = athGrpNm		;
		  this.athGrpDesc  = athGrpDesc        ;
		  this.crtDate     = crtDate        ;
		  this.crtr        = crtr     ;
		  this.mdfDate     = mdfDate     ;
		  this.amdr	       = amdr		;
	  }



	

	public String getChk_info() {
		return chk_info;
	}

	public void setChk_info(String chk_info) {
		this.chk_info = chk_info;
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

	public String getAthGrpIdSel() {
		return athGrpIdSel;
	}

	public void setAthGrpIdSel(String athGrpIdSel) {
		this.athGrpIdSel = athGrpIdSel;
	}

	public String getAthGrpNmSel() {
		return athGrpNmSel;
	}

	public void setAthGrpNmSel(String athGrpNmSel) {
		this.athGrpNmSel = athGrpNmSel;
	}

	public String getGubunSelcd() {
		return gubunSelcd;
	}

	public void setGubunSelcd(String gubunSelcd) {
		this.gubunSelcd = gubunSelcd;
	}

	public String getGubunSelNm() {
		return gubunSelNm;
	}

	public void setGubunSelNm(String gubunSelNm) {
		this.gubunSelNm = gubunSelNm;
	}

	@Override
	public String toString() {
	  return "athGrpMgntVO{" +
			  "athGrpId		='"+athGrpId		+'\''+
			  ",athGrpNm		='"+athGrpNm		+'\''+
			  ", athGrpDesc     = '"+athGrpDesc     + '\''+
			
	      '}';
	}
	
		
	public String getAthGrpId() {
		return athGrpId;
	}

	public void setAthGrpId(String athGrpId) {
		this.athGrpId = athGrpId;
	}

	public String getAthGrpNm() {
		return athGrpNm;
	}

	public void setAthGrpNm(String athGrpNm) {
		this.athGrpNm = athGrpNm;
	}

	public String getAthGrpDesc() {
		return athGrpDesc;
	}

	public void setAthGrpDesc(String athGrpDesc) {
		this.athGrpDesc = athGrpDesc;
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
