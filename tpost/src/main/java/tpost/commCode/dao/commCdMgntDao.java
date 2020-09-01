package tpost.commCode.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.commCode.vo.commCdMgntVO;

@Repository
public class commCdMgntDao {
	
  @Autowired
  SqlSession sqlSessionTemplate;

  public void insert(commCdMgntVO commCdMgntVO) {
    sqlSessionTemplate.update("tpost.commCode.dao.commCdMgntDao.insert1", commCdMgntVO);
                                 
    
  }

  public commCdMgntVO selectOne(commCdMgntVO commCdMgntVO) {
    return sqlSessionTemplate.selectOne("tpost.commCode.dao.commCdMgntDao.selectOne",commCdMgntVO);
  }


  public List<commCdMgntVO> selectList(commCdMgntVO commCdMgntVO) {
	  return sqlSessionTemplate.selectList("tpost.commCode.dao.commCdMgntDao.selectList",commCdMgntVO);
  }
  
  /*페이징 테스트 */
  public List<commCdMgntVO> selectList_page(commCdMgntVO commCdMgntVO) {
	  
	    return sqlSessionTemplate.selectList("tpost.commCode.dao.commCdMgntDao.selectList_page1",commCdMgntVO);
	}
  
  /*페이징 카운트 가져오기 */
  public int selectList_cnt(commCdMgntVO commCdMgntVO) {
	    return sqlSessionTemplate.selectOne("tpost.commCode.dao.commCdMgntDao.selectList_cnt1",commCdMgntVO);
	}
  

}
