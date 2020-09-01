package tpost.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import tpost.commCode.controller.commCdMgntController;
import tpost.commCode.service.commCdMgntService;
import tpost.common.service.menuMainMgntService;
import tpost.common.vo.menuMainMgntVO;
import tpost.common.vo.tmpltMgntVO;
import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.logIn.vo.logInVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping(value = "common")
public class menuMainMgntController {

	@Autowired
	menuMainMgntService menuMainMgntService;

	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	
	public menuMainMgntController() {
	}
	
	
	/*처음화면 호출 */
	 @RequestMapping(value = "/menuMainMgnt", method = RequestMethod.POST)
	  public String menuMainMgntInit(Model model , @ModelAttribute menuMainMgntVO menuMainMgntVO ) {
	    
		 log.debug("/common/menuMainMgnt");
		 
		
		 menuMainMgntVO.setMenuIdSer("");
		 menuMainMgntVO.setMenuNmSer("");
		 
			
		menuMainMgntSel(model, menuMainMgntVO);
		
	    return "common/menuMainMgnt";
	  }
	 
	 /* 그리드 화면 조회 */
	 @RequestMapping(value = "/menuMainMgntSel", method = RequestMethod.POST)
	 public String menuMainMgntSel (Model model , @ModelAttribute menuMainMgntVO menuMainMgntVO ) {
		 
		 log.debug("/common/menuMainMgntSel");
		   
		 PaginationInfo paginationInfo = new PaginationInfo();
		 paginationInfo.setCurrentPageNo(menuMainMgntVO.getPageIndex()); //현재 페이지 번호
		 paginationInfo.setRecordCountPerPage(10);  //한 페이지에 게시되는 게시물 건수
		 paginationInfo.setPageSize(5); //페이징 리스트의 사이즈
		
		 	
		 menuMainMgntVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		 menuMainMgntVO.setLastIndex(paginationInfo.getLastRecordIndex());
		 menuMainMgntVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	  
		 /*내역조회*/
		 model.addAttribute("menuIdSer", menuMainMgntVO.getMenuIdSer());
		 model.addAttribute("menuNmSer", menuMainMgntVO.getMenuNmSer());	
		 model.addAttribute("resultList", menuMainMgntService.menuMainMgntSelect(menuMainMgntVO));
		 /*총건수셋팅*/
		 paginationInfo.setTotalRecordCount ( (Integer) menuMainMgntService.menuMainMgntSelectCnt(menuMainMgntVO));
		 model.addAttribute("paginationInfo", paginationInfo);
			 
		 return "common/menuMainMgnt";
	 }
	 
	 /*메뉴화면 저장 */
	 @ResponseBody
	 @RequestMapping(value = "/menuMainMgnt-insert.ajax", method = RequestMethod.POST)
	 public Map menuMainMgntInsert(Model model , @ModelAttribute menuMainMgntVO menuMainMgntVO ) {
		 
		 log.debug("/common/menuMainMgnt-insert");
		 
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 
		 String errmsg = "";
//		        errmsg = logInService.passWordChg(logInVO);
		 
		 menuMainMgntService.menuMainMgntInsert(menuMainMgntVO);
		 
		 resultMap.put("result", "Y");
		 resultMap.put("errmsg", "저장되었습니다.");
		 	 
		 
		 return resultMap;
	 }
	 
	 /*메뉴화면 업데이트 */
	 @ResponseBody
	 @RequestMapping(value = "/menuMainMgnt-update.ajax", method = RequestMethod.POST)
	 public Map menuMainMgntUpdate(Model model , @ModelAttribute menuMainMgntVO menuMainMgntVO ) {
		 
		 log.debug("/common/menuMainMgnt");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errmsg = "";
		 
		 menuMainMgntService.menuMainMgntUpdate(menuMainMgntVO);
		 
		 resultMap.put("result", "Y");
		 resultMap.put("errmsg", "수정되었습니다.");
		 
		 return resultMap;
	 }

	 /*메뉴화면 삭제 */
	 @ResponseBody
	 @RequestMapping(value = "/menuMainMgnt-delete.ajax", method = RequestMethod.POST)
	 public Map menuMainMgntDelete(Model model , @ModelAttribute menuMainMgntVO menuMainMgntVO ) {
		 
		 log.debug("/common/menuMainMgnt");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errmsg = "";
		 
		 menuMainMgntService.menuMainMgntDelete(menuMainMgntVO);
		 
		 resultMap.put("result", "Y");
		 resultMap.put("errmsg", "삭제 되었습니다.");
		 return resultMap;
	 }
	 
	 
	 /*좌측 메뉴조회하기 */
	 @RequestMapping(value = "/menuMainIndex-select", method = {RequestMethod.GET, RequestMethod.POST})
	 public String menuMainIndexSelect(Model model , @ModelAttribute logInVO logInVO ) {
		 
		 log.debug("/common/menuMainTop-select");
		 
		 /*세션에 있는 custId 셋팅*/
		 log.debug("custId ==> " + EgovUserDetailsHelper.getUserId());
		 
		 logInVO sessionLogInVo =null;
		 if(RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION) != null) {
			 sessionLogInVo = (tpost.logIn.vo.logInVO) RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION);
			 logInVO.setCustId(sessionLogInVo.getCustId());

		 }
		 
		 
		 model.addAttribute("resultList",  menuMainMgntService.menuMainIndexSelect(logInVO));
		
		 return "common/menuIndex";
	 }
	 
	 /*상단 메뉴바 가져오기 test*/
	 @RequestMapping(value = "/menuGnb", method = {RequestMethod.GET, RequestMethod.POST})
	 public String menuMainMenuGnb(Model model , @ModelAttribute logInVO logInVO ) {
		 
		 log.debug("/common/menuGnb");
//		 menuMainMgntService.menuMainMgntDelete(menuMainMgntVO);
		 
		 logInVO sessionLogInVo =null;
		 if(RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION) != null){
			 sessionLogInVo = (tpost.logIn.vo.logInVO) RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION);
//			 logInVO.setCustId(sessionLogInVo.getCustId());
			 
			 model.addAttribute("custId", sessionLogInVo.getCustId());
			 model.addAttribute("custNm", sessionLogInVo.getCustNm());
		 }
		 
		 return "common/menuGnb";
	 }
	 
	 /*메뉴화면 상당바가져오기 */
	 @ResponseBody
	 @RequestMapping(value = "/menuGnb.ajax", method = {RequestMethod.GET, RequestMethod.POST})
	 public Map menuMainMenuGnbAjax(Model model , @ModelAttribute logInVO logInVO ) {
		 
		 log.debug("/common/menuGnb.ajax");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 
		 logInVO sessionLogInVo =null;
		 if(RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION) != null){
			 sessionLogInVo = (tpost.logIn.vo.logInVO) RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION);
//			 logInVO.setCustId(sessionLogInVo.getCustId());
			 logInVO.setCustId(sessionLogInVo.getCustId());
			 resultMap.put("custNm" , sessionLogInVo.getCustNm() );
			 resultMap.put("custId" , sessionLogInVo.getCustId() );
		 }
		 
		 
		 resultMap.put("menuGbn", menuMainMgntService.menuMainGnbSelect(logInVO));
		 resultMap.put("result", "Y");
		 
		 return resultMap;
	 }

}
