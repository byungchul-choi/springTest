package tpost.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.common.dao.custInfMgntDao;
import tpost.common.vo.custInfMgntVO;

@Service
public class custInfMgntService {

  @Autowired
  custInfMgntDao	 dao;

  
  	public void custInfInsert(custInfMgntVO custInfMgntVO){
	     dao.custInfInsert(custInfMgntVO);
	  }

	  
	  public void custInfUpdate(custInfMgntVO custInfMgntVO){
		  dao.custInfUpdate(custInfMgntVO);
	  }
	  
	  public void custInfDelete(custInfMgntVO custInfMgntVO){
		  dao.custInfDelete(custInfMgntVO);
	  }
	  
	  public custInfMgntVO custInfSelOne(custInfMgntVO custInfMgntVO) {
		  return dao.custInfSelOne(custInfMgntVO);
	  }
	  
	  /*목록화면을 조회한다. */
	  public  List<custInfMgntVO> custInfSelect(custInfMgntVO custInfMgntVO) {
		  
		    return dao.custInfSelect(custInfMgntVO);
	 }
	  public  int custInfSelectCnt(custInfMgntVO custInfMgntVO) {
		  return dao.custInfSelectCnt(custInfMgntVO);
	  }
	  
	  public  List<custInfMgntVO> custInfMgntSelectPopu(custInfMgntVO custInfMgntVO) {
		  
		  return dao.custInfMgntSelectPopu(custInfMgntVO);
	  }


	public void custInfMgntPwInit(custInfMgntVO custInfMgntVO) {
		// TODO Auto-generated method stub
		 dao.custInfMgntPwInit(custInfMgntVO);
	}
	  

}
