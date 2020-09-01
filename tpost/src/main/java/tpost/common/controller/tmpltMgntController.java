package tpost.common.controller;

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
import tpost.common.vo.tmpltMgntVO;
import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.common.service.tmpltMgntService;

import com.nhncorp.lucy.security.xss.XssSaxFilter;

@Controller
@RequestMapping(value = "/common")
public class tmpltMgntController {
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	@Autowired
	tmpltMgntService tmpltMgntService;
	
	@RequestMapping(value = "/tmpltMgnt")
	public String tmpltMgntInit(Model model) {
		log.debug("/common/tmpltMgnt - tmpltMgntInit");
		
		tmpltMgntVO tmpltMgntVO = new tmpltMgntVO();
		if(EgovUserDetailsHelper.getAthLev().equals("00")){
			tmpltMgntVO.setInputSndnOgan("");
		}
		else if(EgovUserDetailsHelper.getAthLev().equals("01")) {
			tmpltMgntVO.setInputSndnOgan(EgovUserDetailsHelper.getOganCd());
		}
		tmpltMgntVO.setInputTmpltNm("");
		tmpltMgntVO.setInputTmpltUseYn("");
		
		tmpltMgntSelect(model, tmpltMgntVO);
		
		return "common/tmpltMgnt";
	}
	
	
	//상단 조회 버튼 클릭 시
	@RequestMapping(value = "/tmpltMgntSelect", method = RequestMethod.POST)
	public String tmpltMgntSelect(Model model, @ModelAttribute tmpltMgntVO tmpltMgntVO) {
		log.debug("/common/tmpltMngt - tmpltMgntSelect");
		
		model.addAttribute("inputSndnOgan", tmpltMgntVO.getInputSndnOgan());
		model.addAttribute("inputTmpltNm", tmpltMgntVO.getInputTmpltNm());
		model.addAttribute("inputTmpltUseYn", tmpltMgntVO.getInputTmpltUseYn());
		model.addAttribute("athLev", EgovUserDetailsHelper.getAthLev());
		
		model.addAttribute("tmpltMgntList", tmpltMgntService.tmpltMgntSelect(tmpltMgntVO));
		
		return "common/tmpltMgnt";
	}
	
	
	//템플릿 기본 정보 수정 시
	@ResponseBody
	@RequestMapping(value = "/tmpltInfoUpdate.ajax")
	public Map tmpltInfoUpdate(@ModelAttribute("tmpltMgntVO") tmpltMgntVO tmpltMgntVO) {
		log.debug("/common/tmpltInfoUpdate - tmpltInfoUpdate.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";	
		
		tmpltMgntVO.setAmdr(EgovUserDetailsHelper.getUserId());
		tmpltMgntService.tmpltInfoUpdate(tmpltMgntVO);
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	
	//템플릿 기본 정보 생성 시
	@ResponseBody
	@RequestMapping(value = "/tmpltInfoInsert.ajax")
	public Map tmpltInfoInsert(@ModelAttribute("tmpltMgntVO") tmpltMgntVO tmpltMgntVO) {
		log.debug("/common/tmpltInfoInsert - tmpltInfoInsert.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";	
		
		tmpltMgntVO.setAmdr(EgovUserDetailsHelper.getUserId());
		tmpltMgntVO.setCrtr(EgovUserDetailsHelper.getUserId());
		
		tmpltMgntService.tmpltInfoInsert(tmpltMgntVO);
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	
	//템플릿 기본 정보 셋팅
	@ResponseBody
	@RequestMapping(value = "/tmpltInfoSelect.ajax")
	public Map tmpltInfoSelect(@ModelAttribute("tmpltMgntVO") tmpltMgntVO tmpltMgntVO) {
		log.debug("/common/tmpltInfoSelect - tmpltInfoSelect.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";	
		
		tmpltMgntVO tmpltInfo = tmpltMgntService.tmpltInfoSelect(tmpltMgntVO);
		
		resultMap.put("tmpltInfo", tmpltInfo);
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	
	//템플릿 기본 항목 기관(회사)명 변경 시 템플릿 코드 셋팅
	@ResponseBody
	@RequestMapping(value = "/tmpltCdSet.ajax")
	public Map tmpltCdSet(@ModelAttribute("tmpltMgntVO") tmpltMgntVO tmpltMgntVO) {
		log.debug("/common/tmpltCdSet - tmpltCdSet.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";	
		
		resultMap.put("maxTmpltCd", tmpltMgntService.oganMaxTmpltIdSelect(tmpltMgntVO));
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	//tmpltSchemaPopu(템플릿 스키마 등록/수정) 팝업 오픈
	@ResponseBody
	@RequestMapping(value = "/goTmpltSchemaPopu.ajax")
	public Map goTmpltSchemaPopu(@ModelAttribute("tmpltMgntVO") tmpltMgntVO tmpltMgntVO) {
		log.debug("/common/tmpltSchemaPopu - goTmpltSchemaPopu");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";	
		
		//템플릿 기본 정보 셋팅
		tmpltMgntVO tmpltInfo = tmpltMgntService.tmpltInfoSelect(tmpltMgntVO);
		
		//제목, 필수값 세팅
		resultMap.put("popuTitle", "[" + tmpltInfo.getSndnOgan() + "]" + tmpltInfo.getTmpltNm() 
								 + "(" + (tmpltInfo.getTmpltSchemaYn().equals("N") ? "등록" : "수정")  + ")");
		resultMap.put("schemaYn", tmpltInfo.getTmpltSchemaYn());
		resultMap.put("tmpltCd", tmpltInfo.getTmpltCd());
		resultMap.put("sndnOganCd", tmpltInfo.getSndnOganCd());
		
		//////////////////////////////////////////////////////////////////////////////
		
		//템플릿 스키마가 있을경우 스키마 셋팅
		if(tmpltInfo.getTmpltSchemaYn().equals("Y")) {
			tmpltMgntVO tempVO = new tmpltMgntVO();
			tempVO.setTmpltCd(tmpltInfo.getTmpltCd());
			tempVO.setSndnOganCd(tmpltInfo.getSndnOganCd());
			
			List<tmpltMgntVO> resultVO = tmpltMgntService.tmpltSchemaSelect(tempVO);
			
			resultVO.get(0).setItemAcmsLen(resultVO.get(0).getItemLen());
			for(int i = 1; i < resultVO.size(); i++) {
				int bItemAcmsLen = Integer.parseInt(resultVO.get(i-1).getItemAcmsLen());
				int itemLen = Integer.parseInt(resultVO.get(i).getItemLen());
				
				resultVO.get(i).setItemAcmsLen(String.valueOf(bItemAcmsLen + itemLen));
			}
			
			resultMap.put("tmpltSchemaList", resultVO);
		}

		return resultMap;
	}
	
	
	//스키마 저장 혹은 수정
	@ResponseBody
	@RequestMapping(value = "/tmpltSchemaInsert.ajax")
	public void tmpltSchemaInsert(@ModelAttribute tmpltMgntVO tmpltMgntVO) {
		log.debug("/common/tmpltMngt - tmpltSchemaInsert.ajax");		
		
		tmpltMgntService.tmpltSchemaInsert(tmpltMgntVO);
	}
	
	//goTmpltEdit(템플릿 등록/수정) 페이지 이동
	@RequestMapping(value = "/goTmpltEdit")
	public String goTmpltEdit(Model model , @ModelAttribute tmpltMgntVO tmpltMgntVO) {
		log.debug("/common/goTmpltEdit - goTmpltEdit");

		model.addAttribute("editOganCd", tmpltMgntVO.getEditOganCd());
		model.addAttribute("editTmpltCd", tmpltMgntVO.getEditTmpltCd());
		
		//템플릿 기본 정보 셋팅
		tmpltMgntVO tmpltInfo = tmpltMgntService.tmpltEditSelect(tmpltMgntVO);
		
		model.addAttribute("popuTitle", "[" + tmpltInfo.getSndnOgan() + "]" + tmpltInfo.getTmpltNm() 
		  + " 템플릿 (" + (tmpltInfo.getTmpltCrtYn().equals("N") ? "등록" : "수정")  + ")");
		model.addAttribute("tmpltCrtYn", (tmpltInfo.getTmpltCrtYn().equals("N") ? "저장" : "수정"));
		
		if(tmpltInfo.getTmpltCrtYn().equals("Y"))
		{
			model.addAttribute("editHtml", tmpltInfo.getEditHtml());	
		}
		
		return "common/tmpltEdit";
	}
	
	//goTmpltView(템플릿 미리보기)
	@RequestMapping(value = "/goTmpltView")
	public String goTmpltView(Model model , @ModelAttribute tmpltMgntVO tmpltMgntVO) {
		log.debug("/common/tmpltView - goTmpltView");

		XssSaxFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml", true);
		String clean = commUtil.replaceTag(tmpltMgntVO.getViewHtml());
		String filteredContents = filter.doFilter(clean);
		
		model.addAttribute("viewHtml", filteredContents);
		
		return "common/tmpltView";
	}
	
	//템플릿 저장
	@ResponseBody
	@RequestMapping(value = "/tmpltEditInsert.ajax")
	public Map tmpltEditInsert(@ModelAttribute("tmpltMgntVO") tmpltMgntVO tmpltMgntVO) {
		log.debug("/common/tmpltEditInsert - tmpltEditInsert.ajax");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";	
	
		tmpltMgntService.tmpltEditInsert(tmpltMgntVO);
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
}