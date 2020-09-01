package tpost.common.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.commUtil.commUtil;
import tpost.common.dao.codeMgntDao;
import tpost.common.vo.codeMgntVO;
import tpost.egovcomm.EgovUserDetailsHelper;

@Service
public class codeMgntService {
	
	@Autowired
	codeMgntDao dao;
	
	public List<codeMgntVO> codeMgntSelect(codeMgntVO codeMgntVO){
		return dao.codeMgntSelect(codeMgntVO);
	}
	
	public List<codeMgntVO> cfcdSelect(codeMgntVO codeMgntVO){
		return dao.cfcdSelect(codeMgntVO);
	}
	
	public List<codeMgntVO> bascdSelect(codeMgntVO codeMgntVO){
		return dao.bascdSelect(codeMgntVO);
	}
	
	public List<codeMgntVO> dtcdSelect(codeMgntVO codeMgntVO){
		return dao.dtcdSelect(codeMgntVO);
	}
	
	public codeMgntVO brkdSelect(codeMgntVO codeMgntVO) {
		return dao.brkdSelect(codeMgntVO);
	}
	
	public void cfcdInsert(codeMgntVO codeMgntVO) {
		dao.cfcdInsert(codeMgntVO);
	}
	
	@Transactional
	public void bascInsert(codeMgntVO codeMgntVO) {
		dao.bascInsert(codeMgntVO);
		
		codeMgntVO.setMaxHistSeq("1");
		dao.bascHistInsert(codeMgntVO);
	}
	
	@Transactional
	public void dtcdInsert(codeMgntVO codeMgntVO) {
		dao.dtcdInsert(codeMgntVO);
		
		codeMgntVO.setMaxHistSeq("1");
		dao.dtcdHistInsert(codeMgntVO);
	}
	
	@Transactional
	public void bascUpdate(codeMgntVO codeMgntVO) {
		dao.bascUpdate(codeMgntVO);
		
		codeMgntVO tempVO = new codeMgntVO();
		tempVO.setCfcd(codeMgntVO.getCfcd());
		tempVO.setBasc(codeMgntVO.getBasc());
		
		Integer maxHistSeq = dao.getBascdMaxSeq(codeMgntVO);
		codeMgntVO.setMaxHistSeq(maxHistSeq.toString());

		dao.bascHistInsert(codeMgntVO);
	}
	
	@Transactional
	public void dtcdUpdate(codeMgntVO codeMgntVO) {
		dao.dtcdUpdate(codeMgntVO);
		
		codeMgntVO tempVO = new codeMgntVO();
		tempVO.setCfcd(codeMgntVO.getCfcd());
		tempVO.setBasc(codeMgntVO.getBasc());
		
		Integer maxHistSeq = dao.getDtcdMaxSeq(codeMgntVO);
		codeMgntVO.setMaxHistSeq(maxHistSeq.toString());
		
		dao.dtcdHistInsert(codeMgntVO);
	}
}