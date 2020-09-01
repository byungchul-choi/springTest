package tpost.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tpost.common.vo.menuMainMgntVO;
import tpost.logIn.vo.logInVO;


@Controller
public class menuMainMgntDao {

	 @Autowired
	  SqlSession sqlSessionTemplate;

	  public void menuMainMgntInsert(menuMainMgntVO menuMainMgntVO) {
	    sqlSessionTemplate.update("tpost.common.dao.menuMainMgntDao.menuMainMgntInsert", menuMainMgntVO);
	  }
	  
	  public void menuMainMgntUpdate(menuMainMgntVO menuMainMgntVO) {
		  sqlSessionTemplate.update("tpost.common.dao.menuMainMgntDao.menuMainMgntUpdate", menuMainMgntVO);
	  }
	  
	  public void menuMainMgntDelete(menuMainMgntVO menuMainMgntVO) {
		  sqlSessionTemplate.update("tpost.common.dao.menuMainMgntDao.menuMainMgntDelete", menuMainMgntVO);
	  }

	  public menuMainMgntVO menuMainMgntSelOne(menuMainMgntVO menuMainMgntVO) {
	    return sqlSessionTemplate.selectOne("tpost.common.dao.menuMainMgntDao.menuMainMgntSelOne",menuMainMgntVO);
	  }

	  
	  /*페이징 테스트 */
	  public List<menuMainMgntVO> menuMainMgntSelect(menuMainMgntVO menuMainMgntVO) {
		  
		    return sqlSessionTemplate.selectList("tpost.common.dao.menuMainMgntDao.menuMainMgntSelect",menuMainMgntVO);
		}
	  
	  /*페이징 카운트 가져오기 */
	  public int menuMainMgntSelectCnt(menuMainMgntVO menuMainMgntVO) {
		  return sqlSessionTemplate.selectOne("tpost.common.dao.menuMainMgntDao.menuMainMgntSelectCnt",menuMainMgntVO);
	  }
	  
	  /*상단 메뉴바 가져오기 */
	  public List<logInVO> menuMainIndexSelect(logInVO logInVO) {
		    return sqlSessionTemplate.selectList("tpost.common.dao.menuMainMgntDao.menuMainIndexSelect",logInVO);
		}

	public List<menuMainMgntVO> menuMainGnbSelect(logInVO logInVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("tpost.common.dao.menuMainMgntDao.menuMainGnbSelect",logInVO);
	}
	  


}
