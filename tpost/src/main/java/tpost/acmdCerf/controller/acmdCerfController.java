package tpost.acmdCerf.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
import tpost.acmdCerf.service.acmdCerfService;
import tpost.acmdCerf.vo.acmdCerfVO;
import tpost.elctDoc.service.elctDocService;
import tpost.elctDoc.vo.elctDocVO;
import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.commUtil;
import tpost.egovcomm.EgovUserDetailsHelper;

@Controller
@RequestMapping(value = "/acmdCerf")
public class acmdCerfController {
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);

	@Autowired
	acmdCerfService acmdCerfService;
	
	@Autowired
	elctDocService elctDocService;
	
	@RequestMapping(value = "/acmdCerfMgnt", method = RequestMethod.POST)
	public String acmdCerfMgntInit(Model model) {
		log.debug("/acmdCerf/acmdCerfMgnt - acmdCerfMgntInit");
		
		acmdCerfVO acmdCerfVO = new acmdCerfVO();
		acmdCerfVO.setInputRengStDt(commUtil.YYYYMMFirstDate());
		acmdCerfVO.setInputRengClosDt(commUtil.YYYYMMLastDate());
		acmdCerfVO.setInputIssCls("");
		acmdCerfVO.setInputName("");
		acmdCerfVO.setInputOfapElctAddr("");
		acmdCerfVO.setInputCiNum("");
		
		acmdCerfListSelect(model, acmdCerfVO);
		return "acmdCerf/acmdCerfMgnt";
	}
	
	//상단 조회 버튼 클릭 시
	@RequestMapping(value = "/acmdCerfListSelect", method = RequestMethod.POST)
	public String acmdCerfListSelect(Model model, @ModelAttribute acmdCerfVO acmdCerfVO) {
		log.debug("/acmdCerf/acmdCerfListSelect - acmdCerfListSelect");
		
		model.addAttribute("inputRengStDt",acmdCerfVO.getInputRengStDt());
		model.addAttribute("inputRengClosDt",acmdCerfVO.getInputRengClosDt());
		model.addAttribute("inputIssCls",acmdCerfVO.getInputIssCls());
		model.addAttribute("inputName",acmdCerfVO.getInputName());
		model.addAttribute("inputOfapElctAddr",acmdCerfVO.getInputOfapElctAddr());
		model.addAttribute("inputCiNum",acmdCerfVO.getInputCiNum());
		model.addAttribute("pageIndex",acmdCerfVO.getPageIndex());
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(acmdCerfVO.getPageIndex()); //현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10);  //한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈
		 	
		acmdCerfVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		acmdCerfVO.setLastIndex(paginationInfo.getLastRecordIndex());
		acmdCerfVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		acmdCerfVO.setInputRengStDt(commUtil.dateToText(acmdCerfVO.getInputRengStDt()));
		acmdCerfVO.setInputRengClosDt(commUtil.dateToText(acmdCerfVO.getInputRengClosDt()));
		
		
		//내역 조회
		model.addAttribute("acmdCerfList", acmdCerfService.acmdCerfListSelect(acmdCerfVO));
		
		//총건수셋팅
		paginationInfo.setTotalRecordCount ((Integer)acmdCerfService.acmdCerfAddrListCountSelect(acmdCerfVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		return "acmdCerf/acmdCerfMgnt";
	}		
	
	//유통증명서 신청
	@ResponseBody
	@RequestMapping(value = "/acmdCerfInsert.ajax")
	public Map acmdCerfInsert(@ModelAttribute("acmdCerfVO") acmdCerfVO acmdCerfVO) {
		log.debug("/acmdCerf/acmdCerfInsert - acmdCerfInsert");
		
		acmdCerfVO tempVO = new acmdCerfVO();
		tempVO = acmdCerfVO;
		
		acmdCerfVO resultVO = acmdCerfService.sndnCheck(tempVO);	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage ="";
		
		
		if(resultVO != null) {
			if(resultVO.getAcmdInfoCrtYn().equals("Y")) {
				acmdCerfVO.setIssCls("100");
				acmdCerfVO.setInputPopuOfapElctAddr(resultVO.getOfapElctAddr());
				acmdCerfVO.setAcmdCerfSeq(acmdCerfService.getMaxSeq(acmdCerfVO).toString());
				acmdCerfVO.setInputPopuTrnsDate(commUtil.dateToText(acmdCerfVO.getInputPopuTrnsDate()));
				acmdCerfVO.setAmdr(EgovUserDetailsHelper.getUserId());	//수정자 셋팅
				acmdCerfVO.setCrtr(EgovUserDetailsHelper.getUserId());	//생성자 셋팅
				
				acmdCerfService.acmdCerfInsert(acmdCerfVO);
			}
			else {
				errorMessage = "유통정보생성 실패 건입니다.";
			}
		} else {
			errorMessage = "일치하는 데이터가 없습니다.";
		}
		
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		return resultMap;
	}


	//유통증명서 다운로드
	@RequestMapping(value = "/acmdCertFildDownload")
 	public void acmdCertFildDownload(Model model, HttpServletResponse  response, @ModelAttribute acmdCerfVO acmdCerfVO) throws Exception{
		log.debug("/acmdCerf/acmdCerfMgnt - acmdCertFildDownload");
		
		String osName = System.getProperty("os.name");
 		String saveDir = "";
 		
 		if(osName.toLowerCase().startsWith("window"))  {
 			saveDir = "C:\\" ;
 		}else {
 			saveDir = "/nfsdata01/tpost/file/"+ acmdCerfVO.getOganCd().toLowerCase() +"/acmdCert/";
 		}
 		
 		//직접 파일 정보를 변수에 저장해 놨지만, 이 부분이 db에서 읽어왔다고 가정한다.
		String fileName = acmdCerfVO.getDwldUrl();  /*저장할 파일명*/
		String contentType = "application/pdf";
		
		File file = new File(saveDir + fileName);
		if(file.exists()) {
			long fileSize = file.length();
			
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        response.setHeader("Content-Type", contentType);
	        response.setHeader("Content-Length", "" + fileSize);
	        response.setHeader("Pragma", "no-cache;");
	        response.setHeader("Expires", "-1;");
	        
	        FileInputStream fis = new FileInputStream(saveDir+fileName);
        	ServletOutputStream out = response.getOutputStream();
			
	        try{
	        	int readCount = 0;
	        	byte[] buffer = new byte[1024];
	            while((readCount = fis.read(buffer)) != -1){
	            	out.write(buffer,0,readCount);
	            }
	        }catch(Exception ex){
	            throw new RuntimeException("file Save Error");
	        }
	        finally {
	        	fis.close();
	        	
	        	out.flush();
	        	out.close();
	        	
	        	acmdCerfVO resultVO = acmdCerfService.downloadCheck(acmdCerfVO);
	        	
	        	if(resultVO != null) {
	        		acmdCerfVO tempVO = new acmdCerfVO();
	        		tempVO.setIssCls("500");
	        		tempVO.setInputPopuOganCd(resultVO.getOganCd());
	        		tempVO.setInputPopuTmpltCd(resultVO.getTmpltCd());
	        		tempVO.setInputPopuTrnsDate(resultVO.getTrnsDate());
	        		tempVO.setAcmdCerfSeq(resultVO.getAcmdCerfSeq());
	        		tempVO.setInputPopuOfapElctAddr(resultVO.getOfapElctAddr());
	        		
	        		tempVO.setAmdr(EgovUserDetailsHelper.getUserId());	//수정자 셋팅
	        		tempVO.setCrtr(EgovUserDetailsHelper.getUserId());	//생성자 셋팅
	        		
	        		acmdCerfService.acmdCerfUpdate(tempVO);	
	//        		acmdCerfListSelect(model, acmdCerfVO);
	        	}
	        }
		}

//		return "acmdCerf/acmdCerfMgnt";
	}
}