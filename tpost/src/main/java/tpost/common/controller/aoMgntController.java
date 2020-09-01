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

import tpost.commCode.controller.commCdMgntController;
import tpost.common.service.aoMgntService;
import tpost.common.vo.aoMgntVO;
import tpost.common.vo.custInfMgntVO;
import tpost.common.vo.menuMainMgntVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping(value = "common")
public class aoMgntController {

	@Autowired
	aoMgntService aoMgntService;

	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	
	/*처음화면 호출 */
	 @RequestMapping(value = "/aoMgnt", method = RequestMethod.POST)
	  public String aoMgntInit(Model model , @ModelAttribute aoMgntVO aoMgntVO ) {
	    
		 log.debug("/common/aoMgnt");
		  
		  aoMgntVO.setAoIdSel("");
		  aoMgntVO.setAoNmSel("");
		 	
		  aoMgntSel(model, aoMgntVO);
		
	    return "common/aoMgnt";
	  }
	 
	 /*메뉴화면 저장 */
	 @ResponseBody
	 @RequestMapping(value = "/aoMgnt-insert.ajax", method = RequestMethod.POST)
	 public Map aoMgntInsert(Model model , @ModelAttribute aoMgntVO aoMgntVO ) {
		 
		 log.debug("/common/aoMgnt-insert");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errmsg = "";
		 
		 
		 int cnt = aoMgntService.aoMgntExCnt(aoMgntVO);
		 if ( cnt > 0 ) {
			 
			 resultMap.put("result", "N");
			 resultMap.put("errmsg", "중복된데이터가 있습니다. ID확인하시기바랍니다.");
		 }else {
			 aoMgntService.aoMgntInsert(aoMgntVO);
			 resultMap.put("result", "Y");
			 resultMap.put("errmsg", "정상처리되었습니다.");
		 }
		 
		 return resultMap;
	 }
	 
	 /*메뉴화면 업데이트 */
	 @ResponseBody
	 @RequestMapping(value = "/aoMgnt-update.ajax", method = RequestMethod.POST)
	 public Map aoMgntUpdate(Model model , @ModelAttribute aoMgntVO aoMgntVO ) {
		 
		 log.debug("/common/aoMgnt");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errmsg = "";
		 resultMap.put("result", "Y");
		 resultMap.put("errmsg", "정상처리되었습니다.");
		 
		 aoMgntService.aoMgntUpdate(aoMgntVO);
		 return resultMap;
	 }

	 /*메뉴화면 삭제 */
	 @ResponseBody
	 @RequestMapping(value = "/aoMgnt-delete.ajax", method = RequestMethod.POST)
	 public Map aoMgntDelete(Model model , @ModelAttribute aoMgntVO aoMgntVO ) {
		 
		 log.debug("/common/aoMgnt");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errmsg = "";
		 resultMap.put("result", "Y");
		 resultMap.put("errmsg", "정상처리되었습니다.");
		 aoMgntService.aoMgntDelete(aoMgntVO);
		 return resultMap;
	 }
	 
	 /* 그리드 화면 조회 */
	 @RequestMapping(value = "/aoMgntSel", method = RequestMethod.POST)
	 public String aoMgntSel (Model model , @ModelAttribute aoMgntVO aoMgntVO ) {
		 
		 log.debug("common/aoMgntSel");
		 
		  PaginationInfo paginationInfo = new PaginationInfo();
		  paginationInfo.setCurrentPageNo(aoMgntVO.getPageIndex()); //현재 페이지 번호
		  paginationInfo.setRecordCountPerPage(10);  //한 페이지에 게시되는 게시물 건수
		  paginationInfo.setPageSize(5); //페이징 리스트의 사이즈

	     	
		  aoMgntVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		  aoMgntVO.setLastIndex(paginationInfo.getLastRecordIndex());
		  aoMgntVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		  
		  /*조회 항목셋팅*/
		  model.addAttribute("aoidSel", aoMgntVO.getAoIdSel());
		  model.addAttribute("aoNmSel", aoMgntVO.getAoNmSel());
		  
		  model.addAttribute("resultList", aoMgntService.aoMgntSelect(aoMgntVO));
			
			
		  /*총건수셋팅*/
		  paginationInfo.setTotalRecordCount ( (Integer) aoMgntService.aoMgntSelectCnt(aoMgntVO));
		  model.addAttribute("paginationInfo", paginationInfo);
			

		 
		 return "common/aoMgnt";
	 }
	
	 
	 /**************************************************************************************/
	 /****고객정보팝업호출 **********************************************************************/
	 @RequestMapping(value = "/aoMgntInf-popu", method = RequestMethod.GET)
	  public String aoInfInitPop(Model model , @ModelAttribute aoMgntVO aoMgntVO ) {
	    
		 log.debug("/common/aoMgntInfPopu");
		 
		 model.addAttribute("athGrpIdLink", aoMgntVO.getAthGrpIdLink());
	    return "common/aoMgntInfPopu";
	  }
	 
	 @ResponseBody
	 @RequestMapping(value = "/aoMgntSel-popu", method = RequestMethod.POST)
	 public Map aoInfInitSelPop(Model model , @ModelAttribute menuMainMgntVO menuMainMgntVO ) {
		 
		 log.debug("/common/aoMgntSel-popu");
			
		  Map<String, Object> resultMap = new HashMap<String, Object>();
			 String errorMessage = "";	
			 resultMap.put("athGrpMgntAOList", aoMgntService.aoMgntSelectPopu(menuMainMgntVO));
			 resultMap.put("result", "Y");
			 resultMap.put("errorMessage", errorMessage);
			 
		  
		 return resultMap;
		 
	 }
	 
	 
	 @ResponseBody
	 @RequestMapping(value = "/aoMgntSel_menuMain-popu", method = RequestMethod.POST)
	 public Map aoInfInitSelPop_menuMain(Model model , @ModelAttribute menuMainMgntVO menuMainMgntVO ) {
		 
		 log.debug("/aoMgntSel_menuMain-popu");
			
		  Map<String, Object> resultMap = new HashMap<String, Object>();
			 String errorMessage = "";	
			 resultMap.put("athGrpMgntAOList", aoMgntService.aoMgntSelectPopu_menuMain(menuMainMgntVO));
			 resultMap.put("result", "Y");
			 resultMap.put("errorMessage", errorMessage);
			 
		  
		 return resultMap;
		 
	 }
}
	
