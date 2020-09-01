package tpost.msgRcve.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.msgRcve.vo.msgRcveVO;

@Repository
public class msgRcveDao {
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public msgRcveVO anocInfoListSelect(msgRcveVO msgRcveVO) {
		return sqlSessionTemplate.selectOne("tpost.msgRcve.dao.msgRcveDao.anocInfoListSelect", msgRcveVO);
	}
	public msgRcveVO getOfapElctAddrSelect_Anoc(msgRcveVO msgRcveVO) {
		return sqlSessionTemplate.selectOne("tpost.msgRcve.dao.msgRcveDao.getOfapElctAddrSelect_Anoc", msgRcveVO);
	}
	public msgRcveVO getOfapElctAddrSelect_Rcve(msgRcveVO msgRcveVO) {
		return sqlSessionTemplate.selectOne("tpost.msgRcve.dao.msgRcveDao.getOfapElctAddrSelect_Rcve", msgRcveVO);
	}
	public msgRcveVO sndnRfslSelect(msgRcveVO msgRcveVO) {
		return sqlSessionTemplate.selectOne("tpost.msgRcve.dao.msgRcveDao.sndnRfslSelect", msgRcveVO);
	}
	public void sndnRfslUpdate(msgRcveVO msgRcveVO) {
		sqlSessionTemplate.update("tpost.msgRcve.dao.msgRcveDao.sndnRfslUpdate", msgRcveVO);
	}
	public void sndnRfslMgntInsert(msgRcveVO msgRcveVO) {
		sqlSessionTemplate.insert("tpost.msgRcve.dao.msgRcveDao.sndnRfslMgntInsert", msgRcveVO);
	}
	public void sndnRfslInsert(msgRcveVO msgRcveVO) {
		sqlSessionTemplate.insert("tpost.msgRcve.dao.msgRcveDao.sndnRfslInsert", msgRcveVO);
	}
	public msgRcveVO tmpltHtmlSelect(msgRcveVO msgRcveVO) {
		return sqlSessionTemplate.selectOne("tpost.msgRcve.dao.msgRcveDao.tmpltHtmlSelect", msgRcveVO);
	}
	public List<msgRcveVO> tmpltSchemaSelect(msgRcveVO msgRcveVO) {
		return sqlSessionTemplate.selectList("tpost.msgRcve.dao.msgRcveDao.tmpltSchemaSelect", msgRcveVO);
	}
	
	public String getRdngMaxHistSeq(msgRcveVO msgRcveVO) {
		return sqlSessionTemplate.selectOne("tpost.msgRcve.dao.msgRcveDao.getRdngMaxHistSeq", msgRcveVO);
	}
	public void rdngMgntInsert(msgRcveVO msgRcveVO) {
		sqlSessionTemplate.insert("tpost.msgRcve.dao.msgRcveDao.rdngMgntInsert", msgRcveVO);
	}
	
	public msgRcveVO sndnInfoSelect(msgRcveVO msgRcveVO) {
		return sqlSessionTemplate.selectOne("tpost.msgRcve.dao.msgRcveDao.sndnInfoSelect", msgRcveVO);
	}
	
	public String getHexKeySelect(msgRcveVO msgRcveVO) {
		return sqlSessionTemplate.selectOne("tpost.msgRcve.dao.msgRcveDao.getHexKeySelect", msgRcveVO);
	}
}