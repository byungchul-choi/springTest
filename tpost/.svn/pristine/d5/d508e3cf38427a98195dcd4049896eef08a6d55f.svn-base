package tpost.common.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tpost.common.vo.custInfMgntVO;
import tpost.egovcomm.EgovUserDetailsHelper;


@Controller
public class custInfMgntDao {

	 @Autowired
	  SqlSession sqlSessionTemplate;

	  public custInfMgntVO setCrtrAmdr(custInfMgntVO custInfMgntVO) {
		  
		  /*세션에 있는 custId 셋팅*/
		  custInfMgntVO.setCrtr(EgovUserDetailsHelper.getUserId()); 
		  custInfMgntVO.setAmdr(EgovUserDetailsHelper.getUserId());
		  
		  return custInfMgntVO;
	  }
	  
	  public void custInfInsert(custInfMgntVO custInfMgntVO) {
		  /*세션에 있는 custId 셋팅*/
		 custInfMgntVO =   setCrtrAmdr(custInfMgntVO);
		 
		 /*고객 ID 채번*/
		String custId =  sqlSessionTemplate.selectOne("tpost.common.dao.custInfMgntDao.custInfMgntCustIdSeq", custInfMgntVO);
		custInfMgntVO.setCustId(custId);
		
		/*고객정보관리 테이블 INSERT*/ 
	    sqlSessionTemplate.insert("tpost.common.dao.custInfMgntDao.custInfMgntInsert", custInfMgntVO);
	    
	    /*패스워드 관리 테이블 INSERT 초기패스워드는 핸드폰 번호로 INSERT*/
	    sqlSessionTemplate.insert("tpost.common.dao.custInfMgntDao.custInfPassWordInsert", custInfMgntVO);
	    
	  }
	  
	  public void custInfUpdate(custInfMgntVO custInfMgntVO) {
		  /*세션에 있는 custId 셋팅*/
		  custInfMgntVO =   setCrtrAmdr(custInfMgntVO);
		  sqlSessionTemplate.update("tpost.common.dao.custInfMgntDao.custInfMgntUpdate", custInfMgntVO);
	  }
	  
	  public void custInfDelete(custInfMgntVO custInfMgntVO) {
		  
		String[] inputChk = custInfMgntVO.getInputChk().split(",");
		String[] custNum = custInfMgntVO.getCustNum().split(",");
		
		List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
		
		for(int i = 0; i < inputChk.length; i++) {
			if(inputChk[i].equals("true")) {
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("custNum",  ( i < custNum.length ) ?  custNum[i] : "");
				tempList.add(temp);
			}
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tempList", tempList);
		
		sqlSessionTemplate.update("tpost.common.dao.custInfMgntDao.PassWordMgntDelete", paramMap);
		  sqlSessionTemplate.update("tpost.common.dao.custInfMgntDao.custInfMgntDelete", paramMap);
		  
		  
	  }

	  public custInfMgntVO custInfSelOne(custInfMgntVO custInfMgntVO) {
	    return sqlSessionTemplate.selectOne("tpost.common.dao.custInfMgntDao.custInfMgntSelOne",custInfMgntVO);
	  }

	  
	  /*페이징 테스트 */
	  public List<custInfMgntVO> custInfSelect(custInfMgntVO custInfMgntVO) {
		  
		    return sqlSessionTemplate.selectList("tpost.common.dao.custInfMgntDao.custInfMgntSelect",custInfMgntVO);
		                                          
		}
	  
	  /*페이징 카운트 가져오기 */
	  public int custInfSelectCnt(custInfMgntVO custInfMgntVO) {
		  return sqlSessionTemplate.selectOne("tpost.common.dao.custInfMgntDao.custInfMgntSelectCnt",custInfMgntVO);
	  }
	  
	  /*팝업조회 */
	  public List<custInfMgntVO> custInfMgntSelectPopu(custInfMgntVO custInfMgntVO) {
		  
		  return sqlSessionTemplate.selectList("tpost.common.dao.custInfMgntDao.custInfMgntSelectPopu",custInfMgntVO);
		  
	  }

	public void custInfMgntPwInit(custInfMgntVO custInfMgntVO) {
		// TODO Auto-generated method stub
		  custInfMgntVO =   setCrtrAmdr(custInfMgntVO);
		  sqlSessionTemplate.update("tpost.common.dao.custInfMgntDao.custInfMgntPwInit", custInfMgntVO);
	}
	 
	  


}
