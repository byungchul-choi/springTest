package tpost.batch.batchSndnInfoLst.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.batch.batchSndnInfoLst.dao.batSndnInfoLstDao;
import tpost.batch.batchSndnInfoLst.vo.batSndnInfoLstVO;

@Service
public class batSndnInfoLstService {

  @Autowired
  batSndnInfoLstDao	 dao;
  
  Logger log = (Logger) LogManager.getLogger(batSndnInfoLstService.class);

 
  	
	public void batSndnInfoLstInsert(batSndnInfoLstVO batSndnInfoLstVO) {
		// TODO Auto-generated method stub
		dao.batSndnInfoLstInsert(batSndnInfoLstVO);
	}
	



	 
}
