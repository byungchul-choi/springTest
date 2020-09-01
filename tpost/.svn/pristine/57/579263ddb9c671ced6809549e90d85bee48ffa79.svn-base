package tpost.elctDoc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.elctDoc.dao.msgTmpltDao;
import tpost.elctDoc.vo.msgTmpltVO;

@Service
public class msgTmpltService {
	@Autowired
	msgTmpltDao dao;
	
	public List<msgTmpltVO> tmpltLstSelect(msgTmpltVO msgTmpltVO) {
		return dao.tmpltLstSelect(msgTmpltVO);
	}
	
	public int tmpltLstCntSelect(msgTmpltVO msgTmpltVO) {
		return dao.tmpltLstCntSelect(msgTmpltVO);
	}
	
	public msgTmpltVO msgTmpltDetlSelect(msgTmpltVO msgTmpltVO) {
		return dao.msgTmpltDetlSelect(msgTmpltVO);
	}
	
	public void msgTmpltDetlInsert(msgTmpltVO msgTmpltVO) {
		dao.msgTmpltDetlInsert(msgTmpltVO);
	}
	
	public void msgTmpltDetlUpdate(msgTmpltVO msgTmpltVO) {
		dao.msgTmpltDetlUpdate(msgTmpltVO);
	}
}