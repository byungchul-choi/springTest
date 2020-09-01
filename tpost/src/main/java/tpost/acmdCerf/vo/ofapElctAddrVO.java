package tpost.acmdCerf.vo;

import tpost.egovcomm.vo.PagingDefaultVO;

public class ofapElctAddrVO extends PagingDefaultVO{
	//------------------------------------------------- 공인전자주소 input -------------------------------------------------
	
	
	private String inputChk;
	private String inputPk;						/* pk */
	private String inputSttsStDt;				/* 신청시작일자 */
	private String inputSttsClosDt;				/* 신청종료일자 */
	private String inputBsrpCls;				/* 개인/법인 구분 */
	private String inputName;					/* 고객명 */
	private String inputOfapElctAddr;			/* 공인전자주소 */
	private String inputCiNum;					/* 고유번호*/
	private String inputSttsClcd;				/* 상태여부 */
	
	
	public String getInputChk() {
		return inputChk;
	}
	public void setInputChk(String inputChk) {
		this.inputChk = inputChk;
	}
	
	public String getInputPk() {
		return inputPk;
	}
	public void setInputPk(String inputPk) {
		this.inputPk = inputPk;
	}
	
	public String getInputSttsStDt() {
		return inputSttsStDt;
	}
	public void setInputSttsStDt(String inputSttsStDt) {
		this.inputSttsStDt = inputSttsStDt;
	}
	
	public String getInputSttsClosDt() {
		return inputSttsClosDt;
	}
	public void setInputSttsClosDt(String inputSttsClosDt) {
		this.inputSttsClosDt = inputSttsClosDt;
	}
	
	public String getInputBsrpCls() {
		return inputBsrpCls;
	}
	public void setInputBsrpCls(String inputBsrpCls) {
		this.inputBsrpCls = inputBsrpCls;
	}
	
	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	
	public String getInputOfapElctAddr() {
		return inputOfapElctAddr;
	}
	public void setInputOfapElctAddr(String inputOfapElctAddr) {
		this.inputOfapElctAddr = inputOfapElctAddr;
	}
	
	public String getInputCiNum() {
		return inputCiNum;
	}
	public void setInputCiNum(String inputCiNum) {
		this.inputCiNum = inputCiNum;
	}
	
	public String getInputSttsClcd() {
		return inputSttsClcd;
	}
	public void setInputSttsClcd(String inputSttsClcd) {
		this.inputSttsClcd = inputSttsClcd;
	}
	
	
	//------------------------------------------------- 공인전자주소  -------------------------------------------------

	
	private String name;				/* 고객명 */
	private String ofapElctAddr;		/* 공인전자주소 */
	private String ciNum;				/* 고유번호 */
	private String bsrpCls;				/* 개인/법인 구분 */
	private String sttsClcd;			/* 상태 여부 코드*/
	private String sttsClcdNm;			/* 상태 여부 명*/
	private String sttsDt;				/* 상태 일자 */
	private String histSeq;				/* 시퀀스 */
	private String ciSttsClcd;			/* 고객상태코드 */
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOfapElctAddr() {
		return ofapElctAddr;
	}
	public void setOfapElctAddr(String ofapElctAddr) {
		this.ofapElctAddr = ofapElctAddr;
	}
	
	public String getCiNum() {
		return ciNum;
	}
	public void setCiNum(String ciNum) {
		this.ciNum = ciNum;
	}
	
	public String getBsrpCls() {
		return bsrpCls;
	}
	public void setBsrpCls(String bsrpCls) {
		this.bsrpCls = bsrpCls;
	}
	
	public String getSttsClcd() {
		return sttsClcd;
	}
	public void setSttsClcd(String sttsClcd) {
		this.sttsClcd = sttsClcd;
	}
	
	public String getSttsClcdNm() {
		return sttsClcdNm;
	}
	public void setSttsClcdNm(String sttsClcdNm) {
		this.sttsClcdNm = sttsClcdNm;
	}
	
	public String getSttsDt() {
		return sttsDt;
	}
	public void setSttsDt(String sttsDt) {
		this.sttsDt = sttsDt;
	}
	
	public String getHistSeq() {
		return histSeq;
	}
	public void setHistSeq(String histSeq) {
		this.histSeq = histSeq;
	}
	
	public String getCiSttsClcd() {
		return ciSttsClcd;
	}
	public void setCiSttsClcd(String ciSttsClcd) {
		this.ciSttsClcd = ciSttsClcd;
	}
	
	
	
	//------------------------------------------------- 공인전자주소 등록  -------------------------------------------------

		private String popuChk;	
		private String popuHistSeq;			/* 이력순번 */
		private String popuRnrsCls;			/* 내국인/외국인 구분*/
		private String popuBsrpCls;			/* 개인/법인구분 */
		private String popuName;			/* 이름*/
		private String popuEn;				/* 사업자 영문 약자 */
		private String popuOfapElctAddr;	/* 공인전자주소 */
		private String popuCiNum;			/* 연계번호(CI) */
		private String popuSttsClcd;		/* 상태구분코드 */
		
		private String popuFronBrdt;		/* 생년월일(6자리) */
		private String popuSexClcd;			/* 성별구분코드 */
		private String popuCelpNum;			/* 휴대폰번호 */
		
		public String getPopuChk() {
			return popuChk;
		}
		public void setPopuChk(String popuChk) {
			this.popuChk = popuChk;
		}
		
		public String getPopuHistSeq() {
			return popuHistSeq;
		}
		public void setPopuHistSeq(String popuHistSeq) {
			this.popuHistSeq = popuHistSeq;
		}
		
		public String getPopuRnrsCls() {
			return popuRnrsCls;
		}
		public void setPopuRnrsCls(String popuRnrsCls) {
			this.popuRnrsCls = popuRnrsCls;
		}
		
		public String getPopuBsrpCls() {
			return popuBsrpCls;
		}
		public void setPopuBsrpCls(String popuBsrpCls) {
			this.popuBsrpCls = popuBsrpCls;
		}
		
		public String getPopuName() {
			return popuName;
		}
		public void setPopuName(String popuName) {
			this.popuName = popuName;
		}
		
		public String getPopuFronBrdt() {
			return popuFronBrdt;
		}
		public void setPopuFronBrdt(String popuFronBrdt) {
			this.popuFronBrdt = popuFronBrdt;
		}
		
		public String getPopuSexClcd() {
			return popuSexClcd;
		}
		public void setPopuSexClcd(String popuSexClcd) {
			this.popuSexClcd = popuSexClcd;
		}
		
		public String getPopuEn() {
			return popuEn;
		}
		public void setPopuEn(String popuEn) {
			this.popuEn = popuEn;
		}
		
		public String getPopuOfapElctAddr() {
			return popuOfapElctAddr;
		}
		public void setPopuOfapElctAddr(String popuOfapElctAddr) {
			this.popuOfapElctAddr = popuOfapElctAddr;
		}
		
		public String getPopuCiNum() {
			return popuCiNum;
		}
		public void setPopuCiNum(String popuCiNum) {
			this.popuCiNum = popuCiNum;
		}
		
		public String getPopuSttsClcd() {
			return popuSttsClcd;
		}
		public void setPopuSttsClcd(String popuSttsClcd) {
			this.popuSttsClcd = popuSttsClcd;
		}
		
		public String getPopuCelpNum() {
			return popuCelpNum;
		}
		
		public void setPopuCelpNum(String popuCelpNum) {
			this.popuCelpNum = popuCelpNum;
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