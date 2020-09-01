package tpost.batch.controller;

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

import tpost.batch.service.oganRcrfMgntService;
import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.commUtil;
import tpost.batch.vo.oganRcrfMgntVO;

@Component
@Controller
@RequestMapping(value = "/batch/oganRcrfMgntController")
public class oganRcrfMgntController {

	@Autowired
	oganRcrfMgntService oganRcrfMgntService;
	
	/* 로그설정 */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	/* 발송된 내역에 대해서 사전문자인 경우  기관별 수신/거부 현황 테이블에
	 * 1. 고객이 직접 수신동의 여부를 거부했을 경우, TB_OGAN_RCVE_RF_MGNT에  사전문자 여부 'Y'로 업데이트
	 * 2. 고객이 수신동의 여부에 거부도 동의도 하지 않았을 경우, TB_OGAN_RCVE_RF_MGNT에 사전문자 여부 'Y'로 인서트, TB_OGAN_RCVE_RF_DTL에 동의여부 '1'로 인서트 */
	@ResponseBody
	@RequestMapping(value="/oganRcrfMgnt")
	public Map oganRcrfMgnt(Model model , @ModelAttribute oganRcrfMgntVO oganRcrfMgntVO) {
		log.debug("oganRcrfMgntController - oganRcrfMgnt");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(oganRcrfMgntVO.getTransDt() == null || oganRcrfMgntVO.getTransDt().equals("")) {
			oganRcrfMgntVO.setTransDt(commUtil.getYesterday());
		}
		
		oganRcrfMgntService.oganRcrfMgnt(oganRcrfMgntVO);
		 
		return resultMap;
	}
}