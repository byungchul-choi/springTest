package tpost.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.common.dao.menuMainMgntDao;
import tpost.common.vo.menuMainMgntVO;
import tpost.logIn.vo.logInVO;

@Service
public class menuMainMgntService {

  @Autowired
  menuMainMgntDao	 dao;

  
  	public void menuMainMgntInsert(menuMainMgntVO menuMainMgntVO){
	     dao.menuMainMgntInsert(menuMainMgntVO);
	  }

	  
	  public void menuMainMgntUpdate(menuMainMgntVO menuMainMgntVO){
		  dao.menuMainMgntUpdate(menuMainMgntVO);
	  }
	  
	  public void menuMainMgntDelete(menuMainMgntVO menuMainMgntVO){
		  dao.menuMainMgntDelete(menuMainMgntVO);
	  }
	  
	  public menuMainMgntVO menuMainMgntSelOne(menuMainMgntVO menuMainMgntVO) {
		  return dao.menuMainMgntSelOne(menuMainMgntVO);
	  }
	  
	  /*목록화면을 조회한다. */
	  public  List<menuMainMgntVO> menuMainMgntSelect(menuMainMgntVO menuMainMgntVO) {
		  
		    return dao.menuMainMgntSelect(menuMainMgntVO);
	 }
	  public  int menuMainMgntSelectCnt(menuMainMgntVO menuMainMgntVO) {
		  return dao.menuMainMgntSelectCnt(menuMainMgntVO);
	  }
	  
	  public  List<logInVO> menuMainIndexSelect(logInVO logInVO) {
		  return dao.menuMainIndexSelect(logInVO);
	  }


	public List<menuMainMgntVO> menuMainGnbSelect(logInVO logInVO) {
		// TODO Auto-generated method stub
		 return dao.menuMainGnbSelect(logInVO);
		
	}

}
