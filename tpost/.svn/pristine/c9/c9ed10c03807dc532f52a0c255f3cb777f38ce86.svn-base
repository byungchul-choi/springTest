package tpost.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tpost.common.service.commonService;
import tpost.common.vo.commonVO;


@Controller
@RequestMapping(value = "common")
public class commonController {

	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commonController.class);
	
   @Autowired
   commonService commonService;
   


   /*에러코드 매핑*/
  @RequestMapping(value = "/error{error_code}", method = { RequestMethod.POST, RequestMethod.GET})
  public String error(HttpServletRequest request, Model model , @PathVariable String error_code ) {
	  
	  log.debug("에러페이지 이동입니다. ");
	  System.out.println("에러페이지 이동입니다. ");
	  //model.addAttribute("selectMenu", commonService.selectMenu(commonVO));  //나중에 주석풀기
	  String msg = (String) request.getAttribute("javax.servlet.error.message"); 
      
	  log.debug("String ===> " +  msg);
	  
	  Map<String, Object> map = new HashMap<String, Object>();
      
      map.put("STATUS_CODE", request.getAttribute("javax.servlet.error.status_code"));
      map.put("REQUEST_URI", request.getAttribute("javax.servlet.error.request_uri"));
      map.put("EXCEPTION_TYPE", request.getAttribute("javax.servlet.error.exception_type"));
      map.put("EXCEPTION", request.getAttribute("javax.servlet.error.exception"));
      map.put("SERVLET_NAME", request.getAttribute("javax.servlet.error.servlet_name"));
       
      try {
          int status_code = Integer.parseInt(error_code);
          switch (status_code) {
          case 400: msg = "잘못된 요청입니다."; break;
          case 403: msg = "접근이 금지되었습니다."; break;
          case 404: msg = "페이지를 찾을 수 없습니다."; break;
          case 405: msg = "요청된 메소드가 허용되지 않습니다."; break;
          case 500: msg = "서버에 오류가 발생하였습니다."; break;
          case 503: msg = "서비스를 사용할 수 없습니다."; break;
          default: msg = "알 수 없는 오류가 발생하였습니다."; break;
          }
          if(status_code == 404)
        	  return "/error404";
      } catch(Exception e) {
          msg = "기타 오류가 발생하였습니다.";
      } finally {
          map.put("MESSAGE", msg);
          
          model.addAttribute("MESSAGE", msg);
      }
      log.debug("/error " +  msg);
      return "/error";
  
  }
}
