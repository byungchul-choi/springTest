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
import tpost.common.service.athGrpMgntService;
import tpost.common.vo.aoMgntVO;
import tpost.common.vo.athGrpMgntAOVO;
import tpost.common.vo.athGrpMgntCustVO;
import tpost.common.vo.athGrpMgntVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping(value = "common")
public class athGrpMgntController {

	@Autowired
	athGrpMgntService athGrpMgntService;

	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	
	/*처음화면 호출 */
	 @RequestMapping(value = "/athGrpMgnt", method = RequestMethod.POST)
	  public String aoMgntInit(Model model , @ModelAttribute athGrpMgntVO athGrpMgntVO ) {
	    
		 log.debug("/common/aoMgnt");
		 athGrpMgntVO.setAthGrpIdSel("");
		 athGrpMgntVO.setAthGrpNmSel("");
		 	
		 aoMgntSelect(model, athGrpMgntVO);
		 
	    return "common/athGrpMgnt";
	  }
	 
	 /****************************************************************************************/
	 /*****권한 구룹 **********************************************/
	 /*권한그룹정보  조회 */
	 @RequestMapping(value = "/athGrpMgnt-Select", method = RequestMethod.POST)
	 public String aoMgntSelect(Model model , @ModelAttribute athGrpMgntVO athGrpMgntVO ) {
		 
		 log.debug("/common/athGrpMgnt-Select");
		 
		 PaginationInfo paginationInfo = new PaginationInfo();
		 paginationInfo.setCurrentPageNo(athGrpMgntVO.getPageIndex()); //현재 페이지 번호
		 paginationInfo.setRecordCountPerPage(10);  //한 페이지에 게시되는 게시물 건수
		 paginationInfo.setPageSize(5); //페이징 리스트의 사이즈

		 athGrpMgntVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		 athGrpMgntVO.setLastIndex(paginationInfo.getLastRecordIndex());
		 athGrpMgntVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		  
		  /*조회 항목셋팅*/
		  model.addAttribute("athGrpIdSel", athGrpMgntVO.getAthGrpIdSel());
		  model.addAttribute("athGrpNmSel", athGrpMgntVO.getAthGrpNmSel());
		
		 model.addAttribute("arhGrpResultList", athGrpMgntService.athGrpMgntSelect(athGrpMgntVO));
		 
		 paginationInfo.setTotalRecordCount ( (Integer) athGrpMgntService.athGrpMgntSelectCnt(athGrpMgntVO));
		 model.addAttribute("paginationInfo", paginationInfo);
		 return "common/athGrpMgnt";
	 }
	 
	 /*권한그룹정보  저장 */
	 @ResponseBody
	 @RequestMapping(value = "/athGrpMgnt-insert.ajax", method = RequestMethod.POST)
	 public Map aoMgntInsert(Model model , @ModelAttribute athGrpMgntVO athGrpMgntVO ) {
		 
		 log.debug("/common/athGrpMgnt-insert.ajax");
		 
		 /*데이터 정합성을위해 delete insert 문처리 */
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errorMessage = "";
		 
		 athGrpMgntService.athGrpMgntDelete(athGrpMgntVO);
		 athGrpMgntService.athGrpMgntInsert(athGrpMgntVO);
		 
		 resultMap.put("result", "Y");
		 resultMap.put("status", "insert");
		 resultMap.put("errorMessage", errorMessage);
		 
		 return resultMap;
		 
	 }
	 
	 /*권한그룹정보  삭제 */
	 @ResponseBody
	 @RequestMapping(value = "/athGrpMgnt-delete.ajax", method = RequestMethod.POST)
	 public Map aoMgntDelete(Model model , @ModelAttribute athGrpMgntVO athGrpMgntVO ) {
		 
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errorMessage = "";
		 
		 log.debug("/common/athGrpMgnt-delete");
		 athGrpMgntService.athGrpMgntDelete(athGrpMgntVO);
		 
		 resultMap.put("result", "Y");
		 resultMap.put("status", "delete");
		 resultMap.put("errorMessage", errorMessage);
		 
		 return resultMap ;
	 }
	 
	 /*권한그룹정보  수정 */
	 @RequestMapping(value = "/athGrpMgnt-Update", method = RequestMethod.POST)
	 public String aoMgntUpdate(Model model , @ModelAttribute athGrpMgntVO athGrpMgntVO ) {
		 
		 log.debug("/common/athGrpMgnt-Update");
		 athGrpMgntService.athGrpMgntUpdate(athGrpMgntVO);
		 return "common/athGrpMgnt";
	 }
	 
	
	 /****************************************************************************************/

	 /****************************************************************************************/
	 /*****매핑고객정보 **********************************************/
	 /*권한그룹  고객정보  조회 */
	 @ResponseBody
	 @RequestMapping(value = "/athGrpMgntCust-Select.ajax", method = RequestMethod.POST)
	 public Map aoMgntCustSelect(Model model , @ModelAttribute athGrpMgntCustVO athGrpMgntCustVO ) {
		 
		 log.debug("/common/athGrpMgntCust-Select.ajax");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errorMessage = "";	
		 resultMap.put("athGrpMgntCustList", athGrpMgntService.athGrpMgntCustSelect(athGrpMgntCustVO));
		 resultMap.put("result", "Y");
		 resultMap.put("errorMessage", errorMessage);
		 
		 return resultMap;
		 
	 }
	 
	 /*권한그룹  고객정보  저장 */
	 @ResponseBody
	 @RequestMapping(value = "/athGrpMgntCust-insert.ajax", method = RequestMethod.POST)
	 public Map aoMgntCustInsert(Model model , @ModelAttribute athGrpMgntCustVO athGrpMgntCustVO ) {
		 
		 log.debug("/common/athGrpMgntCust-insert.ajax");
		 String errorMessage = "";

		 athGrpMgntService.athMgntCustInsert(athGrpMgntCustVO);
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 resultMap.put("result", "Y");
		 
		 resultMap.put("errorMessage", errorMessage);
		 return resultMap;
	 }
	 
	 /*권한그룹  고객정보   수정 */
	 @ResponseBody
	 @RequestMapping(value = "/athGrpMgntCust-Update.ajax", method = RequestMethod.POST)
	 public Map aoMgntCustUpdate(Model model , @ModelAttribute athGrpMgntCustVO athGrpMgntCustVO ) {
		 
		 /*권한그룹 고객정보 삭제 : USE_YN : Y-N */
		 log.debug("/common/athGrpMgntCust-Update.ajax");
		 athGrpMgntService.athMgntCustUpdate(athGrpMgntCustVO);
		 
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errorMessage = "";	
//		 resultMap.put("athGrpMgntCustList", athGrpMgntService.athGrpMgntCustSelect(athGrpMgntCustVO));
		 resultMap.put("result", "Y");
		 resultMap.put("status", "delete");
		 resultMap.put("errorMessage", errorMessage);
		 
		 return resultMap;
	 }
	 /****************************************************************************************/
	
	 /*****매핑정보 AO **********************************************/
	 /*권한그룹 AO  조회 */
	 @ResponseBody
	 @RequestMapping(value = "/athGrpMgntAO-Select.ajax", method = RequestMethod.POST)
	 public Map aoMgntAOtSelect(Model model , @ModelAttribute athGrpMgntAOVO athGrpMgntAOVO ) {
		 
		 log.debug("/common/athGrpMgntAO-Select.ajax");
		 
		 Map<String, Object> resultMap = new HashMap<String, Object>();
			
		 String errorMessage = "";	

		 resultMap.put("athGrpMgntAOList", athGrpMgntService.athGrpMgntAOSelect(athGrpMgntAOVO));
		 resultMap.put("result", "Y");
		 resultMap.put("errorMessage", errorMessage);
		 
		 return resultMap;
	 }
	 
	 /*권한그룹정보  저장 */
	 @ResponseBody
	 @RequestMapping(value = "/athGrpMgntAO-insert.ajax", method = RequestMethod.POST)
	 public Map aoMgntAOInsert(Model model , @ModelAttribute athGrpMgntAOVO athGrpMgntAOVO ) {
		 
		 log.debug("/common/athGrpMgntAO-insert.ajax");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errorMessage = "";	
		 
		 athGrpMgntService.athMgntAOInsert(athGrpMgntAOVO);
		 
		 resultMap.put("result", "Y");
		 resultMap.put("status", "delete");
		 resultMap.put("errorMessage", errorMessage);
		 
		 return resultMap;
	 }
	 
	 /*권한그룹정보  수정 */
	 @ResponseBody
	 @RequestMapping(value = "/athGrpMgntAO-update.ajax", method = RequestMethod.POST)
	 public Map aoMgntAOUpdate(Model model , @ModelAttribute athGrpMgntAOVO athGrpMgntAOVO ) {
		 
		 log.debug("/common/athGrpMgntAO-update.ajax");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 
		 athGrpMgntService.athMgntAOUpdate(athGrpMgntAOVO);
		 
			
		 String errorMessage = "";	

		 //resultMap.put("athGrpMgntAOList", athGrpMgntService.athGrpMgntAOSelect(athGrpMgntAOVO));
//		 resultMap.put("athGrpMgntAOList", athGrpMgntService.athGrpMgntAOSelect( (athGrpMgntAOVO) resultMap.get("athGrpMgntAOVO")   ));
		 resultMap.put("result", "Y");
		 resultMap.put("status", "delete");
		 resultMap.put("errorMessage", errorMessage);
		 
		 return resultMap;
	 }
	 /****************************************************************************************/
	 
	
	 
	 /**************************************************************************************/
	 /****매핑AO팝업호출 **********************************************************************/
	 
	 
}
	
