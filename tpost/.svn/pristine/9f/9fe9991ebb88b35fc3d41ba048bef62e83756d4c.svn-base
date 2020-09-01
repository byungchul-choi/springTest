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
import tpost.suppCenter.service.noticeService;
import tpost.suppCenter.vo.noticeVO;

@Controller
@RequestMapping(value = "/suppCenter")
public class noticeController {

	@Autowired
	noticeService noticeService;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(noticeController.class);
	
	@RequestMapping(value = "/noticeMgnt", method = RequestMethod.POST)
	public String noticeMgntInit(Model model) {		
		log.debug("/suppCenter/noticeMgnt - noticeMgntInit");
		
		noticeVO noticeVO = new noticeVO();
		noticeVO.setInputOganCd("");
		noticeVO.setInputWrtgStDt(commUtil.YYYYMMFirstDate());
		noticeVO.setInputWrtgClosDt(commUtil.YYYYMMLastDate());
		noticeVO.setInputWrtgTtl("");
		
		noticeListSelect(model, noticeVO);
		
		return "suppCenter/noticeMgnt";
	}
	
	//상단 조회 버튼 클릭 시
	@RequestMapping(value = "/noticeListSelect", method = RequestMethod.POST)
	public String noticeListSelect(Model model, @ModelAttribute noticeVO noticeVO) {
		log.debug("/suppCenter/noticeMgnt - noticeListSelect");
		
		model.addAttribute("inputOganCd", noticeVO.getInputOganCd());
		model.addAttribute("inputWrtgStDt", noticeVO.getInputWrtgStDt());
		model.addAttribute("inputWrtgClosDt", noticeVO.getInputWrtgClosDt());
		model.addAttribute("inputWrtgTtl", noticeVO.getInputWrtgTtl());
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(noticeVO.getPageIndex()); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(5);  //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		 	
		noticeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		noticeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		noticeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		noticeVO.setInputWrtgStDt(commUtil.dateToText(noticeVO.getInputWrtgStDt()));
		noticeVO.setInputWrtgClosDt(commUtil.dateToText(noticeVO.getInputWrtgClosDt()));
		
		//내역조회
		List<noticeVO> noticeList = noticeService.noticeListSelect(noticeVO);
		model.addAttribute("noticeList", noticeList);
		
		//총건수셋잍
		paginationInfo.setTotalRecordCount ((Integer)noticeService.noticeListCountSelect(noticeVO));
		model.addAttribute("paginationInfo", paginationInfo);
			
		return "suppCenter/noticeMgnt";
	}
	
	//공지사항 글 상세
	@ResponseBody
	@RequestMapping(value = "/noticeDetlSelect.ajax")
	public Map noticeDetlSelect(@ModelAttribute noticeVO noticeVO) {
		log.debug("/suppCenter/noticeMgnt - noticeDetlSelect.ajax");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		noticeVO noticeDetlVO = noticeService.noticeDetlSelect(noticeVO);

		resultMap.put("oganCd", noticeDetlVO.getOganCd());
		resultMap.put("wrtgTtl", noticeDetlVO.getWrtgTtl());
		resultMap.put("wrtgCnts", noticeDetlVO.getWrtgCnts());
		
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	
	//공지사항 글 등록
	@ResponseBody
	@RequestMapping(value = "/noticeInsert.ajax")
	public Map noticeInsert(@ModelAttribute noticeVO noticeVO) {
		log.debug("/suppCenter/noticeMgnt - noticeInsert.ajax");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		noticeService.noticeInsert(noticeVO);

		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	
	//공지사항 글 삭제 => 사용여부만 N으로 변경
	@ResponseBody
	@RequestMapping(value = "/noticeUpdate.ajax")
	public Map noticeUpdate(@ModelAttribute noticeVO noticeVO) {
		log.debug("/suppCenter/noticeMgnt - noticeUpdate.ajax");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String errorMessage = "";
		
		noticeService.noticeUpdate(noticeVO);

		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
}
