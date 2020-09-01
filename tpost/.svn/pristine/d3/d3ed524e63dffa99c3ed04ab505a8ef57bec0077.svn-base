package tpost.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.controller.ascCommUtil;
import tpost.common.service.custInfMgntService;
import tpost.common.vo.custInfMgntVO;


@Controller
@RequestMapping(value = "common")
public class custInfMgntController {

	@Autowired
	custInfMgntService custInfMgntService;

	 @Value("#{properties['aesKey']}") 
	private String aesKey;
	 
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	
	/*처음화면 호출 */
	 @RequestMapping(value = "/custInfMgnt", method = RequestMethod.POST)
	  public String custInfInit(Model model , @ModelAttribute custInfMgntVO custInfMgntVO ) {
	    
		 log.debug("/common/custInfMgnt");
		
		 custInfMgntVO.setOganCdSel("");
		 custInfMgntVO.setCustNmSel("");
		 	
		 custInfSel(model, custInfMgntVO);
		
		 
	    return "common/custInfMgnt";
	  }
	 
	 /*고객정보 저장 */
	 @ResponseBody
	 @RequestMapping(value = "/custInfMgnt-insert.ajax", method = RequestMethod.POST)
	 public Map custInfInsert(Model model , @ModelAttribute custInfMgntVO custInfMgntVO ) {
		 
		log.debug("/common/custInfMgnt-insert ==> " + aesKey);
		log.debug("acsKey ===> " + aesKey );
		 
		/*키셋팅하기*/
		String aesKey1 = ascCommUtil.ascKeyEncode(aesKey); 
		custInfMgntVO.setAesKey(aesKey1);
		 	
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errmsg = "";
		 
		 resultMap.put("result", "Y");
		 resultMap.put("errmsg", "정상처리되었습니다.");
		
		 
		 custInfMgntService.custInfInsert(custInfMgntVO);
		 
		 return resultMap;
	 }
	 
	 /*고객정보 업데이트 */
	 @ResponseBody
	 @RequestMapping(value = "/custInfMgnt-update.ajax", method = RequestMethod.POST)
	 public Map custInfUpdate(Model model , @ModelAttribute custInfMgntVO custInfMgntVO ) {
		 
		 log.debug("/common/custInfMgnt-update");
		 
		 String aesKey1 = ascCommUtil.ascKeyEncode(aesKey); 
		 custInfMgntVO.setAesKey(aesKey1);
		 
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errmsg = "";
		 
		 resultMap.put("result", "Y");
		 resultMap.put("errmsg", "정상처리되었습니다.");
		
		 
		 custInfMgntService.custInfUpdate(custInfMgntVO);
		 return resultMap;
	 }

	 /*고객정보 삭제 */
	 @ResponseBody
	 @RequestMapping(value = "/custInfMgnt-delete.ajax", method = RequestMethod.POST)
	 public Map custInfDelete(Model model , @ModelAttribute custInfMgntVO custInfMgntVO ) {
		 
		 log.debug("/common/custInfMgnt-delete");
		 
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errmsg = "";
		 
		 resultMap.put("result", "Y");
		 resultMap.put("errmsg", "정상처리되었습니다.");
		
		 
		 custInfMgntService.custInfDelete(custInfMgntVO);
		 return resultMap;
	 }
	 
	 /* 그리드 화면 조회 */
	 @RequestMapping(value = "/custInfMgntSel", method = RequestMethod.POST)
	 public String custInfSel (Model model , @ModelAttribute custInfMgntVO custInfMgntVO ) {
		 
		 log.debug("/common/custInfMgntSel");
		 
		 
		 String aesKey1 = ascCommUtil.ascKeyEncode(aesKey); 
		
		 custInfMgntVO.setAesKey(aesKey1);
			
		  
		  model.addAttribute("oganCdSel", custInfMgntVO.getOganCdSel());
		  model.addAttribute("custNmSel", custInfMgntVO.getCustNmSel());	
			 
		  model.addAttribute("resultList", custInfMgntService.custInfSelect(custInfMgntVO));
			
		 
		 return "common/custInfMgnt";
	 }
	 
	 
	 /**************************************************************************************/
	 /****고객정보팝업호출 **********************************************************************/
	 @RequestMapping(value = "/custInfMgntInf-popu", method = RequestMethod.GET)
	  public String custInfMgntInitPopu(Model model , @ModelAttribute custInfMgntVO custInfMgntVO ) {
	    
		 log.debug("/common/custInfMgnt-pop AthGrpIdLink = " + custInfMgntVO.getAthGrpIdLink());
		
		 model.addAttribute("athGrpIdLink", custInfMgntVO.getAthGrpIdLink() );
		 
		 return "common/custInfPopu";
	  }

	 @ResponseBody
	 @RequestMapping(value = "/custInfMgntInfSel-popu.ajax", method = RequestMethod.POST)
	 public Map custInfMgntInitSelPopu(Model model , @ModelAttribute custInfMgntVO custInfMgntVO ) {
		 
		 log.debug("/common/custInfMgntSel-pop.ajax");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
			
		 String errorMessage = "";	
		  /*조회 항목셋팅*/
//		  model.addAttribute("athGrpIdLink", custInfMgntVO.getAthGrpIdLink());
		  
		  /*팝업리스트 조회*/
//		  model.addAttribute("resultList", custInfMgntService.custInfMgntSelectPopu(custInfMgntVO));
		 String aesKey1 = ascCommUtil.ascKeyEncode(aesKey); 
		 custInfMgntVO.setAesKey(aesKey1);
		 
		  resultMap.put("custInfList", custInfMgntService.custInfMgntSelectPopu(custInfMgntVO));
		  resultMap.put("result", "Y");
		  resultMap.put("errorMessage", errorMessage);
			 
			 
		  
		 return resultMap;
	 }
	 @ResponseBody
	 @RequestMapping(value = "/custInfMgnt-pwInit.ajax", method = RequestMethod.POST)
	 public Map custInfMgntPwInit(Model model , @ModelAttribute custInfMgntVO custInfMgntVO ) {
		 
		 log.debug("/custInfMgnt-pwInit.ajax");
		 
		 /*키셋팅하기*/
		String aesKey1 = ascCommUtil.ascKeyEncode(aesKey); 
		custInfMgntVO.setAesKey(aesKey1);
			
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errmsg = "";
		 
		 resultMap.put("result", "Y");
		 resultMap.put("errmsg", "정상처리되었습니다.");
		 
		 custInfMgntService.custInfMgntPwInit(custInfMgntVO);
		 
		 return resultMap;
	 }
	 
}
	
