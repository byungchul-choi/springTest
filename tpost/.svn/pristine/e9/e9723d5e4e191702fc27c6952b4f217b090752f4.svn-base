package tpost.commUtil.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.vo.commUtilVO;
import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.commUtil.service.commUtilService;

@Controller
@RequestMapping(value = "commUtil")
public class commUtilController{
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	@Autowired
	commUtilService commUtilService;
	
	/*
	 * getBascdList.ajax
	 * cfcd(분류코드) 넘기면 해당 분류코드의 기본코드 리스트를 가져오는 함수
	 * param : cfcd (분류코드)
	 * return : bascdList.bascd (기본코드)
	 * return : bascdList.bascdNm (기본코드 명)
	 */
	@ResponseBody
	@RequestMapping(value = "/getBascdList.ajax")
	public Map getBascdList(@ModelAttribute("commUtilVO") commUtilVO commUtilVO) {
		log.debug("/commUtil/getBascdList - getBascdList.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		if(commUtilVO.getCfcd().equals("00001")) {
			if(EgovUserDetailsHelper.getAthLev().equals("00")){
				resultMap.put("bascdList", commUtilService.getBascdList(commUtilVO));
			}
			else if(EgovUserDetailsHelper.getAthLev().equals("01")) {
				List<commUtilVO> tempVOList = new ArrayList();
				commUtilVO tempVO = new commUtilVO();

				tempVO.setCfcd("00001");
				tempVO.setBascd(EgovUserDetailsHelper.getOganCd());

				tempVO = commUtilService.getBascdNmOne(tempVO);
				tempVO.setBascd(EgovUserDetailsHelper.getOganCd());

				tempVOList.add(tempVO);

				resultMap.put("bascdList", tempVOList);
			}
		} else {
			resultMap.put("bascdList", commUtilService.getBascdList(commUtilVO));		
		}
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	/*
	 * getDtcdList.ajax
	 * cfcd(분류코드), bascd(기본코드) 넘기면 해당 분류코드의 기본코드의 세부코드 리스트를 가져오는 함수
	 * param : cfcd (분류코드)
	 * param : bascd (기본코드)
	 * return : dtcdList.dtcd (세부코드)
	 * return : dtcdList.dtcdNm (세부코드 명)
	 */
	@ResponseBody
	@RequestMapping(value = "/getDtcdList.ajax")
	public Map getDtcdList(@ModelAttribute("commUtilVO") commUtilVO commUtilVO) {
		log.debug("/commUtil/getDtcdList - getDtcdList.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		resultMap.put("dtcdList", commUtilService.getDtcdList(commUtilVO));
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	/*
	 * getBascdNmOne.ajax
	 * cfcd(분류코드), bascd(기본코드) 넘기면 특정 기본코드의 이름을 가져오는 함수
	 * param : cfcd (분류코드)
	 * param : bascd (기본코드)
	 * return : bascdNm (기본코드 명)
	 */
	@ResponseBody
	@RequestMapping(value = "/getBascdNmOne.ajax")
	public Map getBascdNmOne(@ModelAttribute("commUtilVO") commUtilVO commUtilVO) {
		log.debug("/commUtil/getBascdNmOne - getBascdNmOne.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		resultMap.put("bascdNm", commUtilService.getBascdNmOne(commUtilVO));
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	/*
	 * getDtcdNmOne.ajax
	 * cfcd(분류코드), bascd(기본코드), dtcd(세부코드) 넘기면 특정 세부코드의 이름을 가져오는 함수
	 * param : cfcd (분류코드)
	 * param : bascd (기본코드)
	 * param : dtcd (세부코드)
	 * return : dtcdNm (세부코드 명)
	 */
	@ResponseBody
	@RequestMapping(value = "/getDtcdNmOne.ajax")
	public Map getDtcdNmOne(@ModelAttribute("commUtilVO") commUtilVO commUtilVO) {
		log.debug("/commUtil/getDtcdNmOne - getDtcdNmOne.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		resultMap.put("dtcdNm", commUtilService.getDtcdNmOne(commUtilVO));
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	
	/*
	 * getOganTmpltList.ajax
	 * oganCd(기관코드) 넘기면 특정 세부코드의 이름을 가져오는 함수
	 * param : oganCd (기관코드)
	 * return : tmpltList.tmpltCd (템플릿 코드)
	 * return : tmpltList.tmpltNm (템플릿 명)
	 */
	@ResponseBody
	@RequestMapping(value = "/getOganTmpltList.ajax")
	public Map getOganTmpltList(@ModelAttribute("commUtilVO") commUtilVO commUtilVO) {
		log.debug("/commUtil/getOganTmpltList - getOganTmpltList.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		resultMap.put("tmpltList", commUtilService.getOganTmpltList(commUtilVO));
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
}