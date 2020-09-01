package tpost.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.common.dao.commonDao;
import tpost.common.vo.commonVO;

@Service
public class commonService {

  @Autowired
  commonDao dao;

  
  public  List<commonVO> selectMenu(commonVO commonVO) {
	    return dao.selectMenu(commonVO);
  }


	public  List<commonVO> selectSubMenu(commonVO commonVO) {
		// TODO Auto-generated method stub
		return dao.selectSubMenu(commonVO);
		
	}


  

}
