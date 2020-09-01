package tpost.acmdCerf.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.acmdCerf.vo.oganRcveRfVO;

@Repository
public class oganRcveRfDao {
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<oganRcveRfVO> oganRcveRfListSelect(oganRcveRfVO oganRcveRfVO){
		return sqlSessionTemplate.selectList("tpost.acmdCerf.dao.oganRcveRfDao.oganRcveRfListSelect", oganRcveRfVO);
	}
	public int oganRcveRfListCountSelect(oganRcveRfVO oganRcveRfVO){
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.oganRcveRfDao.oganRcveRfListCountSelect", oganRcveRfVO);
	}
	public int allCsntCountSelect(oganRcveRfVO oganRcveRfVO){
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.oganRcveRfDao.allCsntCountSelect", oganRcveRfVO);
	}
	public int allRfslCountSelect(oganRcveRfVO oganRcveRfVO){
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.oganRcveRfDao.allRfslCountSelect", oganRcveRfVO);
	}
	
	public void sndnExecInsert(oganRcveRfVO oganRcveRfVO) {
		sqlSessionTemplate.insert("tpost.acmdCerf.dao.oganRcveRfDao.sndnExecInsert", oganRcveRfVO);
	}
	public void sndnExecHisInsert(oganRcveRfVO oganRcveRfVO) {
		sqlSessionTemplate.insert("tpost.acmdCerf.dao.oganRcveRfDao.sndnExecHisInsert", oganRcveRfVO);
	}
	
	public oganRcveRfVO sndnExecDupSelect(oganRcveRfVO oganRcveRfVO){
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.oganRcveRfDao.sndnExecDupSelect", oganRcveRfVO);
	}
	
	public void sndnExecUpdate(oganRcveRfVO oganRcveRfVO) {
		sqlSessionTemplate.update("tpost.acmdCerf.dao.oganRcveRfDao.sndnExecUpdate", oganRcveRfVO);
	}
	
	public List<oganRcveRfVO> sndnExecSelect(oganRcveRfVO oganRcveRfVO){
		return sqlSessionTemplate.selectList("tpost.acmdCerf.dao.oganRcveRfDao.sndnExecSelect", oganRcveRfVO);
	}
	
	public int getMaxPk(oganRcveRfVO oganRcveRfVO){
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.oganRcveRfDao.getMaxPk", oganRcveRfVO);
	}
	
	public int getHisSeq(oganRcveRfVO oganRcveRfVO){
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.oganRcveRfDao.getHisSeq", oganRcveRfVO);
	}
	
	
}