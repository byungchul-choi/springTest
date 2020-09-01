package tpost.commUtil.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.commUtil.vo.commUtilVO;

@Repository
public class commUtilDao{
	
	@Autowired
	SqlSession sqlSessionTemplate;
	public List<commUtilVO> getBascdList(commUtilVO commUtilVO){
		return sqlSessionTemplate.selectList("tpost.commUtil.dao.commUtilDao.getBascdList", commUtilVO);
	}
	public List<commUtilVO> getDtcdList(commUtilVO commUtilVO){
		return sqlSessionTemplate.selectList("tpost.commUtil.dao.commUtilDao.getDtcdList", commUtilVO);
	}
	
	public commUtilVO getBascdNmOne(commUtilVO commUtilVO){
		return sqlSessionTemplate.selectOne("tpost.commUtil.dao.commUtilDao.getBascdNmOne", commUtilVO);
	}
	public commUtilVO getDtcdNmOne(commUtilVO commUtilVO){
		return sqlSessionTemplate.selectOne("tpost.commUtil.dao.commUtilDao.getDtcdNmOne", commUtilVO);
	}
	
	public List<commUtilVO> getOganTmpltList(commUtilVO commUtilVO){
		return sqlSessionTemplate.selectList("tpost.commUtil.dao.commUtilDao.getOganTmpltList", commUtilVO);
	}	
	
	public String getHolyYn(String date) {
		return sqlSessionTemplate.selectOne("tpost.commUtil.dao.commUtilDao.getHolyYn", date);
	}
}