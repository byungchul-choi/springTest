package tpost.common.vo;

public class codeMgntVO {
	private String inputCfcd;
	private String inputCfcdNm;
	private String inputBasc;
	private String inputBascNm;
	private String inputDtcd;
	
	public String getInputCfcd() {
		return inputCfcd;
	}
	public void setInputCfcd(String inputCfcd) {
		this.inputCfcd = inputCfcd;
	}
	
	public String getInputCfcdNm() {
		return inputCfcdNm;
	}
	public void setInputCfcdNm(String inputCfcdNm) {
		this.inputCfcdNm = inputCfcdNm;
	}
	
	public String getInputBasc() {
		return inputBasc;
	}
	public void setInputBasc(String inputBasc) {
		this.inputBasc = inputBasc;
	}
	
	public String getInputBascNm() {
		return inputBascNm;
	}
	public void setInputBascNm(String inputBascNm) {
		this.inputBascNm = inputBascNm;
	}
	
	public String getInputDtcd() {
		return inputDtcd;
	}
	public void setInputDtcd(String inputDtcd) {
		this.inputDtcd = inputDtcd;
	}
	
	//------------------------------------------------- 분류코드 -------------------------------------------------
	private String cfcd;				/* 분류코드 */
	private String cfcdNm;				/* 분류코드 명 */
	private String cfcdDesc;			/* 분류코드  설명*/
	
	public String getCfcd() {
		return cfcd;
	}
	public void setCfcd(String cfcd) {
		this.cfcd = cfcd;
	}
	
	public String getCfcdNm() {
		return cfcdNm;
	}
	public void setCfcdNm(String cfcdNm) {
		this.cfcdNm = cfcdNm;
	}
	
	public String getCfcdDesc() {
		return cfcdDesc;
	}
	public void setCfcdDesc(String cfcdDesc) {
		this.cfcdDesc = cfcdDesc;
	}
	//------------------------------------------------- 분류코드 -------------------------------------------------
	
	
	
	//------------------------------------------------- 기본코드 -------------------------------------------------
	private String basc;				/* 기본코드 */
	private String bascNm;				/* 기본코드 명 */
	private String bascDesc;			/* 기본코드 설명 */
	private String bascApplStDt;		/* 기본코드 적용시작일자 */
	private String bascApplClosDt;		/* 기본코드  적용종료일자*/
	private String bascUseYn;			/* 기본코드  사용여부*/
	
	public String getBasc() {
		return basc;
	}
	public void setBasc(String basc) {
		this.basc = basc;
	}
	
	public String getBascNm() {
		return bascNm;
	}
	public void setBascNm(String bascNm) {
		this.bascNm = bascNm;
	}
	
	public String getBascDesc() {
		return bascDesc;
	}
	public void setBascDesc(String bascDesc) {
		this.bascDesc = bascDesc;
	}
	
	public String getBascApplStDt() {
		return bascApplStDt;
	}
	public void setBascApplStDt(String bascApplStDt) {
		this.bascApplStDt = bascApplStDt;
	}
	
	public String getBascApplClosDt() {
		return bascApplClosDt;
	}
	public void setBascApplClosDt(String bascApplClosDt) {
		this.bascApplClosDt = bascApplClosDt;
	}
	
	public String getBascUseYn() {
		return bascUseYn;
	}
	public void setBascUseYn(String bascUseYn) {
		this.bascUseYn = bascUseYn;
	}
	
	
	//------------------------------------------------- 상세코드 -------------------------------------------------
	private String dtcd;				/* 상세코드 */
	private String dtcdNm;				/* 상세코드 명 */
	private String dtcdDesc;			/* 상세코드 설명 */
	private String dtcdApplStDt;		/* 상세코드 적용시작일자 */
	private String dtcdApplClosDt;		/* 상세코드 적용종료일자 */
	private String dtcdUseYn;			/* 상세코드 사용여부 */
	
	public String getDtcd() {
		return dtcd;
	}
	public void setDtcd(String dtcd) {
		this.dtcd = dtcd;
	}
	
	public String getDtcdNm() {
		return dtcdNm;
	}
	public void setDtcdNm(String dtcdNm) {
		this.dtcdNm = dtcdNm;
	}
	
	public String getDtcdDesc() {
		return dtcdDesc;
	}
	public void setDtcdDesc(String dtcdDesc) {
		this.dtcdDesc = dtcdDesc;
	}
	
	public String getDtcdApplStDt() {
		return dtcdApplStDt;
	}
	public void setDtcdApplStDt(String dtcdApplStDt) {
		this.dtcdApplStDt = dtcdApplStDt;
	}
	
	public String getDtcdApplClosDt() {
		return dtcdApplClosDt;
	}
	public void setDtcdApplClosDt(String dtcdApplClosDt) {
		this.dtcdApplClosDt = dtcdApplClosDt;
	}
	
	public String getDtcdUseYn() {
		return dtcdUseYn;
	}
	public void setDtcdUseYn(String dtcdUseYn) {
		this.dtcdUseYn = dtcdUseYn;
	}
	//------------------------------------------------- 상세코드 -------------------------------------------------
			
	private String maxHistSeq;					
	
	public String getMaxHistSeq() {
		return maxHistSeq;
	}
	
	public void setMaxHistSeq(String maxHistSeq) {
		this.maxHistSeq = maxHistSeq;
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