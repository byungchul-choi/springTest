package tpost.elctDoc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.elctDoc.vo.elctDocVO;

@Repository
public class elctDocDao {
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<elctDocVO> elctDocListSelect(elctDocVO elctDocVO){
		return sqlSessionTemplate.selectList("tpost.elctDoc.dao.elctDocDao.elctDocListSelect", elctDocVO);
	}
	
	public int elctDocListCountSelect(elctDocVO elctDocVO) {
		return sqlSessionTemplate.selectOne("tpost.elctDoc.dao.elctDocDao.elctDocListCountSelect", elctDocVO);
	}
	
	public elctDocVO elctDocDetlTitleSelect(elctDocVO elctDocVO){
		return sqlSessionTemplate.selectOne("tpost.elctDoc.dao.elctDocDao.elctDocDetlTitleSelect", elctDocVO);
	}
	
	public List<elctDocVO> elctDocDetlListALLSelect(elctDocVO elctDocVO){
		return sqlSessionTemplate.selectList("tpost.elctDoc.dao.elctDocDao.elctDocDetlListALLSelect", elctDocVO);
	}
	
	public List<elctDocVO> elctDocDetlListSelect(elctDocVO elctDocVO){
		return sqlSessionTemplate.selectList("tpost.elctDoc.dao.elctDocDao.elctDocDetlListSelect", elctDocVO);
	}
	
	public int elctDocDetlListCountSelect(elctDocVO elctDocVO){
		return sqlSessionTemplate.selectOne("tpost.elctDoc.dao.elctDocDao.elctDocDetlListCountSelect", elctDocVO);
	}
	
	public List<elctDocVO> elctDocFailListSelect(elctDocVO elctDocVO){
		return sqlSessionTemplate.selectList("tpost.elctDoc.dao.elctDocDao.elctDocFailListSelect", elctDocVO);
	}
}