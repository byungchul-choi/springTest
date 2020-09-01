package tpost.acmdCerf.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.acmdCerf.vo.ofapElctAddrVO;

@Repository
public class ofapElctAddrDao {
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<ofapElctAddrVO> ofapElctAddrListSelect(ofapElctAddrVO ofapElctAddrVO){
		return sqlSessionTemplate.selectList("tpost.acmdCerf.dao.ofapElctAddrDao.ofapElctAddrListSelect", ofapElctAddrVO);
	}
	
	public int ofapElctAddrListCountSelect(ofapElctAddrVO ofapElctAddrVO){
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.ofapElctAddrDao.ofapElctAddrListCountSelect", ofapElctAddrVO);
	}
	
	public List<ofapElctAddrVO> ofapElctAddrHistListSelect(ofapElctAddrVO ofapElctAddrVO){
		return sqlSessionTemplate.selectList("tpost.acmdCerf.dao.ofapElctAddrDao.ofapElctAddrHistListSelect", ofapElctAddrVO);
	}
	
	public void ofapElctAddrUpdate(ofapElctAddrVO ofapElctAddrVO){
		sqlSessionTemplate.update("tpost.acmdCerf.dao.ofapElctAddrDao.ofapElctAddrUpdate", ofapElctAddrVO);
	}
	
	public void rcveCiInfoUpdate(ofapElctAddrVO ofapElctAddrVO){
		sqlSessionTemplate.update("tpost.acmdCerf.dao.ofapElctAddrDao.rcveCiInfoUpdate", ofapElctAddrVO);
	}
	
	public void ofapElctAddrInsert(ofapElctAddrVO ofapElctAddrVO){
		sqlSessionTemplate.insert("tpost.acmdCerf.dao.ofapElctAddrDao.ofapElctAddrInsert", ofapElctAddrVO);
	}
	
	public void ofapElctAddrHisInsert(ofapElctAddrVO ofapElctAddrVO){
		sqlSessionTemplate.insert("tpost.acmdCerf.dao.ofapElctAddrDao.ofapElctAddrHisInsert", ofapElctAddrVO);
	}
	
	public void rcveCiInfoInsert(ofapElctAddrVO ofapElctAddrVO){
		sqlSessionTemplate.insert("tpost.acmdCerf.dao.ofapElctAddrDao.rcveCiInfoInsert", ofapElctAddrVO);
	}
	
	public ofapElctAddrVO ofapElctAddrDupSelect(ofapElctAddrVO ofapElctAddrVO){
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.ofapElctAddrDao.ofapElctAddrDupSelect", ofapElctAddrVO);
	}
	
	public Integer getMaxHistSeq(ofapElctAddrVO ofapElctAddrVO){
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.ofapElctAddrDao.getMaxHistSeq", ofapElctAddrVO);
	}
	
	public String getOfapElctAddr(ofapElctAddrVO ofapElctAddrVO) {
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.ofapElctAddrDao.getOfapElctAddr", ofapElctAddrVO);
	}
	
	public String getCiNum(ofapElctAddrVO ofapElctAddrVO) {
		return sqlSessionTemplate.selectOne("tpost.acmdCerf.dao.ofapElctAddrDao.getCiNum", ofapElctAddrVO);
	}
}