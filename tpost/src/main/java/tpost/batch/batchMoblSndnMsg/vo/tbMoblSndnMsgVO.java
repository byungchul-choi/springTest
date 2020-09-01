package tpost.batch.batchMoblSndnMsg.vo;


public class tbMoblSndnMsgVO {   /*페이징 vo 확장시켜서 사용*/

	
	 // 자료구분코드 0x00000003: SMS/LMS/MMS발송대상 송신
    private String dataGbnCd;

    // 기관코드 수요기관 관리 구분 코드
    private String svcOrgCd;

    // 요청일자 요청일자(YYYYMMDD)
    private String transDt;

    // 일련번호 일련번호
    private String seqNo;

    // 맵핑요청그룹코드 맵핑요청그룹코드
    private String svcGrpCd;

    // 아이핀CI값 휴대폰번호 매핑을 위한 IPIN_CI
    private String ipinCi;

    // 기관명 수요기관 관리 구분 이름
    private String svcOrgNm;

    // 메시지타입 0:SMS, 1:LMS, 2:MMS
    private String msgType;

    // URL포함여부 0: URL 미포함, 1:URL 포함
    private String msgInfo;

    // 메시지사이즈 발송 메시지 사이즈
    private Integer msgSize;

    // 메시지내용 MMS 발송 메시지
    private String msgText;

    // MMS첨부이미지사이즈 MMS 첨부 이미지 사이즈
    private Long mmsImgSize;

    // 이미지데이터 이미지데이터
    private String mmsImg;

    // 회신번호 수요기관의 대표번호(MSISDN규격상 12자리)
    private String srcCallNo;

    // 문서명 수요기관 문서 이름
    private String cnForm;

    // 메시지제목 메시지 제목
    private String msgTitle;

    // 메시지발송구분 메시지발송구분
    private String balsongGbn;

    // 다회선사용자발송구분 다회선사용자발송구분
    private String multiMblPrcGbn;

    // 수신동의리스트사이즈 수신동의리스트사이즈
    private Integer cellNoListSize;

    // 수신동의리스트 수신동의리스트
    private String cellNoList;

    // 발송일시 (예약)발송일시
    private String sndnDtm;

    // 발송유효일시 발송유효일시
    private String sndnVldDtm;

    // 발송처리구분 0:발송미처리,1:발송처리
    private String sndnProcCl;

    // 총건수 휴대폰매핑요청총건수
    private Integer totalCnt;

    // 템플릿코드 템플릿코드
    private String tmpltCd;

    // 고지안내URL 고지안내URL( 부장님이 만든 난수)
    private String anocInfoUrl;

    // 수신거부URL 수신거부URL( 부장님이 만든 난수)
    private String rcveRfslUrl;

    // 공인전자주소 공인전자주소
    private String ofapElctAddr;


    private String name;
	
	public tbMoblSndnMsgVO() {
	  }

	  public tbMoblSndnMsgVO(
			  String transDt            
			  , String svcOrgCd         
			  , String seqNo            
			  , String dataGbnCd        
			  , String ipinCi           
			  , String svcOrgNm         
			  , String msgType          
			  , String msgInfo          
			  , int msgSize          
			  , String msgText          
			  , long mmsImgSize       
			  , String mmsImg           
			  , String srcCallNo        
			  , String cnForm           
			  , String msgTitle         
			  , String balsongGbn       
			  , String multiMblPrcGbn   
			  , int cellNoListSize   
			  , String cellNoList       
			  , String sndnDtm          
			  , String sndnVldDtm       
			  , String sndnProcCl       
			  , String tmpltCd          

			            ) {
		
		  this.transDt = transDt;
		  this.svcOrgCd = svcOrgCd;
		  this.seqNo = seqNo;
		  this.dataGbnCd = dataGbnCd;
		  this.ipinCi = ipinCi;
		  this.svcOrgNm = svcOrgNm;
		  this.msgType = msgType;
		  this.msgInfo = msgInfo;
		  this.msgSize = msgSize;
		  this.msgText = msgText;
		  this.mmsImgSize = mmsImgSize;
		  this.mmsImg = mmsImg;
		  this.srcCallNo = srcCallNo;
		  this.cnForm = cnForm;
		  this.msgTitle = msgTitle;
		  this.balsongGbn = balsongGbn;
		  this.multiMblPrcGbn = multiMblPrcGbn;
		  this.cellNoListSize = cellNoListSize;
		  this.cellNoList = cellNoList;
		  this.sndnDtm = sndnDtm;
		  this.sndnVldDtm = sndnVldDtm;
		  this.sndnProcCl = sndnProcCl;
		  this.tmpltCd = tmpltCd;
		  
	  }



	@Override
	public String toString() {
	  return "TbMoblSndnMsgVO{" +
						"  transDt         = '" +  transDt         + '\''+  
						", svcOrgCd       = '" +   svcOrgCd        + '\''+  
						", seqNo          = '" +   seqNo           + '\''+  
						", dataGbnCd      = '" +   dataGbnCd       + '\''+  
						", ipinCi         = '" +   ipinCi          + '\''+  
						", svcOrgNm       = '" +   svcOrgNm        + '\''+  
						", msgType        = '" +   msgType         + '\''+  
						", msgInfo        = '" +   msgInfo         + '\''+  
						", msgSize        = '" +   msgSize         + '\''+  
						", msgText        = '" +   msgText         + '\''+  
						", mmsImgSize     = '" +   mmsImgSize      + '\''+  
						", mmsImg         = '" +   mmsImg          + '\''+  
						", srcCallNo      = '" +   srcCallNo       + '\''+  
						", cnForm         = '" +   cnForm          + '\''+  
						", msgTitle       = '" +   msgTitle        + '\''+  
						", balsongGbn     = '" +   balsongGbn      + '\''+  
						", multiMblPrcGbn = '" +   multiMblPrcGbn  + '\''+  
						", cellNoListSize = '" +   cellNoListSize  + '\''+  
						", cellNoList     = '" +   cellNoList      + '\''+  
						", sndnDtm        = '" +   sndnDtm         + '\''+  
						", sndnVldDtm     = '" +   sndnVldDtm      + '\''+  
						", sndnProcCl     = '" +   sndnProcCl      + '\''+  
						", tmpltCd        = '" +   tmpltCd         + '\''+  
	      '}'
			  ;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataGbnCd() {
		return dataGbnCd;
	}

	public void setDataGbnCd(String dataGbnCd) {
		this.dataGbnCd = dataGbnCd;
	}

	public String getSvcOrgCd() {
		return svcOrgCd;
	}

	public void setSvcOrgCd(String svcOrgCd) {
		this.svcOrgCd = svcOrgCd;
	}

	public String getTransDt() {
		return transDt;
	}

	public void setTransDt(String transDt) {
		this.transDt = transDt;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getSvcGrpCd() {
		return svcGrpCd;
	}

	public void setSvcGrpCd(String svcGrpCd) {
		this.svcGrpCd = svcGrpCd;
	}

	public String getIpinCi() {
		return ipinCi;
	}

	public void setIpinCi(String ipinCi) {
		this.ipinCi = ipinCi;
	}

	public String getSvcOrgNm() {
		return svcOrgNm;
	}

	public void setSvcOrgNm(String svcOrgNm) {
		this.svcOrgNm = svcOrgNm;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public Integer getMsgSize() {
		return msgSize;
	}

	public void setMsgSize(Integer msgSize) {
		this.msgSize = msgSize;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public Long getMmsImgSize() {
		return mmsImgSize;
	}

	public void setMmsImgSize(Long mmsImgSize) {
		this.mmsImgSize = mmsImgSize;
	}

	public String getMmsImg() {
		return mmsImg;
	}

	public void setMmsImg(String mmsImg) {
		this.mmsImg = mmsImg;
	}

	public String getSrcCallNo() {
		return srcCallNo;
	}

	public void setSrcCallNo(String srcCallNo) {
		this.srcCallNo = srcCallNo;
	}

	public String getCnForm() {
		return cnForm;
	}

	public void setCnForm(String cnForm) {
		this.cnForm = cnForm;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getBalsongGbn() {
		return balsongGbn;
	}

	public void setBalsongGbn(String balsongGbn) {
		this.balsongGbn = balsongGbn;
	}

	public String getMultiMblPrcGbn() {
		return multiMblPrcGbn;
	}

	public void setMultiMblPrcGbn(String multiMblPrcGbn) {
		this.multiMblPrcGbn = multiMblPrcGbn;
	}

	public Integer getCellNoListSize() {
		return cellNoListSize;
	}

	public void setCellNoListSize(Integer cellNoListSize) {
		this.cellNoListSize = cellNoListSize;
	}

	public String getCellNoList() {
		return cellNoList;
	}

	public void setCellNoList(String cellNoList) {
		this.cellNoList = cellNoList;
	}

	public String getSndnDtm() {
		return sndnDtm;
	}

	public void setSndnDtm(String sndnDtm) {
		this.sndnDtm = sndnDtm;
	}

	public String getSndnVldDtm() {
		return sndnVldDtm;
	}

	public void setSndnVldDtm(String sndnVldDtm) {
		this.sndnVldDtm = sndnVldDtm;
	}

	public String getSndnProcCl() {
		return sndnProcCl;
	}

	public void setSndnProcCl(String sndnProcCl) {
		this.sndnProcCl = sndnProcCl;
	}

	public Integer getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(Integer totalCnt) {
		this.totalCnt = totalCnt;
	}

	public String getTmpltCd() {
		return tmpltCd;
	}

	public void setTmpltCd(String tmpltCd) {
		this.tmpltCd = tmpltCd;
	}

	public String getAnocInfoUrl() {
		return anocInfoUrl;
	}

	public void setAnocInfoUrl(String anocInfoUrl) {
		this.anocInfoUrl = anocInfoUrl;
	}

	public String getRcveRfslUrl() {
		return rcveRfslUrl;
	}

	public void setRcveRfslUrl(String rcveRfslUrl) {
		this.rcveRfslUrl = rcveRfslUrl;
	}

	public String getOfapElctAddr() {
		return ofapElctAddr;
	}

	public void setOfapElctAddr(String ofapElctAddr) {
		this.ofapElctAddr = ofapElctAddr;
	}

	
	
	
	
}
