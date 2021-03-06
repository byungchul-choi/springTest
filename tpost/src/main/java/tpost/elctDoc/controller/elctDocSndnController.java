package tpost.elctDoc.controller;

import java.io.File;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nhncorp.lucy.security.xss.XssSaxFilter;

import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.commUtil;
import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.elctDoc.vo.elctDocSndnVO;
import tpost.elctDoc.vo.msgTmpltVO;
import tpost.elctDoc.service.elctDocSndnService;
import tpost.elctDoc.service.msgTmpltService;

@Controller
@RequestMapping(value = "/elctDoc")
public class elctDocSndnController {
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	@Autowired
	elctDocSndnService elctDocSndnService;
	
	@Autowired
	msgTmpltService msgTmpltService;
	
	//전자문서 발송 페이지
	@RequestMapping(value = "/elctDocSndn", method = RequestMethod.POST)
	public String elctDocSndnInit(Model model) {
		log.debug("/elctDoc/elctDocSndn - elctDocSndnInit");
		
		elctDocSndnVO elctDocSndnVO = new elctDocSndnVO();
		
		if(EgovUserDetailsHelper.getAthLev().equals("00")){
			elctDocSndnVO.setIOganCd("");
		}
		else if(EgovUserDetailsHelper.getAthLev().equals("01")){
			elctDocSndnVO.setIOganCd(EgovUserDetailsHelper.getOganCd());
		}
		
		elctDocSndnVO.setISndnCd("");
		elctDocSndnVO.setIMsgTitle("");
		elctDocSndnVO.setITmpltCd("");
		elctDocSndnVO.setISndnStDt(commUtil.YYYYMMFirstDate());
		elctDocSndnVO.setISndnClosDt(commUtil.YYYYMMLastDate());
		
		elctDocSndnListSelect(model, elctDocSndnVO);
		
		return "elctDoc/elctDocSndn";
	}
	
	//상단 조회 버튼 클릭 시 
	@RequestMapping(value = "/elctDocSndnListSelect", method = RequestMethod.POST)
	public String elctDocSndnListSelect(Model model , @ModelAttribute elctDocSndnVO elctDocSndnVO) {
		log.debug("/elctDoc/elctDocSndn - elctDocSndnListSelect");
		
		model.addAttribute("iOganCd", elctDocSndnVO.getIOganCd());
		model.addAttribute("iSndnCd", elctDocSndnVO.getISndnCd());
		model.addAttribute("iTmpltCd", elctDocSndnVO.getITmpltCd());
		model.addAttribute("iSndnStDt", elctDocSndnVO.getISndnStDt());
		model.addAttribute("iSndnClosDt", elctDocSndnVO.getISndnClosDt());
		model.addAttribute("iMsgTitle", elctDocSndnVO.getIMsgTitle());
		model.addAttribute("athLev", EgovUserDetailsHelper.getAthLev());
		
		elctDocSndnVO.setISndnStDt(commUtil.dateToText(elctDocSndnVO.getISndnStDt()));
		elctDocSndnVO.setISndnClosDt(commUtil.dateToText(elctDocSndnVO.getISndnClosDt()));
		
		List<elctDocSndnVO> elctDocSndnList = elctDocSndnService.elctDocSndnListSelect(elctDocSndnVO);
		
		for(int i = 0; i < elctDocSndnList.size(); i++) {
			if(elctDocSndnList.get(i).getAprYn().equals("Y")) {
				if(Integer.parseInt(elctDocSndnList.get(i).getAllStts()) == 1)
					elctDocSndnList.get(i).setAprYn("D");

				if(Integer.parseInt(elctDocSndnList.get(i).getAllStts()) == 2)
					elctDocSndnList.get(i).setAprYn("F");
			}
		}
		
		model.addAttribute("elctDocSndnlist", elctDocSndnList);
		
		return "elctDoc/elctDocSndn";
	}
	
	//승인미승인여부 업데이트
	@ResponseBody
	@RequestMapping(value = "/elctDocSndnAprYnUpdate.ajax")
	public Map elctDocSndnAprYnUpdate(@ModelAttribute("elctDocSndnVO") elctDocSndnVO elctDocSndnVO) {
		log.debug("/elctDoc/elctDocSndn - elctDocSndnAprYnUpdate");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		elctDocSndnVO.setTransDt(commUtil.dateToText(elctDocSndnVO.getTransDt()));
		elctDocSndnVO.setAmdr(EgovUserDetailsHelper.getUserId());	//수정자 셋팅
		elctDocSndnVO.setCrtr(EgovUserDetailsHelper.getUserId());	//생성자 셋팅
		
		elctDocSndnService.elctDocSndnAprYnUpdate(elctDocSndnVO);
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	//전자문서 발송 상세내역 가져옴
	@ResponseBody
	@RequestMapping(value = "/elctDocSndnDetlSelect.ajax")
	public Map elctDocSndnDetlSelect(@ModelAttribute("elctDocSndnVO") elctDocSndnVO elctDocSndnVO) {
		log.debug("/elctDoc/elctDocSndn - elctDocSndnDetlSelect.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		elctDocSndnVO.setTransDt(commUtil.dateToText(elctDocSndnVO.getTransDt()));
		elctDocSndnVO resultVO = elctDocSndnService.elctDocSndnDetlSelect(elctDocSndnVO);
		String dtm = resultVO.getSndnDtm();	
		resultVO.setSndnD(commUtil.textToDate(dtm.substring(0, 8)));
		resultVO.setSndnH(dtm.substring(8, 10));
		resultVO.setSndnM(dtm.substring(10, 12));
		
		String temp = resultVO.getMmsImg();
		temp = temp.replace("C:\\image\\file\\", "");
		resultVO.setMmsImg(temp);
		
		resultMap.put("sndnDetl", resultVO);
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	
	//전자문서 발송내역 추가
	@ResponseBody
	@RequestMapping(value = "/elctDocSndnInsert.ajax")
	public Map elctDocSndnInsert(MultipartHttpServletRequest request, @ModelAttribute("elctDocSndnVO") elctDocSndnVO elctDocSndnVO) {
		log.debug("/elctDoc/elctDocSndn - elctDocSndnInsert");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		elctDocSndnVO.setAmdr(EgovUserDetailsHelper.getUserId());	//수정자 셋팅
		elctDocSndnVO.setCrtr(EgovUserDetailsHelper.getUserId());	//생성자 셋팅
		
		String sndnDtm = commUtil.dateToText(elctDocSndnVO.getSndnD()) + elctDocSndnVO.getSndnH() + elctDocSndnVO.getSndnM() + "00";
		elctDocSndnVO.setSndnDtm(sndnDtm);
		
		if(elctDocSndnService.elctDocSndnDupCheck(elctDocSndnVO) > 0) {
			errorMessage = "이미 저장되어있는 데이터입니다. 기존 내역을 수정해주세요.";
		}else {
			/*파일저장 메소트 추가*/
			if(!elctDocSndnVO.getImgSize().equals("0")) {
				String fileNm = elctFileUpload(request, elctDocSndnVO.getOganCd());
				 
				String osName = System.getProperty("os.name");
				if(osName.toLowerCase().startsWith("window"))  {
					fileNm = fileNm.substring(14) ;
				}else {
					fileNm = fileNm.substring(22);
				}
				 
				if(!"file_creation_failure".equals(fileNm) && !"file_format_not_supported".equals(fileNm)) {
					elctDocSndnVO.setMmsImg(fileNm);
				} else if("file_creation_failure".equals(fileNm)) {
					elctDocSndnVO.setMmsImg("");
					errorMessage = "파일을 저장하지 못했습니다.";
				}else if("file_format_not_supported".equals(fileNm)) {
					elctDocSndnVO.setMmsImg("");
					errorMessage = "지원하는 이미지 파일 형식이 아닙니다.";
				}
			}
			else {
				msgTmpltVO tempVO = new msgTmpltVO();
				tempVO.setOganCd(elctDocSndnVO.getOganCd());
				tempVO.setTmpltCd(elctDocSndnVO.getTmpltCd());
				
				msgTmpltVO resultVO = msgTmpltService.msgTmpltDetlSelect(tempVO);
				
				if(resultVO.getMsgImg() != null || !resultVO.getMsgImg().equals("")) {
					elctDocSndnVO.setMmsImg(resultVO.getMsgImg());
					elctDocSndnVO.setImgSize(resultVO.getMsgImgSize());
				}else {
					elctDocSndnVO.setMmsImg("");
					elctDocSndnVO.setImgSize("0");
				}
			}
			
			if(errorMessage.equals("")) {
				XssSaxFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml", true);
				elctDocSndnVO.setMsgTitle(filter.doFilter(elctDocSndnVO.getMsgTitle()));
				elctDocSndnVO.setMsgText(filter.doFilter(elctDocSndnVO.getMsgText()));
				
				elctDocSndnService.elctDocSndnInsert(elctDocSndnVO);
			}
		}
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	//파일저장
	public String elctFileUpload(MultipartHttpServletRequest request, String oganCd) {
 		log.debug("/fileUpload");
 		
		 String osName = System.getProperty("os.name");
		 String saveDir = "";
     
	    if(osName.toLowerCase().startsWith("window"))  {
	    	saveDir = "C:\\image\\file\\" ;
		}else {
			saveDir = "/nfsdata01/tpost/file/"+ oganCd.toLowerCase()+"/mmsFile/";
		}
	    
	    List<MultipartFile> fileList = request.getFiles("img");
    
	    if(request.getFiles("img").get(0).getSize() != 0){ 
	    	fileList = request.getFiles("img");
	    }
	    
	    String saveFileName = "";
	    String originFileName = "";
	    String extx = "";
	    for (MultipartFile mf : fileList) { 
	    	String[] fn = mf.getOriginalFilename().split("\\.");
	    		
	    	extx = "." + fn[fn.length-1];
	    	if(extx.toLowerCase().equals(".jpg") || extx.toLowerCase().equals(".jpeg") || extx.toLowerCase().equals(".gif") || extx.toLowerCase().equals(".sis"))
	    	{
	    		for(int i = 0; i < fn.length-1; i++) {
		    		originFileName += fn[i]; // 원본 파일 명	
		    	}
		    
		    	/*파일명 확인있는경우 뒤에 1 2 3 붙여줌*/
		    	File f = new File(saveDir+originFileName+extx);
		    	if(f.exists()) {
		    	     for (int i=0; i < 10000 ; i++) {
		    	    	 saveFileName = originFileName +"_"+i+extx;
		    	    	 f = new File(saveDir+saveFileName);
		    	    	 
		    	    	 if(f.exists()) {
		    	    		 log.debug("파일 존재 ==> " +  saveFileName);
		    	    	 }else {
		    	    		 break;
		    	    	 }
		    	     }
		    	} else {
		    		  log.debug("파일 없음");    
		    	      saveFileName = originFileName+extx;
		    	}
		    	
		    	try { // 파일생성
		    		   mf.transferTo(new File(saveDir, saveFileName)); 
		    	} catch (Exception e) { 
		    		e.printStackTrace(); 
		    		return "file_creation_failure";
		    	}
	    	} else {
	    		return "file_format_not_supported";
	    	}
	    		
	    	 
    	}
	    
		 return saveDir + saveFileName;
 	}
	
	//전자문서 발송내역 수정
	@ResponseBody
	@RequestMapping(value = "/elctDocSndnUpdate.ajax")
	public Map elctDocSndnUpdate(MultipartHttpServletRequest request, @ModelAttribute("elctDocSndnVO") elctDocSndnVO elctDocSndnVO) {
		log.debug("/elctDoc/elctDocSndn - elctDocSndnUpdate");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
	
		elctDocSndnVO.setAmdr(EgovUserDetailsHelper.getUserId());	//수정자 셋팅
		elctDocSndnVO.setCrtr(EgovUserDetailsHelper.getUserId());	//생성자 셋팅
		
		String sndnDtm = commUtil.dateToText(elctDocSndnVO.getSndnD()) + elctDocSndnVO.getSndnH() + elctDocSndnVO.getSndnM() + "00";
		elctDocSndnVO.setSndnDtm(sndnDtm);
		
		if(!elctDocSndnVO.getImgSize().equals("0")) {
			String fileNm = elctFileUpload(request, elctDocSndnVO.getOganCd());
		 
			String osName = System.getProperty("os.name");
			if(osName.toLowerCase().startsWith("window"))  {
				fileNm = fileNm.substring(14);
			}else {
				fileNm = fileNm.substring(22);
			}
			
			if(!"file_creation_failure".equals(fileNm) && !"file_format_not_supported".equals(fileNm)) {
				elctDocSndnVO.setMmsImg(fileNm);
				elctDocSndnVO.setSaveFn("");
			}else if("file_creation_failure".equals(fileNm)) {
				elctDocSndnVO.setMmsImg("");
				errorMessage = "파일을 저장하지 못했습니다.";
			}else if("file_format_not_supported".equals(fileNm)) {
				elctDocSndnVO.setMmsImg("");
				errorMessage = "지원하는 이미지 파일 형식이 아닙니다.";
			}
		} else {
			if(elctDocSndnVO.getSaveFn().equals("")) { //기존 이미지를 삭제할 경우
				elctDocSndnVO.setMmsImg("");
				elctDocSndnVO.setImgSize("0");
			}
		}
		
		if(errorMessage.equals("")) {
			XssSaxFilter filter = XssSaxFilter.getInstance("lucy-xss-sax.xml", true);
			elctDocSndnVO.setMsgTitle(filter.doFilter(elctDocSndnVO.getMsgTitle()));
			elctDocSndnVO.setMsgText(filter.doFilter(elctDocSndnVO.getMsgText()));
			
			elctDocSndnService.elctDocSndnUpdate(elctDocSndnVO);	
		}
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
	
	
	//전자문서 템플릿 가져오기
	@ResponseBody
	@RequestMapping(value = "/changeMsgTmpltSelect.ajax")
	public Map changeMsgTmpltSelect(@ModelAttribute("elctDocSndnVO") elctDocSndnVO elctDocSndnVO) {
		log.debug("/elctDoc/elctDocSndn - changeMsgTmpltSelect.ajax");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";
		
		
		msgTmpltVO tempVO = new msgTmpltVO();
		tempVO.setOganCd(elctDocSndnVO.getOganCd());
		tempVO.setTmpltCd(elctDocSndnVO.getTmpltCd());
		
		msgTmpltVO resultVO = msgTmpltService.msgTmpltDetlSelect(tempVO);
		
		String temp = resultVO.getMsgImg();
		temp = temp.replace("C:\\image\\file\\", "");
		resultVO.setMsgImg(temp);
		
		resultMap.put("msgTmplt", resultVO);
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}
}