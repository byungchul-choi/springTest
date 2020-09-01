package tpost.batch.batchMoblSndnMsg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.batch.batchMoblSndnMsg.vo.tbMoblSndnMsgVO;
import tpost.commCode.controller.commCdMgntController;

@Repository
public class batMoblSndnMsgDao {
	
  @Autowired
  SqlSession sqlSessionTemplate;

  /*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	

	public List<tbMoblSndnMsgVO> tbMoblSndnMsgSendList(String transDt) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("tpost.batch.batchMoblSndnMsg.dao.batMoblSndnMsgDao.tbMoblSndnMsgSendList", transDt);
	}

	public void tbMoblSndnMsgtInsert(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("tpost.batch.batchMoblSndnMsg.dao.batMoblSndnMsgDao.tbMoblSndnMsgtInsert", paramMap);
	}
	
}

