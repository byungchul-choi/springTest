package tpost.sttc.controller;

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

import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.commUtil;
import tpost.sttc.service.sttcBfrInfoService;
import tpost.sttc.vo.sttcInfoVO;

@Controller
@RequestMapping(value = "/sttc")
public class sttcBfrInfoController {

	@Autowired
	sttcBfrInfoService sttcBfrInfoService;

	@Value("#{properties['aesKey']}") 
	private String aesKey;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	
	public sttcBfrInfoController() {
	}
	
	
	/*처음화면 호출 */
	@RequestMapping(value = "/sttcBfrInfoInit", method = {RequestMethod.GET, RequestMethod.POST})
	public String sttcBfrInfoInit(Model model , @ModelAttribute sttcInfoVO sttcInfoVO, HttpServletRequest request ) {
		
		log.debug("sttc/sttcBfrInfoInit");
		
//		log.debug("sttc/sttcInfo getInputIssStDt ==> " + sttcInfoVO.getInputIssStDt() );
//		log.debug("sttc/sttcInfo commUtil.dateToText ==> " + commUtil.dateToText(sttcInfoVO.getInputIssStDt() )) ;
		
//		 sttcInfoVO.setInputIssStDt(commUtil.dateToText(sttcInfoVO.getInputIssStDt()));
//		 sttcInfoVO.setInputIssClosDt(commUtil.dateToText(sttcInfoVO.getInputIssClosDt()));
		
//		model.addAttribute("oganCd", sttcInfoVO.getOganCd());
//		model.addAttribute("tmpltCd", sttcInfoVO.getTmpltCd());
//		model.addAttribute("inputIssStDt", sttcInfoVO.getInputIssStDt());
//		model.addAttribute("inputIssClosDt", sttcInfoVO.getInputIssClosDt());
//		
//		model.addAttribute("resultList", sttcInfoService.sttcInfoSelect(sttcInfoVO));
//		model.addAttribute("resultListDetail", sttcInfoService.sttcInfoDetlSelect(sttcInfoVO));
		
		return "sttc/sttcBfrInfo";
	}
	
	/*처음화면 호출 */
	 @RequestMapping(value = "/sttcBfrInfo", method = {RequestMethod.GET, RequestMethod.POST})
	  public String sttcBfrInfo(Model model , @ModelAttribute sttcInfoVO sttcInfoVO, HttpServletRequest request ) {
	    
		 log.debug("sttc/sttcBfrInfo");
		 
		 sttcInfoVO.setInputIssStDt(sttcInfoVO.getInputIssStDt().replace("-", ""));
		 sttcInfoVO.setInputIssClosDt(sttcInfoVO.getInputIssClosDt().replace("-", ""));
		 
		 
		 model.addAttribute("oganCd", sttcInfoVO.getOganCd());
		 model.addAttribute("tmpltCd", sttcInfoVO.getTmpltCd());
		 model.addAttribute("inputIssStDt",  commUtil.textToDate(sttcInfoVO.getInputIssStDt()) ) ;
		 model.addAttribute("inputIssClosDt", commUtil.textToDate( sttcInfoVO.getInputIssClosDt()) );
		 model.addAttribute("radioChk1", sttcInfoVO.getRadioChk1() );
		  
		 model.addAttribute("resultList", sttcBfrInfoService.sttcBfrInfoSelect(sttcInfoVO));
		 
		 model.addAttribute("resultListDetail", sttcBfrInfoService.sttcBfrInfoDetlSelect(sttcInfoVO));
		  
	    return "sttc/sttcBfrInfo";
	  }
	 
	 
}
