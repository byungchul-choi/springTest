package tpost.elctDoc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.elctDoc.vo.elctDocSndnVO;

@Repository
public class elctDocSndnDao {
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<elctDocSndnVO> elctDocSndnListSelect(elctDocSndnVO elctDocSndnVO){
		return sqlSessionTemplate.selectList("tpost.elctDoc.dao.elctDocSndnDao.elctDocSndnListSelect", elctDocSndnVO);
	}
	
	public elctDocSndnVO elctDocSndnDetlSelect(elctDocSndnVO elctDocSndnVO){
		return sqlSessionTemplate.selectOne("tpost.elctDoc.dao.elctDocSndnDao.elctDocSndnDetlSelect", elctDocSndnVO);
	}
	
	public void elctDocSndnAprYnUpdate(elctDocSndnVO elctDocSndnVO) {
		sqlSessionTemplate.update("tpost.elctDoc.dao.elctDocSndnDao.elctDocSndnAprYnUpdate", elctDocSndnVO);
	}
	
	public int elctDocSndnDupCheck(elctDocSndnVO elctDocSndnVO){
		return sqlSessionTemplate.selectOne("tpost.elctDoc.dao.elctDocSndnDao.elctDocSndnDupCheck", elctDocSndnVO);
	}
	
	public void elctDocSndnInsert(elctDocSndnVO elctDocSndnVO){
		sqlSessionTemplate.insert("tpost.elctDoc.dao.elctDocSndnDao.elctDocSndnInsert", elctDocSndnVO);
	}
	
	public void elctDocSndnUpdate(elctDocSndnVO elctDocSndnVO) {
		sqlSessionTemplate.update("tpost.elctDoc.dao.elctDocSndnDao.elctDocSndnUpdate", elctDocSndnVO);
	}
}