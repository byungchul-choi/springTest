package tpost.elctDoc.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.elctDoc.vo.msgTmpltVO;

@Repository
public class msgTmpltDao {
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<msgTmpltVO> tmpltLstSelect(msgTmpltVO msgTmpltVO){
		return sqlSessionTemplate.selectList("tpost.elctDoc.dao.msgTmpltDao.tmpltLstSelect", msgTmpltVO);
	}
	
	public int tmpltLstCntSelect(msgTmpltVO msgTmpltVO){
		return sqlSessionTemplate.selectOne("tpost.elctDoc.dao.msgTmpltDao.tmpltLstCntSelect", msgTmpltVO);
	}
	
	public msgTmpltVO msgTmpltDetlSelect(msgTmpltVO msgTmpltVO){
		return sqlSessionTemplate.selectOne("tpost.elctDoc.dao.msgTmpltDao.msgTmpltDetlSelect", msgTmpltVO);
	}
	
	public void msgTmpltDetlInsert(msgTmpltVO msgTmpltVO) {
		sqlSessionTemplate.insert("tpost.elctDoc.dao.msgTmpltDao.msgTmpltDetlInsert", msgTmpltVO);
	}
	
	public void msgTmpltDetlUpdate(msgTmpltVO msgTmpltVO) {
		sqlSessionTemplate.update("tpost.elctDoc.dao.msgTmpltDao.msgTmpltDetlUpdate", msgTmpltVO);
	}
}