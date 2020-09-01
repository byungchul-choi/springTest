package tpost.suppCenter.controller;

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

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import tpost.commUtil.commUtil;
import tpost.suppCenter.service.faqService;
import tpost.suppCenter.vo.faqVO;


@Controller
@RequestMapping(value = "/suppCenter")
public class faqController {

	@Autowired
	faqService faqService;

	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(faqController.class);
	
	
	/*처음화면 호출 */
	@RequestMapping(value = "/faqMgnt", method = RequestMethod.POST)
	public String faqMgntInit(Model model) {
		log.debug("/suppCenter/faqMgnt - faqMgntInit");
		
		faqVO faqVO = new faqVO();
		faqVO.setInputFaqCd("");
		faqVO.setInputWrtgStDt(commUtil.YYYYMMFirstDate());
		faqVO.setInputWrtgClosDt(commUtil.YYYYMMLastDate());
		faqVO.setInputWrtgTtl("");
		
		faqListSelect(model, faqVO);
		
		return "suppCenter/faqMgnt";
	} 
	
	//상단 조회 버튼 클릭 시
	@RequestMapping(value = "/faqListSelect", method = RequestMethod.POST)
	public String faqListSelect(Model model, @ModelAttribute faqVO faqVO) {
		log.debug("/suppCenter/faqMgnt - faqListSelect");

		model.addAttribute("inputFaqCd", faqVO.getInputFaqCd());
		model.addAttribute("inputWrtgStDt", faqVO.getInputWrtgStDt());
		model.addAttribute("inputWrtgClosDt", faqVO.getInputWrtgClosDt());
		model.addAttribute("inputWrtgTtl", faqVO.getInputWrtgTtl());
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(faqVO.getPageIndex()); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(5);  //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		 	
		faqVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		faqVO.setLastIndex(paginationInfo.getLastRecordIndex());
		faqVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		faqVO.setInputWrtgStDt(commUtil.dateToText(faqVO.getInputWrtgStDt()));
		faqVO.setInputWrtgClosDt(commUtil.dateToText(faqVO.getInputWrtgClosDt()));
		
		//내역조회
		List<faqVO> faqList = faqService.faqListSelect(faqVO);
		model.addAttribute("faqList", faqList);
		
		//총건수셋잍
		paginationInfo.setTotalRecordCount ((Integer)faqService.faqListCountSelect(faqVO));
		model.addAttribute("paginationInfo", paginationInfo);
			
		return "suppCenter/faqMgnt";
	}
	
	//faq 글 상세
	@ResponseBody
	@RequestMapping(value = "/faqDetlSelect.ajax")
	public Map faqDetlSelect(@ModelAttribute faqVO faqVO) {
		log.debug("/suppCenter/faqMgnt - faqDetlSelect.ajax");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		faqVO faqDetlVO = faqService.faqDetlSelect(faqVO);

		resultMap.put("faqCd", faqDetlVO.getFaqCd());
		resultMap.put("wrtgTtl", faqDetlVO.getWrtgTtl());
		resultMap.put("wrtgCnts", faqDetlVO.getWrtgCnts());
		
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	
	//faq 글 등록
	@ResponseBody
	@RequestMapping(value = "/faqInsert.ajax")
	public Map faqInsert(@ModelAttribute faqVO faqVO) {
		log.debug("/suppCenter/faqMgnt - faqInsert.ajax");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		faqService.faqInsert(faqVO);

		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	
	//faq 글 삭제 => 사용여부만 N으로 변경
	@ResponseBody
	@RequestMapping(value = "/faqUpdate.ajax")
	public Map faqUpdate(@ModelAttribute faqVO faqVO) {
		log.debug("/suppCenter/faqMgnt - faqUpdate.ajax");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		faqService.faqUpdate(faqVO);

		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
}
