package tpost.sttc.controller;

import java.util.Base64;

import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;


import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.commUtil;
import tpost.commUtil.controller.ascCommUtil;
import tpost.common.vo.menuMainMgntVO;
import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.logIn.service.logInService;
import tpost.logIn.vo.logInVO;
import tpost.sttc.service.sttcInfoService;
import tpost.sttc.vo.sttcInfoVO;



@Controller
@RequestMapping(value = "/sttc")
public class sttcInfoController {

	@Autowired
	sttcInfoService sttcInfoService;

	@Value("#{properties['aesKey']}") 
	private String aesKey;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	
	public sttcInfoController() {
	}
	
	
	/*처음화면 호출 */
	@RequestMapping(value = "/sttcInfoInit", method = {RequestMethod.GET, RequestMethod.POST})
	public String sttcInfoInit(Model model , @ModelAttribute sttcInfoVO sttcInfoVO, HttpServletRequest request ) {
		
		log.debug("sttc/sttcInfo");
		
//		log.debug("sttc/sttcInfo getInputIssStDt ==> " + sttcInfoVO.getInputIssStDt() );
//		log.debug("sttc/sttcInfo commUtil.dateToText ==> " + commUtil.dateToText(sttcInfoVO.getInputIssStDt() )) ;
		
//		 sttcInfoVO.setInputIssStDt(commUtil.dateToText(sttcInfoVO.getInputIssStDt()));
//		 sttcInfoVO.setInputIssClosDt(commUtil.dateToText(sttcInfoVO.getInputIssClosDt()));
		
		model.addAttribute("oganCd", sttcInfoVO.getOganCd());
		model.addAttribute("tmpltCd", sttcInfoVO.getTmpltCd());
		model.addAttribute("inputIssStDt", sttcInfoVO.getInputIssStDt());
		model.addAttribute("inputIssClosDt", sttcInfoVO.getInputIssClosDt());
		
		model.addAttribute("resultList", sttcInfoService.sttcInfoSelect(sttcInfoVO));
		model.addAttribute("resultListDetail", sttcInfoService.sttcInfoDetlSelect(sttcInfoVO));
		
		return "sttc/sttcInfo";
	}
	
	/*처음화면 호출 */
	 @RequestMapping(value = "/sttcInfo", method = {RequestMethod.GET, RequestMethod.POST})
	  public String sttcInfo(Model model , @ModelAttribute sttcInfoVO sttcInfoVO, HttpServletRequest request ) {
	    
		 log.debug("sttc/sttcInfo");
		 
		 sttcInfoVO.setInputIssStDt(sttcInfoVO.getInputIssStDt().replace("-", ""));
		 log.debug("sttc/sttcInfo 1-1");
		 sttcInfoVO.setInputIssClosDt(sttcInfoVO.getInputIssClosDt().replace("-", ""));
		 log.debug("sttc/sttcInfo 1- 2");
		 
		 log.debug("sttc/sttcInfo1");
		 model.addAttribute("oganCd", sttcInfoVO.getOganCd());
		 model.addAttribute("tmpltCd", sttcInfoVO.getTmpltCd());
		 model.addAttribute("inputIssStDt",  commUtil.textToDate(sttcInfoVO.getInputIssStDt()) ) ;
		 model.addAttribute("inputIssClosDt", commUtil.textToDate( sttcInfoVO.getInputIssClosDt()) );
		 model.addAttribute("radioChk1", sttcInfoVO.getRadioChk1() );
		  
		 log.debug("sttc/sttcInfo2");
		 model.addAttribute("resultList", sttcInfoService.sttcInfoSelect(sttcInfoVO));
		 
		 log.debug("sttc/sttcInfo3");
		 model.addAttribute("resultListDetail", sttcInfoService.sttcInfoDetlSelect(sttcInfoVO));
		  
	    return "sttc/sttcInfo";
	  }
	 
	 
}
