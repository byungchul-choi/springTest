package tpost.common.vo;

public class tmpltMgntVO {
	
	private String inputSndnOgan;			/* 기관(회사)명 인풋값 */
	private String inputTmpltNm;			/* 템플릿 이름 인풋값 */
	private String inputTmpltUseYn;			/* 사용여부  인풋값 */
	
	public String getInputSndnOgan() {
		return inputSndnOgan;
	}
	public void setInputSndnOgan(String inputSndnOgan) {
		this.inputSndnOgan = inputSndnOgan;
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
	
	
	//------------------------------------------------- 템플릿 목록 화면 -------------------------------------------------
	
	
	
	private String sndnOgan;					/* 기관(회사)명  */
	private String sndnOganCd;					/* 기관(회사)코드 */
	private String tmpltCd;						/* 템플릿코드 */
	private String tmpltNm;						/* 템플릿 명 */
	private String tmpltUseYn;					/* 사용여부 */
	private String tmpltSchemaYn;				/* 스키마생성여부 */
	private String tmpltCrtYn;					/* 템플릿생성여부 */
	private String tmpltDesc;					/* 템플릿설명 */
	
	public String getSndnOgan() {
		return sndnOgan;
	}
	public void setSndnOgan(String sndnOgan) {
		this.sndnOgan = sndnOgan;
	}
	
	public String getSndnOganCd() {
		return sndnOganCd;
	}
	public void setSndnOganCd(String sndnOganCd) {
		this.sndnOganCd = sndnOganCd;
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
	
	public String getTmpltUseYn() {
		return tmpltUseYn;
	}
	public void setTmpltUseYn(String tmpltUseYn) {
		this.tmpltUseYn = tmpltUseYn;
	}
	
	public String getTmpltSchemaYn() {
		return tmpltSchemaYn;
	}
	public void setTmpltSchemaYn(String tmpltSchemaYn) {
		this.tmpltSchemaYn = tmpltSchemaYn;
	}
	
	public String getTmpltCrtYn() {
		return tmpltCrtYn;
	}
	public void setTmpltCrtYn(String tmpltCrtYn) {
		this.tmpltCrtYn = tmpltCrtYn;
	}
	
	public String getTmpltDesc() {
		return tmpltDesc;
	}
	public void setTmpltDesc(String tmpltDesc) {
		this.tmpltDesc = tmpltDesc;
	}
	
	
	

	//------------------------------------------------- 스키마 관련 -------------------------------------------------
	
	
	private String inputChk;
	private String itemNm;			/* 항목이름  */
	private String itemSno;			/* 항목일련번호  */
	private String itemLen;			/* 항목길이 */
	private String itemAcmsLen;		/* 항목누적길이 */
	private String itemLocSeqn;		/* 항목위치순번  */
	
	
	public String getInputChk() {
		return inputChk;
	}
	public void setInputChk(String inputChk) {
		this.inputChk = inputChk;
	}
	
	public String getItemNm() {
		return itemNm;
	}
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}	
	
	public String getItemSno() {
		return itemSno;
	}
	public void setItemSno(String itemSno) {
		this.itemSno = itemSno;
	}	
	
	public String getItemLen() {
		return itemLen;
	}
	public void setItemLen(String itemLen) {
		this.itemLen = itemLen;
	}	
	
	public String getItemAcmsLen() {
		return itemAcmsLen;
	}
	public void setItemAcmsLen(String itemAcmsLen) {
		this.itemAcmsLen = itemAcmsLen;
	}	
	
	public String getItemLocSeqn() {
		return itemLocSeqn;
	}
	public void setItemLocSeqn(String itemLocSeqn) {
		this.itemLocSeqn = itemLocSeqn;
	}	

	
	//------------------------------------------------- 양식 등록/수정 관련 -------------------------------------------------
	

	private String editOganCd;			/* 기관이름  */
	private String editTmpltCd;			/* 템플릿코드  */
	private String editHtml;			/* HTML 양식 */
	private String viewHtml;			/* HTML 양식 */
	
	public String getEditOganCd() {
		return editOganCd;
	}
	public void setEditOganCd(String editOganCd) {
		this.editOganCd = editOganCd;
	}	
	
	public String getEditTmpltCd() {
		return editTmpltCd;
	}
	public void setEditTmpltCd(String editTmpltCd) {
		this.editTmpltCd = editTmpltCd;
	}	
	
	public String getEditHtml() {
		return editHtml;
	}
	public void setEditHtml(String editHtml) {
		this.editHtml = editHtml;
	}
	
	public String getViewHtml() {
		return viewHtml;
	}
	public void setViewHtml(String viewHtml) {
		this.viewHtml = viewHtml;
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