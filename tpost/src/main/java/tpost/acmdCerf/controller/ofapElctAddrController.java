package tpost.acmdCerf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.commUtil;
import tpost.egovcomm.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import tpost.acmdCerf.service.ofapElctAddrService;
import tpost.acmdCerf.vo.ofapElctAddrVO;

@Controller
@RequestMapping(value = "/acmdCerf")
public class ofapElctAddrController {
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	@Autowired
	ofapElctAddrService ofapElctAddrService;
	
	@RequestMapping(value = "/ofapElctAddrMgnt", method = RequestMethod.POST)
	public String ofapElctAddrMgntInit(Model model) {
		log.debug("/acmdCerf/ofapElctAddrMgnt - ofapElctAddrMgntInit");
		
		ofapElctAddrVO ofapElctAddrVO = new ofapElctAddrVO();
		ofapElctAddrVO.setInputSttsStDt(commUtil.YYYYMMFirstDate());
		ofapElctAddrVO.setInputSttsClosDt(commUtil.YYYYMMLastDate());
		ofapElctAddrVO.setInputBsrpCls("");
		ofapElctAddrVO.setInputSttsClcd("");
		ofapElctAddrVO.setInputName("");
		ofapElctAddrVO.setInputOfapElctAddr("");
		ofapElctAddrVO.setInputCiNum("");
		
		ofapElctAddrListSelect(model, ofapElctAddrVO);
		
		return "acmdCerf/ofapElctAddrMgnt";
	}
	
	//상단 조회 버튼 클릭 시 (페이징 포함)
	@RequestMapping(value = "/ofapElctAddrListSelect", method = RequestMethod.POST)
	public String ofapElctAddrListSelect(Model model , @ModelAttribute ofapElctAddrVO ofapElctAddrVO) {
		log.debug("/acmdCerf/ofapElctAddrListSelect - ofapElctAddrListSelect");
		
		model.addAttribute("inputSttsStDt",ofapElctAddrVO.getInputSttsStDt());
		model.addAttribute("inputSttsClosDt",ofapElctAddrVO.getInputSttsClosDt());
		model.addAttribute("inputBsrpCls",ofapElctAddrVO.getInputBsrpCls());
		model.addAttribute("inputName",ofapElctAddrVO.getInputName());
		model.addAttribute("inputOfapElctAddr",ofapElctAddrVO.getInputOfapElctAddr());
		model.addAttribute("inputCiNum",ofapElctAddrVO.getInputCiNum());
		model.addAttribute("inputRegnYn",ofapElctAddrVO.getInputSttsClcd());		
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(ofapElctAddrVO.getPageIndex()); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);  //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		 	
		ofapElctAddrVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		ofapElctAddrVO.setLastIndex(paginationInfo.getLastRecordIndex());
		ofapElctAddrVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		ofapElctAddrVO.setInputSttsStDt(commUtil.dateToText(ofapElctAddrVO.getInputSttsStDt()));
		ofapElctAddrVO.setInputSttsClosDt(commUtil.dateToText(ofapElctAddrVO.getInputSttsClosDt()));	
		
		List<ofapElctAddrVO> ofapElctAddrList = ofapElctAddrService.ofapElctAddrListSelect(ofapElctAddrVO);
		
		//내역 조회
		model.addAttribute("ofapElctAddrList", ofapElctAddrList);
		
		//총건수셋팅
		paginationInfo.setTotalRecordCount ((Integer)ofapElctAddrService.ofapElctAddrListCountSelect(ofapElctAddrVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "acmdCerf/ofapElctAddrMgnt";
	}
	
	//공인전자주소 해지 업데이트
	@ResponseBody
	@RequestMapping(value = "/ofapElctAddrSece.ajax")
	public Map ofapElctAddrSece(@ModelAttribute("ofapElctAddrVO") ofapElctAddrVO ofapElctAddrVO) {
		log.debug("/acmdCerf/ofapElctAddrClacUpdate - ofapElctAddrSece");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";	
			
		ofapElctAddrService.ofapElctAddrSece(ofapElctAddrVO);
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	//공인전자주소 추가
	@ResponseBody
	@RequestMapping(value = "/ofapElctAddrListInsert.ajax")
	public Map ofapElctAddrListInsert(@ModelAttribute("ofapElctAddrVO") ofapElctAddrVO ofapElctAddrVO) {
		log.debug("/acmdCerf/ofapElctAddrListInsert - ofapElctAddrListInsert.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";

		String[] popuChk = ofapElctAddrVO.getPopuChk().split(",");
		String[] popuCiNum = ofapElctAddrVO.getPopuCiNum().split(",");
		String popuBsrpCls = ofapElctAddrVO.getPopuBsrpCls();
		
		String userId = EgovUserDetailsHelper.getUserId();
		
		for(int i = 0; i < popuChk.length; i++) {
			if(popuChk[i].equals("true") && popuBsrpCls.equals("100")) {
				if(popuCiNum[i].getBytes().length != 88) {
					errorMessage = "연계정보(CI) 값은 유효하지 않은 값입니다. 다시 입력해주세요.";
					
					resultMap.put("errorCount", i+1);
					resultMap.put("errorMessage", errorMessage);
					
					return resultMap;
				}
			}
		}
		
		ofapElctAddrService.ofapElctAddrListInsert(ofapElctAddrVO);
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/ofapElctAddrHistSelect.ajax")
	public Map ofapElctAddrHistSelect(@ModelAttribute("ofapElctAddrVO") ofapElctAddrVO ofapElctAddrVO) {
		log.debug("/acmdCerf/ofapElctAddrHistSelect - ofapElctAddrHistSelect.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		ofapElctAddrVO.setInputOfapElctAddr(commUtil.StringReplace(ofapElctAddrVO.getInputOfapElctAddr()));
		List<ofapElctAddrVO> histList = ofapElctAddrService.ofapElctAddrHistListSelect(ofapElctAddrVO);
		
		resultMap.put("histList", histList);
		
		return resultMap;
	}
	
	//공인전자주소 추가 전 체크
	@ResponseBody
	@RequestMapping(value = "/ofapElctAddrListCheck.ajax")
	public Map ofapElctAddrListCheck(@ModelAttribute("ofapElctAddrVO") ofapElctAddrVO ofapElctAddrVO) {
		log.debug("/acmdCerf/ofapElctAddrListCheck - ofapElctAddrListCheck.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		String result = ofapElctAddrService.ofapElctAddrListCheck(ofapElctAddrVO);
		
		resultMap.put("result", result);
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
}