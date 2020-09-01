package tpost.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.common.vo.commonVO;

@Repository
public class commonDao {
	
  @Autowired
  SqlSession sqlSessionTemplate;

  public List<commonVO> selectMenu(commonVO commonVO) {
	  return sqlSessionTemplate.selectList("tpost.common.dao.commonDao.selectMenu",commonVO);
	                                        
	                                       
  }

public List<commonVO> selectSubMenu(commonVO commonVO) {
	// TODO Auto-generated method stub
	return sqlSessionTemplate.selectList("tpost.common.dao.commonDao.selectSubMenu",commonVO);

}

}
