package tpost.egovcomm.service;

import java.util.List;

public interface EgovUserSessionService {

	Object getAuthenticatedUser();

    /**
     * 인증된 사용자 여부를 체크한다.
     */
    Boolean isAuthenticated();

	List<String> getAuthorities();

	/**
	 * 사용자 아이디를 리턴한다.
	 * 	로그인 정보가 없을 경우 'guest' 리턴
	 * 파일명 : getUserId
	 * 작성일 : 2019. 6. 10. 오전 10:52:30
	 * 작성자 : 최병철
	 * @return
	 */
	String getUserId();
	
	
	
	/**
	 * 사용자 권한정보 리턴한다.
	 * 	로그인 정보가 없을 경우 'guest' 리턴
	 * 파일명 : getAthLev
	 * 작성일 : 2019. 6. 10. 오전 10:52:30
	 * 작성자 : 최병철
	 * @return
	 */
	String getAthLev();
	
	
	/**
	 * 사용자 기관명 리턴한다.
	 * 	로그인 정보가 없을 경우 'guest' 리턴
	 * 파일명 : getOganCd
	 * 작성일 : 2019. 6. 10. 오전 10:52:30
	 * 작성자 : 최병철
	 * @return
	 */
	String getOganCd();


	
}
