package tpost.batch.batchElctAddrInfo.controller;

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

import tpost.batch.batchElctAddrInfo.service.batElctAddrInfoService;
import tpost.batch.batchElctAddrInfo.vo.batElctAddrInfoVO;
import tpost.commCode.controller.commCdMgntController;

@Component
@Controller
@RequestMapping(value = "/batch/batElctAddrInfo")
public class batElctAddrInfoController {

	@Autowired
	batElctAddrInfoService batElctAddrInfoService;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);

	String osName = System.getProperty("os.name");
	
	 /*공인전자주소채번  */
	 @ResponseBody
	 @RequestMapping(value = "/batElctAddrInfo", method = RequestMethod.POST)
	 public Map batElctAddrInfo(Model model , @ModelAttribute batElctAddrInfoVO batElctAddrInfoVO ) {
		 
		 log.debug("batElctAddrInfo");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		
		 /* 공인전자 주소 채번*/
		 batElctAddrInfo(batElctAddrInfoVO);
		                
		 /*test */
		 resultMap.put("result", "Y");
		 resultMap.put("result", "Y");
		 resultMap.put("result", "Y");
		 resultMap.put("result", "Y");
		 resultMap.put("status", "delete");
		 resultMap.put("errorMessage", "");
		 
		 return resultMap;
	 }

//		@Scheduled(fixedRate=1000000)
	 private void batElctAddrInfo(batElctAddrInfoVO batElctAddrInfoVO) {
			// TODO Auto-generated method stub
		 /*서비스 단에서 채번하고 입력하는 부분을 놓아둠*/
		 batElctAddrInfoService.elctAddrInfoInsert(batElctAddrInfoVO);
			
		}
	 
	 
}
	
