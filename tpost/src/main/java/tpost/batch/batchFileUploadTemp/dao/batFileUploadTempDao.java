package tpost.batch.batchFileUploadTemp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.batch.batchFileUploadTemp.vo.batFileUploadTempVO;
import tpost.commCode.controller.commCdMgntController;

@Repository
public class batFileUploadTempDao {
	
  @Autowired
  SqlSession sqlSessionTemplate;

  /*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
	
	public void fileTestInsert(List<batFileUploadTempVO> batFileUploadTempVO) {
		
		log.debug("listFileTestVO ==> " + batFileUploadTempVO.size() );
		  sqlSessionTemplate.insert("tpost.batch.batchFileUploadTemp.dao.batFileUploadTempDao.batchFileUploadTempInsert", batFileUploadTempVO);
		
	}

	
	public void tbBatchFileUpldTempTruncate(batFileUploadTempVO batFileUploadTempVO) {
		log.debug("tbBatchFileUpldTempTruncate ==> " );
		sqlSessionTemplate.insert("tpost.batch.batchFileUploadTemp.dao.batFileUploadTempDao.tbBatchFileUpldTempTruncate", batFileUploadTempVO);
	}


	/*발송항목 실패 리스트*/
	public void Chk_fileTestSelectInsert_Fail(batFileUploadTempVO batFileUploadTempVO) {
		// TODO Auto-generated method stub
		log.debug("Chk_fileTestSelectInsert_Fail " );
		sqlSessionTemplate.insert("tpost.batch.batchFileUploadTemp.dao.batFileUploadTempDao.batchFileTestInsert_Fail", batFileUploadTempVO);
	}
	

	public  List<batFileUploadTempVO>  fileTestTempSel(batFileUploadTempVO batFileUploadTempVO) {
	// TODO Auto-generated method stub
		log.debug("fileTestTempSel " );
		return  sqlSessionTemplate.selectList("tpost.batch.batchFileUploadTemp.dao.batFileUploadTempDao.tbSndnInfoLstSelect", batFileUploadTempVO);
	}



}
