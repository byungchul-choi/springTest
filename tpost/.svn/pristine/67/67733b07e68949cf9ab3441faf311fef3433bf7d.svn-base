package tpost.egovcomm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tpost.commCode.controller.commCdMgntController;
import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.egovcomm.egovutil.EgovStringUtil;
import tpost.logIn.vo.logInVO;



/**
 * 사용자 세션체크 인터셉터
 * @author 티시스
 * @since 2018
 * @version 1.0
 * @see
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    /* 사용자 세션체크 인터셉터
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	/* 함수 호출전 권한 호출 관리 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	HttpSession httpSession = request.getSession();
    	

    	String servletPath = request.getServletPath();
		String requestQS = request.getQueryString();
		
		logInVO sessionVo = (logInVO) httpSession.getAttribute("logInVO");

		log.debug("세션 고객정보  sessionVo " + sessionVo);
		
		
        if(EgovUserDetailsHelper.isAuthenticated()){
        	log.debug("로그인되었습니다.");
        	log.debug("로그인 권한 호출 성공입니다.");
            return true;
        }else{
        	log.error("preHandle 로그인 권한 호출 실패입니다");
        	// ajax 호출인 경우
			if("true".equals((String) request.getHeader("ajax"))){
				log.error("로그인 권한 호출 실패입니다.");
				response.sendError(505);
				return false;
			}else{
				log.error("로그인 페이지로 이동합니다. ");
				if(!EgovStringUtil.isEmpty(requestQS)){
					httpSession.setAttribute("prevUrl", servletPath + "?" + requestQS);
				}else{
					httpSession.setAttribute("prevUrl", servletPath);
				}
	        	
	        	response.sendRedirect("/tpost/logIn/logIn");
	        	
	        	return false;
			}
        	
        }
     
    }

}
