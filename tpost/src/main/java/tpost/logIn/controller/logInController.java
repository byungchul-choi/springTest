package tpost.logIn.controller;

import java.util.Base64;

import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;


import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.controller.ascCommUtil;
import tpost.common.vo.menuMainMgntVO;
import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.logIn.service.logInService;
import tpost.logIn.vo.logInVO;



@Controller
public class logInController {

	@Autowired
	logInService logInService;

	@Value("#{properties['aesKey']}") 
	private String aesKey;
	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	
	public logInController() {
	}
	
	
	/*처음화면 호출 */
	 @RequestMapping(value = "logIn/logIn", method = {RequestMethod.GET, RequestMethod.POST})
	  public String logInInit(Model model , @ModelAttribute logInVO logInVO, HttpServletRequest request ) {
	    
		 log.debug("/logIn/logIn");
		 
	    return "logIn/logIn";
	  }
	 
	 /*처음화면 호출 로그인팝업*/
	 @RequestMapping(value = "logIn/pwAltrPopu", method = {RequestMethod.GET, RequestMethod.POST})
	 public String pwAltrPopu(Model model , @ModelAttribute logInVO logInVO ) {
		 
		 log.debug("/logIn/pwAltrPopu");
		 /*세션에 있는 custId 셋팅*/
		 log.debug("custId ==> " + logInVO.getCustId());
		 
		 logInVO sessionLogInVo =null;
		 if(RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION) != null) {
			 sessionLogInVo = (tpost.logIn.vo.logInVO) RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION);
			 log.debug("sessionLogInVo ==> " + sessionLogInVo.getCustId());
		 }
		 
		 model.addAttribute("custId" , logInVO.getCustId() );
		 
		 return "logIn/pwAltrPopu";
	 }
	 
	 /*로그인후 ID PW 인증 팝업 호출 */
	 @RequestMapping(value = "logIn/factorAuth", method = {RequestMethod.GET, RequestMethod.POST})
	 public String factorAuth(Model model , @ModelAttribute logInVO logInVO ) {
		 
		 log.debug("/logIn/factorAuth");
		 /*세션에 있는 custId 셋팅*/
		 
		 model.addAttribute("custId" , logInVO.getCustId() );
		 
		 return "/logIn/factorAuth";
	 }

	 
	 /*로그인 패스워드 바꾸기  */
	 @RequestMapping(value = "logIn/passWordChg", method = {RequestMethod.POST})
	 public String passWordChg(Model model , @ModelAttribute logInVO logInVO ) {
		 
		 log.debug("/logIn/passWordChg.ajax");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 String errmsg = logInService.passWordChg(logInVO);
		 
		 if("0".equals(errmsg ) ) {
			 resultMap.put("result", "Y");
			 logInVO.setResult("Y");
			 resultMap.put("errorMessage", "정상처리되었습니다.");
			 logInVO.setErrMsg("정상처리되었습니다.");
		 }else {
			 resultMap.put("result", "N");
			 logInVO.setResult("N");
			 resultMap.put("errorMessage", errmsg);
			 logInVO.setErrMsg(errmsg);
		 }	 
		 
		 model.addAttribute("result", logInVO.getResult());
		 model.addAttribute("url",   "passWordChg" );
		 model.addAttribute("errorMessage", logInVO.getErrMsg() );
		 
		 log.debug("/logIn/factorAuthn");
		 
		 return "/logIn/factorAuth";
	 }
	 
	 /* 아이디 비번 로그인  처리 */
	 @RequestMapping(value = "logIn/logInAction", method = {RequestMethod.GET, RequestMethod.POST})
	 public String logInAction1(Model model , @ModelAttribute logInVO logInVO, HttpServletRequest request ) {
		 
		 log.debug("/logIn/logInAction");
		 log.debug("acsKey ===> " + aesKey );
		 log.debug("acsKey ===> " + aesKey );
		 
		 String errorMessage = "";	
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 
		 /*키셋팅하기*/
		 String aesKey1 = ascCommUtil.ascKeyEncode(aesKey); 
		 logInVO.setAesKey(aesKey1);
		 
		 
		 logInVO resultVO = logInService.logInAction(logInVO);
		 
		 if (resultVO != null && resultVO.getCustId() != null && !resultVO.getCustId().equals("")) {
			 
			 if(resultVO.getPasswordFailCnt() >= 5) {   /*비밀번호 5회이상 초과시 오류*/
				 request.getSession().setAttribute("logInVO", resultVO);
				 logInVO.setResult("pwFiveFailTimeOver");
				 
			 }else
				 
				 if(resultVO.getPasswordFailCnt() == -1) {   /*비밀번호 5회이상 초과시 오류*/
					 logInVO.setResult("N");
					 logInVO.setErrMsg("로그인 ID or 비밀번호가 정확하지 않습니다.");
				 }else
					 
					 if(! "Y".equals( resultVO.getLastPwChgYn() ) ) {   /*비밀번호 3개월이상 변경안할시 오류 초과시 오류*/
						 request.getSession().setAttribute("logInVO", resultVO);
						 logInVO.setResult("pwLastChgOver");

					 }else
						 
						 if(resultVO.getCelpNumSha2().equals(resultVO.getPassWord())){  /*비밀번호 초기비밀번호는 핸드폰번호 이거랑 같을시 변경*/
							 
							 logInVO.setErrMsg("초기화된 비밀번호 입니다. 새 비밀번호를 등록해 주세요.");
							 logInVO.setResult("init");
						 }else if( Integer.parseInt(resultVO.getMdfDays()) > 90 ) {
							 logInVO.setErrMsg("비밀번호 변경일수가 90일이 넘었습니다.");
							 logInVO.setResult("init");
						 }
			 			 else {
							 
							 request.getSession().setAttribute("logInVO", resultVO);
							 logInVO.setResult("Y");
						 }
		 }
		 else {
			 logInVO.setResult("N");
			 logInVO.setErrMsg("로그인 ID or 비밀번호가 정확하지 않습니다.");
			 
			 resultMap.put("errorMessage", "로그인 ID or 비밀번호가 정확하지 않습니다.");
		 }
		 model.addAttribute("result", logInVO.getResult());
		 model.addAttribute("url",   "logInAction" );
		 model.addAttribute("errorMessage", logInVO.getErrMsg() );
		 
		 log.debug("/logIn/factorAuthn");
		 
		 return "/logIn/factorAuth";
	 }
	
	
	 	 /**
		 * 핸드폰 인증 요청
		 * 파일명 : factor auth
		 * 작성일 : 2019. 7. 22. 오전 10:54:17
		 * 작성자 : 최병철
		 * @param request
		 * @return
		 */
	 	@RequestMapping("logIn/factorAuth_req")
	 	public String factorAuthReq(Model model , HttpServletRequest request,  @ModelAttribute logInVO logInVO){
	 		/*CTIF_KNCD 인증종류 01 : 핸드폰인증*/ 
	 		log.debug("/logIn/factorAuth_req");
	 		
	 		Map<String, Object> resultMap = new HashMap<String, Object>();
	 		
	 		/*키셋팅하기*/
	 		String aesKey1 = ascCommUtil.ascKeyEncode(aesKey); 
	 		logInVO.setAesKey(aesKey1);
	 		
	 		logInVO resultVO = logInService.factorAuthReq(logInVO);
	 		
	 		log.debug("인증번호 ==> " + resultVO.getCtifNo());
	 		String ctifNo = "";
	 		
	 		if (resultVO != null && resultVO.getCustId() != null && !resultVO.getCustId().equals("")) {
	 			
	 			if( "1".equals( resultVO.getErrCode()) ) {
	 				resultMap.put("result", "N");
	 				resultMap.put("errorMessage", resultVO.getErrMsg());
	 				
	 				logInVO.setResult("N");
	 				logInVO.setErrMsg(resultVO.getErrMsg());
	 				
	 			}else {
	 				
	 				ctifNo = resultVO.getCtifNo();
	 				
	 				resultMap.put("result", "Y");
	 				resultMap.put("ctifNo", resultVO.getCtifNo());
	 				request.getSession().setAttribute("logInVO", resultVO);
	 				
	 				logInVO.setResult("Y");
	 				logInVO.setErrMsg(resultVO.getErrMsg());
	 				
	 			}
	 			
	 		}
	 		else {
	 			resultMap.put("result", "N");
	 			resultMap.put("errorMessage", "핸드폰인증확인중 오류가 발생하였습니다.");
	 		}
	 		
	 		 model.addAttribute("result", logInVO.getResult());
			 model.addAttribute("url",   "factorAuth_req" );
			 model.addAttribute("ctifNo",   ctifNo );
			 model.addAttribute("errorMessage", logInVO.getErrMsg() );
			 
	 		return "/logIn/factorAuth";
	 	}
	 	/**
	 	 * 핸드폰 인증확인
	 	 * 파일명 : factor auth
	 	 * 작성일 : 2019. 7. 22. 오전 10:54:17
	 	 * 작성자 : 최병철
	 	 * @param request
	 	 * @return
	 	 */
	 	@ResponseBody
	 	@RequestMapping("logIn/factorAuth_confirm.ajax")
	 	public Map factorAuth_confirm1(HttpServletRequest request,  @ModelAttribute logInVO logInVO){
	 		log.debug("/logIn/factorAuth_confirm.ajax");
	 		
	 		Map<String, Object> resultMap = new HashMap<String, Object>();
	 		
	 		logInVO resultVO = logInService.factorAuthConfirm(logInVO);
	 		
	 		if (resultVO != null && resultVO.getCustId() != null && !resultVO.getCustId().equals("")) {
	 			
	 			if( "1".equals( resultVO.getErrCode()) ) {
	 				resultMap.put("result", "N");
	 				resultMap.put("errorMessage", resultVO.getErrMsg());
	 			}else {
	 				request.getSession().setAttribute("logInVO", resultVO); /*핸드폰 인증후 세션처리 함*/
	 				resultMap.put("result", "Y");
	 				resultMap.put("errorMessage", "인증되었습니다.");
	 			}
	 			
	 		}
	 		else {
	 			resultMap.put("result", "N");
	 			resultMap.put("errorMessage", "핸드폰인증확인중 오류가 발생하였습니다.");
	 		}
	 		
	 		return resultMap;
	 	}
	 	/**
	 	 * 핸드폰 인증확인
	 	 * 파일명 : factor auth
	 	 * 작성일 : 2019. 7. 22. 오전 10:54:17
	 	 * 작성자 : 최병철
	 	 * @param request
	 	 * @return
	 	 */
	 	@RequestMapping("logIn/factorAuth_confirm")
	 	public String factorAuth_confirm(Model model , HttpServletRequest request,  @ModelAttribute logInVO logInVO){
	 		log.debug("/logIn/factorAuth_confirm");
	 		
	 		Map<String, Object> resultMap = new HashMap<String, Object>();
	 		
	 		logInVO resultVO = logInService.factorAuthConfirm(logInVO);
	 		
	 		if (resultVO != null && resultVO.getCustId() != null && !resultVO.getCustId().equals("")) {
	 			
	 			if( "1".equals( resultVO.getErrCode()) ) {
	 				resultMap.put("result", "N");
	 				resultMap.put("errorMessage", resultVO.getErrMsg());
	 				
	 				logInVO.setResult("N");
	 				logInVO.setErrMsg(  resultVO.getErrMsg() );
	 			}else {
	 				request.getSession().setAttribute("logInVO", resultVO); /*핸드폰 인증후 세션처리 함*/
	 				resultMap.put("result", "Y");
	 				
	 				logInVO.setResult("Y");
	 				logInVO.setErrMsg( "인증되었습니다.");
	 				resultMap.put("errorMessage", "인증되었습니다.");
	 			}
	 			
	 		}
	 		else {
	 			resultMap.put("result", "N");
	 			resultMap.put("errorMessage", "핸드폰인증확인중 오류가 발생하였습니다.");
	 		}
	 		
	 		 model.addAttribute("result", logInVO.getResult());
			 model.addAttribute("url",   "factorAuth_confirm" );
			 model.addAttribute("errorMessage", logInVO.getErrMsg() );
	 		
	 		return "/logIn/factorAuth";
	 	}
	 	/**
	 	 * 비밀번호 초기화
	 	 * 파일명 : factor auth
	 	 * 작성일 : 2019. 7. 22. 오전 10:54:17
	 	 * 작성자 : 최병철
	 	 * @param request
	 	 * @return
	 	 */
	 	@RequestMapping("logIn/passWordChg_init")
	 	public String passWordChg_init(Model model , HttpServletRequest request,  @ModelAttribute logInVO logInVO){
	 		log.debug("/logIn/passWordChg_init");
	 		
	 		Map<String, Object> resultMap = new HashMap<String, Object>();
	 		
	 		logInVO resultVO = logInService.passWordChg_init(logInVO);
	 		
	 		if (resultVO != null && resultVO.getCustId() != null && !resultVO.getCustId().equals("")) {
	 			
	 			if( "1".equals( resultVO.getErrCode()) ) {
	 				resultMap.put("result", "N");
	 				logInVO.setResult("N");
	 				resultMap.put("errorMessage", resultVO.getErrMsg());
	 				logInVO.setErrMsg(resultVO.getErrMsg());
	 			}else {
	 				request.getSession().setAttribute("logInVO", resultVO); /*핸드폰 인증후 세션처리 함*/
	 				resultMap.put("result", "Y");
	 				logInVO.setResult("Y");
	 				resultMap.put("errorMessage", "비밀번호가 변경되었습니다.");
	 				logInVO.setErrMsg("비밀번호가 변경되었습니다.");
	 			}
	 			
	 		}
	 		else {
	 			resultMap.put("result", "N");
	 			logInVO.setResult("N");
	 			resultMap.put("errorMessage", "핸드폰인증확인중 오류가 발생하였습니다.");
	 			logInVO.setErrMsg( "핸드폰인증확인중 오류가 발생하였습니다.");
	 		}
	 		
	 		 model.addAttribute("result", logInVO.getResult());
			 model.addAttribute("url",   "passWordChg_init" );
			 model.addAttribute("errorMessage", logInVO.getErrMsg() );
	 		 
	 		return "/logIn/factorAuth";
	 	}
	 	
	 	 /*로그인후 ID PW 인증 팝업 호출 */
		 @RequestMapping(value = "logIn/pwInitPopu", method = {RequestMethod.GET, RequestMethod.POST})
		 public String pwInitPopu(Model model , @ModelAttribute logInVO logInVO ) {
			 
			 log.debug("/logIn/pwInitPopu");
			 
			 return "logIn/pwInitPopu";
		 }
		 
		 /*처음화면 호출 로그인팝업*/
		 
		
	 	
	 /**
		 * 로그아웃 처리
		 * 파일명 : actionLogout
		 * 작성일 : 2019. 7. 22. 오전 10:54:17
		 * 작성자 : 최병철
		 * @param request
		 * @return
		 */
	 	@ResponseBody
		@RequestMapping("logIn/actionLogout.ajax")
		public Map actionLogoutAjax(HttpServletRequest request){
			Map<String, Object> resultMap = new HashMap<String,Object>();
			
			request.getSession().setAttribute("logInVO", null);
			
			resultMap.put("result", "Y");
			
			 return resultMap;
		}
	 	
	 	/*처음화면 호출 */
		 @RequestMapping(value = "logIn/actionLogout", method = {RequestMethod.GET, RequestMethod.POST})
		  public String actionLogout(Model model , @ModelAttribute logInVO logInVO, HttpServletRequest request ) {
		    
			 log.debug("/logIn/actionLogout");
			 request.getSession().setAttribute("logInVO", null);
			
		     return "logIn/logIn";
		  }
	 
}
