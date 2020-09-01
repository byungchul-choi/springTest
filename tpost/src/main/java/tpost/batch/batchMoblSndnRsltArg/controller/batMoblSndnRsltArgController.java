package tpost.batch.batchMoblSndnRsltArg.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tpost.batch.batchMoblSndnRsltArg.service.batMoblSndnRsltArgService;
import tpost.batch.batchMoblSndnRsltArg.vo.batMoblSndnRsltArgVO;
import tpost.commCode.controller.commCdMgntController;



@Component
@Controller
@RequestMapping(value = "/batch")
public class batMoblSndnRsltArgController {

	/**
	 * 
	 모바일 발송결과 배치
	 1. 주기  일배치
	 2. 모바일수신메시지 (TB_MOBL_RCVE_MSG) :  발송결과코드 (RESULT_CD) 를 기준으로 모바일발송메시지 (TB_MOBL_SNDN_MSG) 를 확인하여
	 3. 모바일 성공메시지 (TB_SNDN_SUCC_MSG), 모바일실패메시지 (TB_SNDN_FAIL_MSG) 에 넣는다. 
	*/
	@Autowired
	batMoblSndnRsltArgService batMoblSndnRsltArgService;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);

	String osName = System.getProperty("os.name");
	
	
	 /*파일 배치 테스트 입니다.  */
	 @ResponseBody
	 @RequestMapping(value = "/moblSndnRsltArg", method = RequestMethod.POST)
	 public Map moblSndnRslt(Model model , @ModelAttribute batMoblSndnRsltArgVO batMoblSndnRsltArgVO ) {
		 
		 log.debug("/batch/moblSndnRsltArg");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		
		 /*문자 발송 메세지 성공건 저장*/
		 log.debug("/batch/moblSndnRsltArgSuccIns");
		 batMoblSndnRsltArgService.moblSndnRsltArgSuccIns (batMoblSndnRsltArgVO);
		 
		 
		 /*문자 발송 메세지 실패건건 저장*/
		 log.debug("/batch/moblSndnRsltArgFailIns");
		 batMoblSndnRsltArgService.moblSndnRsltArgFailIns (batMoblSndnRsltArgVO);
		 
		 return resultMap;
	 }
	
 	
	 
}
	
