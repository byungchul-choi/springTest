package tpost.batch.batchMoblSndnRsltArg.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.batch.batchMoblSndnRsltArg.vo.batMoblSndnRsltArgVO;


@Repository
public class batMoblSndnRsltArgDao {
	
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<batMoblSndnRsltArgVO> moblSndnRsltArgSuccIns(batMoblSndnRsltArgVO batMoblSndnRsltArgVO){
		return sqlSessionTemplate.selectList("tpost.batch.dao.batMoblSndnRsltArgDao.moblSndnRsltArgSuccIns", batMoblSndnRsltArgVO);
	}
	
	public List<batMoblSndnRsltArgVO> moblSndnRsltArgFailIns(batMoblSndnRsltArgVO batMoblSndnRsltArgVO){
		return sqlSessionTemplate.selectList("tpost.batch.dao.batMoblSndnRsltArgDao.moblSndnRsltArgFailIns", batMoblSndnRsltArgVO);
	}
	

}
