package tpost.batch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.batch.vo.oganRcrfMgntVO;


@Repository
public class oganRcrfMgntDao {
	
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<oganRcrfMgntVO> moblMsgLstSelect(oganRcrfMgntVO oganRcrfMgntVO){
		return sqlSessionTemplate.selectList("tpost.batch.dao.oganRcrfMgntDao.moblMsgLstSelect", oganRcrfMgntVO);
	}
	
	public List<oganRcrfMgntVO> oganRcveRfListSelect(oganRcrfMgntVO oganRcrfMgntVO){
		return sqlSessionTemplate.selectList("tpost.batch.dao.oganRcrfMgntDao.oganRcveRfListSelect", oganRcrfMgntVO);
	}	
	
	public void oganRcveRfInsert(Map<String, Object> iMap) {
		sqlSessionTemplate.insert("tpost.batch.dao.oganRcrfMgntDao.oganRcveRfInsert", iMap);
	}
	
	public void oganRcveRfDtlInsert(Map<String, Object> iMap) {
		sqlSessionTemplate.insert("tpost.batch.dao.oganRcrfMgntDao.oganRcveRfDtlInsert", iMap);
	}
	
	public void oganRcveRfUpdate(oganRcrfMgntVO oganRcrfMgntVO) {
		sqlSessionTemplate.update("tpost.batch.dao.oganRcrfMgntDao.oganRcveRfUpdate", oganRcrfMgntVO);
	}
}
