package tpost.commCode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.commCode.dao.commCdMgntDao;
import tpost.commCode.vo.commCdMgntVO;

@Service
public class commCdMgntService {

  @Autowired
  commCdMgntDao dao;

  public void insert(commCdMgntVO commCdMgntVO){
    dao.insert(commCdMgntVO);
  }

  public commCdMgntVO selectOne(commCdMgntVO commCdMgntVO) {
    return dao.selectOne(commCdMgntVO);
  }
  
  public  List<commCdMgntVO> selectList(commCdMgntVO commCdMgntVO) {
	    return dao.selectList(commCdMgntVO);
  }
  
  public void insertUserBatch(List<commCdMgntVO> list) {
//    dao.insertUserBatch(list);
  }
  
  /*페이징 처리 테스트 */
  public  List<commCdMgntVO> selectList_page(commCdMgntVO commCdMgntVO) {
	    return dao.selectList_page(commCdMgntVO);
 }
  public  int selectList_cnt(commCdMgntVO commCdMgntVO) {
	  return dao.selectList_cnt(commCdMgntVO);
  }
  

}
