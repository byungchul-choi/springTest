package tpost.batch.batchSndnInfoLst.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.batch.batchSndnInfoLst.vo.batSndnInfoLstVO;
import tpost.commCode.controller.commCdMgntController;

@Repository
public class batSndnInfoLstDao {
	
  @Autowired
  SqlSession sqlSessionTemplate;

  /*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	

	/*발송 목록정보 select Insert*/
	public void batSndnInfoLstInsert(batSndnInfoLstVO batSndnInfoLstVO) {
		// TODO Auto-generated method stub  tbSndnInfoLstUpload
		log.debug("fileTestSelectInsert ==> " );
//		sqlSessionTemplate.insert("tpost.batch.dao.fileTestDao.tbSndnInfoLstUpload", fileTestVO);
		
		/*발송수신정보목록 INSERT */
		sqlSessionTemplate.insert("tpost.batch.dao.batSndnInfoLstDao.tbSndnSnrcInfoLstUpload", batSndnInfoLstVO);
		
		/*발송송신정보목록 INSERT*/
		sqlSessionTemplate.insert("tpost.batch.dao.batSndnInfoLstDao.tbSndnTrnsInfoLstUpload", batSndnInfoLstVO);
	}
	
	

}
