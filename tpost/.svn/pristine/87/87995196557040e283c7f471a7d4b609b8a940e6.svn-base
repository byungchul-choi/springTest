package tpost.sttc.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.commCode.controller.commCdMgntController;
import tpost.sttc.dao.sttcInfoDao;
import tpost.sttc.vo.sttcInfoVO;

@Service
public class sttcInfoService {

  @Autowired
  sttcInfoDao	 dao;
  /*로그설정 입니다. */
	Logger log = (Logger) LogManager.getLogger(commCdMgntController.class);
  /**
   * 매핑권한 그룹정보 
   * */
  
  /*로그인  . */

	public sttcInfoVO sttcInfoSelect(sttcInfoVO sttcInfoVO) {
		  return dao.sttcInfoSelect(sttcInfoVO);
	  }
	
	
	public  List<sttcInfoVO> sttcInfoDetlSelect(sttcInfoVO sttcInfoVO) {
		  
		return dao.sttcInfoDetlSelect(sttcInfoVO);
	  }


}
