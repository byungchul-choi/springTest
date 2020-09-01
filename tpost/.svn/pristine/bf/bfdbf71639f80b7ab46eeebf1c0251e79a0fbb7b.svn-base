package tpost.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.common.dao.aoMgntDao;
import tpost.common.vo.aoMgntVO;
import tpost.common.vo.menuMainMgntVO;

@Service
public class aoMgntService {

  @Autowired
  aoMgntDao	 dao;

  
  	public void aoMgntInsert(aoMgntVO aoMgntVO){
	     dao.aoMgntInsert(aoMgntVO);
	  }

	  
	  public void aoMgntUpdate(aoMgntVO aoMgntVO){
		  dao.aoMgntUpdate(aoMgntVO);
	  }
	  
	  public void aoMgntDelete(aoMgntVO aoMgntVO){
		  dao.aoMgntDelete(aoMgntVO);
	  }
	  
	  public aoMgntVO aoMgntSelOne(aoMgntVO aoMgntVO) {
		  return dao.aoMgntSelOne(aoMgntVO);
	  }
	  
	  /*목록화면을 조회한다. */
	  public  List<aoMgntVO> aoMgntSelect(aoMgntVO aoMgntVO) {
		  
		    return dao.aoMgntSelect(aoMgntVO);
	 }
	  public  int aoMgntSelectCnt(aoMgntVO aoMgntVO) {
		  return dao.aoMgntSelectCnt(aoMgntVO);
	  }
	  
	  public  int aoMgntExCnt(aoMgntVO aoMgntVO) {
		  return dao.aoMgntExCnt(aoMgntVO);
	  }
	  
	  public  List<aoMgntVO> aoMgntSelectPopu(menuMainMgntVO menuMainMgntVO) {
		  
		  return dao.aoMgntSelectPopu(menuMainMgntVO);
	  }


	public List<aoMgntVO> aoMgntSelectPopu_menuMain(menuMainMgntVO menuMainMgntVO) {
		// TODO Auto-generated method stub
		 return dao.aoMgntSelectPopu_menuMain(menuMainMgntVO);
	}
	  

}
