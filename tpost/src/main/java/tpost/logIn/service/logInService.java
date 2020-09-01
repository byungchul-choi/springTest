package tpost.logIn.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.commCode.controller.commCdMgntController;
import tpost.commUtil.commUtil;
import tpost.logIn.dao.logInDao;
import tpost.logIn.vo.logInVO;

@Service
public class logInService {

  @Autowired
  logInDao	 dao;
  /*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
  /**
   * 매핑권한 그룹정보 
   * */
  
  /*로그인  . */
	public  logInVO logInAction(logInVO logInVO) {
		
		/*로그인 시도횟수 변경*/
		int logInCnt = dao.logInActionCnt(logInVO);
		if(logInCnt >= 5) {  /*로그인 시도횟수 5번 초과*/
			logInVO.setPasswordFailCnt(logInCnt);
			return logInVO;
		}else if( logInCnt == -1) {
			logInVO.setPasswordFailCnt(logInCnt);
			return logInVO;
		}
		
		logInCnt = logInCnt + 1; /*1씩증가*/
		logInVO.setPasswordFailCnt(logInCnt);
		dao.logInActionCntUpdate(logInVO); /*로그인 시도횟수 업데이트 */
		
		System.out.println("resultDate ==> " + 11111);
		
		/*로그인 최종날짜 확인*/
		String resultDate = dao.logInActionDate(logInVO); /*로그인 최종날짜 업데이트 */
		if(! "Y".equals( resultDate ) ) {
			logInVO.setLastPwChgYn("N");
			return logInVO;
		}
		System.out.println("resultDate ==> " + resultDate);
		
		logInVO = dao.logInAction(logInVO);
		
		if(logInVO != null) {
			logInCnt = 0; /*로그인 성공시 */
			logInVO.setPasswordFailCnt(logInCnt);
			logInVO.setLastPwChgYn("Y");
			dao.logInActionCntUpdate(logInVO); /*로그인 시도횟수 업데이트 */
		}
		
		return logInVO;
	}

	public String passWordChg(logInVO logInVO) {
	// TODO Auto-generated method stub
		
		/*현재 패스워드 확인 암호화 적용으로 인한 수정 */
		String stPass = logInVO.getNewPassWord();
		
		logInVO =  dao.logInAction(logInVO);
		if(logInVO == null) {
		    return "현재비밀번호를 확인해주세요 ";
		}else {
			log.debug( "NewPassWord ==> " + logInVO.getNewPassWord()  );
		}
		
		/*새로운 패스워드 확인*/
		
		logInVO.setNewPassWord(stPass);
		
		String result =  dao.passWordHistSelect(logInVO);
		
		if(result == null) {
		
			dao.passWordChgUpdate(logInVO); /*기존패스워드 사용을 'N'으로 업데이트*/
			dao.passWordChgInsert(logInVO); /*신규패스워드를 입력한다.*/
			return "0";
		}else {
			return "최근에 사용한 비밀번호는 사용하실수 없습니다.";
		}
		
	
	}

	/*인증 번호 요청*/
	public logInVO factorAuthReq(logInVO logInVO) {
		
		/*번호 인증전 체크로직*/
		String celpNum = logInVO.getCelpNum();
		logInVO =  dao.tbCustInfo(logInVO);
		
		log.debug("회원정보와 전화번호가 일치하지 않습니다." + logInVO.getCelpNum());
		if(!celpNum.equals(logInVO.getCelpNum())) {
			logInVO.setErrMsg("회원정보와 전화번호가 일치하지 않습니다.");
			logInVO.setErrCode("1");
			return logInVO;
		}
		
		/*인증번호 핸드폰 전송 모듈 추가하기 */
		
		/*랜덤값 디비에 저장 호출*/
		double randomValue = Math.random();
		int intValue = (int)(randomValue * 10000) + 1;
		
		String strValue = commUtil.setLPad(""+intValue , 4 , "0" );
		
		log.debug("인증번호 " + strValue);
		logInVO.setCtifNo(strValue);
		
		dao.tbCtifCfrmReqUpdate(logInVO);
		dao.tbCtifCfrmReqInsert(logInVO);
		
		return logInVO;
	}

	/*인증번호 확인*/
	public logInVO factorAuthConfirm(logInVO logInVO) {

		/*인증번호 확인*/
		logInVO resultVO =  dao.tbCtifCfrmSel (logInVO);
		
		/*결과값이 잇는지만 확인하고 로그인세션값을 조회한다.*/
		if (resultVO != null && resultVO.getCustId() != null && !resultVO.getCustId().equals("")) {
			
			/*세션에 저잘할 로그인정보 조회*/
			logInVO =  dao.tbCustInfo(logInVO);
		
		}else {
			logInVO.setErrMsg("인증번호가 잘못되었습니다.");
			logInVO.setErrCode("1");
			return logInVO;
			
		}
		return logInVO;
	}

	/*인증번호 초기화*/
	public logInVO passWordChg_init(logInVO logInVO) {
		// TODO Auto-generated method stub
		logInVO resultVO =  dao.tbCtifCfrmSel (logInVO); /*인증번호 확인*/
		
		if (resultVO != null && resultVO.getCustId() != null && !resultVO.getCustId().equals("")) {
			
			/*새로운 패스워드 확인*/
			String result =  dao.passWordHistSelect(logInVO);
			
			if(result == null) {
			
				dao.passWordChgUpdate(logInVO); /*기존패스워드 사용을 'N'으로 업데이트*/
				dao.passWordChgInsert(logInVO); /*신규패스워드를 입력한다.*/
				
				logInVO.setErrMsg("정상적으로 변경되었습니다.");
				logInVO.setErrCode("0");
				return logInVO;
				
			}else {
				logInVO.setErrMsg("최근에 사용한 비밀 번호로는 변경할수없습니다.");
				logInVO.setErrCode("1");
				return logInVO;
			}
		
		}else {
			logInVO.setErrMsg("인증번호가 잘못되었습니다.");
			logInVO.setErrCode("1");
			return logInVO;
			
		}
	}
  
}
