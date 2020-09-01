package tpost.elctDoc.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
import tpost.elctDoc.vo.elctDocVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import tpost.elctDoc.service.elctDocService;

@Controller
@RequestMapping(value = "/elctDoc")
public class elctDocController {
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	@Autowired
	elctDocService elctDocService;
	
	//전자문서 송/수신 상세 페이지
	@RequestMapping(value = "/elctDocList", method = RequestMethod.POST)
	public String elctDocListInit(Model model) {
		log.debug("/elctDoc/elctDocList - elctDocListInit :: " + EgovUserDetailsHelper.getAthLev());
		
		elctDocVO elctDocVO = new elctDocVO();
		
		if(EgovUserDetailsHelper.getAthLev().equals("00")){
			elctDocVO.setInputOganCd("");
		}
		else if(EgovUserDetailsHelper.getAthLev().equals("01")){
			elctDocVO.setInputOganCd(EgovUserDetailsHelper.getOganCd());
		}
		
		elctDocVO.setInputSndnCd("");
		elctDocVO.setInputCi("");
		elctDocVO.setInputTmpltCd("");
		elctDocVO.setInputStDt(commUtil.YYYYMMFirstDate());
		elctDocVO.setInputLastDt(commUtil.YYYYMMLastDate());
		
		elctDocListSelect(model, elctDocVO);
		
		return "elctDoc/elctDocList";
	}
	
	//상단 조회 버튼 클릭 시 (페이징 포함)
	@RequestMapping(value = "/elctDocListSelect", method = RequestMethod.POST)
	public String elctDocListSelect(Model model , @ModelAttribute elctDocVO elctDocVO) {
		log.debug("/elctDoc/elctDocListSelect - elctDocListSelect");
		
		model.addAttribute("inputOganCd",elctDocVO.getInputOganCd());
		model.addAttribute("inputSndnCd",elctDocVO.getInputSndnCd());
		model.addAttribute("inputCi",elctDocVO.getInputCi());
		model.addAttribute("inputTmpltCd",elctDocVO.getInputTmpltCd());
		model.addAttribute("inputStDt",elctDocVO.getInputStDt());
		model.addAttribute("inputLastDt",elctDocVO.getInputLastDt());
		model.addAttribute("pageIndex",elctDocVO.getPageIndex());
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(elctDocVO.getPageIndex()); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);  //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		 	
		elctDocVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		elctDocVO.setLastIndex(paginationInfo.getLastRecordIndex());
		elctDocVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			
		if(elctDocVO.getInputStDt().isEmpty()) elctDocVO.setInputStDt("00010101");
		else elctDocVO.setInputStDt(commUtil.dateToText(elctDocVO.getInputStDt()));
		
		if(elctDocVO.getInputLastDt().isEmpty()) elctDocVO.setInputLastDt("99991231");
		else elctDocVO.setInputLastDt(commUtil.dateToText(elctDocVO.getInputLastDt()));
		
		//내역 조회
		model.addAttribute("elctDocList", elctDocService.elctDocListSelect(elctDocVO));
		
		//총건수셋팅
		paginationInfo.setTotalRecordCount ((Integer)elctDocService.elctDocListCountSelect(elctDocVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "elctDoc/elctDocList";
	}
	
	
	//상세페이지 이동 시(페이징 포함)
	@RequestMapping(value = "/elctDocDetlInit")
	public String elctDocDetlInit(Model model , @ModelAttribute elctDocVO elctDocVO) {
		log.debug("/elctDoc/elctDocDetlInit - elctDocDetlInit" + " " + elctDocVO.getInputSendYn() + " " + elctDocVO.getInputCiNum() + " " + elctDocVO.getInputName());

		model.addAttribute("inputSendYn", elctDocVO.getInputSendYn());
		model.addAttribute("inputCiNum", elctDocVO.getInputCiNum());
		model.addAttribute("inputName", elctDocVO.getInputName());
		
		model.addAttribute("mOganCd", elctDocVO.getMOganCd());
		model.addAttribute("mSndnDate", elctDocVO.getMSndnDate());
		model.addAttribute("mTmpltCd", elctDocVO.getMTmpltCd());
		
		model.addAttribute("pOganCd", elctDocVO.getPOganCd());
		model.addAttribute("pSndnCd", elctDocVO.getPSndnCd());
		model.addAttribute("pTmpltCd", elctDocVO.getPTmpltCd());
		model.addAttribute("pStDt", elctDocVO.getPStDt());
		model.addAttribute("pLastDt", elctDocVO.getPLastDt());
		model.addAttribute("pCi", elctDocVO.getPCi());
		
		model.addAttribute("pPgIndex", elctDocVO.getPPgIndex());
		
		//////////////////////////////////////////////////////////////////////////////
		
		elctDocVO tempVO = elctDocVO;
		
		elctDocVO elctDocDetlTitleVO = elctDocService.elctDocDetlTitleSelect(tempVO);
		
		String title = elctDocDetlTitleVO.getSndnDate() + " " 
					 + elctDocDetlTitleVO.getOganNm() + " ("
					 + elctDocDetlTitleVO.getTmpltNm() + ") 송/수신 상세";
		
		model.addAttribute("title", title); //제목 셋팅
		
		//////////////////////////////////////////////////////////////////////////////
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(tempVO.getPageIndex()); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);  //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		 	
		tempVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		tempVO.setLastIndex(paginationInfo.getLastRecordIndex());
		tempVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		if(tempVO.getInputSendYn() == null) tempVO.setInputSendYn("");
		if(tempVO.getInputCiNum() == null) tempVO.setInputCiNum("");
		if(tempVO.getInputName() == null) tempVO.setInputName("");
		
		List<elctDocVO> elctDocDetlList = elctDocService.elctDocDetlListSelect(tempVO);
		
		String osName = System.getProperty("os.name");
		String saveDir = "";
		
		if(osName.toLowerCase().startsWith("window"))  {
	    	saveDir = "http://localhost:8080/tpost/msgRcve/msgRcveGdc?a=" ;
		}else {
			InetAddress local; 
			try { 
				local = InetAddress.getLocalHost(); 
				String ip = local.getHostAddress(); 
				if(ip.equals("10.10.19.64")) {
					saveDir = "http://" + ip + ":8080/tpost/msgRcve/msgRcveGdc?a=";	
				}else if(ip.equals("172.27.30.57")) {
					saveDir = "http://" + ip + ":8088/tpost/msgRcve/msgRcveGdc?a=";	
				}else if(ip.equals("223.62.243.14")) {
					saveDir = "http://172.27.30.57:8088/tpost/msgRcve/msgRcveGdc?a=";	
				}
				
			} catch (UnknownHostException e1) { 
				e1.printStackTrace(); 
			}
		}
		
		for(int i = 0; i < elctDocDetlList.size(); i++) {
			elctDocDetlList.get(i).setSendUrl(saveDir+elctDocDetlList.get(i).getSendUrl()+"&b="+elctDocDetlList.get(i).getHexKey());
		}
		
		//내역 조회
		model.addAttribute("elctDocDetlList", elctDocDetlList);
		
		//총건수셋팅
		paginationInfo.setTotalRecordCount((Integer)elctDocService.elctDocDetlListCountSelect(tempVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		//////////////////////////////////////////////////////////////////////////////
		int allCount = 0;				//총건수
		int succCount = 0; 				//성공건수
		int failCount = 0; 				//실패건수
		int allRdngCount = 0; 			//전체열람건수
		int succAcmdInfoCrtCount = 0; 	//유통정보 생성 성공건수
		int failAcmdInfoCrtCount = 0; 	//유통정보 생성 실패건수
		
		List<elctDocVO> elctDocDetlAllList = elctDocService.elctDocDetlListALLSelect(tempVO);
		
		for(int i = 0; i < elctDocDetlAllList.size(); i++) {
			allCount++;
			
			if(elctDocDetlAllList.get(i).getSendYn().equals("Y")) succCount++;
			else failCount++;
			
			if(elctDocDetlAllList.get(i).getRdngDate() != null && !elctDocDetlAllList.get(i).getRdngDate().equals("")) allRdngCount++;
		}
		
		model.addAttribute("allCount", allCount);
		model.addAttribute("succCount", succCount);
		model.addAttribute("failCount", failCount);
		model.addAttribute("allRdngCount", allRdngCount);
		model.addAttribute("succAcmdInfoCrtCount", succAcmdInfoCrtCount);
		model.addAttribute("failAcmdInfoCrtCount", failAcmdInfoCrtCount);
		
		float succProb = ((float)succCount/(float)allCount) * 100;		//성공률 => 성공건수/전체건수 * 100
		float rdngProb = ((float)allRdngCount/(float)succCount) * 100;	//열람률 => 열람건수/성공건수 * 100
		
		model.addAttribute("succProb", Math.round(succProb*100d)/100d);
		model.addAttribute("rdngProb", Math.round(rdngProb*100d)/100d);
		
		return "elctDoc/elctDocDetl";
	}
	
	//디테일 페이지에서 목록으로 돌아가는 함수
	@RequestMapping(value = "/goElctDocList", method = RequestMethod.POST)
	public String goElctDocList(Model model , @ModelAttribute elctDocVO elctDocVO) {
		log.debug("/elctDoc/goElctDocList - goElctDocList");
		elctDocVO.setInputOganCd(elctDocVO.getPOganCd());
		elctDocVO.setInputSndnCd(elctDocVO.getPSndnCd());
		elctDocVO.setInputCi(elctDocVO.getPCi());
		elctDocVO.setInputTmpltCd(elctDocVO.getPTmpltCd());
		elctDocVO.setInputStDt(commUtil.dateToText(elctDocVO.getPStDt()));
		elctDocVO.setInputLastDt(commUtil.dateToText(elctDocVO.getPLastDt()));
		elctDocVO.setPageIndex(Integer.parseInt(elctDocVO.getPPgIndex()));
			
		model.addAttribute("inputOganCd",elctDocVO.getInputOganCd());
		model.addAttribute("inputSndnCd",elctDocVO.getInputSndnCd());
		model.addAttribute("inputCi",elctDocVO.getInputCi());
		model.addAttribute("inputTmpltCd",elctDocVO.getInputTmpltCd());
		model.addAttribute("inputStDt",commUtil.textToDate(elctDocVO.getInputStDt()));
		model.addAttribute("inputLastDt",commUtil.textToDate(elctDocVO.getInputLastDt()));
		model.addAttribute("pageIndex",elctDocVO.getPageIndex());
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(Integer.parseInt(elctDocVO.getPPgIndex())); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);  //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		 	
		elctDocVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		elctDocVO.setLastIndex(paginationInfo.getLastRecordIndex());
		elctDocVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		//내역 조회
		model.addAttribute("elctDocList", elctDocService.elctDocListSelect(elctDocVO));
		
		//총건수셋팅
		paginationInfo.setTotalRecordCount ((Integer)elctDocService.elctDocListCountSelect(elctDocVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "elctDoc/elctDocList";
	}
	
	
	//elctDocFailPopu(전자문서 송신실패 내역) 팝업 오픈
	@ResponseBody
	@RequestMapping(value = "/goElctDocFailPopu.ajax")
	public Map goElctDocFailPopu(@ModelAttribute("elctDocVO") elctDocVO elctDocVO) {
		log.debug("/elctDoc/goElctDocFailPopu - goElctDocFailPopu.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		List<elctDocVO> elctDocDetlFailList = elctDocService.elctDocFailListSelect(elctDocVO);
		resultMap.put("elctDocDetlFailList", elctDocDetlFailList);
		
		int allCount = 0;
		for(int i = 0; i < elctDocDetlFailList.size(); i++) {
			allCount += Integer.parseInt(elctDocDetlFailList.get(i).getPopuSendFailCount());
		}
		
		resultMap.put("allCount", allCount);
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	//acmdCerfFailPopu(유통증명 송신실패 내역) 팝업 오픈
	@ResponseBody
	@RequestMapping(value = "/goAcmdCerfFailPopu.ajax")
	public Map goAcmdCerfFailPopu(@ModelAttribute("elctDocVO") elctDocVO elctDocVO) {
		log.debug("/elctDoc/goAcmdCerfFailPopu - goAcmdCerfFailPopu.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		elctDocVO tempVO = elctDocVO;
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
}