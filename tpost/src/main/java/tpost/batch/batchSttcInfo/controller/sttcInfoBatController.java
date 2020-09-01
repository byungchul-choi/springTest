package tpost.batch.batchSttcInfo.controller;

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

import tpost.batch.batchSttcInfo.service.sttcInfoBatService;
import tpost.batch.batchSttcInfo.vo.sttcInfoBatVO;


@Component
@Controller
@RequestMapping(value = "/batch")
public class sttcInfoBatController {

	/**
	 * 
	 모바일 통계페이지
	 통계정보생성을 위한 모바일 성공 메세지 합쿼리
	통계정보생성을 위한 모바일 실패 메세지 합쿼리
	통계정보생성을 위한 모바일 열람건수 합쿼리
	My SQL 함수 ON DUPLICATE KEY UPDATE 을 사용하여 데이터가 있는경우 건수만 업데이트
	*/
	
	@Autowired
	sttcInfoBatService sttcInfoBatService;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(sttcInfoBatController.class);

	String osName = System.getProperty("os.name");
	
	
	 /*파일 배치 테스트 입니다.  */
	 @ResponseBody
	 @RequestMapping(value = "/sttcInfoBatInsert", method = RequestMethod.POST)
	 public Map sttcInfoBatInsert(Model model , @ModelAttribute sttcInfoBatVO sttcInfoBatVO ) {
		 
		 log.debug("/batch/sttcInfoInsert");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		
		 /*문자 발송 메세지 성공건 저장*/
		 sttcInfoBatService.sttcInfoBatIns  (sttcInfoBatVO);
		 
		 
		 return resultMap;
	 }
	
 	
	 
}
	
