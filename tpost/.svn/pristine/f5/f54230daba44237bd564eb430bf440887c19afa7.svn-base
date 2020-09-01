package tpost.batch.batchFileUploadTemp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.batch.batchFileUploadTemp.dao.batFileUploadTempDao;
import tpost.batch.batchFileUploadTemp.vo.batFileUploadTempVO;

@Service
public class batFileUploadTempService {

  @Autowired
  batFileUploadTempDao	 dao;
  
  Logger log = (Logger) LogManager.getLogger(batFileUploadTempService.class);

  	@Transactional
  	public void fileTestInsert(List<batFileUploadTempVO> arrayFileTestVO){
  		 
	     dao.fileTestInsert(arrayFileTestVO);
	     
	  }
  	
	
	public void tbBatchFileUpldTempTruncate(batFileUploadTempVO fileTestVO) {
		// TODO Auto-generated method stub
		dao.tbBatchFileUpldTempTruncate(fileTestVO);
	}

	public void fileTestInsert_fail(batFileUploadTempVO retStr) {
	// TODO Auto-generated method stub
		dao.Chk_fileTestSelectInsert_Fail(retStr);
	}

	
	public List<batFileUploadTempVO> fileTestTempSel(batFileUploadTempVO batFileUploadTempVO) {
	// TODO Auto-generated method stub
		return dao.fileTestTempSel(batFileUploadTempVO);
	}

}
