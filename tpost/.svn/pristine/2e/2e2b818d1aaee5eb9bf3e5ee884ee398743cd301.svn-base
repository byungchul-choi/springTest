package tpost.restComu.restSuppCenter.controller;

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
import tpost.restComu.restSuppCenter.service.restNoticeService;
import tpost.restComu.restSuppCenter.vo.restNoticeVO;

@Controller
@RequestMapping(value = "/restComu/restSuppCenter")
public class restNoticeController {

	@Autowired
	restNoticeService restNoticeService;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(restNoticeController.class);
	
	/* 처음 화면 호출 */
	@ResponseBody
	@RequestMapping(value = "/noticeList", method = {RequestMethod.POST, RequestMethod.GET})
	public Map noticeListInit(Model model, @ModelAttribute restNoticeVO restNoticeVO) {
		log.debug("/restComu/restSuppCenter/noticeList - noticeListInit");
		
		return noticeListSelect(model, restNoticeVO);
	}
	
	/* 상단 조회 버튼 클릭 시 */
	@ResponseBody
	@RequestMapping(value = "/noticeListSelect", method = {RequestMethod.POST, RequestMethod.GET})
	public Map noticeListSelect(Model model, @ModelAttribute restNoticeVO restNoticeVO) {
		log.debug("/restComu/restSuppCenter/noticeList - noticeListSelect");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("inputOganCd", restNoticeVO.getInputOganCd());
		resultMap.put("inputWrtgStDt", restNoticeVO.getInputWrtgStDt());
		resultMap.put("inputWrtgClosDt", restNoticeVO.getInputWrtgClosDt());
		resultMap.put("inputWrtgTtl", restNoticeVO.getInputWrtgTtl());
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(restNoticeVO.getPageIndex()); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);  //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		 	
		restNoticeVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		restNoticeVO.setLastIndex(paginationInfo.getLastRecordIndex());
		restNoticeVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		restNoticeVO.setInputWrtgStDt(commUtil.dateToText(restNoticeVO.getInputWrtgStDt()));
		restNoticeVO.setInputWrtgClosDt(commUtil.dateToText(restNoticeVO.getInputWrtgClosDt()));
		
		//내역조회
		List<restNoticeVO> noticeList = restNoticeService.noticeListSelect(restNoticeVO);
		
		//총건수셋팅
		Integer cnt = (Integer)restNoticeService.noticeListCountSelect(restNoticeVO);
		
		resultMap.put("pagingCount", cnt);
		
		for(int i = 0; i < noticeList.size(); i++) {
			Integer seq = cnt + 1 - Integer.parseInt(noticeList.get(i).getSeq());
			noticeList.get(i).setSeq(seq.toString());
		}
		
		resultMap.put("noticeList", noticeList);
		
		return resultMap;
	}
	
	/*
	//상세페이지로
	@RequestMapping(value = "/goNoticeDetl", method = RequestMethod.POST)
	public String goNoticeDetl(Model model, @ModelAttribute restNoticeVO restNoticeVO) {		
		log.debug("/suppCenter/noticeDetl - goNoticeDetl");
		
		model.addAttribute("pOganCd", restNoticeVO.getPOganCd());
		model.addAttribute("pStDt", restNoticeVO.getPStDt());
		model.addAttribute("pClosDt", restNoticeVO.getPClosDt());
		model.addAttribute("pWrtgTtl", restNoticeVO.getPWrtgTtl());
		model.addAttribute("wrtgNum", restNoticeVO.getWrtgNum());
		
		model.addAttribute("noticeDetl", restNoticeService.noticeDetlSelect(restNoticeVO));
		
		return "suppCenter/noticeDtl";
	}
	
	//상세페이지에서 목록으로 돌아가는 함수
	@RequestMapping(value="/goNoticeList", method = RequestMethod.POST)
	public String goNoticeList(Model model, @ModelAttribute restNoticeVO restNoticeVO) {
		log.debug("/suppCenter/noticeList - goNoticeList");
		
		restNoticeVO.setInputOganCd(restNoticeVO.getPOganCd());
		restNoticeVO.setInputWrtgStDt(restNoticeVO.getPStDt());
		restNoticeVO.setInputWrtgClosDt(restNoticeVO.getPClosDt());
		restNoticeVO.setInputWrtgTtl(restNoticeVO.getPWrtgTtl());
		
		noticeListSelect(model, restNoticeVO);
		
		return "suppCenter/noticeList";
	}	
	*/
}



