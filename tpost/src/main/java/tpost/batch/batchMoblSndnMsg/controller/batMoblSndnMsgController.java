package tpost.batch.batchMoblSndnMsg.controller;

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

import tpost.batch.batchMoblSndnMsg.service.batMoblSndnMsgService;
import tpost.batch.batchMoblSndnMsg.vo.batMoblSndnMsgVO;
import tpost.commCode.controller.commCdMgntController;



@Component
@Controller
@RequestMapping(value = "/batch/batMoblSndnMsg")
public class batMoblSndnMsgController {

	@Autowired
	batMoblSndnMsgService batMoblSndnMsgService;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);

	String osName = System.getProperty("os.name");
	
	
	 /*배치파일 최종 업로드 테스트 입니다.*/
	 @ResponseBody
	 @RequestMapping(value = "/batMoblSndnMsgUpload", method = RequestMethod.POST)
	 public Map batMoblSndnMsgUpload(Model model , @ModelAttribute batMoblSndnMsgVO batMoblSndnMsgVO ) {
		 
		 log.debug("전송 MMS 테스트입니다.");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 
		 batMoblSndnMsgUpload(batMoblSndnMsgVO);
		 
		 resultMap.put("result", "Y");
		 resultMap.put("status", "delete");
		 resultMap.put("errorMessage", "");
		 
		 return resultMap;
	 }
	
	 
	
	
	 
	

//	@Scheduled(fixedRate=1000000)
	public void batMoblSndnMsgUpload(batMoblSndnMsgVO batMoblSndnMsgVO){
		
		 log.debug("파일 업로드 배치 테스트 입니다.");
		
		/*MMS 파일 업로드 */
		 String transDt = "";
//		 transDt = "20200331";
		 
		 batTbMoblSndnMsg_upload(batMoblSndnMsgVO);
		
	}

	/*파일 최종 MMS 업로드 테스트입니다.*/
	private void batTbMoblSndnMsg_upload(batMoblSndnMsgVO batMoblSndnMsgVO) {
		
		batMoblSndnMsgService.tbMoblSndnMsg_upload(batMoblSndnMsgVO);
	}
}
	
