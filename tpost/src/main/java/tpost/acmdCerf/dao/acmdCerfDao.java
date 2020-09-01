package tpost.acmdCerf.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.acmdCerf.vo.acmdCerfVO;

@Repository
public class acmdCerfDao {
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<acmdCerfVO> acmdCerfListSelect(acmdCerfVO acmdCerfVO){
		return sqlSessionTemplate.selectList("tpost.acmdCerf.dao.acmdCerfDao.acmdCerfListSelect", acmdCerfVO);
	}
	
	public int acmdCerfAddrListCountSelect(acmdCerfVO acmdCerfVO) {
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.acmdCerfDao.acmdCerfAddrListCountSelect", acmdCerfVO);
	}
	
	public void acmdCerfInsert(acmdCerfVO acmdCerfVO){
		sqlSessionTemplate.insert("tpost.acmdCerf.dao.acmdCerfDao.acmdCerfInsert", acmdCerfVO);
	}
	
	public void acmdCerfHisInsert(acmdCerfVO acmdCerfVO){
		sqlSessionTemplate.insert("tpost.acmdCerf.dao.acmdCerfDao.acmdCerfHisInsert", acmdCerfVO);
	}
	
	public void acmdCerfUpdate(acmdCerfVO acmdCerfVO) {
		sqlSessionTemplate.update("tpost.acmdCerf.dao.acmdCerfDao.acmdCerfUpdate", acmdCerfVO);
	}
	
	public acmdCerfVO sndnCheck(acmdCerfVO acmdCerfVO) {
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.acmdCerfDao.sndnCheck", acmdCerfVO);
	}
	
	public acmdCerfVO downloadCheck(acmdCerfVO acmdCerfVO) {
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.acmdCerfDao.downloadCheck", acmdCerfVO);
	}
	
	public Integer getMaxSeq(acmdCerfVO acmdCerfVO){
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.acmdCerfDao.getMaxSeq", acmdCerfVO);
	}

}