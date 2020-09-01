package tpost.common.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tpost.common.vo.aoMgntVO;
import tpost.common.vo.menuMainMgntVO;


@Controller
public class aoMgntDao {

	 @Autowired
	  SqlSession sqlSessionTemplate;

	  public void aoMgntInsert(aoMgntVO aoMgntVO) {
	    sqlSessionTemplate.update("tpost.common.dao.aoMgntDao.aoMgntInsert", aoMgntVO);
	  }
	  
	  public void aoMgntUpdate(aoMgntVO aoMgntVO) {
		  sqlSessionTemplate.update("tpost.common.dao.aoMgntDao.aoMgntUpdate", aoMgntVO);
	  }
	  
	  public void aoMgntDelete(aoMgntVO aoMgntVO) {
		 
		  String[] getInputChk = aoMgntVO.getInputChk().split(",");
		  String[] getAoIdDel = aoMgntVO.getAoIdDel().split(",");
		  List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
		  
		  for(int i = 0; i < getInputChk.length; i++) {
				if(getInputChk[i].equals("true")) {
					Map<String, Object> temp = new HashMap<String, Object>();
					temp.put("aoId",  ( i  < getAoIdDel.length ) ?  getAoIdDel[i] : "");
					tempList.add(temp);
				}
			}
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tempList", tempList);
			
		    sqlSessionTemplate.update("tpost.common.dao.aoMgntDao.aoMgntDelete", paramMap);
	  }

	  public aoMgntVO aoMgntSelOne(aoMgntVO aoMgntVO) {
	    return sqlSessionTemplate.selectOne("tpost.common.dao.aoMgntDao.aoMgntSelOne",aoMgntVO);
	  }

	  
	  /*페이징 테스트 */
	  public List<aoMgntVO> aoMgntSelect(aoMgntVO aoMgntVO) {
		  
		    return sqlSessionTemplate.selectList("tpost.common.dao.aoMgntDao.aoMgntSelect",aoMgntVO);
		                                          
		}
	  
	  /*페이징 카운트 가져오기 */
	  public int aoMgntSelectCnt(aoMgntVO aoMgntVO) {
		  return sqlSessionTemplate.selectOne("tpost.common.dao.aoMgntDao.aoMgntSelectCnt",aoMgntVO);
	  }
	  
	  public List<aoMgntVO> aoMgntSelectPopu(menuMainMgntVO menuMainMgntVO) {
		  
//		  return sqlSessionTemplate.selectList("tpost.common.dao.aoMgntDao.aoMgntSelectPopu",aoMgntVO); tpost.common.vo.menuMainMgntVO
		  return sqlSessionTemplate.selectList("tpost.common.dao.athGrpMgntDao.menuMgntSelectPopu",menuMainMgntVO);
		  
	  }
	  
	  public int aoMgntExCnt(aoMgntVO aoMgntVO) {
		  
		  return sqlSessionTemplate.selectOne("tpost.common.dao.aoMgntDao.aoMgntExCnt",aoMgntVO);
		  
	  }

	public List<aoMgntVO> aoMgntSelectPopu_menuMain(menuMainMgntVO menuMainMgntVO) {
		// TODO Auto-generated method stub
//		return sqlSessionTemplate.selectList("tpost.common.dao.aoMgntDao.aoMgntSelectPopuMenuMain",aoMgntVO);
		return sqlSessionTemplate.selectList("tpost.common.dao.athGrpMgntDao.menuMgntSelectPopu",menuMainMgntVO);
	}
	 
	  


}
