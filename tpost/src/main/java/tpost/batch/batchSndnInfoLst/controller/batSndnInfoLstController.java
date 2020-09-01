package tpost.batch.batchSndnInfoLst.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tpost.batch.batchSndnInfoLst.service.batSndnInfoLstService;
import tpost.batch.batchSndnInfoLst.vo.batSndnInfoLstVO;
import tpost.commCode.controller.commCdMgntController;



@Component
@Controller
@RequestMapping(value = "/batch/batSndnInfoLst")
public class batSndnInfoLstController {

	@Autowired
	batSndnInfoLstService batSndnInfoLstService;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);

	String osName = System.getProperty("os.name");
	

	/*발송내역적재   */
	 @ResponseBody
	 @RequestMapping(value = "/batSndnInfoLstUpload", method = RequestMethod.POST)
	 public Map batSndnInfoLst(Model model , @ModelAttribute batSndnInfoLstVO batSndnInfoLstVO ) {
		 
		 log.debug("batSndnInfoLst");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		  
		 /*파일 실행일 기관코드 템플릿 코드 가져오는 곳 추후 셋팅 설정해주기*/
	      String exeDt = "";
	      String oganCd = "";
	      String tmpltCd = "";
	      
//	      oganCd = "100";
//	      tmpltCd = "10000005";
	      
	      
	      oganCd = batSndnInfoLstVO.getOganCd();
		  tmpltCd = batSndnInfoLstVO.getTmpltCd();
	      
		  batSndnInfoLstVO.setOganCd(oganCd);
		  batSndnInfoLstVO.setTmpltCd(tmpltCd);
	      /*-----------------------------------------------------*/
		 
		 
		 /*발송내역 테이블 업로드 */
		  batSndnInfoLstService.batSndnInfoLstInsert(batSndnInfoLstVO);
		 
		 /*test */
		 resultMap.put("result", "Y");
		 resultMap.put("result", "Y");
		 resultMap.put("result", "Y");
		 resultMap.put("result", "Y");
		 resultMap.put("status", "delete");
		 resultMap.put("errorMessage", "");
		 
		 return resultMap;
	 }
	 
}
	
