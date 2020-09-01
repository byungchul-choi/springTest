package tpost.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.common.vo.codeMgntVO;

@Repository
public class codeMgntDao {

	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<codeMgntVO> codeMgntSelect(codeMgntVO codeMgntVO){
		return sqlSessionTemplate.selectList("tpost.common.dao.codeMgntDao.codeMgntSelect", codeMgntVO);
	}
	
	public List<codeMgntVO> cfcdSelect(codeMgntVO codeMgntVO){
		return sqlSessionTemplate.selectList("tpost.common.dao.codeMgntDao.cfcdSelect", codeMgntVO);
	}
	
	public List<codeMgntVO> bascdSelect(codeMgntVO codeMgntVO){
		return sqlSessionTemplate.selectList("tpost.common.dao.codeMgntDao.bascdSelect", codeMgntVO);
	}
	
	public List<codeMgntVO> dtcdSelect(codeMgntVO codeMgntVO){
		return sqlSessionTemplate.selectList("tpost.common.dao.codeMgntDao.dtcdSelect", codeMgntVO);
	}
	
	public codeMgntVO brkdSelect(codeMgntVO codeMgntVO){
		return sqlSessionTemplate.selectOne("tpost.common.dao.codeMgntDao.brkdSelect", codeMgntVO);
	}
	
	public void cfcdInsert(codeMgntVO codeMgntVO){
		sqlSessionTemplate.insert("tpost.common.dao.codeMgntDao.cfcdInsert", codeMgntVO);
	}
	
	public void bascInsert(codeMgntVO codeMgntVO){
		sqlSessionTemplate.insert("tpost.common.dao.codeMgntDao.bascInsert", codeMgntVO);
	}
	
	public void bascHistInsert(codeMgntVO codeMgntVO){
		sqlSessionTemplate.insert("tpost.common.dao.codeMgntDao.bascHistInsert", codeMgntVO);
	}
	
	public void dtcdInsert(codeMgntVO codeMgntVO){
		sqlSessionTemplate.insert("tpost.common.dao.codeMgntDao.dtcdInsert", codeMgntVO);
	}
	
	public void dtcdHistInsert(codeMgntVO codeMgntVO){
		sqlSessionTemplate.insert("tpost.common.dao.codeMgntDao.dtcdHistInsert", codeMgntVO);
	}
	
	public void bascUpdate(codeMgntVO codeMgntVO){
		sqlSessionTemplate.update("tpost.common.dao.codeMgntDao.bascUpdate", codeMgntVO);
	}
	
	public void dtcdUpdate(codeMgntVO codeMgntVO){
		sqlSessionTemplate.update("tpost.common.dao.codeMgntDao.dtcdUpdate", codeMgntVO);
	}

	public Integer getBascdMaxSeq(codeMgntVO codeMgntVO){
		return sqlSessionTemplate.selectOne("tpost.common.dao.codeMgntDao.getBascdMaxSeq", codeMgntVO);
	}
	
	public Integer getDtcdMaxSeq(codeMgntVO codeMgntVO){
		return sqlSessionTemplate.selectOne("tpost.common.dao.codeMgntDao.getDtcdMaxSeq", codeMgntVO);
	}
}