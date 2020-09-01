package tpost.commCode.controller;

import java.util.HashMap;
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

import tpost.commCode.service.commCdMgntService;
import tpost.commCode.vo.commCdMgntVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping(value = "/comm")
public class commCdMgntController {

	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
   @Autowired
   commCdMgntService commCdMgntService;
   

  @RequestMapping(value = "/commCdMgnt", method = RequestMethod.POST)
  public String commCdMgntInit() {
    
	  System.out.println("/comm/commCdMgnt commCdMgntInit");
    return "comm/commCdMgnt";
  }
  
  @RequestMapping(value = "/commCdMgntSel", method = RequestMethod.POST)
  public String commCdMgntSearch( Model model , @ModelAttribute commCdMgntVO commCdMgntVO ) {
    
	  System.out.println("/comm/commCdMgntSearch 여기까지 탔어요  ==> " +commCdMgntVO.getCfcd() );
	  
      model.addAttribute("commCdMgntList", commCdMgntService.selectList(commCdMgntVO));
	  
      return "comm/commCdMgnt";
  }
  
  
  @RequestMapping(value = "/commCdMgnt/cfcdPopu", method = { RequestMethod.POST, RequestMethod.GET})
  public String commCdMgntInitPopu(@ModelAttribute commCdMgntVO commCdMgntVO) {
    
	  System.out.println("/popup/CommCdMgntPopu 팝업호출이에요");
	  System.out.println("commCdMgntVO.getCfcd()" + commCdMgntVO.getCfcd());
	  
	  return "popup/commCdMgntPopu";
  }

  @RequestMapping(value = "/commCdMgnt/cfcdPopu-insert", method = RequestMethod.POST)
  public String commCdMgntInitPopuInsert(Model model , @ModelAttribute commCdMgntVO commCdMgntVO) {
    
	  System.out.println("/popup/CommCdMgntPopu 팝업저장이에요");
//	  commCdMgntService.insert(commCdMgntVO);
	  commCdMgntService.selectOne(commCdMgntVO);
    
	  model.addAttribute("commCdMgntList", commCdMgntService.selectOne(commCdMgntVO));
	  model.addAttribute("test10", "111111");
	  model.addAttribute("test11", "222222");
	  
    return "/popup/commCdMgntPopu";
  }
  
  @RequestMapping(value = "/commCdMgnt/cfcdPopu-delete", method = RequestMethod.POST)
  public String commCdMgntInitPopuDelete(@ModelAttribute commCdMgntVO commCdMgntVO) {
	  
	  System.out.println("/popup/CommCdMgntPopu 팝업삭제이에요");

	  return "redirect:/popup/commCdMgntPopu";
  }
  
  @RequestMapping(value = "/commCdMgnt/cfcdPopu-update", method = RequestMethod.POST)
  public String commCdMgntInitPopuUpdate(@ModelAttribute commCdMgntVO commCdMgntVO) {
	  
	  System.out.println("/popup/CommCdMgntPopu 팝업업데이트이에요");

	  return "redirect:/popup/commCdMgntPopu";
  }
  
  /*한글테스트 입니다. */
  @RequestMapping(value = "/commCdMgnt/commCdMgnt_kr", method = RequestMethod.POST)
  public String commCdMgnt_kr(@ModelAttribute commCdMgntVO commCdMgntVO) {
	  
	  log.debug(commCdMgntVO.getCfcd());
	  log.debug(commCdMgntVO.getCfcdNm());
	  log.debug(commCdMgntVO.getCfcdDesc());
//	  commCdMgntService.insert(commCdMgntVO);
	  commCdMgntService.insert(commCdMgntVO);
	  
	  return "comm/commCdMgnt";
  }

  
  /*ajax 에서 화면 호출 시작*/
  @ResponseBody
  @RequestMapping("/commCdMgnt/cfcdPopu-select.ajax")
	public  Map searchMarketList(@ModelAttribute("commCdMgntVO") commCdMgntVO commCdMgntVO){
//	  public  ModelAndView searchMarketList(@ModelAttribute("commCdMgntVO") commCdMgntVO commCdMgntVO){
	  
	

	  System.out.println("아작스 화면호출 테스트 입니다. ");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String errorMessage = "";	

		resultMap.put("cfcdtList", commCdMgntService.selectList(commCdMgntVO));
			
		resultMap.put("result", "Y");
		resultMap.put("errorMessage", errorMessage);
		
		//return new ModelAndView("jsonView", resultMap);
		return resultMap ;
	}
	
  /*ajax 에서 화면 호출 종료*/
  
  
  /*페이징 처리 테스트 */
  @RequestMapping(value = "/commCdMgnt/commCdMgnt-paging", method = RequestMethod.POST)
  public String commCdMgntPaging(Model model , @ModelAttribute commCdMgntVO commCdMgntVO) {
	  
	  log.debug("페이징 처리 테스트 입니다. ");
	  
	  log.error("페이징 처리 테스트 입니다.1111 ");
	  
	  PaginationInfo paginationInfo = new PaginationInfo();
	  paginationInfo.setCurrentPageNo(commCdMgntVO.getPageIndex()); //현재 페이지 번호
	  paginationInfo.setRecordCountPerPage(2);  //한 페이지에 게시되는 게시물 건수
	  paginationInfo.setPageSize(5); //페이징 리스트의 사이즈

     	
	  commCdMgntVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	  commCdMgntVO.setLastIndex(paginationInfo.getLastRecordIndex());
	  commCdMgntVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
	  
		/*내역조회*/
		model.addAttribute("resultList", commCdMgntService.selectList_page(commCdMgntVO));
		
		/*총건수셋팅*/
		paginationInfo.setTotalRecordCount ( (Integer) commCdMgntService.selectList_cnt(commCdMgntVO));
		model.addAttribute("paginationInfo", paginationInfo);
		

		return "comm/commCdMgnt";
  }

}
