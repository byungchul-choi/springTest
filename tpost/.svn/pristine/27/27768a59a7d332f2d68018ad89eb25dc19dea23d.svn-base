package tpost.suppCenter.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpost.egovcomm.EgovUserDetailsHelper;
import tpost.suppCenter.dao.noticeDao;
import tpost.suppCenter.vo.noticeVO;

@Service
public class noticeService {

	@Autowired
	noticeDao dao;
	
	public List<noticeVO> noticeListSelect(noticeVO noticeVO){
		return dao.noticeListSelect(noticeVO);
	}
	
	public int noticeListCountSelect(noticeVO noticeVO) {
		return dao.noticeListCountSelect(noticeVO);
	}
	
	public noticeVO noticeDetlSelect(noticeVO noticeVO) {
		return dao.noticeDetlSelect(noticeVO);
	}

	@Transactional
	public void noticeInsert(noticeVO noticeVO) {
		int wrtgNum = dao.getMaxWrtgNum(noticeVO);
		noticeVO.setWrtgNum(Integer.toString(wrtgNum));
		noticeVO.setAmdr(EgovUserDetailsHelper.getUserId());
		noticeVO.setCrtr(EgovUserDetailsHelper.getUserId());
		
		dao.noticeInsert(noticeVO);
	}
	
	public void noticeUpdate(noticeVO noticeVO) {
		noticeVO.setAmdr(EgovUserDetailsHelper.getUserId());
		dao.noticeUpdate(noticeVO);
	}
}
