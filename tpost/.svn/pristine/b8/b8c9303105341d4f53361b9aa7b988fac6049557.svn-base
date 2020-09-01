package tpost.batch.controller;

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

import tpost.batch.service.moblSndnBulkInsService;
import tpost.batch.vo.moblSndnBulkInsVO;


@Component
@Controller
@RequestMapping(value = "/batch")
public class moblSndnBulkIns{

	@Autowired
	moblSndnBulkInsService moblSndnBulkInsService;
	
	/*로그설정 입니다. */
	Logger logger = (Logger) LogManager.getLogger(moblSndnBulkIns.class);

	 /*파일 배치 테스트 입니다.  */
	 @ResponseBody
	 @RequestMapping(value = "/moblSndnBulkIns", method = RequestMethod.POST)
	 public Map moblSndnBulkIns(Model model , @ModelAttribute moblSndnBulkInsVO moblSndnBulkInsVO ) {
		 logger.debug("moblSndnBulkIns Start");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		
		 
		 moblSndnBulkInsService.moblSndnBulkIns(moblSndnBulkInsVO);
		 
		 
		 return resultMap;
		 
		   
	 }
	 
	 
	 

}