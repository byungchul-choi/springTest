package tpost.batch.batchSttcInfo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tpost.batch.batchSttcInfo.vo.sttcInfoBatVO;


@Repository
public class sttcInfoBatDao {
	
	@Autowired
	SqlSession sqlSessionTemplate;
	
	public List<sttcInfoBatVO> sndnSuccMsgSel(sttcInfoBatVO sttcInfoBatVO){
		return sqlSessionTemplate.selectList("tpost.batch.dao.batchSttcInfoDao.sndnSuccMsgSel", sttcInfoBatVO);
	}
	public List<sttcInfoBatVO> sndnSuccMsgIns(sttcInfoBatVO sttcInfoBatVO){
		return sqlSessionTemplate.selectList("tpost.batch.dao.batchSttcInfoDao.sndnSuccMsgIns", sttcInfoBatVO);
	}
	
	public List<sttcInfoBatVO> sndnFailMsgSel(sttcInfoBatVO sttcInfoBatVO){
		return sqlSessionTemplate.selectList("tpost.batch.dao.batchSttcInfoDao.sndnFailMsgSel", sttcInfoBatVO);
	}
	public List<sttcInfoBatVO> sndnFailMsgIns(sttcInfoBatVO sttcInfoBatVO){
		return sqlSessionTemplate.selectList("tpost.batch.dao.batchSttcInfoDao.sndnFailMsgIns", sttcInfoBatVO);
	}
	
	public List<sttcInfoBatVO> sndnRdngSel(sttcInfoBatVO sttcInfoBatVO){
		return sqlSessionTemplate.selectList("tpost.batch.dao.batchSttcInfoDao.sndnRdngSel", sttcInfoBatVO);
	}
	public List<sttcInfoBatVO> sndnRdngIns(sttcInfoBatVO sttcInfoBatVO){
		return sqlSessionTemplate.selectList("tpost.batch.dao.batchSttcInfoDao.sndnRdngIns", sttcInfoBatVO);
	}
	

}
