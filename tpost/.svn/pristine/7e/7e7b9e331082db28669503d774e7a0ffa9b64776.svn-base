package tpost.sttc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tpost.sttc.vo.sttcInfoVO;


@Controller
public class sttcInfoDao {

	 @Autowired
	  SqlSession sqlSessionTemplate;
	 

	 /* 전체 통계건 조회 */
	  public sttcInfoVO sttcInfoSelect(sttcInfoVO sttcInfoVO) {
	   
		return  sqlSessionTemplate.selectOne("tpost.sttc.dao.sttcInfoDao.sttcInfoSelect", sttcInfoVO);
	  }
	  
	  
	  
	  /*상세통계조회*/
		public List<sttcInfoVO> sttcInfoDetlSelect(sttcInfoVO sttcInfoVO) {
			return sqlSessionTemplate.selectList("tpost.sttc.dao.sttcInfoDao.sttcInfoDetlSelect",sttcInfoVO);
		}

}
