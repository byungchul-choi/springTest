package tpost.egovcomm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import tpost.egovcomm.service.EgovUserSessionService;
import tpost.logIn.vo.logInVO;


/**import egov.commCode.vo.commCdMgntVO;
 * 
 * @author 공통서비스 개발팀 서준식
 * @since 2011. 6. 25.
 * @version 1.0
 * @see
 *
 * <pre>
 * 개정이력(Modification Information) 
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011. 8. 12.    서준식        최초생성
 *  
 *  </pre>
 */

public class EgovUserSessionServiceImpl extends EgovAbstractServiceImpl implements EgovUserSessionService {
	
	
	@Autowired
//	private MarketOperMapper marketOperMapper;

	public Object getAuthenticatedUser() {
		if (RequestContextHolder.getRequestAttributes() == null) {
			return null;
		}

		return RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION);

	}

	public List<String> getAuthorities() {

		// 권한 설정을 리턴한다.
		List<String> listAuth = new ArrayList<String>();

		return listAuth;
	}

	public Boolean isAuthenticated() {
		
		if (RequestContextHolder.getRequestAttributes() == null) {
			return false;
		} else {
			
			if (RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION) == null) {
				return false;
			} else {
				return true;
			}
			
		}
		
	}
	
	
	/**
	 * 사용자 아이디를 리턴
	 * 	로그인 정보가 없을 경우 'guest' 리턴
	 * 파일명 : getUserId
	 * 작성일 : 2019. 6. 10. 오전 10:53:05
	 * 작성자 : 최병철
	 * @return
	 */
	public String getUserId(){
		if (RequestContextHolder.getRequestAttributes() == null) {
			return "guest";
		}else {
			logInVO userVO = (logInVO) RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION);
			if (userVO == null) {
				return "guest";
			} else {
				return userVO.getCustId();
			}
			
		}
	}
	
	/**
	 * 사용자 사용자 기관을  리턴
	 * 	로그인 정보가 없을 경우 'guest' 리턴
	 * 파일명 : getUserId
	 * 작성일 : 2019. 6. 10. 오전 10:53:05
	 * 작성자 : 최병철
	 * @return
	 */
	public String getOganCd(){
		if (RequestContextHolder.getRequestAttributes() == null) {
			return "guest";
		}else {
			logInVO userVO = (logInVO) RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION);
			if (userVO == null) {
				return "guest";
			} else {
				return userVO.getOganCd();
			}
			
		}
	}
	/**
	 * 사용자 사용자 권한등급 
	 * 	권한 정보가 없을 경우 '01' 리턴
	 * 파일명 : getUserId
	 * 작성일 : 2019. 6. 10. 오전 10:53:05
	 * 작성자 : 최병철
	 * @return
	 */
	public String getAthLev(){
		if (RequestContextHolder.getRequestAttributes() == null) {
			return "01";
		}else {
			logInVO userVO = (logInVO) RequestContextHolder.getRequestAttributes().getAttribute("logInVO", RequestAttributes.SCOPE_SESSION);
			if (userVO == null) {
				return "01";
			} else {
				return userVO.getAthLev();
			}
			
		}
	}
	
}
