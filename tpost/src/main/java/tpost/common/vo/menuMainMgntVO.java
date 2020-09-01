package tpost.common.vo;

@SuppressWarnings("serial")
public class menuMainMgntVO extends aoMgntVO {   /*페이징 vo 확장시켜서 사용*/

	/*조회조건 */
	private String menuIdSer  =" "	  ;
	private String menuNmSer  =" "   ;
	
	/*저장 변수 */
	private String menuId	  =""	  ;
	private String menuNm     =""    ;
	private String uprMenuId  =""    ;
	private String uprMenuNm  =""    ;
	private String grpYn      =""    ;
	private String dispNo     =""    ;
	private String aoId       =""    ;
	private String aoNm       =""    ;
	private String crtDate    =""    ;
	private String crtr       =""    ;
	private String mdfDate    =""   ;
	private String amdr       =""     ;
	private String menuDept       =""     ;
	
	private String subMenu       =""     ;
	private String pathNm       =""     ;
	
	
	public menuMainMgntVO() {
	  }

	  public menuMainMgntVO(
			  			  String menuDept
			  			, String menuId
			            , String menuNm
			            , String menuIdSer
			            , String menuNmSer
			            , String uprMenuId
			            , String uprMenuNm 
			            , String grpYn 
			            , String dispNo 
			            , String aoId
			            , String crtDate
			            , String crtr
			            , String mdfDate
			            , String amdr
			            ) {
		  this.menuDept	= menuDept		;
		  this.menuIdSer	= menuIdSer		;
		  this.menuNmSer    = menuNmSer        ;
		  this.menuId		= menuId		;
		  this.menuNm         = menuNm        ;
		  this.uprMenuId      = uprMenuId     ;
		  this.uprMenuNm      = uprMenuNm     ;
		  this.grpYn          = grpYn         ;
		  this.dispNo         = dispNo        ;
		  this.aoId           = aoId          ;
		  this.crtDate        = crtDate       ;
		  this.crtr           = crtr          ;
		  this.mdfDate        = mdfDate       ;
		  this.amdr           = amdr          ;
	    
	  }



	@Override
	public String toString() {
	  return "menuMainMgntVO{" +
			  "menuDept		='"+menuDept		+'\''+
			  ",menuId		='"+menuId		+'\''+
			  ", menuNm     = '"+menuNm     + '\''+
			", uprMenuId  = '"+uprMenuId  + '\''+
			", uprMenuNm  = '"+uprMenuNm  + '\''+
			", grpYn      = '"+grpYn      + '\''+
			", dispNo     = '"+dispNo     + '\''+
			", aoId       = '"+aoId       + '\''+
			", crtDate    = '"+crtDate    + '\''+
			", crtr       = '"+crtr       + '\''+
			", mdfDate    = '"+mdfDate    + '\''+
			", amdr       = '"+amdr       + '\''+
	      
	      
	      
	      
	      '}';
	}
	
	
	

	public String getPathNm() {
		return pathNm;
	}

	public void setPathNm(String pathNm) {
		this.pathNm = pathNm;
	}

	public String getAoNm() {
		return aoNm;
	}

	public void setAoNm(String aoNm) {
		this.aoNm = aoNm;
	}

	public String getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(String subMenu) {
		this.subMenu = subMenu;
	}

	public String getMenuDept() {
		return menuDept;
	}

	public void setMenuDept(String menuDept) {
		this.menuDept = menuDept;
	}

	public String getMenuIdSer() {
		return menuIdSer;
	}

	public void setMenuIdSer(String menuIdSer) {
		this.menuIdSer = menuIdSer;
	}

	public String getMenuNmSer() {
		return menuNmSer;
	}

	public void setMenuNmSer(String menuNmSer) {
		this.menuNmSer = menuNmSer;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getUprMenuId() {
		return uprMenuId;
	}

	public void setUprMenuId(String uprMenuId) {
		this.uprMenuId = uprMenuId;
	}

	public String getUprMenuNm() {
		return uprMenuNm;
	}

	public void setUprMenuNm(String uprMenuNm) {
		this.uprMenuNm = uprMenuNm;
	}

	public String getGrpYn() {
		return grpYn;
	}

	public void setGrpYn(String grpYn) {
		this.grpYn = grpYn;
	}

	public String getDispNo() {
		return dispNo;
	}

	public void setDispNo(String dispNo) {
		this.dispNo = dispNo;
	}

	public String getAoId() {
		return aoId;
	}

	public void setAoId(String aoId) {
		this.aoId = aoId;
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
