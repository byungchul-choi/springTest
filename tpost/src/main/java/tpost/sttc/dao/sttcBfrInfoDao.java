package tpost.sttc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tpost.sttc.vo.sttcInfoVO;


@Controller
public class sttcBfrInfoDao {

	 @Autowired
	  SqlSession sqlSessionTemplate;
	 

	 /* 전체 통계건 조회 */
	  public sttcInfoVO sttcBfrInfoSelect(sttcInfoVO sttcInfoVO) {
	   System.out.println("sttcBfrInfoSelect" );
		return  sqlSessionTemplate.selectOne("tpost.sttc.dao.sttcBfrInfoDao.sttcBfrInfoSelect", sttcInfoVO);
	  }
	  
	  
	  
	  /*상세통계조회*/
		public List<sttcInfoVO> sttcBfrInfoDetlSelect(sttcInfoVO sttcInfoVO) {
			 System.out.println("sttcBfrInfoDetlSelect" );
			return sqlSessionTemplate.selectList("tpost.sttc.dao.sttcBfrInfoDao.sttcBfrInfoDetlSelect",sttcInfoVO);
		}

}
