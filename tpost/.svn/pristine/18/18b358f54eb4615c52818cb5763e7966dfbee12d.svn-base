package tpost.batch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.batch.dao.moblSndnBulkInsDao;
import tpost.batch.vo.moblSndnBulkInsVO;

@Service
public class moblSndnBulkInsService {

  @Autowired
  moblSndnBulkInsDao	 dao;
  
  Logger log = (Logger) LogManager.getLogger(moblSndnBulkInsService.class);

  public  void moblSndnBulkIns(moblSndnBulkInsVO moblSndnBulkInsVO) {
	// TODO Auto-generated method stub
	
	  dao.moblSndnBulkIns(moblSndnBulkInsVO);
		
	  
  	}

  	

	 
}
