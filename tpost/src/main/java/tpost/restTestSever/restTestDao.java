package tpost.restTestSever;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tpost.commCode.controller.commCdMgntController;



@Controller
public class restTestDao {

	@Autowired
	SqlSession sqlSessionTemplate;

	
	/*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	public List<restTestServerVO> testSelect(restTestServerVO restTestServerVO) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("tpost.batch.dao.kakaoTestDao.restFulTest", restTestServerVO);
	}
	 
	
}
