package tpost.acmdCerf.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.acmdCerf.dao.acmdCerfDao;
import tpost.acmdCerf.vo.acmdCerfVO;

@Service
public class acmdCerfService {
	@Autowired
	acmdCerfDao dao;
	
	public List<acmdCerfVO> acmdCerfListSelect(acmdCerfVO acmdCerfVO){
		return dao.acmdCerfListSelect(acmdCerfVO);
	}
	
	public int acmdCerfAddrListCountSelect(acmdCerfVO acmdCerfVO) {
		return dao.acmdCerfAddrListCountSelect(acmdCerfVO);
	}
	
	@Transactional
	public void acmdCerfInsert(acmdCerfVO acmdCerfVO){
		dao.acmdCerfInsert(acmdCerfVO);
		dao.acmdCerfHisInsert(acmdCerfVO);
	}

	@Transactional
	public void acmdCerfUpdate(acmdCerfVO acmdCerfVO){
		dao.acmdCerfUpdate(acmdCerfVO);
		dao.acmdCerfHisInsert(acmdCerfVO);
	}
	
	public acmdCerfVO sndnCheck(acmdCerfVO acmdCerfVO) {
		return dao.sndnCheck(acmdCerfVO);
	}

	public acmdCerfVO downloadCheck(acmdCerfVO acmdCerfVO) {
		return dao.downloadCheck(acmdCerfVO);
	}
	
	public Integer getMaxSeq(acmdCerfVO acmdCerfVO) {
		return dao.getMaxSeq(acmdCerfVO);
	}
}