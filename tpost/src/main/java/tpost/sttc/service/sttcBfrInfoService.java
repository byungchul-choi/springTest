package tpost.sttc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.commCode.controller.commCdMgntController;
import tpost.sttc.dao.sttcBfrInfoDao;
import tpost.sttc.dao.sttcInfoDao;
import tpost.sttc.vo.sttcInfoVO;

@Service
public class sttcBfrInfoService {

  @Autowired
  sttcBfrInfoDao	 dao;
  /*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
  /**
   * 매핑권한 그룹정보 
   * */
  
  

	public sttcInfoVO sttcBfrInfoSelect(sttcInfoVO sttcInfoVO) {
		  return dao.sttcBfrInfoSelect(sttcInfoVO);
	  }
	
	
	public  List<sttcInfoVO> sttcBfrInfoDetlSelect(sttcInfoVO sttcInfoVO) {
		  
		return dao.sttcBfrInfoDetlSelect(sttcInfoVO);
	  }


}
