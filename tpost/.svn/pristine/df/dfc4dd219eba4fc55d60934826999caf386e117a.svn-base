package tpost.egovcomm;

import java.util.List;

import tpost.egovcomm.service.EgovUserSessionService;



/**
 * EgovUserDetails Helper 클래스
 *
 * @author 티시스
 * @since 2016
 * @version 1.0
 * @see
 */

public class EgovUserDetailsHelper {

    static EgovUserSessionService egovUserDetailsService;

    public EgovUserSessionService getEgovUserDetailsService() {
        return egovUserDetailsService;
    }

    public void setEgovUserDetailsService(EgovUserSessionService egovUserDetailsService) {
        EgovUserDetailsHelper.egovUserDetailsService = egovUserDetailsService;
    }

    /**
     * 인증된 사용자객체를 VO형식으로 가져온다.
     * @return Object - 사용자 ValueObject
     */
    public static Object getAuthenticatedUser() {
        return egovUserDetailsService.getAuthenticatedUser();
    }

    /**
     * 인증된 사용자 여부를 체크한다.
     * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)
     */
    public static Boolean isAuthenticated() {
    	
    	return egovUserDetailsService.isAuthenticated();
    }
    
    /**
	 * 인증된 사용자의 권한 정보를 가져온다.
	 * 예) [ROLE_ADMIN, ROLE_USER, ROLE_A, ROLE_B, ROLE_RESTRICTED, IS_AUTHENTICATED_FULLY, IS_AUTHENTICATED_REMEMBERED, IS_AUTHENTICATED_ANONYMOUSLY]
	 * @return List - 사용자 권한정보 목록
	 */
	public List<String> getAuthorities() {
		return egovUserDetailsService.getAuthorities();
	}
	
	/**
	 * 사용자 아이디를 리턴
	 * 	로그인 정보가 없을 경우 'guest' 리턴
	 * 파일명 : getUserId
	 * 작성일 : 2019. 6. 10. 오전 10:53:05
	 * 작성자 : 최병철
	 * @return
	 */
	public static String getUserId() {
		return egovUserDetailsService.getUserId();
	}
	
	/**
	 * 사용자 아이디를 리턴
	 * 	기관 정보가 없을 경우 'guest' 리턴
	 * 파일명 : getOganCd
	 * 작성일 : 2019. 6. 10. 오전 10:53:05
	 * 작성자 : 최병철
	 * @return
	 */
	public static String getOganCd() {
		return egovUserDetailsService.getOganCd();
	}
	
	/**
	 * 사용자 권핮정보 리턴
	 * 	권핮정보 정보가 없을 경우 '01' 리턴
	 * 파일명 : getAthLev
	 * 작성일 : 2019. 6. 10. 오전 10:53:05
	 * 작성자 : 최병철
	 * @return
	 */
	public static String getAthLev() {
		return egovUserDetailsService.getAthLev();
	}
	
	

}
