package tpost.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.common.vo.tmpltMgntVO;

@Repository
public class tmpltMgntDao {
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<tmpltMgntVO> tmpltMgntSelect(tmpltMgntVO tmpltMgntVO){
		return sqlSessionTemplate.selectList("tpost.common.dao.tmpltMgntDao.tmpltMgntSelect", tmpltMgntVO);
	}
	
	public tmpltMgntVO tmpltInfoSelect(tmpltMgntVO tmpltMgntVO) {
		return sqlSessionTemplate.selectOne("tpost.common.dao.tmpltMgntDao.tmpltInfoSelect", tmpltMgntVO);
	}
	
	public void tmpltInfoUpdate(tmpltMgntVO tmpltMgntVO) {
		sqlSessionTemplate.update("tpost.common.dao.tmpltMgntDao.tmpltInfoUpdate", tmpltMgntVO);
	}
	
	public void tmpltInfoInsert(tmpltMgntVO tmpltMgntVO) {
		sqlSessionTemplate.insert("tpost.common.dao.tmpltMgntDao.tmpltInfoInsert", tmpltMgntVO);
	}
	
	public List<tmpltMgntVO> tmpltSchemaSelect(tmpltMgntVO tmpltMgntVO) {
		return sqlSessionTemplate.selectList("tpost.common.dao.tmpltMgntDao.tmpltSchemaSelect", tmpltMgntVO);
	}
	
	public void tmpltSchemaDelete(tmpltMgntVO tmpltMgntVO) {
		sqlSessionTemplate.delete("tpost.common.dao.tmpltMgntDao.tmpltSchemaDelete", tmpltMgntVO);
	}
	
	public void tmpltSchemaInsert(tmpltMgntVO tmpltMgntVO) {
		sqlSessionTemplate.insert("tpost.common.dao.tmpltMgntDao.tmpltSchemaInsert", tmpltMgntVO);
	}
	
	public tmpltMgntVO tmpltEditSelect(tmpltMgntVO tmpltMgntVO) {
		return sqlSessionTemplate.selectOne("tpost.common.dao.tmpltMgntDao.tmpltEditSelect", tmpltMgntVO);
	}
	
	public void tmpltEditDelete(tmpltMgntVO tmpltMgntVO) {
		sqlSessionTemplate.delete("tpost.common.dao.tmpltMgntDao.tmpltEditDelete", tmpltMgntVO);
	}
	
	public void tmpltEditInsert(tmpltMgntVO tmpltMgntVO) {
		sqlSessionTemplate.insert("tpost.common.dao.tmpltMgntDao.tmpltEditInsert", tmpltMgntVO);
	}
	
	public String oganMaxTmpltIdSelect(tmpltMgntVO tmpltMgntVO) {
		return sqlSessionTemplate.selectOne("tpost.common.dao.tmpltMgntDao.oganMaxTmpltIdSelect", tmpltMgntVO);
	}
}