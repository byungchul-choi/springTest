package tpost.logIn.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tpost.common.vo.custInfMgntVO;
import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.logIn.vo.logInVO;


@Controller
public class logInDao {

	 @Autowired
	  SqlSession sqlSessionTemplate;
	 
	 public logInVO setCrtrAmdr(logInVO logInVO) {
		  
		  /*세션에 있는 custId 셋팅*/
		 logInVO.setCrtr(EgovUserDetailsHelper.getUserId()); 
		 logInVO.setAmdr(EgovUserDetailsHelper.getUserId());
		  
		  return logInVO;
	  }

	 /*로그인비번 확인*/
	  public logInVO logInAction(logInVO logInVO) {
	   
		return  sqlSessionTemplate.selectOne("tpost.logIn.dao.logInDao.logInAction", logInVO);
	  }
	  
	  /*로그인 시도 카운트 확인*/
	  public int logInActionCnt(logInVO logInVO) {
		   
			return  sqlSessionTemplate.selectOne("tpost.logIn.dao.logInDao.logInActionCnt", logInVO);
	  }
	  
	  /*로그인 최종 비밀번호 변경 날짜확인*/
	  public String logInActionDate(logInVO logInVO) {
		  
		  return  sqlSessionTemplate.selectOne("tpost.logIn.dao.logInDao.logInActionDate", logInVO);
	  }
	  
	  
	  
	  /*로그인 시도횟수 업데이트 확인*/
	  public void logInActionCntUpdate(logInVO logInVO) {
		  
		  sqlSessionTemplate.update("tpost.logIn.dao.logInDao.logInActionCntUpdate", logInVO);
		  
	  }

	  /*기존 패스워드를 'N' 으로 업데이트 한다.*/
	public void passWordChgUpdate(logInVO logInVO) {

		logInVO = setCrtrAmdr(logInVO);
		sqlSessionTemplate.update("tpost.logIn.dao.logInDao.passWordChgUpdate", logInVO);
		return ; 

	}
	
	/*새로운 패스워드를 입력한다.*/
	public void passWordChgInsert(logInVO logInVO) {
		
		logInVO = setCrtrAmdr(logInVO);
		sqlSessionTemplate.update("tpost.logIn.dao.logInDao.passWordChgInsert", logInVO);
		return ; 
	}

	/*최근에 사용한 패스워드인지 확인한다.*/
	public String passWordHistSelect(logInVO logInVO) {
		return  sqlSessionTemplate.selectOne("tpost.logIn.dao.logInDao.passWordHistSelect", logInVO);
		
	}

	/*고객정보 가져오기*/
	public logInVO tbCustInfo(logInVO logInVO) {
		// TODO Auto-generated method stub
		return  sqlSessionTemplate.selectOne("tpost.logIn.dao.logInDao.tbCustInfo", logInVO);
	}

	/*본인인증 확인번호 기존것업데이트*/
	public void tbCtifCfrmReqUpdate(logInVO logInVO) {
		logInVO = setCrtrAmdr(logInVO);
		sqlSessionTemplate.update("tpost.logIn.dao.logInDao.tbCtifCfrmReqUpdate", logInVO);
		return ; 
	}
	
	/*본인인증 확인 번호 insert*/
	public void tbCtifCfrmReqInsert(logInVO logInVO) {
		logInVO = setCrtrAmdr(logInVO);
		sqlSessionTemplate.insert("tpost.logIn.dao.logInDao.tbCtifCfrmReq", logInVO);
		return ; 
	}

	/*본인인증 확인번호 조회*/
	public logInVO tbCtifCfrmSel(logInVO logInVO) {
		logInVO = setCrtrAmdr(logInVO);
		logInVO = sqlSessionTemplate.selectOne("tpost.logIn.dao.logInDao.tbCtifCfrmSel", logInVO);
		return logInVO;
	}

}
