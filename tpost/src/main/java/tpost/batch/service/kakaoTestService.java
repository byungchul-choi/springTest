package tpost.batch.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.batch.controller.kakaoTest;
import tpost.batch.dao.kakaoTestDao;
import tpost.batch.vo.kakaoTestVO;

@Service
public class kakaoTestService {

  @Autowired
  kakaoTestDao	 dao;
  
  Logger log = (Logger) LogManager.getLogger(kakaoTestService.class);

  public  void kakaoTestInsert(kakaoTestVO kakaoTestVO) {
	// TODO Auto-generated method stub
	   dao.kakaoTestInsert(kakaoTestVO);	
  }

  	

	 
}
